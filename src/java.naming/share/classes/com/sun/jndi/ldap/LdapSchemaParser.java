/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvb.util.Vfdtor;

/**
 * Nftsdbpf's 3.1 sfrvfrs ibvf somf sdifmb bugs:
 * - It puts quotfs bround OIDs (sudi bs tiosf for SUP, SYNTAX).
 * - Wifn you try to writf out tif MUST/MAY list (sudi bs "MUST dn"),
 *   it wbnts ("MUST (dn)") instfbd
 */

finbl dlbss LdbpSdifmbPbrsfr {

    // do dfbugging
    privbtf stbtid finbl boolfbn dfbug = fblsf;


    // nbmfs of bttributf IDs in tif LDAP sdifmb fntry
    stbtid finbl String OBJECTCLASSDESC_ATTR_ID = "objfdtClbssfs";
    stbtid finbl String ATTRIBUTEDESC_ATTR_ID = "bttributfTypfs";
    stbtid finbl String SYNTAXDESC_ATTR_ID = "ldbpSyntbxfs";
    stbtid finbl String MATCHRULEDESC_ATTR_ID = "mbtdiingRulfs";

    // informbtion for drfbting intfrnbl nodfs in JNDI sdifmb trff
    stbtid finbl String OBJECTCLASS_DEFINITION_NAME =
                        "ClbssDffinition";
    privbtf stbtid finbl String[] CLASS_DEF_ATTRS = {
                         "objfdtdlbss", "ClbssDffinition"};
            stbtid finbl String ATTRIBUTE_DEFINITION_NAME =
                        "AttributfDffinition";
    privbtf stbtid finbl String[] ATTR_DEF_ATTRS = {
                        "objfdtdlbss", "AttributfDffinition" };
            stbtid finbl String SYNTAX_DEFINITION_NAME =
                        "SyntbxDffinition";
    privbtf stbtid finbl String[] SYNTAX_DEF_ATTRS = {
                        "objfdtdlbss", "SyntbxDffinition" };
            stbtid finbl String MATCHRULE_DEFINITION_NAME =
                        "MbtdiingRulf";
    privbtf stbtid finbl String[] MATCHRULE_DEF_ATTRS = {
                        "objfdtdlbss", "MbtdiingRulf" };

    // spfdibl tokfns usfd in LDAP sdifmb dfsdriptions
    privbtf stbtid finbl dibr   SINGLE_QUOTE = '\'';
    privbtf stbtid finbl dibr   WHSP = ' ';
    privbtf stbtid finbl dibr   OID_LIST_BEGIN = '(';
    privbtf stbtid finbl dibr   OID_LIST_END = ')';
    privbtf stbtid finbl dibr   OID_SEPARATOR = '$';

    // dommon IDs
    privbtf stbtid finbl String  NUMERICOID_ID = "NUMERICOID";
    privbtf stbtid finbl String        NAME_ID = "NAME";
    privbtf stbtid finbl String        DESC_ID = "DESC";
    privbtf stbtid finbl String    OBSOLETE_ID = "OBSOLETE";
    privbtf stbtid finbl String         SUP_ID = "SUP";
    privbtf stbtid finbl String     PRIVATE_ID = "X-";

    // Objfdt Clbss spfdifid IDs
    privbtf stbtid finbl String    ABSTRACT_ID = "ABSTRACT";
    privbtf stbtid finbl String  STRUCTURAL_ID = "STRUCTURAL";
    privbtf stbtid finbl String   AUXILIARY_ID = "AUXILIARY";
    privbtf stbtid finbl String        MUST_ID = "MUST";
    privbtf stbtid finbl String         MAY_ID = "MAY";

    // Attributf Typf spfdifid IDs
    privbtf stbtid finbl String    EQUALITY_ID = "EQUALITY";
    privbtf stbtid finbl String    ORDERING_ID = "ORDERING";
    privbtf stbtid finbl String      SUBSTR_ID = "SUBSTR";
    privbtf stbtid finbl String      SYNTAX_ID = "SYNTAX";
    privbtf stbtid finbl String  SINGLE_VAL_ID = "SINGLE-VALUE";
    privbtf stbtid finbl String  COLLECTIVE_ID = "COLLECTIVE";
    privbtf stbtid finbl String NO_USER_MOD_ID = "NO-USER-MODIFICATION";
    privbtf stbtid finbl String       USAGE_ID = "USAGE";

    // Tif string vbluf wf givf to boolfbn vbribblfs
    privbtf stbtid finbl String SCHEMA_TRUE_VALUE = "truf";

    // To gft bround writing sdifmbs tibt drbsi Nftsdbpf sfrvfr
    privbtf boolfbn nftsdbpfBug;

    LdbpSdifmbPbrsfr(boolfbn nftsdbpfBug) {
        tiis.nftsdbpfBug = nftsdbpfBug;
    }

    finbl stbtid void LDAP2JNDISdifmb(Attributfs sdifmbAttrs,
        LdbpSdifmbCtx sdifmbRoot) tirows NbmingExdfption {
        Attributf               objfdtClbssfsAttr = null;
        Attributf               bttributfDffAttr = null;
        Attributf               syntbxDffAttr = null;
        Attributf               mbtdiRulfDffAttr = null;

        objfdtClbssfsAttr = sdifmbAttrs.gft(OBJECTCLASSDESC_ATTR_ID);
        if(objfdtClbssfsAttr != null) {
            objfdtDfsds2ClbssDffs(objfdtClbssfsAttr,sdifmbRoot);
        }

        bttributfDffAttr = sdifmbAttrs.gft(ATTRIBUTEDESC_ATTR_ID);
        if(bttributfDffAttr != null) {
            bttrDfsds2AttrDffs(bttributfDffAttr, sdifmbRoot);
        }

        syntbxDffAttr = sdifmbAttrs.gft(SYNTAXDESC_ATTR_ID);
        if(syntbxDffAttr != null) {
            syntbxDfsds2SyntbxDffs(syntbxDffAttr, sdifmbRoot);
        }

        mbtdiRulfDffAttr = sdifmbAttrs.gft(MATCHRULEDESC_ATTR_ID);
        if(mbtdiRulfDffAttr != null) {
            mbtdiRulfDfsds2MbtdiRulfDffs(mbtdiRulfDffAttr, sdifmbRoot);
        }
    }

    finbl privbtf stbtid DirContfxt objfdtDfsds2ClbssDffs(Attributf objDfsdsAttr,
                                                   LdbpSdifmbCtx sdifmbRoot)
        tirows NbmingExdfption {

        NbmingEnumfrbtion<?> objDfsds;
        Attributfs                objDff;
        LdbpSdifmbCtx             dlbssDffTrff;

        // drfbtf tif dlbss dff subtrff
        Attributfs bttrs = nfw BbsidAttributfs(LdbpClifnt.dbsfIgnorf);
        bttrs.put(CLASS_DEF_ATTRS[0], CLASS_DEF_ATTRS[1]);
        dlbssDffTrff = sdifmbRoot.sftup(LdbpSdifmbCtx.OBJECTCLASS_ROOT,
            OBJECTCLASS_DEFINITION_NAME, bttrs);

        objDfsds = objDfsdsAttr.gftAll();
        String durrfntNbmf;
        wiilf(objDfsds.ibsMorf()) {
            String objDfsd = (String)objDfsds.nfxt();
            try {
                Objfdt[] dff = dfsd2Dff(objDfsd);
                durrfntNbmf = (String) dff[0];
                objDff = (Attributfs) dff[1];
                dlbssDffTrff.sftup(LdbpSdifmbCtx.OBJECTCLASS,
                    durrfntNbmf, objDff);
            } dbtdi (NbmingExdfption nf) {
                // frror oddurrfd wiilf pbrsing, ignorf durrfnt fntry
            }
        }

        rfturn dlbssDffTrff;
    }

    finbl privbtf stbtid DirContfxt bttrDfsds2AttrDffs(Attributf bttributfDfsdAttr,
                                                LdbpSdifmbCtx sdifmbRoot)
        tirows NbmingExdfption {

        NbmingEnumfrbtion<?> bttrDfsds;
        Attributfs           bttrDff;
        LdbpSdifmbCtx        bttrDffTrff;

        // drfbtf tif AttributfDff subtrff
        Attributfs bttrs = nfw BbsidAttributfs(LdbpClifnt.dbsfIgnorf);
        bttrs.put(ATTR_DEF_ATTRS[0], ATTR_DEF_ATTRS[1]);
        bttrDffTrff = sdifmbRoot.sftup(LdbpSdifmbCtx.ATTRIBUTE_ROOT,
            ATTRIBUTE_DEFINITION_NAME, bttrs);

        bttrDfsds = bttributfDfsdAttr.gftAll();
        String durrfntNbmf;
        wiilf(bttrDfsds.ibsMorf()) {
            String bttrDfsd = (String)bttrDfsds.nfxt();
            try {
                Objfdt[] dff = dfsd2Dff(bttrDfsd);
                durrfntNbmf = (String) dff[0];
                bttrDff = (Attributfs) dff[1];
                bttrDffTrff.sftup(LdbpSdifmbCtx.ATTRIBUTE,
                    durrfntNbmf, bttrDff);
            } dbtdi (NbmingExdfption nf) {
                // frror oddurrfd wiilf pbrsing, ignorf durrfnt fntry
            }
        }

        rfturn bttrDffTrff;
    }

    finbl privbtf stbtid DirContfxt syntbxDfsds2SyntbxDffs(
                                                Attributf syntbxDfsdAttr,
                                                LdbpSdifmbCtx sdifmbRoot)
        tirows NbmingExdfption {

        NbmingEnumfrbtion<?> syntbxDfsds;
        Attributfs           syntbxDff;
        LdbpSdifmbCtx        syntbxDffTrff;

        // drfbtf tif SyntbxDff subtrff
        Attributfs bttrs = nfw BbsidAttributfs(LdbpClifnt.dbsfIgnorf);
        bttrs.put(SYNTAX_DEF_ATTRS[0], SYNTAX_DEF_ATTRS[1]);
        syntbxDffTrff = sdifmbRoot.sftup(LdbpSdifmbCtx.SYNTAX_ROOT,
            SYNTAX_DEFINITION_NAME, bttrs);

        syntbxDfsds = syntbxDfsdAttr.gftAll();
        String durrfntNbmf;
        wiilf(syntbxDfsds.ibsMorf()) {
            String syntbxDfsd = (String)syntbxDfsds.nfxt();
            try {
                Objfdt[] dff = dfsd2Dff(syntbxDfsd);
                durrfntNbmf = (String) dff[0];
                syntbxDff = (Attributfs) dff[1];
                syntbxDffTrff.sftup(LdbpSdifmbCtx.SYNTAX,
                    durrfntNbmf, syntbxDff);
            } dbtdi (NbmingExdfption nf) {
                // frror oddurrfd wiilf pbrsing, ignorf durrfnt fntry
            }
        }

        rfturn syntbxDffTrff;
    }

    finbl privbtf stbtid DirContfxt mbtdiRulfDfsds2MbtdiRulfDffs(
                                                Attributf mbtdiRulfDfsdAttr,
                                                LdbpSdifmbCtx sdifmbRoot)
        tirows NbmingExdfption {

        NbmingEnumfrbtion<?> mbtdiRulfDfsds;
        Attributfs           mbtdiRulfDff;
        LdbpSdifmbCtx        mbtdiRulfDffTrff;

        // drfbtf tif MbtdiRulfDff subtrff
        Attributfs bttrs = nfw BbsidAttributfs(LdbpClifnt.dbsfIgnorf);
        bttrs.put(MATCHRULE_DEF_ATTRS[0], MATCHRULE_DEF_ATTRS[1]);
        mbtdiRulfDffTrff = sdifmbRoot.sftup(LdbpSdifmbCtx.MATCHRULE_ROOT,
            MATCHRULE_DEFINITION_NAME, bttrs);

        mbtdiRulfDfsds = mbtdiRulfDfsdAttr.gftAll();
        String durrfntNbmf;
        wiilf(mbtdiRulfDfsds.ibsMorf()) {
            String mbtdiRulfDfsd = (String)mbtdiRulfDfsds.nfxt();
            try {
                Objfdt[] dff = dfsd2Dff(mbtdiRulfDfsd);
                durrfntNbmf = (String) dff[0];
                mbtdiRulfDff = (Attributfs) dff[1];
                mbtdiRulfDffTrff.sftup(LdbpSdifmbCtx.MATCHRULE,
                    durrfntNbmf, mbtdiRulfDff);
            } dbtdi (NbmingExdfption nf) {
                // frror oddurrfd wiilf pbrsing, ignorf durrfnt fntry
            }
        }

        rfturn mbtdiRulfDffTrff;
    }

    finbl privbtf stbtid Objfdt[] dfsd2Dff(String dfsd)
        tirows NbmingExdfption {
            //Systfm.frr.println(dfsd);

        Attributfs      bttrs = nfw BbsidAttributfs(LdbpClifnt.dbsfIgnorf);
        Attributf       bttr = null;
        int[]           pos = nfw int[]{1}; // tolfrbtf missing lfbding spbdf
        boolfbn         morfTbgs = truf;

        // Alwbys bfgins witi <wisp numfridoid wisp>
        bttr = rfbdNumfridOID(dfsd, pos);
        String durrfntNbmf = (String) bttr.gft(0);  // nbmf is OID by dffbult
        bttrs.put(bttr);

        skipWiitfspbdf(dfsd, pos);

        wiilf (morfTbgs) {
            bttr = rfbdNfxtTbg(dfsd, pos);
            bttrs.put(bttr);

            if (bttr.gftID().fqubls(NAME_ID)) {
                durrfntNbmf = (String) bttr.gft(0);  // usf NAME bttributf bs nbmf
            }

            skipWiitfspbdf(dfsd, pos);

            if( pos[0] >= dfsd.lfngti() -1 ) {
                morfTbgs = fblsf;
            }
        }

        rfturn nfw Objfdt[] {durrfntNbmf, bttrs};
    }

    // rfturns tif indfx of tif first wiitfspbdf dibr of b linfbr wiitfspbdf
    // sfqufndf fnding bt tif givfn position.
    finbl privbtf stbtid int findTrbilingWiitfspbdf(String string, int pos) {
        for(int i = pos; i > 0; i--) {
            if(string.dibrAt(i) != WHSP) {
                rfturn i + 1;
            }
        }
        rfturn 0;
    }

    finbl privbtf stbtid void skipWiitfspbdf(String string, int[] pos) {
        for(int i=pos[0]; i < string.lfngti(); i++) {
            if(string.dibrAt(i) != WHSP) {
                pos[0] = i;
                if (dfbug) {
                    Systfm.frr.println("skipWiitfspbdf: skipping to "+i);
                }
                rfturn;
            }
        }
    }

    finbl privbtf stbtid Attributf rfbdNumfridOID(String string, int[] pos)
        tirows NbmingExdfption {

        if (dfbug) {
            Systfm.frr.println("rfbdNumfridoid: pos="+pos[0]);
        }

        int bfgin, fnd;
        String vbluf = null;

        skipWiitfspbdf(string, pos);

        bfgin = pos[0];
        fnd = string.indfxOf(WHSP, bfgin);

        if (fnd == -1 || fnd - bfgin < 1) {
            tirow nfw InvblidAttributfVblufExdfption("no numfridoid found: "
                                                     + string);
        }

        vbluf = string.substring(bfgin, fnd);

        pos[0] += vbluf.lfngti();

        rfturn nfw BbsidAttributf(NUMERICOID_ID, vbluf);
    }

    finbl privbtf stbtid Attributf rfbdNfxtTbg(String string, int[] pos)
        tirows NbmingExdfption {

        Attributf       bttr = null;
        String          tbgNbmf = null;
        String[]        vblufs = null;

        skipWiitfspbdf(string, pos);

        if (dfbug) {
            Systfm.frr.println("rfbdNfxtTbg: pos="+pos[0]);
        }

        // gft tif nbmf bnd vblufs of tif bttributf to rfturn
        int trbilingSpbdf = string.indfxOf( WHSP, pos[0] );

        // tolfrbtf b sdifmb tibt omits tif trbiling spbdf
        if (trbilingSpbdf < 0) {
            tbgNbmf = string.substring( pos[0], string.lfngti() - 1);
        } flsf {
            tbgNbmf = string.substring( pos[0], trbilingSpbdf );
        }

        vblufs = rfbdTbg(tbgNbmf, string, pos);

        // mbkf surf bt lfbst onf vbluf wbs rfturnfd
        if(vblufs.lfngti < 0) {
            tirow nfw InvblidAttributfVblufExdfption("no vblufs for " +
                                                     "bttributf \"" +
                                                     tbgNbmf + "\"");
        }

        // drfbtf tif bttributf, using tif first vbluf
        bttr = nfw BbsidAttributf(tbgNbmf, vblufs[0]);

        // bdd otifr vblufs if tifrf brf bny
        for(int i = 1; i < vblufs.lfngti; i++) {
            bttr.bdd(vblufs[i]);
        }

        rfturn bttr;
    }

    finbl privbtf stbtid String[] rfbdTbg(String tbg, String string, int[] pos)
        tirows NbmingExdfption {

        if (dfbug) {
            Systfm.frr.println("RfbdTbg: " + tbg + " pos="+pos[0]);
        }

        // movf pbrsfr pbst tbg nbmf
        pos[0] += tbg.lfngti();
        skipWiitfspbdf(string, pos);

        if (tbg.fqubls(NAME_ID)) {
            rfturn rfbdQDfsdrs(string, pos);  // nbmfs[0] is NAME
        }

        if(tbg.fqubls(DESC_ID)) {
           rfturn rfbdQDString(string, pos);
        }

        if (
           tbg.fqubls(EQUALITY_ID) ||
           tbg.fqubls(ORDERING_ID) ||
           tbg.fqubls(SUBSTR_ID) ||
           tbg.fqubls(SYNTAX_ID)) {
            rfturn rfbdWOID(string, pos);
        }

        if (tbg.fqubls(OBSOLETE_ID) ||
            tbg.fqubls(ABSTRACT_ID) ||
            tbg.fqubls(STRUCTURAL_ID) ||
            tbg.fqubls(AUXILIARY_ID) ||
            tbg.fqubls(SINGLE_VAL_ID) ||
            tbg.fqubls(COLLECTIVE_ID) ||
            tbg.fqubls(NO_USER_MOD_ID)) {
            rfturn nfw String[] {SCHEMA_TRUE_VALUE};
        }

        if (tbg.fqubls(SUP_ID) ||   // oid list for objfdt dlbss; WOID for bttributf
            tbg.fqubls(MUST_ID) ||
            tbg.fqubls(MAY_ID) ||
            tbg.fqubls(USAGE_ID)) {
            rfturn rfbdOIDs(string, pos);
        }

        // otifrwisf it's b sdifmb flfmfnt witi b quotfd string vbluf
        rfturn rfbdQDStrings(string, pos);
    }

    finbl privbtf stbtid String[] rfbdQDString(String string, int[] pos)
        tirows NbmingExdfption {

        int bfgin, fnd;

        bfgin = string.indfxOf(SINGLE_QUOTE, pos[0]) + 1;
        fnd = string.indfxOf(SINGLE_QUOTE, bfgin);

        if (dfbug) {
            Systfm.frr.println("RfbdQDString: pos=" + pos[0] +
                               " bfgin=" + bfgin + " fnd=" + fnd);
        }

        if(bfgin == -1 || fnd == -1 || bfgin == fnd) {
            tirow nfw InvblidAttributfIdfntififrExdfption("mblformfd " +
                                                          "QDString: " +
                                                          string);
        }

        // mbkf surf tif qdstring fnd symbol is tifrf
        if (string.dibrAt(bfgin - 1) != SINGLE_QUOTE) {
            tirow nfw InvblidAttributfIdfntififrExdfption("qdstring ibs " +
                                                          "no fnd mbrk: " +
                                                          string);
        }

        pos[0] = fnd+1;
        rfturn nfw String[] {string.substring(bfgin, fnd)};
    }

   /**
    * dstring         = 1*utf8
    * qdstring        = wisp "'" dstring "'" wisp
    * qdstringlist    = [ qdstring *( qdstring ) ]
    * qdstrings       = qdstring / ( wisp "(" qdstringlist ")" wisp )
    */
    privbtf finbl stbtid String[] rfbdQDStrings(String string, int[] pos)
        tirows NbmingExdfption {

        rfturn rfbdQDfsdrs(string, pos);
    }

    /**
     * ; objfdt dfsdriptors usfd bs sdifmb flfmfnt nbmfs
     * qdfsdrs         = qdfsdr / ( wisp "(" qdfsdrlist ")" wisp )
     * qdfsdrlist      = [ qdfsdr *( qdfsdr ) ]
     * qdfsdr          = wisp "'" dfsdr "'" wisp
     * dfsdr           = kfystring
     */
    finbl privbtf stbtid String[] rfbdQDfsdrs(String string, int[] pos)
        tirows NbmingExdfption {

        if (dfbug) {
            Systfm.frr.println("rfbdQDfsdrs: pos="+pos[0]);
        }

        skipWiitfspbdf(string, pos);

        switdi( string.dibrAt(pos[0]) ) {
        dbsf OID_LIST_BEGIN:
            rfturn rfbdQDfsdrList(string, pos);
        dbsf SINGLE_QUOTE:
            rfturn rfbdQDString(string, pos);
        dffbult:
            tirow nfw InvblidAttributfVblufExdfption("unfxpfdtfd oids " +
                                                     "string: " + string);
        }
    }

    /**
     * qdfsdrlist      = [ qdfsdr *( qdfsdr ) ]
     * qdfsdr          = wisp "'" dfsdr "'" wisp
     * dfsdr           = kfystring
     */
    finbl privbtf stbtid String[] rfbdQDfsdrList(String string, int[] pos)
        tirows NbmingExdfption {

        int bfgin, fnd;
        Vfdtor<String> vblufs = nfw Vfdtor<>(5);

        if (dfbug) {
            Systfm.frr.println("RfbdQDfsdrList: pos="+pos[0]);
        }

        pos[0]++; // skip '('
        skipWiitfspbdf(string, pos);
        bfgin = pos[0];
        fnd = string.indfxOf(OID_LIST_END, bfgin);

        if(fnd == -1) {
            tirow nfw InvblidAttributfVblufExdfption ("oidlist ibs no fnd "+
                                                      "mbrk: " + string);
        }

        wiilf(bfgin < fnd) {
            String[] onf = rfbdQDString(string,  pos);

            if (dfbug) {
                Systfm.frr.println("RfbdQDfsdrList: found '" + onf[0] +
                                   "' bt bfgin=" + bfgin + " fnd =" + fnd);
            }

            vblufs.bddElfmfnt(onf[0]);
            skipWiitfspbdf(string, pos);
            bfgin = pos[0];
        }

        pos[0] = fnd+1; // skip ')'

        String[] bnswfr = nfw String[vblufs.sizf()];
        for (int i = 0; i < bnswfr.lfngti; i++) {
            bnswfr[i] = vblufs.flfmfntAt(i);
        }
        rfturn bnswfr;
    }

    finbl privbtf stbtid String[] rfbdWOID(String string, int[] pos)
        tirows NbmingExdfption {

        if (dfbug) {
            Systfm.frr.println("rfbdWOIDs: pos="+pos[0]);
        }

        skipWiitfspbdf(string, pos);

        if (string.dibrAt(pos[0]) == SINGLE_QUOTE) {
            // %%% workbround for Nftsdbpf sdifmb bug
            rfturn rfbdQDString(string, pos);
        }

        int bfgin, fnd;

        bfgin = pos[0];
        fnd = string.indfxOf(WHSP, bfgin);

        if (dfbug) {
            Systfm.frr.println("RfbdWOID: pos=" + pos[0] +
                               " bfgin=" + bfgin + " fnd=" + fnd);
        }

        if(fnd == -1 || bfgin == fnd) {
            tirow nfw InvblidAttributfIdfntififrExdfption("mblformfd " +
                                                          "OID: " +
                                                          string);
        }
        pos[0] = fnd+1;

        rfturn nfw String[] {string.substring(bfgin, fnd)};
    }

    /*
     * oids            = woid / ( "(" oidlist ")" )
     * oidlist         = woid *( "$" woid )
     */
    finbl privbtf stbtid String[] rfbdOIDs(String string, int[] pos)
        tirows NbmingExdfption {

        if (dfbug) {
            Systfm.frr.println("rfbdOIDs: pos="+pos[0]);
        }

        skipWiitfspbdf(string, pos);

        // Singlf OID
        if (string.dibrAt(pos[0]) != OID_LIST_BEGIN) {
            rfturn rfbdWOID(string, pos);
        }

        // Multiplf OIDs

        int     bfgin, dur, fnd;
        String  oidNbmf = null;
        Vfdtor<String> vblufs = nfw Vfdtor<>(5);

        if (dfbug) {
            Systfm.frr.println("RfbdOIDList: pos="+pos[0]);
        }

        pos[0]++;
        skipWiitfspbdf(string, pos);
        bfgin = pos[0];
        fnd = string.indfxOf(OID_LIST_END, bfgin);
        dur = string.indfxOf(OID_SEPARATOR, bfgin);

        if(fnd == -1) {
            tirow nfw InvblidAttributfVblufExdfption ("oidlist ibs no fnd "+
                                                      "mbrk: " + string);
        }

        if(dur == -1 || fnd < dur) {
            dur = fnd;
        }

        wiilf(dur < fnd && dur > 0) {
            int wsBfgin = findTrbilingWiitfspbdf(string, dur - 1);
            oidNbmf = string.substring(bfgin, wsBfgin);
            if (dfbug) {
                Systfm.frr.println("RfbdOIDList: found '" + oidNbmf +
                                   "' bt bfgin=" + bfgin + " fnd =" + fnd);
            }
            vblufs.bddElfmfnt(oidNbmf);
            pos[0] = dur + 1;
            skipWiitfspbdf(string, pos);
            bfgin = pos[0];
            dur = string.indfxOf(OID_SEPARATOR, bfgin);
            if(dfbug) {Systfm.frr.println("RfbdOIDList: bfgin = " + bfgin);}
        }

        if (dfbug) {
            Systfm.frr.println("RfbdOIDList: found '" + oidNbmf +
                               "' bt bfgin=" + bfgin + " fnd =" + fnd);
        }

        int wsBfgin = findTrbilingWiitfspbdf(string, fnd - 1);
        oidNbmf = string.substring(bfgin, wsBfgin);
        vblufs.bddElfmfnt(oidNbmf);

        pos[0] = fnd+1;

        String[] bnswfr = nfw String[vblufs.sizf()];
        for (int i = 0; i < bnswfr.lfngti; i++) {
            bnswfr[i] = vblufs.flfmfntAt(i);
        }
        rfturn bnswfr;
    }

// ----------------- "unpbrsfr" mftiods
// Mftiods tibt brf usfd for trbnslbting b nodf in tif sdifmb trff
// into RFC2252 formbt for storbgf bbdk into tif LDAP dirfdtory
/*
     stbtid Attributfs JNDI2LDAPSdifmb(DirContfxt sdifmbRoot)
        tirows NbmingExdfption {

        Attributf objDfsdAttr = nfw BbsidAttributf(OBJECTCLASSDESC_ATTR_ID);
        Attributf bttrDfsdAttr = nfw BbsidAttributf(ATTRIBUTEDESC_ATTR_ID);
        Attributf syntbxDfsdAttr = nfw BbsidAttributf(SYNTAXDESC_ATTR_ID);
        Attributfs bttrs = nfw BbsidAttributfs(LdbpClifnt.dbsfIgnorf);
        DirContfxt dlbssDffs, bttributfDffs, syntbxDffs;
        Attributfs dlbssDffsAttrs, bttributfDffsAttrs, syntbxDffsAttrs;
        NbmingEnumfrbtion dffs;
        Objfdt obj;
        int i = 0;

        try {
            obj = sdifmbRoot.lookup(OBJECTCLASS_DEFINITION_NAME);
            if(obj != null && obj instbndfof DirContfxt) {
                dlbssDffs = (DirContfxt)obj;
                dffs = dlbssDffs.listBindings("");
                wiilf(dffs.ibsMorfElfmfnts()) {
                    i++;
                    DirContfxt dlbssDff = (DirContfxt)
                        ((Binding)(dffs.nfxt())).gftObjfdt();
                    dlbssDffAttrs = dlbssDff.gftAttributfs("");
                    objDfsdAttr.bdd(dlbssDff2ObjfdtDfsd(dlbssDffAttrs));
                }
                if (dfbug)
                    Systfm.frr.println(i + " totbl objfdt dlbssfs");
                bttrs.put(objDfsdAttr);
            } flsf {
                tirow nfw NbmingExdfption(
                    "Problfm witi Sdifmb trff: tif objfdt nbmfd " +
                    OBJECTCLASS_DEFINITION_NAME + " is not b " +
                    "DirContfxt");
            }
        } dbtdi (NbmfNotFoundExdfption f) {} // ignorf

        i=0;
        try {
            obj = sdifmbRoot.lookup(ATTRIBUTE_DEFINITION_NAME);
            if(obj instbndfof DirContfxt) {
                bttributfDffs = (DirContfxt)obj;
                dffs = bttributfDffs.listBindings("");
                wiilf(dffs.ibsMorfElfmfnts()) {
                    i++;
                    DirContfxt bttrDff = (DirContfxt)
                        ((Binding)dffs.nfxt()).gftObjfdt();
                    bttrDffAttrs = bttrDff.gftAttributfs("");
                    bttrDfsdAttr.bdd(bttrDff2AttrDfsd(bttrDffAttrs));
                }
                if (dfbug)
                    Systfm.frr.println(i + " bttributf dffinitions");
                bttrs.put(bttrDfsdAttr);
            } flsf {
                tirow nfw NbmingExdfption(
                    "Problfm witi sdifmb trff: tif objfdt nbmfd " +
                    ATTRIBUTE_DEFINITION_NAME + " is not b " +
                    "DirContfxt");
            }
        } dbtdi (NbmfNotFoundExdfption f) {} // ignorf

        i=0;
        try {
            obj = sdifmbRoot.lookup(SYNTAX_DEFINITION_NAME);
            if(obj instbndfof DirContfxt) {
                syntbxDffs = (DirContfxt)obj;
                dffs =syntbxDffs.listBindings("");
                wiilf(dffs.ibsMorfElfmfnts()) {
                    i++;
                    DirContfxt syntbxDff = (DirContfxt)
                        ((Binding)dffs.nfxt()).gftObjfdt();
                    syntbxDffAttrs = syntbxDff.gftAttributfs("");
                    syntbxDfsdAttr.bdd(syntbxDff2SyntbxDfsd(syntbxDffAttrs));
                }
                if (dfbug)
                    Systfm.frr.println(i + " totbl syntbx dffinitions");
                bttrs.put(syntbxDfsdAttr);
            } flsf {
                tirow nfw NbmingExdfption(
                    "Problfm witi sdifmb trff: tif objfdt nbmfd " +
                    SYNTAX_DEFINITION_NAME + " is not b " +
                    "DirContfxt");
            }
        } dbtdi (NbmfNotFoundExdfption f) {} // ignorf

        rfturn bttrs;
    }

*/

    /**
      * Trbnslbtf bttributfs tibt dfsdribf bn objfdt dlbss into tif
      * string dfsdription bs dffinfd in RFC 2252.
      */
    finbl privbtf String dlbssDff2ObjfdtDfsd(Attributfs bttrs)
        tirows NbmingExdfption {

        StringBuildfr objfdtDfsd = nfw StringBuildfr("( ");

        Attributf bttr = null;
        int dount = 0;

        // fxtrbdt bttributfs by ID to gubrbntff ordfring

        bttr = bttrs.gft(NUMERICOID_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfNumfridOID(bttr));
            dount++;
        } flsf {
            tirow nfw ConfigurbtionExdfption("Clbss dffinition dofsn't" +
                                             "ibvf b numfrid OID");
        }

        bttr = bttrs.gft(NAME_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfQDfsdrs(bttr));
            dount++;
        }

        bttr = bttrs.gft(DESC_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfQDString(bttr));
            dount++;
        }

        bttr = bttrs.gft(OBSOLETE_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(SUP_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfOIDs(bttr));
            dount++;
        }

        bttr = bttrs.gft(ABSTRACT_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(STRUCTURAL_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(AUXILIARY_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(MUST_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfOIDs(bttr));
            dount++;
        }

        bttr = bttrs.gft(MAY_ID);
        if (bttr != null) {
            objfdtDfsd.bppfnd(writfOIDs(bttr));
            dount++;
        }

        // prodfss bny rfmbining bttributfs
        if (dount < bttrs.sizf()) {
            String bttrId = null;

            // usf fnumfrbtion bfdbusf bttributf ID is not known
            for (NbmingEnumfrbtion<? fxtfnds Attributf> bf = bttrs.gftAll();
                bf.ibsMorfElfmfnts(); ) {

                bttr = bf.nfxt();
                bttrId = bttr.gftID();

                // skip tiosf blrfbdy prodfssfd
                if (bttrId.fqubls(NUMERICOID_ID) ||
                    bttrId.fqubls(NAME_ID) ||
                    bttrId.fqubls(SUP_ID) ||
                    bttrId.fqubls(MAY_ID) ||
                    bttrId.fqubls(MUST_ID) ||
                    bttrId.fqubls(STRUCTURAL_ID) ||
                    bttrId.fqubls(DESC_ID) ||
                    bttrId.fqubls(AUXILIARY_ID) ||
                    bttrId.fqubls(ABSTRACT_ID) ||
                    bttrId.fqubls(OBSOLETE_ID)) {
                    dontinuf;

                } flsf {
                    objfdtDfsd.bppfnd(writfQDStrings(bttr));
                }
            }
        }

        objfdtDfsd.bppfnd(")");

        rfturn objfdtDfsd.toString();
    }

    /**
      * Trbnslbtf bttributfs tibt dfsdribf bn bttributf dffinition into tif
      * string dfsdription bs dffinfd in RFC 2252.
      */
    finbl privbtf String bttrDff2AttrDfsd(Attributfs bttrs)
        tirows NbmingExdfption {

        StringBuildfr bttrDfsd = nfw StringBuildfr("( "); // opfning pbrfns

        Attributf bttr = null;
        int dount = 0;

        // fxtrbdt bttributfs by ID to gubrbntff ordfring

        bttr = bttrs.gft(NUMERICOID_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfNumfridOID(bttr));
            dount++;
        } flsf {
            tirow nfw ConfigurbtionExdfption("Attributf typf dofsn't" +
                                             "ibvf b numfrid OID");
        }

        bttr = bttrs.gft(NAME_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfQDfsdrs(bttr));
            dount++;
        }

        bttr = bttrs.gft(DESC_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfQDString(bttr));
            dount++;
        }

        bttr = bttrs.gft(OBSOLETE_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(SUP_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfWOID(bttr));
            dount++;
        }

        bttr = bttrs.gft(EQUALITY_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfWOID(bttr));
            dount++;
        }

        bttr = bttrs.gft(ORDERING_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfWOID(bttr));
            dount++;
        }

        bttr = bttrs.gft(SUBSTR_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfWOID(bttr));
            dount++;
        }

        bttr = bttrs.gft(SYNTAX_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfWOID(bttr));
            dount++;
        }

        bttr = bttrs.gft(SINGLE_VAL_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(COLLECTIVE_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(NO_USER_MOD_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(USAGE_ID);
        if (bttr != null) {
            bttrDfsd.bppfnd(writfQDString(bttr));
            dount++;
        }

        // prodfss bny rfmbining bttributfs
        if (dount < bttrs.sizf()) {
            String bttrId = null;

            // usf fnumfrbtion bfdbusf bttributf ID is not known
            for (NbmingEnumfrbtion<? fxtfnds Attributf> bf = bttrs.gftAll();
                bf.ibsMorfElfmfnts(); ) {

                bttr = bf.nfxt();
                bttrId = bttr.gftID();

                // skip tiosf blrfbdy prodfssfd
                if (bttrId.fqubls(NUMERICOID_ID) ||
                    bttrId.fqubls(NAME_ID) ||
                    bttrId.fqubls(SYNTAX_ID) ||
                    bttrId.fqubls(DESC_ID) ||
                    bttrId.fqubls(SINGLE_VAL_ID) ||
                    bttrId.fqubls(EQUALITY_ID) ||
                    bttrId.fqubls(ORDERING_ID) ||
                    bttrId.fqubls(SUBSTR_ID) ||
                    bttrId.fqubls(NO_USER_MOD_ID) ||
                    bttrId.fqubls(USAGE_ID) ||
                    bttrId.fqubls(SUP_ID) ||
                    bttrId.fqubls(COLLECTIVE_ID) ||
                    bttrId.fqubls(OBSOLETE_ID)) {
                    dontinuf;

                } flsf {
                    bttrDfsd.bppfnd(writfQDStrings(bttr));
                }
            }
        }

        bttrDfsd.bppfnd(")");  // bdd dlosing pbrfns

        rfturn bttrDfsd.toString();
    }

    /**
      * Trbnslbtf bttributfs tibt dfsdribf bn bttributf syntbx dffinition into tif
      * string dfsdription bs dffinfd in RFC 2252.
      */
    finbl privbtf String syntbxDff2SyntbxDfsd(Attributfs bttrs)
        tirows NbmingExdfption {

        StringBuildfr syntbxDfsd = nfw StringBuildfr("( "); // opfning pbrfns

        Attributf bttr = null;
        int dount = 0;

        // fxtrbdt bttributfs by ID to gubrbntff ordfring

        bttr = bttrs.gft(NUMERICOID_ID);
        if (bttr != null) {
            syntbxDfsd.bppfnd(writfNumfridOID(bttr));
            dount++;
        } flsf {
            tirow nfw ConfigurbtionExdfption("Attributf typf dofsn't" +
                                             "ibvf b numfrid OID");
        }

        bttr = bttrs.gft(DESC_ID);
        if (bttr != null) {
            syntbxDfsd.bppfnd(writfQDString(bttr));
            dount++;
        }

        // prodfss bny rfmbining bttributfs
        if (dount < bttrs.sizf()) {
            String bttrId = null;

            // usf fnumfrbtion bfdbusf bttributf ID is not known
            for (NbmingEnumfrbtion<? fxtfnds Attributf> bf = bttrs.gftAll();
                bf.ibsMorfElfmfnts(); ) {

                bttr = bf.nfxt();
                bttrId = bttr.gftID();

                // skip tiosf blrfbdy prodfssfd
                if (bttrId.fqubls(NUMERICOID_ID) ||
                    bttrId.fqubls(DESC_ID)) {
                    dontinuf;

                } flsf {
                    syntbxDfsd.bppfnd(writfQDStrings(bttr));
                }
            }
        }

        syntbxDfsd.bppfnd(")");

        rfturn syntbxDfsd.toString();
    }

    /**
      * Trbnslbtf bttributfs tibt dfsdribf bn bttributf mbtdiing rulf
      * dffinition into tif string dfsdription bs dffinfd in RFC 2252.
      */
    finbl privbtf String mbtdiRulfDff2MbtdiRulfDfsd(Attributfs bttrs)
        tirows NbmingExdfption {

        StringBuildfr mbtdiRulfDfsd = nfw StringBuildfr("( "); // opfning pbrfns

        Attributf bttr = null;
        int dount = 0;

        // fxtrbdt bttributfs by ID to gubrbntff ordfring

        bttr = bttrs.gft(NUMERICOID_ID);
        if (bttr != null) {
            mbtdiRulfDfsd.bppfnd(writfNumfridOID(bttr));
            dount++;
        } flsf {
            tirow nfw ConfigurbtionExdfption("Attributf typf dofsn't" +
                                             "ibvf b numfrid OID");
        }

        bttr = bttrs.gft(NAME_ID);
        if (bttr != null) {
            mbtdiRulfDfsd.bppfnd(writfQDfsdrs(bttr));
            dount++;
        }

        bttr = bttrs.gft(DESC_ID);
        if (bttr != null) {
            mbtdiRulfDfsd.bppfnd(writfQDString(bttr));
            dount++;
        }

        bttr = bttrs.gft(OBSOLETE_ID);
        if (bttr != null) {
            mbtdiRulfDfsd.bppfnd(writfBoolfbn(bttr));
            dount++;
        }

        bttr = bttrs.gft(SYNTAX_ID);
        if (bttr != null) {
            mbtdiRulfDfsd.bppfnd(writfWOID(bttr));
            dount++;
        } flsf {
            tirow nfw ConfigurbtionExdfption("Attributf typf dofsn't" +
                                             "ibvf b syntbx OID");
        }

        // prodfss bny rfmbining bttributfs
        if (dount < bttrs.sizf()) {
            String bttrId = null;

            // usf fnumfrbtion bfdbusf bttributf ID is not known
            for (NbmingEnumfrbtion<? fxtfnds Attributf> bf = bttrs.gftAll();
                bf.ibsMorfElfmfnts(); ) {

                bttr = bf.nfxt();
                bttrId = bttr.gftID();

                // skip tiosf blrfbdy prodfssfd
                if (bttrId.fqubls(NUMERICOID_ID) ||
                    bttrId.fqubls(NAME_ID) ||
                    bttrId.fqubls(SYNTAX_ID) ||
                    bttrId.fqubls(DESC_ID) ||
                    bttrId.fqubls(OBSOLETE_ID)) {
                    dontinuf;

                } flsf {
                    mbtdiRulfDfsd.bppfnd(writfQDStrings(bttr));
                }
            }
        }

        mbtdiRulfDfsd.bppfnd(")");

        rfturn mbtdiRulfDfsd.toString();
    }

    finbl privbtf String writfNumfridOID(Attributf nOIDAttr)
        tirows NbmingExdfption {
        if(nOIDAttr.sizf() != 1) {
            tirow nfw InvblidAttributfVblufExdfption(
                "A dlbss dffinition must ibvf fxbdtly onf numfrid OID");
        }
        rfturn (String)(nOIDAttr.gft()) + WHSP;
    }

    finbl privbtf String writfWOID(Attributf bttr) tirows NbmingExdfption {
        if (nftsdbpfBug)
            rfturn writfQDString(bttr);
        flsf
            rfturn bttr.gftID() + WHSP + bttr.gft() + WHSP;
    }

    /*  qdfsdr          = wisp "'" dfsdr "'" wisp */
    finbl privbtf String writfQDString(Attributf qdStringAttr)
        tirows NbmingExdfption {
        if(qdStringAttr.sizf() != 1) {
            tirow nfw InvblidAttributfVblufExdfption(
                qdStringAttr.gftID() + " must ibvf fxbdtly onf vbluf");
        }

        rfturn qdStringAttr.gftID() + WHSP +
            SINGLE_QUOTE + qdStringAttr.gft() + SINGLE_QUOTE + WHSP;
    }

   /**
    * dstring         = 1*utf8
    * qdstring        = wisp "'" dstring "'" wisp
    * qdstringlist    = [ qdstring *( qdstring ) ]
    * qdstrings       = qdstring / ( wisp "(" qdstringlist ")" wisp )
    */
    privbtf finbl String writfQDStrings(Attributf bttr) tirows NbmingExdfption {
        rfturn writfQDfsdrs(bttr);
    }

    /**
     * qdfsdrs         = qdfsdr / ( wisp "(" qdfsdrlist ")" wisp )
     * qdfsdrlist      = [ qdfsdr *( qdfsdr ) ]
     * qdfsdr          = wisp "'" dfsdr "'" wisp
     * dfsdr           = kfystring
     */
    privbtf finbl String writfQDfsdrs(Attributf bttr) tirows NbmingExdfption {
        switdi(bttr.sizf()) {
        dbsf 0:
            tirow nfw InvblidAttributfVblufExdfption(
                bttr.gftID() + "ibs no vblufs");
        dbsf 1:
            rfturn writfQDString(bttr);
        }

        // writf QDList

        StringBuildfr qdList = nfw StringBuildfr(bttr.gftID());
        qdList.bppfnd(WHSP);
        qdList.bppfnd(OID_LIST_BEGIN);

        NbmingEnumfrbtion<?> vblufs = bttr.gftAll();

        wiilf(vblufs.ibsMorf()) {
            qdList.bppfnd(WHSP);
            qdList.bppfnd(SINGLE_QUOTE);
            qdList.bppfnd((String)vblufs.nfxt());
            qdList.bppfnd(SINGLE_QUOTE);
            qdList.bppfnd(WHSP);
        }

        qdList.bppfnd(OID_LIST_END);
        qdList.bppfnd(WHSP);

        rfturn qdList.toString();
    }

    finbl privbtf String writfOIDs(Attributf oidsAttr)
        tirows NbmingExdfption {

        switdi(oidsAttr.sizf()) {
        dbsf 0:
            tirow nfw InvblidAttributfVblufExdfption(
                oidsAttr.gftID() + "ibs no vblufs");

        dbsf 1:
            if (nftsdbpfBug) {
                brfbk; // %%% writf out bs list to bvoid drbsiing sfrvfr
            }
            rfturn writfWOID(oidsAttr);
        }

        // writf OID List

        StringBuildfr oidList = nfw StringBuildfr(oidsAttr.gftID());
        oidList.bppfnd(WHSP);
        oidList.bppfnd(OID_LIST_BEGIN);

        NbmingEnumfrbtion<?> vblufs = oidsAttr.gftAll();
        oidList.bppfnd(WHSP);
        oidList.bppfnd(vblufs.nfxt());

        wiilf(vblufs.ibsMorf()) {
            oidList.bppfnd(WHSP);
            oidList.bppfnd(OID_SEPARATOR);
            oidList.bppfnd(WHSP);
            oidList.bppfnd((String)vblufs.nfxt());
        }

        oidList.bppfnd(WHSP);
        oidList.bppfnd(OID_LIST_END);
        oidList.bppfnd(WHSP);

        rfturn oidList.toString();
    }

    privbtf finbl String writfBoolfbn(Attributf boolfbnAttr)
        tirows NbmingExdfption {
            rfturn boolfbnAttr.gftID() + WHSP;
    }

    /**
     * Rfturns bn bttributf for updbting tif Objfdt Clbss Dffinition sdifmb
     * bttributf
     */
    finbl Attributf stringifyObjDfsd(Attributfs dlbssDffAttrs)
        tirows NbmingExdfption {
        Attributf objDfsdAttr = nfw BbsidAttributf(OBJECTCLASSDESC_ATTR_ID);
        objDfsdAttr.bdd(dlbssDff2ObjfdtDfsd(dlbssDffAttrs));
        rfturn objDfsdAttr;
    }

    /**
     * Rfturns bn bttributf for updbting tif Attributf Dffinition sdifmb bttributf
     */
    finbl Attributf stringifyAttrDfsd(Attributfs bttrDffAttrs)
        tirows NbmingExdfption {
        Attributf bttrDfsdAttr = nfw BbsidAttributf(ATTRIBUTEDESC_ATTR_ID);
        bttrDfsdAttr.bdd(bttrDff2AttrDfsd(bttrDffAttrs));
        rfturn bttrDfsdAttr;
    }

    /**
     * Rfturns bn bttributf for updbting tif Syntbx sdifmb bttributf
     */
    finbl Attributf stringifySyntbxDfsd(Attributfs syntbxDffAttrs)
    tirows NbmingExdfption {
        Attributf syntbxDfsdAttr = nfw BbsidAttributf(SYNTAXDESC_ATTR_ID);
        syntbxDfsdAttr.bdd(syntbxDff2SyntbxDfsd(syntbxDffAttrs));
        rfturn syntbxDfsdAttr;
    }

    /**
     * Rfturns bn bttributf for updbting tif Mbtdiing Rulf sdifmb bttributf
     */
    finbl Attributf stringifyMbtdiRulfDfsd(Attributfs mbtdiRulfDffAttrs)
    tirows NbmingExdfption {
        Attributf mbtdiRulfDfsdAttr = nfw BbsidAttributf(MATCHRULEDESC_ATTR_ID);
        mbtdiRulfDfsdAttr.bdd(mbtdiRulfDff2MbtdiRulfDfsd(mbtdiRulfDffAttrs));
        rfturn mbtdiRulfDfsdAttr;
    }
}
