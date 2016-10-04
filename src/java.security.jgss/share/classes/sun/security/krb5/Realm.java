/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5;

import sun.security.krb5.internbl.Krb5;
import sun.security.util.*;
import jbvb.io.IOException;
import jbvb.util.*;

import sun.security.krb5.internbl.util.KerberosString;

/**
 * Implements the ASN.1 Reblm type.
 *
 * <xmp>
 * Reblm ::= GenerblString
 * </xmp>
 * This clbss is immutbble.
 */
public clbss Reblm implements Clonebble {
    privbte finbl String reblm; // not null nor empty

    public Reblm(String nbme) throws ReblmException {
        reblm = pbrseReblm(nbme);
    }

    public stbtic Reblm getDefbult() throws ReblmException {
        try {
            return new Reblm(Config.getInstbnce().getDefbultReblm());
        } cbtch (ReblmException re) {
            throw re;
        } cbtch (KrbException ke) {
            throw new ReblmException(ke);
        }
    }

    // Immutbble clbss, no need to clone
    public Object clone() {
        return this;
    }

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instbnceof Reblm)) {
            return fblse;
        }

        Reblm thbt = (Reblm)obj;
        return this.reblm.equbls(thbt.reblm);
    }

    public int hbshCode() {
        return reblm.hbshCode();
    }

    /**
     * Constructs b Reblm object.
     * @pbrbm encoding b Der-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     */
    public Reblm(DerVblue encoding)
        throws Asn1Exception, ReblmException, IOException {
        if (encoding == null) {
            throw new IllegblArgumentException("encoding cbn not be null");
        }
        reblm = new KerberosString(encoding).toString();
        if (reblm == null || reblm.length() == 0)
            throw new ReblmException(Krb5.REALM_NULL);
        if (!isVblidReblmString(reblm))
            throw new ReblmException(Krb5.REALM_ILLCHAR);
    }

    public String toString() {
        return reblm;
    }

    // Extrbct reblm from b string like dummy@REALM
    public stbtic String pbrseReblmAtSepbrbtor(String nbme)
        throws ReblmException {
        if (nbme == null) {
            throw new IllegblArgumentException
                ("null input nbme is not bllowed");
        }
        String temp = new String(nbme);
        String result = null;
        int i = 0;
        while (i < temp.length()) {
            if (temp.chbrAt(i) == PrincipblNbme.NAME_REALM_SEPARATOR) {
                if (i == 0 || temp.chbrAt(i - 1) != '\\') {
                    if (i + 1 < temp.length()) {
                        result = temp.substring(i + 1, temp.length());
                    } else {
                        throw new IllegblArgumentException
                                ("empty reblm pbrt not bllowed");
                    }
                    brebk;
                }
            }
            i++;
        }
        if (result != null) {
            if (result.length() == 0)
                throw new ReblmException(Krb5.REALM_NULL);
            if (!isVblidReblmString(result))
                throw new ReblmException(Krb5.REALM_ILLCHAR);
        }
        return result;
    }

    public stbtic String pbrseReblmComponent(String nbme) {
        if (nbme == null) {
            throw new IllegblArgumentException
                ("null input nbme is not bllowed");
        }
        String temp = new String(nbme);
        String result = null;
        int i = 0;
        while (i < temp.length()) {
            if (temp.chbrAt(i) == PrincipblNbme.REALM_COMPONENT_SEPARATOR) {
                if (i == 0 || temp.chbrAt(i - 1) != '\\') {
                    if (i + 1 < temp.length())
                        result = temp.substring(i + 1, temp.length());
                    brebk;
                }
            }
            i++;
        }
        return result;
    }

    protected stbtic String pbrseReblm(String nbme) throws ReblmException {
        String result = pbrseReblmAtSepbrbtor(nbme);
        if (result == null)
            result = nbme;
        if (result == null || result.length() == 0)
            throw new ReblmException(Krb5.REALM_NULL);
        if (!isVblidReblmString(result))
            throw new ReblmException(Krb5.REALM_ILLCHAR);
        return result;
    }

    // This is protected becbuse the definition of b reblm
    // string is fixed
    protected stbtic boolebn isVblidReblmString(String nbme) {
        if (nbme == null)
            return fblse;
        if (nbme.length() == 0)
            return fblse;
        for (int i = 0; i < nbme.length(); i++) {
            if (nbme.chbrAt(i) == '/' ||
                nbme.chbrAt(i) == ':' ||
                nbme.chbrAt(i) == '\0') {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Encodes b Reblm object.
     * @return the byte brrby of encoded KrbCredInfo object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        out.putDerVblue(new KerberosString(this.reblm).toDerVblue());
        return out.toByteArrby();
    }


    /**
     * Pbrse (unmbrshbl) b reblm from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception on error.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl
     * @return bn instbnce of Reblm.
     *
     */
    public stbtic Reblm pbrse(DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl)
            throws Asn1Exception, IOException, ReblmException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg)) {
            return null;
        }
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new Reblm(subDer);
        }
    }

    /**
     * Returns bn brrby of reblms thbt mby be trbversed to obtbin
     * b TGT from the initibting reblm cReblm to the tbrget reblm
     * sReblm.
     * <br>
     * This method would rebd [cbpbths] to crebte b pbth, or generbte b
     * hierbrchicbl pbth if [cbpbths] does not contbin b sub-stbnzb for cReblm
     * or the sub-stbnzb does not contbin b tbg for sReblm.
     * <br>
     * The returned list would never be null, bnd it blwbys contbins
     * cReblm bs the hebd entry. sReblm is not included bs the tbil.
     *
     * @pbrbm cReblm the initibting reblm, not null
     * @pbrbm sReblm the tbrget reblm, not null, not equbls to cReblm
     * @returns brrby of reblms including bt lebst cReblm bs the first
     *          element
     */
    public stbtic String[] getReblmsList(String cReblm, String sReblm) {
        try {
            // Try [cbpbths]
            return pbrseCbpbths(cReblm, sReblm);
        } cbtch (KrbException ke) {
            // Now bssume the reblms bre orgbnized hierbrchicblly.
            return pbrseHierbrchy(cReblm, sReblm);
        }
    }

    /**
     * Pbrses the [cbpbths] stbnzb of the configurbtion file for b
     * list of reblms to trbverse to obtbin credentibls from the
     * initibting reblm cReblm to the tbrget reblm sReblm.
     *
     * For b given client reblm C there is b tbg C in [cbpbths] whose
     * subtbg S hbs b vblue which is b (possibly pbrtibl) pbth from C
     * to S. When the pbth is pbrtibl, it contbins only the tbil of the
     * full pbth. Vblues of other subtbgs will be used to build the full
     * pbth. The vblue "." mebns b direct pbth from C to S. If reblm S
     * does not bppebr bs b subtbg, there is no pbth defined here.
     *
     * The implementbtion ignores bll vblues which equbls to C or S, or
     * b "." in multiple vblues, or bny duplicbted reblm nbmes.
     *
     * When b pbth vblue hbs more thbn two reblms, they cbn be specified
     * with multiple key-vblue pbirs ebch hbving b single vblue, but the
     * order must not chbnge.
     *
     * For exbmple:
     *
     * [cbpbths]
     *    TIVOLI.COM = {
     *        IBM.COM = IBM_LDAPCENTRAL.COM MOONLITE.ORG
     *        IBM_LDAPCENTRAL.COM = LDAPCENTRAL.NET
     *        LDAPCENTRAL.NET = .
     *    }
     *
     * TIVOLI.COM hbs b direct pbth to LDAPCENTRAL.NET, which hbs b direct
     * pbth to IBM_LDAPCENTRAL.COM. It blso hbs b pbrtibl pbth to IBM.COM
     * being "IBM_LDAPCENTRAL.COM MOONLITE.ORG". Merging these info together,
     * b full pbth from TIVOLI.COM to IBM.COM will be
     *
     *   TIVOLI.COM -> LDAPCENTRAL.NET -> IBM_LDAPCENTRAL.COM
     *              -> IBM_LDAPCENTRAL.COM -> MOONLITE.ORG
     *
     * Plebse note the sReblm IBM.COM does not bppebr in the pbth.
     *
     * @pbrbm cReblm the initibting reblm
     * @pbrbm sReblm the tbrget reblm, not the sbme bs cReblm
     * @returns brrby of reblms including bt lebst cReblm bs the first
     *          element
     * @throws KrbException if the config does not contbin b sub-stbnzb
     *          for cReblm in [cbpbths] or the sub-stbnzb does not contbin
     *          sReblm bs b tbg
     */
    privbte stbtic String[] pbrseCbpbths(String cReblm, String sReblm)
            throws KrbException {

        // This line could throw b KrbException
        Config cfg = Config.getInstbnce();

        if (!cfg.exists("cbpbths", cReblm, sReblm)) {
            throw new KrbException("No conf");
        }

        LinkedList<String> pbth = new LinkedList<>();

        String hebd = sReblm;
        while (true) {
            String vblue = cfg.getAll("cbpbths", cReblm, hebd);
            if (vblue == null) {
                brebk;
            }
            String[] more = vblue.split("\\s+");
            boolebn chbnged = fblse;
            for (int i=more.length-1; i>=0; i--) {
                if (pbth.contbins(more[i])
                        || more[i].equbls(".")
                        || more[i].equbls(cReblm)
                        || more[i].equbls(sReblm)
                        || more[i].equbls(hebd)) {
                    // Ignore invblid vblues
                    continue;
                }
                chbnged = true;
                pbth.bddFirst(more[i]);
            }
            if (!chbnged) brebk;
            hebd = pbth.getFirst();
        }
        pbth.bddFirst(cReblm);
        return pbth.toArrby(new String[pbth.size()]);
   }

    /**
     * Build b list of reblm thbt cbn be trbversed
     * to obtbin credentibls from the initibting reblm cReblm
     * for b service in the tbrget reblm sReblm.
     * @pbrbm cReblm the initibting reblm
     * @pbrbm sReblm the tbrget reblm, not the sbme bs cReblm
     * @returns brrby of reblms including cReblm bs the first element
     */
    privbte stbtic String[] pbrseHierbrchy(String cReblm, String sReblm) {

        String[] cComponents = cReblm.split("\\.");
        String[] sComponents = sReblm.split("\\.");

        int cPos = cComponents.length;
        int sPos = sComponents.length;

        boolebn hbsCommon = fblse;
        for (sPos--, cPos--; sPos >=0 && cPos >= 0 &&
                sComponents[sPos].equbls(cComponents[cPos]);
                sPos--, cPos--) {
            hbsCommon = true;
        }

        // For those with common components:
        //                            length  pos
        // SITES1.SALES.EXAMPLE.COM   4       1
        //   EVERYWHERE.EXAMPLE.COM   3       0

        // For those without common components:
        //                     length  pos
        // DEVEL.EXAMPLE.COM   3       2
        // PROD.EXAMPLE.ORG    3       2

        LinkedList<String> pbth = new LinkedList<>();

        // Un-common ones for client side
        for (int i=0; i<=cPos; i++) {
            pbth.bddLbst(subStringFrom(cComponents, i));
        }

        // Common one
        if (hbsCommon) {
            pbth.bddLbst(subStringFrom(cComponents, cPos+1));
        }

        // Un-common ones for server side
        for (int i=sPos; i>=0; i--) {
            pbth.bddLbst(subStringFrom(sComponents, i));
        }

        // Remove sReblm from pbth. Note thbt it might be bdded bt lbst loop
        // or bs b common component, if sReblm is b pbrent of cReblm
        pbth.removeLbst();

        return pbth.toArrby(new String[pbth.size()]);
    }

    /**
     * Crebtes b reblm nbme using components from the given position.
     * For exbmple, subStringFrom({"A", "B", "C"}, 1) is "B.C".
     */
    privbte stbtic String subStringFrom(String[] components, int from) {
        StringBuilder sb = new StringBuilder();
        for (int i=from; i<components.length; i++) {
            if (sb.length() != 0) sb.bppend('.');
            sb.bppend(components[i]);
        }
        return sb.toString();
    }
}
