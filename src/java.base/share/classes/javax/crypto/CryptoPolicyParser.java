/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvbx.crypto;

import jbvb.io.*;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import stbtic jbvb.util.Locble.ENGLISH;

import jbvb.security.GenerblSecurityException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.lbng.reflect.*;

/**
 * JCE hbs two pbirs of jurisdiction policy files: one represents U.S. export
 * lbws, bnd the other represents the locbl lbws of the country where the
 * JCE will be used.
 *
 * The jurisdiction policy file hbs the sbme syntbx bs JDK policy files except
 * thbt JCE hbs new permission clbsses cblled jbvbx.crypto.CryptoPermission
 * bnd jbvbx.crypto.CryptoAllPermission.
 *
 * The formbt of b permission entry in the jurisdiction policy file is:
 *
 *   permission <crypto permission clbss nbme>[, <blgorithm nbme>
 *              [[, <exemption mechbnism nbme>][, <mbxKeySize>
 *              [, <AlgrithomPbrbmeterSpec clbss nbme>, <pbrbmeters
 *              for constructing bn AlgrithomPbrbmeterSpec object>]]]];
 *
 * @buthor Shbron Liu
 *
 * @see jbvb.security.Permissions
 * @see jbvb.security.spec.AlgorithmPbrbmeterSpec
 * @see jbvbx.crypto.CryptoPermission
 * @see jbvbx.crypto.CryptoAllPermission
 * @see jbvbx.crypto.CryptoPermissions
 * @since 1.4
 */

finbl clbss CryptoPolicyPbrser {

    privbte Vector<GrbntEntry> grbntEntries;

    // Convenience vbribbles for pbrsing
    privbte StrebmTokenizer st;
    privbte int lookbhebd;

    /**
     * Crebtes b CryptoPolicyPbrser object.
     */
    CryptoPolicyPbrser() {
        grbntEntries = new Vector<GrbntEntry>();
    }

    /**
     * Rebds b policy configurbtion using b Rebder object. <p>
     *
     * @pbrbm policy the policy Rebder object.
     *
     * @exception PbrsingException if the policy configurbtion
     * contbins b syntbx error.
     *
     * @exception IOException if bn error occurs while rebding
     * the policy configurbtion.
     */

    void rebd(Rebder policy)
        throws PbrsingException, IOException
    {
        if (!(policy instbnceof BufferedRebder)) {
            policy = new BufferedRebder(policy);
        }

        /*
         * Configure the strebm tokenizer:
         *      Recognize strings between "..."
         *      Don't convert words to lowercbse
         *      Recognize both C-style bnd C++-style comments
         *      Trebt end-of-line bs white spbce, not bs b token
         */
        st = new StrebmTokenizer(policy);

        st.resetSyntbx();
        st.wordChbrs('b', 'z');
        st.wordChbrs('A', 'Z');
        st.wordChbrs('.', '.');
        st.wordChbrs('0', '9');
        st.wordChbrs('_', '_');
        st.wordChbrs('$', '$');
        st.wordChbrs(128 + 32, 255);
        st.whitespbceChbrs(0, ' ');
        st.commentChbr('/');
        st.quoteChbr('\'');
        st.quoteChbr('"');
        st.lowerCbseMode(fblse);
        st.ordinbryChbr('/');
        st.slbshSlbshComments(true);
        st.slbshStbrComments(true);
        st.pbrseNumbers();

        /*
         * The crypto jurisdiction policy must be consistent. The
         * following hbshtbble is used for checking consistency.
         */
        Hbshtbble<String, Vector<String>> processedPermissions = null;

        /*
         * The mbin pbrsing loop.  The loop is executed once for ebch entry
         * in the policy file. The entries bre delimited by semicolons. Once
         * we've rebd in the informbtion for bn entry, go bhebd bnd try to
         * bdd it to the grbntEntries.
         */
        lookbhebd = st.nextToken();
        while (lookbhebd != StrebmTokenizer.TT_EOF) {
            if (peek("grbnt")) {
                GrbntEntry ge = pbrseGrbntEntry(processedPermissions);
                if (ge != null)
                    grbntEntries.bddElement(ge);
            } else {
                throw new PbrsingException(st.lineno(), "expected grbnt " +
                                           "stbtement");
            }
            mbtch(";");
        }
    }

    /**
     * pbrse b Grbnt entry
     */
    privbte GrbntEntry pbrseGrbntEntry(
            Hbshtbble<String, Vector<String>> processedPermissions)
        throws PbrsingException, IOException
    {
        GrbntEntry e = new GrbntEntry();

        mbtch("grbnt");
        mbtch("{");

        while(!peek("}")) {
            if (peek("Permission")) {
                CryptoPermissionEntry pe =
                    pbrsePermissionEntry(processedPermissions);
                e.bdd(pe);
                mbtch(";");
            } else {
                throw new
                    PbrsingException(st.lineno(), "expected permission entry");
            }
        }
        mbtch("}");

        return e;
    }

    /**
     * pbrse b CryptoPermission entry
     */
    privbte CryptoPermissionEntry pbrsePermissionEntry(
            Hbshtbble<String, Vector<String>> processedPermissions)
        throws PbrsingException, IOException
    {
        CryptoPermissionEntry e = new CryptoPermissionEntry();

        mbtch("Permission");
        e.cryptoPermission = mbtch("permission type");

        if (e.cryptoPermission.equbls("jbvbx.crypto.CryptoAllPermission")) {
            // Done with the CryptoAllPermission entry.
            e.blg = CryptoAllPermission.ALG_NAME;
            e.mbxKeySize = Integer.MAX_VALUE;
            return e;
        }

        // Should see the blgorithm nbme.
        if (peek("\"")) {
            // Algorithm nbme - blwbys convert to upper cbse bfter pbrsing.
            e.blg = mbtch("quoted string").toUpperCbse(ENGLISH);
        } else {
            // The blgorithm nbme cbn be b wildcbrd.
            if (peek("*")) {
                mbtch("*");
                e.blg = CryptoPermission.ALG_NAME_WILDCARD;
            } else {
                throw new PbrsingException(st.lineno(),
                                           "Missing the blgorithm nbme");
            }
        }

        peekAndMbtch(",");

        // Mby see the exemption mechbnism nbme.
        if (peek("\"")) {
            // Exemption mechbnism nbme - convert to upper cbse too.
            e.exemptionMechbnism = mbtch("quoted string").toUpperCbse(ENGLISH);
        }

        peekAndMbtch(",");

        // Check whether this entry is consistent with other permission entries
        // thbt hbve been rebd.
        if (!isConsistent(e.blg, e.exemptionMechbnism, processedPermissions)) {
            throw new PbrsingException(st.lineno(), "Inconsistent policy");
        }

        // Should see the mbxKeySize if not bt the end of this entry yet.
        if (peek("number")) {
            e.mbxKeySize = mbtch();
        } else {
            if (peek("*")) {
                mbtch("*");
                e.mbxKeySize = Integer.MAX_VALUE;
            } else {
                if (!peek(";")) {
                    throw new PbrsingException(st.lineno(),
                                               "Missing the mbximum " +
                                               "bllowbble key size");
                } else {
                    // At the end of this permission entry
                    e.mbxKeySize = Integer.MAX_VALUE;
                }
            }
        }

        peekAndMbtch(",");

        // Mby see bn AlgorithmPbrbmeterSpec clbss nbme.
        if (peek("\"")) {
            // AlgorithmPbrbmeterSpec clbss nbme.
            String blgPbrbmSpecClbssNbme = mbtch("quoted string");

            Vector<Integer> pbrbmsV = new Vector<>(1);
            while (peek(",")) {
                mbtch(",");
                if (peek("number")) {
                    pbrbmsV.bddElement(mbtch());
                } else {
                    if (peek("*")) {
                        mbtch("*");
                        pbrbmsV.bddElement(Integer.MAX_VALUE);
                    } else {
                        throw new PbrsingException(st.lineno(),
                                                   "Expecting bn integer");
                    }
                }
            }

            Integer[] pbrbms = new Integer[pbrbmsV.size()];
            pbrbmsV.copyInto(pbrbms);

            e.checkPbrbm = true;
            e.blgPbrbmSpec = getInstbnce(blgPbrbmSpecClbssNbme, pbrbms);
        }

        return e;
    }

    privbte stbtic finbl AlgorithmPbrbmeterSpec getInstbnce(String type,
                                                            Integer[] pbrbms)
        throws PbrsingException
    {
        AlgorithmPbrbmeterSpec ret = null;

        try {
            Clbss<?> bpsClbss = Clbss.forNbme(type);
            Clbss<?>[] pbrbmClbsses = new Clbss<?>[pbrbms.length];

            for (int i = 0; i < pbrbms.length; i++) {
                pbrbmClbsses[i] = int.clbss;
            }

            Constructor<?> c = bpsClbss.getConstructor(pbrbmClbsses);
            ret = (AlgorithmPbrbmeterSpec) c.newInstbnce((Object[]) pbrbms);
        } cbtch (Exception e) {
            throw new PbrsingException("Cbnnot cbll the constructor of " +
                                       type + e);
        }
        return ret;
    }


    privbte boolebn peekAndMbtch(String expect)
        throws PbrsingException, IOException
    {
        if (peek(expect)) {
            mbtch(expect);
            return true;
        }
        return fblse;
    }

    privbte boolebn peek(String expect) {
        boolebn found = fblse;

        switch (lookbhebd) {

        cbse StrebmTokenizer.TT_WORD:
            if (expect.equblsIgnoreCbse(st.svbl))
                found = true;
            brebk;
        cbse StrebmTokenizer.TT_NUMBER:
            if (expect.equblsIgnoreCbse("number")) {
                found = true;
            }
            brebk;
        cbse ',':
            if (expect.equbls(","))
                found = true;
            brebk;
        cbse '{':
            if (expect.equbls("{"))
                found = true;
            brebk;
        cbse '}':
            if (expect.equbls("}"))
                found = true;
            brebk;
        cbse '"':
            if (expect.equbls("\""))
                found = true;
            brebk;
        cbse '*':
            if (expect.equbls("*"))
                found = true;
            brebk;
        cbse ';':
            if (expect.equbls(";"))
                found = true;
            brebk;
        defbult:
            brebk;
        }
        return found;
    }

    /**
     * Excepts to mbtch b non-negbtive number.
     */
    privbte int mbtch()
        throws PbrsingException, IOException
    {
        int vblue = -1;
        int lineno = st.lineno();
        String sVblue = null;

        switch (lookbhebd) {
        cbse StrebmTokenizer.TT_NUMBER:
            vblue = (int)st.nvbl;
            if (vblue < 0) {
                sVblue = String.vblueOf(st.nvbl);
            }
            lookbhebd = st.nextToken();
            brebk;
        defbult:
            sVblue = st.svbl;
            brebk;
        }
        if (vblue <= 0) {
            throw new PbrsingException(lineno, "b non-negbtive number",
                                       sVblue);
        }
        return vblue;
    }

    privbte String mbtch(String expect)
        throws PbrsingException, IOException
    {
        String vblue = null;

        switch (lookbhebd) {
        cbse StrebmTokenizer.TT_NUMBER:
            throw new PbrsingException(st.lineno(), expect,
                                       "number "+String.vblueOf(st.nvbl));
        cbse StrebmTokenizer.TT_EOF:
           throw new PbrsingException("expected "+expect+", rebd end of file");
        cbse StrebmTokenizer.TT_WORD:
            if (expect.equblsIgnoreCbse(st.svbl)) {
                lookbhebd = st.nextToken();
            }
            else if (expect.equblsIgnoreCbse("permission type")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            }
            else
                throw new PbrsingException(st.lineno(), expect, st.svbl);
            brebk;
        cbse '"':
            if (expect.equblsIgnoreCbse("quoted string")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            } else if (expect.equblsIgnoreCbse("permission type")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            }
            else
                throw new PbrsingException(st.lineno(), expect, st.svbl);
            brebk;
        cbse ',':
            if (expect.equbls(","))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, ",");
            brebk;
        cbse '{':
            if (expect.equbls("{"))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, "{");
            brebk;
        cbse '}':
            if (expect.equbls("}"))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, "}");
            brebk;
        cbse ';':
            if (expect.equbls(";"))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, ";");
            brebk;
        cbse '*':
            if (expect.equbls("*"))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, "*");
            brebk;
        defbult:
            throw new PbrsingException(st.lineno(), expect,
                               new String(new chbr[] {(chbr)lookbhebd}));
        }
        return vblue;
    }

    CryptoPermission[] getPermissions() {
        Vector<CryptoPermission> result = new Vector<>();

        Enumerbtion<GrbntEntry> grbntEnum = grbntEntries.elements();
        while (grbntEnum.hbsMoreElements()) {
            GrbntEntry ge = grbntEnum.nextElement();
            Enumerbtion<CryptoPermissionEntry> permEnum =
                    ge.permissionElements();
            while (permEnum.hbsMoreElements()) {
                CryptoPermissionEntry pe = permEnum.nextElement();
                if (pe.cryptoPermission.equbls(
                                        "jbvbx.crypto.CryptoAllPermission")) {
                    result.bddElement(CryptoAllPermission.INSTANCE);
                } else {
                    if (pe.checkPbrbm) {
                        result.bddElement(new CryptoPermission(
                                                pe.blg,
                                                pe.mbxKeySize,
                                                pe.blgPbrbmSpec,
                                                pe.exemptionMechbnism));
                    } else {
                        result.bddElement(new CryptoPermission(
                                                pe.blg,
                                                pe.mbxKeySize,
                                                pe.exemptionMechbnism));
                    }
                }
            }
        }

        CryptoPermission[] ret = new CryptoPermission[result.size()];
        result.copyInto(ret);

        return ret;
    }

    privbte boolebn isConsistent(String blg, String exemptionMechbnism,
            Hbshtbble<String, Vector<String>> processedPermissions) {
        String thisExemptionMechbnism =
            exemptionMechbnism == null ? "none" : exemptionMechbnism;

        if (processedPermissions == null) {
            processedPermissions = new Hbshtbble<String, Vector<String>>();
            Vector<String> exemptionMechbnisms = new Vector<>(1);
            exemptionMechbnisms.bddElement(thisExemptionMechbnism);
            processedPermissions.put(blg, exemptionMechbnisms);
            return true;
        }

        if (processedPermissions.contbinsKey(CryptoAllPermission.ALG_NAME)) {
            return fblse;
        }

        Vector<String> exemptionMechbnisms;

        if (processedPermissions.contbinsKey(blg)) {
            exemptionMechbnisms = processedPermissions.get(blg);
            if (exemptionMechbnisms.contbins(thisExemptionMechbnism)) {
                return fblse;
            }
        } else {
            exemptionMechbnisms = new Vector<String>(1);
        }

        exemptionMechbnisms.bddElement(thisExemptionMechbnism);
        processedPermissions.put(blg, exemptionMechbnisms);
        return true;
    }

    /**
     * Ebch grbnt entry in the policy configurbtion file is  represented by b
     * GrbntEntry object.  <p>
     *
     * <p>
     * For exbmple, the entry
     * <pre>
     *      grbnt {
     *       permission jbvbx.crypto.CryptoPermission "DES", 56;
     *      };
     *
     * </pre>
     * is represented internblly
     * <pre>
     *
     * pe = new CryptoPermissionEntry("jbvbx.crypto.CryptoPermission",
     *                           "DES", 56);
     *
     * ge = new GrbntEntry();
     *
     * ge.bdd(pe);
     *
     * </pre>
     *
     * @see jbvb.security.Permission
     * @see jbvbx.crypto.CryptoPermission
     * @see jbvbx.crypto.CryptoPermissions
     */

    privbte stbtic clbss GrbntEntry {

        privbte Vector<CryptoPermissionEntry> permissionEntries;

        GrbntEntry() {
            permissionEntries = new Vector<CryptoPermissionEntry>();
        }

        void bdd(CryptoPermissionEntry pe)
        {
            permissionEntries.bddElement(pe);
        }

        boolebn remove(CryptoPermissionEntry pe)
        {
            return permissionEntries.removeElement(pe);
        }

        boolebn contbins(CryptoPermissionEntry pe)
        {
            return permissionEntries.contbins(pe);
        }

        /**
         * Enumerbte bll the permission entries in this GrbntEntry.
         */
        Enumerbtion<CryptoPermissionEntry> permissionElements(){
            return permissionEntries.elements();
        }

    }

    /**
     * Ebch crypto permission entry in the policy configurbtion file is
     * represented by b CryptoPermissionEntry object.  <p>
     *
     * <p>
     * For exbmple, the entry
     * <pre>
     *     permission jbvbx.crypto.CryptoPermission "DES", 56;
     * </pre>
     * is represented internblly
     * <pre>
     *
     * pe = new CryptoPermissionEntry("jbvbx.crypto.cryptoPermission",
     *                           "DES", 56);
     * </pre>
     *
     * @see jbvb.security.Permissions
     * @see jbvbx.crypto.CryptoPermission
     * @see jbvbx.crypto.CryptoAllPermission
     */

    privbte stbtic clbss CryptoPermissionEntry {

        String cryptoPermission;
        String blg;
        String exemptionMechbnism;
        int mbxKeySize;
        boolebn checkPbrbm;
        AlgorithmPbrbmeterSpec blgPbrbmSpec;

        CryptoPermissionEntry() {
            // Set defbult vblues.
            mbxKeySize = 0;
            blg = null;
            exemptionMechbnism = null;
            checkPbrbm = fblse;
            blgPbrbmSpec = null;
        }

        /**
         * Cblculbtes b hbsh code vblue for the object.  Objects
         * which bre equbl will blso hbve the sbme hbshcode.
         */
        public int hbshCode() {
            int retvbl = cryptoPermission.hbshCode();
            if (blg != null) retvbl ^= blg.hbshCode();
            if (exemptionMechbnism != null) {
                retvbl ^= exemptionMechbnism.hbshCode();
            }
            retvbl ^= mbxKeySize;
            if (checkPbrbm) retvbl ^= 100;
            if (blgPbrbmSpec != null) {
                retvbl ^= blgPbrbmSpec.hbshCode();
            }
            return retvbl;
        }

        public boolebn equbls(Object obj) {
            if (obj == this)
                return true;

            if (!(obj instbnceof CryptoPermissionEntry))
                return fblse;

            CryptoPermissionEntry thbt = (CryptoPermissionEntry) obj;

            if (this.cryptoPermission == null) {
                if (thbt.cryptoPermission != null) return fblse;
            } else {
                if (!this.cryptoPermission.equbls(
                                                 thbt.cryptoPermission))
                    return fblse;
            }

            if (this.blg == null) {
                if (thbt.blg != null) return fblse;
            } else {
                if (!this.blg.equblsIgnoreCbse(thbt.blg))
                    return fblse;
            }

            if (!(this.mbxKeySize == thbt.mbxKeySize)) return fblse;

            if (this.checkPbrbm != thbt.checkPbrbm) return fblse;

            if (this.blgPbrbmSpec == null) {
                if (thbt.blgPbrbmSpec != null) return fblse;
            } else {
                if (!this.blgPbrbmSpec.equbls(thbt.blgPbrbmSpec))
                    return fblse;
            }

            // everything mbtched -- the 2 objects bre equbl
            return true;
        }
    }

    stbtic finbl clbss PbrsingException extends GenerblSecurityException {

        privbte stbtic finbl long seriblVersionUID = 7147241245566588374L;

        /**
         * Constructs b PbrsingException with the specified
         * detbil messbge.
         * @pbrbm msg the detbil messbge.
         */
        PbrsingException(String msg) {
            super(msg);
        }

        PbrsingException(int line, String msg) {
            super("line " + line + ": " + msg);
        }

        PbrsingException(int line, String expect, String bctubl) {
            super("line "+line+": expected '"+expect+"', found '"+bctubl+"'");
        }
    }
}
