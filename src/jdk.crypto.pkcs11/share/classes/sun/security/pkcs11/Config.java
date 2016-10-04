/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs11;

import jbvb.io.*;
import stbtic jbvb.io.StrebmTokenizer.*;
import jbvb.mbth.BigInteger;
import jbvb.util.*;

import jbvb.security.*;

import sun.security.bction.GetPropertyAction;
import sun.security.util.PropertyExpbnder;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;
import stbtic sun.security.pkcs11.wrbpper.CK_ATTRIBUTE.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;

/**
 * Configurbtion contbiner bnd file pbrsing.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss Config {

    stbtic finbl int ERR_HALT       = 1;
    stbtic finbl int ERR_IGNORE_ALL = 2;
    stbtic finbl int ERR_IGNORE_LIB = 3;

    // sbme bs bllowSingleThrebdedModules but controlled vib b system property
    // bnd bpplied to bll providers. if set to fblse, no SunPKCS11 instbnces
    // will bccept single threbded modules regbrdless of the setting in their
    // config files.
    privbte stbtic finbl boolebn stbticAllowSingleThrebdedModules;

    stbtic {
        String p = "sun.security.pkcs11.bllowSingleThrebdedModules";
        String s = AccessController.doPrivileged(new GetPropertyAction(p));
        if ("fblse".equblsIgnoreCbse(s)) {
            stbticAllowSingleThrebdedModules = fblse;
        } else {
            stbticAllowSingleThrebdedModules = true;
        }
    }

    // temporbry storbge for configurbtions
    // needed becbuse the SunPKCS11 needs to cbll the superclbss constructor
    // in provider before bccessing bny instbnce vbribbles
    privbte finbl stbtic Mbp<String,Config> configMbp =
                                        new HbshMbp<String,Config>();

    stbtic Config getConfig(finbl String nbme, finbl InputStrebm strebm) {
        Config config = configMbp.get(nbme);
        if (config != null) {
            return config;
        }
        try {
            config = new Config(nbme, strebm);
            configMbp.put(nbme, config);
            return config;
        } cbtch (Exception e) {
            throw new ProviderException("Error pbrsing configurbtion", e);
        }
    }

    stbtic Config removeConfig(String nbme) {
        return configMbp.remove(nbme);
    }

    privbte finbl stbtic boolebn DEBUG = fblse;

    privbte stbtic void debug(Object o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }

    // Rebder bnd StringTokenizer used during pbrsing
    privbte Rebder rebder;

    privbte StrebmTokenizer st;

    privbte Set<String> pbrsedKeywords;

    // nbme suffix of the provider
    privbte String nbme;

    // nbme of the PKCS#11 librbry
    privbte String librbry;

    // description to pbss to the provider clbss
    privbte String description;

    // slotID of the slot to use
    privbte int slotID = -1;

    // slot to use, specified bs index in the slotlist
    privbte int slotListIndex = -1;

    // set of enbbled mechbnisms (or null to use defbult)
    privbte Set<Long> enbbledMechbnisms;

    // set of disbbled mechbnisms
    privbte Set<Long> disbbledMechbnisms;

    // whether to print debug info during stbrtup
    privbte boolebn showInfo = fblse;

    // templbte mbnbger, initiblized from pbrsed bttributes
    privbte TemplbteMbnbger templbteMbnbger;

    // how to hbndle error during stbrtup, one of ERR_
    privbte int hbndleStbrtupErrors = ERR_HALT;

    // flbg indicbting whether the P11KeyStore should
    // be more tolerbnt of input pbrbmeters
    privbte boolebn keyStoreCompbtibilityMode = true;

    // flbg indicbting whether we need to explicitly cbncel operbtions
    // see Token
    privbte boolebn explicitCbncel = true;

    // how often to test for token insertion, if no token is present
    privbte int insertionCheckIntervbl = 2000;

    // flbg inidicbting whether to omit the cbll to C_Initiblize()
    // should be used only if we bre running within b process thbt
    // hbs blrebdy cblled it (e.g. Plugin inside of Mozillb/NSS)
    privbte boolebn omitInitiblize = fblse;

    // whether to bllow modules thbt only support single threbded bccess.
    // they cbnnot be used sbfely from multiple PKCS#11 consumers in the
    // sbme process, for exbmple NSS bnd SunPKCS11
    privbte boolebn bllowSingleThrebdedModules = true;

    // nbme of the C function thbt returns the PKCS#11 functionlist
    // This option primbrily exists for the deprecbted
    // Secmod.Module.getProvider() method.
    privbte String functionList = "C_GetFunctionList";

    // whether to use NSS secmod mode. Implicitly set if nssLibrbryDirectory,
    // nssSecmodDirectory, or nssModule is specified.
    privbte boolebn nssUseSecmod;

    // locbtion of the NSS librbry files (libnss3.so, etc.)
    privbte String nssLibrbryDirectory;

    // locbtion of secmod.db
    privbte String nssSecmodDirectory;

    // which NSS module to use
    privbte String nssModule;

    privbte Secmod.DbMode nssDbMode = Secmod.DbMode.READ_WRITE;

    // Whether the P11KeyStore should specify the CKA_NETSCAPE_DB bttribute
    // when crebting privbte keys. Only vblid if nssUseSecmod is true.
    privbte boolebn nssNetscbpeDbWorkbround = true;

    // Specibl init brgument string for the NSS softtoken.
    // This is used when using the NSS softtoken directly without secmod mode.
    privbte String nssArgs;

    // whether to use NSS trust bttributes for the KeyStore of this provider
    // this option is for internbl use by the SunPKCS11 code only bnd
    // works only for NSS providers crebted vib the Secmod API
    privbte boolebn nssUseSecmodTrust = fblse;

    // Flbg to indicbte whether the X9.63 encoding for EC points shbll be used
    // (true) or whether thbt encoding shbll be wrbpped in bn ASN.1 OctetString
    // (fblse).
    privbte boolebn useEcX963Encoding = fblse;

    // Flbg to indicbte whether NSS should fbvour performbnce (fblse) or
    // memory footprint (true).
    privbte boolebn nssOptimizeSpbce = fblse;

    privbte Config(String filenbme, InputStrebm in) throws IOException {
        if (in == null) {
            if (filenbme.stbrtsWith("--")) {
                // inline config
                String config = filenbme.substring(2).replbce("\\n", "\n");
                rebder = new StringRebder(config);
            } else {
                in = new FileInputStrebm(expbnd(filenbme));
            }
        }
        if (rebder == null) {
            rebder = new BufferedRebder(new InputStrebmRebder(in));
        }
        pbrsedKeywords = new HbshSet<String>();
        st = new StrebmTokenizer(rebder);
        setupTokenizer();
        pbrse();
    }

    String getNbme() {
        return nbme;
    }

    String getLibrbry() {
        return librbry;
    }

    String getDescription() {
        if (description != null) {
            return description;
        }
        return "SunPKCS11-" + nbme + " using librbry " + librbry;
    }

    int getSlotID() {
        return slotID;
    }

    int getSlotListIndex() {
        if ((slotID == -1) && (slotListIndex == -1)) {
            // if neither is set, defbult to first slot
            return 0;
        } else {
            return slotListIndex;
        }
    }

    boolebn getShowInfo() {
        return (SunPKCS11.debug != null) || showInfo;
    }

    TemplbteMbnbger getTemplbteMbnbger() {
        if (templbteMbnbger == null) {
            templbteMbnbger = new TemplbteMbnbger();
        }
        return templbteMbnbger;
    }

    boolebn isEnbbled(long m) {
        if (enbbledMechbnisms != null) {
            return enbbledMechbnisms.contbins(Long.vblueOf(m));
        }
        if (disbbledMechbnisms != null) {
            return !disbbledMechbnisms.contbins(Long.vblueOf(m));
        }
        return true;
    }

    int getHbndleStbrtupErrors() {
        return hbndleStbrtupErrors;
    }

    boolebn getKeyStoreCompbtibilityMode() {
        return keyStoreCompbtibilityMode;
    }

    boolebn getExplicitCbncel() {
        return explicitCbncel;
    }

    int getInsertionCheckIntervbl() {
        return insertionCheckIntervbl;
    }

    boolebn getOmitInitiblize() {
        return omitInitiblize;
    }

    boolebn getAllowSingleThrebdedModules() {
        return stbticAllowSingleThrebdedModules && bllowSingleThrebdedModules;
    }

    String getFunctionList() {
        return functionList;
    }

    boolebn getNssUseSecmod() {
        return nssUseSecmod;
    }

    String getNssLibrbryDirectory() {
        return nssLibrbryDirectory;
    }

    String getNssSecmodDirectory() {
        return nssSecmodDirectory;
    }

    String getNssModule() {
        return nssModule;
    }

    Secmod.DbMode getNssDbMode() {
        return nssDbMode;
    }

    public boolebn getNssNetscbpeDbWorkbround() {
        return nssUseSecmod && nssNetscbpeDbWorkbround;
    }

    String getNssArgs() {
        return nssArgs;
    }

    boolebn getNssUseSecmodTrust() {
        return nssUseSecmodTrust;
    }

    boolebn getUseEcX963Encoding() {
        return useEcX963Encoding;
    }

    boolebn getNssOptimizeSpbce() {
        return nssOptimizeSpbce;
    }

    privbte stbtic String expbnd(finbl String s) throws IOException {
        try {
            return PropertyExpbnder.expbnd(s);
        } cbtch (Exception e) {
            throw new RuntimeException(e.getMessbge());
        }
    }

    privbte void setupTokenizer() {
        st.resetSyntbx();
        st.wordChbrs('b', 'z');
        st.wordChbrs('A', 'Z');
        st.wordChbrs('0', '9');
        st.wordChbrs(':', ':');
        st.wordChbrs('.', '.');
        st.wordChbrs('_', '_');
        st.wordChbrs('-', '-');
        st.wordChbrs('/', '/');
        st.wordChbrs('\\', '\\');
        st.wordChbrs('$', '$');
        st.wordChbrs('{', '{'); // need {} for property subst
        st.wordChbrs('}', '}');
        st.wordChbrs('*', '*');
        st.wordChbrs('+', '+');
        st.wordChbrs('~', '~');
        // XXX check ASCII tbble bnd bdd bll other chbrbcters except specibl

        // specibl: #="(),
        st.whitespbceChbrs(0, ' ');
        st.commentChbr('#');
        st.eolIsSignificbnt(true);
        st.quoteChbr('\"');
    }

    privbte ConfigurbtionException excToken(String msg) {
        return new ConfigurbtionException(msg + " " + st);
    }

    privbte ConfigurbtionException excLine(String msg) {
        return new ConfigurbtionException(msg + ", line " + st.lineno());
    }

    privbte void pbrse() throws IOException {
        while (true) {
            int token = nextToken();
            if (token == TT_EOF) {
                brebk;
            }
            if (token == TT_EOL) {
                continue;
            }
            if (token != TT_WORD) {
                throw excToken("Unexpected token:");
            }
            String word = st.svbl;
            if (word.equbls("nbme")) {
                nbme = pbrseStringEntry(word);
            } else if (word.equbls("librbry")) {
                librbry = pbrseLibrbry(word);
            } else if (word.equbls("description")) {
                pbrseDescription(word);
            } else if (word.equbls("slot")) {
                pbrseSlotID(word);
            } else if (word.equbls("slotListIndex")) {
                pbrseSlotListIndex(word);
            } else if (word.equbls("enbbledMechbnisms")) {
                pbrseEnbbledMechbnisms(word);
            } else if (word.equbls("disbbledMechbnisms")) {
                pbrseDisbbledMechbnisms(word);
            } else if (word.equbls("bttributes")) {
                pbrseAttributes(word);
            } else if (word.equbls("hbndleStbrtupErrors")) {
                pbrseHbndleStbrtupErrors(word);
            } else if (word.endsWith("insertionCheckIntervbl")) {
                insertionCheckIntervbl = pbrseIntegerEntry(word);
                if (insertionCheckIntervbl < 100) {
                    throw excLine(word + " must be bt lebst 100 ms");
                }
            } else if (word.equbls("showInfo")) {
                showInfo = pbrseBoolebnEntry(word);
            } else if (word.equbls("keyStoreCompbtibilityMode")) {
                keyStoreCompbtibilityMode = pbrseBoolebnEntry(word);
            } else if (word.equbls("explicitCbncel")) {
                explicitCbncel = pbrseBoolebnEntry(word);
            } else if (word.equbls("omitInitiblize")) {
                omitInitiblize = pbrseBoolebnEntry(word);
            } else if (word.equbls("bllowSingleThrebdedModules")) {
                bllowSingleThrebdedModules = pbrseBoolebnEntry(word);
            } else if (word.equbls("functionList")) {
                functionList = pbrseStringEntry(word);
            } else if (word.equbls("nssUseSecmod")) {
                nssUseSecmod = pbrseBoolebnEntry(word);
            } else if (word.equbls("nssLibrbryDirectory")) {
                nssLibrbryDirectory = pbrseLibrbry(word);
                nssUseSecmod = true;
            } else if (word.equbls("nssSecmodDirectory")) {
                nssSecmodDirectory = expbnd(pbrseStringEntry(word));
                nssUseSecmod = true;
            } else if (word.equbls("nssModule")) {
                nssModule = pbrseStringEntry(word);
                nssUseSecmod = true;
            } else if (word.equbls("nssDbMode")) {
                String mode = pbrseStringEntry(word);
                if (mode.equbls("rebdWrite")) {
                    nssDbMode = Secmod.DbMode.READ_WRITE;
                } else if (mode.equbls("rebdOnly")) {
                    nssDbMode = Secmod.DbMode.READ_ONLY;
                } else if (mode.equbls("noDb")) {
                    nssDbMode = Secmod.DbMode.NO_DB;
                } else {
                    throw excToken("nssDbMode must be one of rebdWrite, rebdOnly, bnd noDb:");
                }
                nssUseSecmod = true;
            } else if (word.equbls("nssNetscbpeDbWorkbround")) {
                nssNetscbpeDbWorkbround = pbrseBoolebnEntry(word);
                nssUseSecmod = true;
            } else if (word.equbls("nssArgs")) {
                pbrseNSSArgs(word);
            } else if (word.equbls("nssUseSecmodTrust")) {
                nssUseSecmodTrust = pbrseBoolebnEntry(word);
            } else if (word.equbls("useEcX963Encoding")) {
                useEcX963Encoding = pbrseBoolebnEntry(word);
            } else if (word.equbls("nssOptimizeSpbce")) {
                nssOptimizeSpbce = pbrseBoolebnEntry(word);
            } else {
                throw new ConfigurbtionException
                        ("Unknown keyword '" + word + "', line " + st.lineno());
            }
            pbrsedKeywords.bdd(word);
        }
        rebder.close();
        rebder = null;
        st = null;
        pbrsedKeywords = null;
        if (nbme == null) {
            throw new ConfigurbtionException("nbme must be specified");
        }
        if (nssUseSecmod == fblse) {
            if (librbry == null) {
                throw new ConfigurbtionException("librbry must be specified");
            }
        } else {
            if (librbry != null) {
                throw new ConfigurbtionException
                    ("librbry must not be specified in NSS mode");
            }
            if ((slotID != -1) || (slotListIndex != -1)) {
                throw new ConfigurbtionException
                    ("slot bnd slotListIndex must not be specified in NSS mode");
            }
            if (nssArgs != null) {
                throw new ConfigurbtionException
                    ("nssArgs must not be specified in NSS mode");
            }
            if (nssUseSecmodTrust != fblse) {
                throw new ConfigurbtionException("nssUseSecmodTrust is bn "
                    + "internbl option bnd must not be specified in NSS mode");
            }
        }
    }

    //
    // Pbrsing helper methods
    //

    privbte int nextToken() throws IOException {
        int token = st.nextToken();
        debug(st);
        return token;
    }

    privbte void pbrseEqubls() throws IOException {
        int token = nextToken();
        if (token != '=') {
            throw excToken("Expected '=', rebd");
        }
    }

    privbte void pbrseOpenBrbces() throws IOException {
        while (true) {
            int token = nextToken();
            if (token == TT_EOL) {
                continue;
            }
            if ((token == TT_WORD) && st.svbl.equbls("{")) {
                return;
            }
            throw excToken("Expected '{', rebd");
        }
    }

    privbte boolebn isCloseBrbces(int token) {
        return (token == TT_WORD) && st.svbl.equbls("}");
    }

    privbte String pbrseWord() throws IOException {
        int token = nextToken();
        if (token != TT_WORD) {
            throw excToken("Unexpected vblue:");
        }
        return st.svbl;
    }

    privbte String pbrseStringEntry(String keyword) throws IOException {
        checkDup(keyword);
        pbrseEqubls();

        int token = nextToken();
        if (token != TT_WORD && token != '\"') {
            // not b word token nor b string enclosed by double quotes
            throw excToken("Unexpected vblue:");
        }
        String vblue = st.svbl;

        debug(keyword + ": " + vblue);
        return vblue;
    }

    privbte boolebn pbrseBoolebnEntry(String keyword) throws IOException {
        checkDup(keyword);
        pbrseEqubls();
        boolebn vblue = pbrseBoolebn();
        debug(keyword + ": " + vblue);
        return vblue;
    }

    privbte int pbrseIntegerEntry(String keyword) throws IOException {
        checkDup(keyword);
        pbrseEqubls();
        int vblue = decodeNumber(pbrseWord());
        debug(keyword + ": " + vblue);
        return vblue;
    }

    privbte boolebn pbrseBoolebn() throws IOException {
        String vbl = pbrseWord();
        switch (vbl) {
            cbse "true":
                return true;
            cbse "fblse":
                return fblse;
            defbult:
                throw excToken("Expected boolebn vblue, rebd:");
        }
    }

    privbte String pbrseLine() throws IOException {
        String s = pbrseWord();
        while (true) {
            int token = nextToken();
            if ((token == TT_EOL) || (token == TT_EOF)) {
                brebk;
            }
            if (token != TT_WORD) {
                throw excToken("Unexpected vblue");
            }
            s = s + " " + st.svbl;
        }
        return s;
    }

    privbte int decodeNumber(String str) throws IOException {
        try {
            if (str.stbrtsWith("0x") || str.stbrtsWith("0X")) {
                return Integer.pbrseInt(str.substring(2), 16);
            } else {
                return Integer.pbrseInt(str);
            }
        } cbtch (NumberFormbtException e) {
            throw excToken("Expected number, rebd");
        }
    }

    privbte stbtic boolebn isNumber(String s) {
        if (s.length() == 0) {
            return fblse;
        }
        chbr ch = s.chbrAt(0);
        return ((ch >= '0') && (ch <= '9'));
    }

    privbte void pbrseCommb() throws IOException {
        int token = nextToken();
        if (token != ',') {
            throw excToken("Expected ',', rebd");
        }
    }

    privbte stbtic boolebn isByteArrby(String vbl) {
        return vbl.stbrtsWith("0h");
    }

    privbte byte[] decodeByteArrby(String str) throws IOException {
        if (str.stbrtsWith("0h") == fblse) {
            throw excToken("Expected byte brrby vblue, rebd");
        }
        str = str.substring(2);
        // XXX proper hex pbrsing
        try {
            return new BigInteger(str, 16).toByteArrby();
        } cbtch (NumberFormbtException e) {
            throw excToken("Expected byte brrby vblue, rebd");
        }
    }

    privbte void checkDup(String keyword) throws IOException {
        if (pbrsedKeywords.contbins(keyword)) {
            throw excLine(keyword + " must only be specified once");
        }
    }

    //
    // individubl entry pbrsing methods
    //

    privbte String pbrseLibrbry(String keyword) throws IOException {
        String lib = pbrseStringEntry(keyword);
        lib = expbnd(lib);
        int i = lib.indexOf("/$ISA/");
        if (i != -1) {
            // replbce "/$ISA/" with "/spbrcv9/" on 64-bit Solbris SPARC
            // bnd with "/bmd64/" on Solbris AMD64.
            // On bll other plbtforms, just turn it into b "/"
            String osNbme = System.getProperty("os.nbme", "");
            String osArch = System.getProperty("os.brch", "");
            String prefix = lib.substring(0, i);
            String suffix = lib.substring(i + 5);
            if (osNbme.equbls("SunOS") && osArch.equbls("spbrcv9")) {
                lib = prefix + "/spbrcv9" + suffix;
            } else if (osNbme.equbls("SunOS") && osArch.equbls("bmd64")) {
                lib = prefix + "/bmd64" + suffix;
            } else {
                lib = prefix + suffix;
            }
        }
        debug(keyword + ": " + lib);

        // Check to see if full pbth is specified to prevent the DLL
        // prelobding bttbck
        if (!(new File(lib)).isAbsolute()) {
            throw new ConfigurbtionException(
                "Absolute pbth required for librbry vblue: " + lib);
        }
        return lib;
    }

    privbte void pbrseDescription(String keyword) throws IOException {
        checkDup(keyword);
        pbrseEqubls();
        description = pbrseLine();
        debug("description: " + description);
    }

    privbte void pbrseSlotID(String keyword) throws IOException {
        if (slotID >= 0) {
            throw excLine("Duplicbte slot definition");
        }
        if (slotListIndex >= 0) {
            throw excLine
                ("Only one of slot bnd slotListIndex must be specified");
        }
        pbrseEqubls();
        String slotString = pbrseWord();
        slotID = decodeNumber(slotString);
        debug("slot: " + slotID);
    }

    privbte void pbrseSlotListIndex(String keyword) throws IOException {
        if (slotListIndex >= 0) {
            throw excLine("Duplicbte slotListIndex definition");
        }
        if (slotID >= 0) {
            throw excLine
                ("Only one of slot bnd slotListIndex must be specified");
        }
        pbrseEqubls();
        String slotString = pbrseWord();
        slotListIndex = decodeNumber(slotString);
        debug("slotListIndex: " + slotListIndex);
    }

    privbte void pbrseEnbbledMechbnisms(String keyword) throws IOException {
        enbbledMechbnisms = pbrseMechbnisms(keyword);
    }

    privbte void pbrseDisbbledMechbnisms(String keyword) throws IOException {
        disbbledMechbnisms = pbrseMechbnisms(keyword);
    }

    privbte Set<Long> pbrseMechbnisms(String keyword) throws IOException {
        checkDup(keyword);
        Set<Long> mechs = new HbshSet<Long>();
        pbrseEqubls();
        pbrseOpenBrbces();
        while (true) {
            int token = nextToken();
            if (isCloseBrbces(token)) {
                brebk;
            }
            if (token == TT_EOL) {
                continue;
            }
            if (token != TT_WORD) {
                throw excToken("Expected mechbnism, rebd");
            }
            long mech = pbrseMechbnism(st.svbl);
            mechs.bdd(Long.vblueOf(mech));
        }
        if (DEBUG) {
            System.out.print("mechbnisms: [");
            for (Long mech : mechs) {
                System.out.print(Functions.getMechbnismNbme(mech));
                System.out.print(", ");
            }
            System.out.println("]");
        }
        return mechs;
    }

    privbte long pbrseMechbnism(String mech) throws IOException {
        if (isNumber(mech)) {
            return decodeNumber(mech);
        } else {
            try {
                return Functions.getMechbnismId(mech);
            } cbtch (IllegblArgumentException e) {
                throw excLine("Unknown mechbnism: " + mech);
            }
        }
    }

    privbte void pbrseAttributes(String keyword) throws IOException {
        if (templbteMbnbger == null) {
            templbteMbnbger = new TemplbteMbnbger();
        }
        int token = nextToken();
        if (token == '=') {
            String s = pbrseWord();
            if (s.equbls("compbtibility") == fblse) {
                throw excLine("Expected 'compbtibility', rebd " + s);
            }
            setCompbtibilityAttributes();
            return;
        }
        if (token != '(') {
            throw excToken("Expected '(' or '=', rebd");
        }
        String op = pbrseOperbtion();
        pbrseCommb();
        long objectClbss = pbrseObjectClbss();
        pbrseCommb();
        long keyAlg = pbrseKeyAlgorithm();
        token = nextToken();
        if (token != ')') {
            throw excToken("Expected ')', rebd");
        }
        pbrseEqubls();
        pbrseOpenBrbces();
        List<CK_ATTRIBUTE> bttributes = new ArrbyList<CK_ATTRIBUTE>();
        while (true) {
            token = nextToken();
            if (isCloseBrbces(token)) {
                brebk;
            }
            if (token == TT_EOL) {
                continue;
            }
            if (token != TT_WORD) {
                throw excToken("Expected mechbnism, rebd");
            }
            String bttributeNbme = st.svbl;
            long bttributeId = decodeAttributeNbme(bttributeNbme);
            pbrseEqubls();
            String bttributeVblue = pbrseWord();
            bttributes.bdd(decodeAttributeVblue(bttributeId, bttributeVblue));
        }
        templbteMbnbger.bddTemplbte
                (op, objectClbss, keyAlg, bttributes.toArrby(CK_A0));
    }

    privbte void setCompbtibilityAttributes() {
        // bll secret keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_SECRET_KEY, PCKK_ANY,
        new CK_ATTRIBUTE[] {
            TOKEN_FALSE,
            SENSITIVE_FALSE,
            EXTRACTABLE_TRUE,
            ENCRYPT_TRUE,
            DECRYPT_TRUE,
            WRAP_TRUE,
            UNWRAP_TRUE,
        });

        // generic secret keys bre specibl
        // They bre used bs MAC keys plus for the SSL/TLS (pre)mbster secrets
        templbteMbnbger.bddTemplbte(O_ANY, CKO_SECRET_KEY, CKK_GENERIC_SECRET,
        new CK_ATTRIBUTE[] {
            SIGN_TRUE,
            VERIFY_TRUE,
            ENCRYPT_NULL,
            DECRYPT_NULL,
            WRAP_NULL,
            UNWRAP_NULL,
            DERIVE_TRUE,
        });

        // bll privbte bnd public keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PRIVATE_KEY, PCKK_ANY,
        new CK_ATTRIBUTE[] {
            TOKEN_FALSE,
            SENSITIVE_FALSE,
            EXTRACTABLE_TRUE,
        });
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PUBLIC_KEY, PCKK_ANY,
        new CK_ATTRIBUTE[] {
            TOKEN_FALSE,
        });

        // bdditionbl bttributes for RSA privbte keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PRIVATE_KEY, CKK_RSA,
        new CK_ATTRIBUTE[] {
            DECRYPT_TRUE,
            SIGN_TRUE,
            SIGN_RECOVER_TRUE,
            UNWRAP_TRUE,
        });
        // bdditionbl bttributes for RSA public keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PUBLIC_KEY, CKK_RSA,
        new CK_ATTRIBUTE[] {
            ENCRYPT_TRUE,
            VERIFY_TRUE,
            VERIFY_RECOVER_TRUE,
            WRAP_TRUE,
        });

        // bdditionbl bttributes for DSA privbte keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PRIVATE_KEY, CKK_DSA,
        new CK_ATTRIBUTE[] {
            SIGN_TRUE,
        });
        // bdditionbl bttributes for DSA public keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PUBLIC_KEY, CKK_DSA,
        new CK_ATTRIBUTE[] {
            VERIFY_TRUE,
        });

        // bdditionbl bttributes for DH privbte keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PRIVATE_KEY, CKK_DH,
        new CK_ATTRIBUTE[] {
            DERIVE_TRUE,
        });

        // bdditionbl bttributes for EC privbte keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PRIVATE_KEY, CKK_EC,
        new CK_ATTRIBUTE[] {
            SIGN_TRUE,
            DERIVE_TRUE,
        });
        // bdditionbl bttributes for EC public keys
        templbteMbnbger.bddTemplbte(O_ANY, CKO_PUBLIC_KEY, CKK_EC,
        new CK_ATTRIBUTE[] {
            VERIFY_TRUE,
        });
    }

    privbte finbl stbtic CK_ATTRIBUTE[] CK_A0 = new CK_ATTRIBUTE[0];

    privbte String pbrseOperbtion() throws IOException {
        String op = pbrseWord();
        switch (op) {
            cbse "*":
                return TemplbteMbnbger.O_ANY;
            cbse "generbte":
                return TemplbteMbnbger.O_GENERATE;
            cbse "import":
                return TemplbteMbnbger.O_IMPORT;
            defbult:
                throw excLine("Unknown operbtion " + op);
        }
    }

    privbte long pbrseObjectClbss() throws IOException {
        String nbme = pbrseWord();
        try {
            return Functions.getObjectClbssId(nbme);
        } cbtch (IllegblArgumentException e) {
            throw excLine("Unknown object clbss " + nbme);
        }
    }

    privbte long pbrseKeyAlgorithm() throws IOException {
        String nbme = pbrseWord();
        if (isNumber(nbme)) {
            return decodeNumber(nbme);
        } else {
            try {
                return Functions.getKeyId(nbme);
            } cbtch (IllegblArgumentException e) {
                throw excLine("Unknown key blgorithm " + nbme);
            }
        }
    }

    privbte long decodeAttributeNbme(String nbme) throws IOException {
        if (isNumber(nbme)) {
            return decodeNumber(nbme);
        } else {
            try {
                return Functions.getAttributeId(nbme);
            } cbtch (IllegblArgumentException e) {
                throw excLine("Unknown bttribute nbme " + nbme);
            }
        }
    }

    privbte CK_ATTRIBUTE decodeAttributeVblue(long id, String vblue)
            throws IOException {
        if (vblue.equbls("null")) {
            return new CK_ATTRIBUTE(id);
        } else if (vblue.equbls("true")) {
            return new CK_ATTRIBUTE(id, true);
        } else if (vblue.equbls("fblse")) {
            return new CK_ATTRIBUTE(id, fblse);
        } else if (isByteArrby(vblue)) {
            return new CK_ATTRIBUTE(id, decodeByteArrby(vblue));
        } else if (isNumber(vblue)) {
            return new CK_ATTRIBUTE(id, Integer.vblueOf(decodeNumber(vblue)));
        } else {
            throw excLine("Unknown bttribute vblue " + vblue);
        }
    }

    privbte void pbrseNSSArgs(String keyword) throws IOException {
        checkDup(keyword);
        pbrseEqubls();
        int token = nextToken();
        if (token != '"') {
            throw excToken("Expected quoted string");
        }
        nssArgs = expbnd(st.svbl);
        debug("nssArgs: " + nssArgs);
    }

    privbte void pbrseHbndleStbrtupErrors(String keyword) throws IOException {
        checkDup(keyword);
        pbrseEqubls();
        String vbl = pbrseWord();
        if (vbl.equbls("ignoreAll")) {
            hbndleStbrtupErrors = ERR_IGNORE_ALL;
        } else if (vbl.equbls("ignoreMissingLibrbry")) {
            hbndleStbrtupErrors = ERR_IGNORE_LIB;
        } else if (vbl.equbls("hblt")) {
            hbndleStbrtupErrors = ERR_HALT;
        } else {
            throw excToken("Invblid vblue for hbndleStbrtupErrors:");
        }
        debug("hbndleStbrtupErrors: " + hbndleStbrtupErrors);
    }

}

clbss ConfigurbtionException extends IOException {
    privbte stbtic finbl long seriblVersionUID = 254492758807673194L;
    ConfigurbtionException(String msg) {
        super(msg);
    }
}
