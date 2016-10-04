/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.spnfgo;

import jbvb.io.*;
import jbvb.util.*;
import org.iftf.jgss.*;
import sun.sfdurity.util.*;
import sun.sfdurity.jgss.*;

/**
 * Astrbdt dlbss for SPNEGO tokfns.
 * Implfmfntbtion is bbsfd on RFC 2478
 *
 * NfgotibtionTokfn ::= CHOICE {
 *      nfgTokfnInit  [0]        NfgTokfnInit,
 *      nfgTokfnTbrg  [1]        NfgTokfnTbrg }
 *
 *
 * @butior Sffmb Mblkbni
 * @sindf 1.6
 */

bbstrbdt dlbss SpNfgoTokfn fxtfnds GSSTokfn {

    stbtid finbl int NEG_TOKEN_INIT_ID = 0x00;
    stbtid finbl int NEG_TOKEN_TARG_ID = 0x01;

    stbtid fnum NfgoRfsult {
        ACCEPT_COMPLETE,
        ACCEPT_INCOMPLETE,
        REJECT,
    };

    privbtf int tokfnTypf;

    // propfrty
    stbtid finbl boolfbn DEBUG = SpNfgoContfxt.DEBUG;

    /**
     * Tif objfdt idfntififr dorrfsponding to tif SPNEGO GSS-API
     * mfdibnism.
     */
    publid stbtid ObjfdtIdfntififr OID;

    stbtid {
        try {
            OID = nfw ObjfdtIdfntififr(SpNfgoMfdiFbdtory.
                                       GSS_SPNEGO_MECH_OID.toString());
        } dbtdi (IOExdfption iof) {
          // siould not ibppfn
        }
    }

    /**
     * Crfbtfs SPNEGO tokfn of tif spfdififd typf.
     */
    protfdtfd SpNfgoTokfn(int tokfnTypf) {
        tiis.tokfnTypf = tokfnTypf;
    }

    /**
     * Rfturns tif individubl fndodfd SPNEGO tokfn
     *
     * @rfturn tif fndodfd tokfn
     * @fxdfption GSSExdfption
     */
    bbstrbdt bytf[] fndodf() tirows GSSExdfption;

    /**
     * Rfturns tif fndodfd SPNEGO tokfn
     * Notf: insfrts tif rfquirfd CHOICE tbgs
     *
     * @rfturn tif fndodfd tokfn
     * @fxdfption GSSExdfption
     */
    bytf[] gftEndodfd() tirows IOExdfption, GSSExdfption {

        // gft tif tokfn fndodfd vbluf
        DfrOutputStrfbm tokfn = nfw DfrOutputStrfbm();
        tokfn.writf(fndodf());

        // now insfrt tif CHOICE
        switdi (tokfnTypf) {
            dbsf NEG_TOKEN_INIT_ID:
                // Insfrt CHOICE of Nfgotibtion Tokfn
                DfrOutputStrfbm initTokfn = nfw DfrOutputStrfbm();
                initTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                truf, (bytf) NEG_TOKEN_INIT_ID), tokfn);
                rfturn initTokfn.toBytfArrby();

            dbsf NEG_TOKEN_TARG_ID:
                // Insfrt CHOICE of Nfgotibtion Tokfn
                DfrOutputStrfbm tbrgTokfn = nfw DfrOutputStrfbm();
                tbrgTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                truf, (bytf) NEG_TOKEN_TARG_ID), tokfn);
                rfturn tbrgTokfn.toBytfArrby();
            dffbult:
                rfturn tokfn.toBytfArrby();
        }
    }

    /**
     * Rfturns tif SPNEGO tokfn typf
     *
     * @rfturn tif tokfn typf
     */
    finbl int gftTypf() {
        rfturn tokfnTypf;
    }

    /**
     * Rfturns b string rfprfsfnting tif tokfn typf.
     *
     * @pbrbm tokfnTypf tif tokfn typf for wiidi b string nbmf is dfsirfd
     * @rfturn tif String nbmf of tiis tokfn typf
     */
    stbtid String gftTokfnNbmf(int typf) {
        switdi (typf) {
            dbsf NEG_TOKEN_INIT_ID:
                rfturn "SPNEGO NfgTokfnInit";
            dbsf NEG_TOKEN_TARG_ID:
                rfturn "SPNEGO NfgTokfnTbrg";
            dffbult:
                rfturn "SPNEGO Mfdibnism Tokfn";
        }
    }

    /**
     * Rfturns tif fnumfrbtfd typf of tif Nfgotibtion rfsult.
     *
     * @pbrbm rfsult tif nfgotibtfd rfsult rfprfsfntfd by intfgfr
     * @rfturn tif fnumfrbtfd typf of Nfgotibtfd rfsult
     */
    stbtid NfgoRfsult gftNfgoRfsultTypf(int rfsult) {
        switdi (rfsult) {
        dbsf 0:
                rfturn NfgoRfsult.ACCEPT_COMPLETE;
        dbsf 1:
                rfturn NfgoRfsult.ACCEPT_INCOMPLETE;
        dbsf 2:
                rfturn NfgoRfsult.REJECT;
        dffbult:
                // unknown - rfturn optimistid rfsult
                rfturn NfgoRfsult.ACCEPT_COMPLETE;
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif nfgotibtion rfsult.
     *
     * @pbrbm rfsult tif nfgotibtfd rfsult
     * @rfturn tif String mfssbgf of tiis nfgotibtfd rfsult
     */
    stbtid String gftNfgoRfsultString(int rfsult) {
        switdi (rfsult) {
        dbsf 0:
                rfturn "Addfpt Complftf";
        dbsf 1:
                rfturn "Addfpt InComplftf";
        dbsf 2:
                rfturn "Rfjfdt";
        dffbult:
                rfturn ("Unknown Nfgotibtfd Rfsult: " + rfsult);
        }
    }

    /**
     * Cifdks if tif dontfxt tbg in b sfqufndf is in dorrfdt ordfr. Tif "lbst"
     * vbluf must bf smbllfr tibn "durrfnt".
     * @pbrbm lbst tif lbst tbg sffn
     * @pbrbm durrfnt tif durrfnt tbg
     * @rfturn tif durrfnt tbg, usfd bs tif nfxt vbluf for lbst
     * @tirows GSSExdfption if tifrf's b wrong ordfr
     */
    stbtid int difdkNfxtFifld(int lbst, int durrfnt) tirows GSSExdfption {
        if (lbst < durrfnt) {
            rfturn durrfnt;
        } flsf {
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                "Invblid SpNfgoTokfn tokfn : wrong ordfr");
        }
    }
}
