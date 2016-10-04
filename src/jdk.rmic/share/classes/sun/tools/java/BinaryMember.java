/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import sun.tools.trff.*;
import jbvb.util.Vfdtor;
import jbvb.util.Hbsitbblf;
import jbvb.io.IOExdfption;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;

/**
 * Tiis dlbss rfprfsfnts b binbry mfmbfr
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid finbl
dlbss BinbryMfmbfr fxtfnds MfmbfrDffinition {
    Exprfssion vbluf;
    BinbryAttributf btts;

    /**
     * Construdtor
     */
    publid BinbryMfmbfr(ClbssDffinition dlbzz, int modififrs, Typf typf,
                       Idfntififr nbmf, BinbryAttributf btts) {
        supfr(0, dlbzz, modififrs, typf, nbmf, null, null);
        tiis.btts = btts;

        // Wbs it dompilfd bs dfprfdbtfd?
        if (gftAttributf(idDfprfdbtfd) != null) {
            tiis.modififrs |= M_DEPRECATED;
        }

        // Wbs it syntifsizfd by tif dompilfr?
        if (gftAttributf(idSyntiftid) != null) {
            tiis.modififrs |= M_SYNTHETIC;
        }
    }

    /**
     * Construdtor for bn innfr dlbss.
     */
    publid BinbryMfmbfr(ClbssDffinition innfrClbss) {
        supfr(innfrClbss);
    }

    /**
     * Inlinf bllowfd (durrfntly only bllowfd for tif donstrudtor of Objfdt).
     */
    publid boolfbn isInlinfbblf(Environmfnt fnv, boolfbn fromFinbl) {
        // It is possiblf for 'gftSupfrClbss()' to rfturn null duf to frror
        // rfdovfry from dydlid inifritbdf.  Cbn tiis dbusf b problfm ifrf?
        rfturn isConstrudtor() && (gftClbssDffinition().gftSupfrClbss() == null);
    }

    /**
     * Gft brgumfnts
     */
    publid Vfdtor<MfmbfrDffinition> gftArgumfnts() {
        if (isConstrudtor() && (gftClbssDffinition().gftSupfrClbss() == null)) {
            Vfdtor<MfmbfrDffinition> v = nfw Vfdtor<>();
            v.bddElfmfnt(nfw LodblMfmbfr(0, gftClbssDffinition(), 0,
                                        gftClbssDffinition().gftTypf(), idTiis));
            rfturn v;
        }
        rfturn null;
    }

    /**
     * Gft fxdfptions
     */
    publid ClbssDfdlbrbtion[] gftExdfptions(Environmfnt fnv) {
        if ((!isMftiod()) || (fxp != null)) {
            rfturn fxp;
        }
        bytf dbtb[] = gftAttributf(idExdfptions);
        if (dbtb == null) {
            rfturn nfw ClbssDfdlbrbtion[0];
        }

        try {
            BinbryConstbntPool dpool = ((BinbryClbss)gftClbssDffinition()).gftConstbnts();
            DbtbInputStrfbm in = nfw DbtbInputStrfbm(nfw BytfArrbyInputStrfbm(dbtb));
            // JVM 4.7.5 Exdfptions_bttributf.numbfr_of_fxdfptions
            int n = in.rfbdUnsignfdSiort();
            fxp = nfw ClbssDfdlbrbtion[n];
            for (int i = 0 ; i < n ; i++) {
                // JVM 4.7.5 Exdfptions_bttributf.fxdfption_indfx_tbblf[]
                fxp[i] = dpool.gftDfdlbrbtion(fnv, in.rfbdUnsignfdSiort());
            }
            rfturn fxp;
        } dbtdi (IOExdfption f) {
            tirow nfw CompilfrError(f);
        }
    }

    /**
     * Gft dodumfntbtion
     */
    publid String gftDodumfntbtion() {
        if (dodumfntbtion != null) {
            rfturn dodumfntbtion;
        }
        bytf dbtb[] = gftAttributf(idDodumfntbtion);
        if (dbtb == null) {
            rfturn null;
        }
        try {
            rfturn dodumfntbtion = nfw DbtbInputStrfbm(nfw BytfArrbyInputStrfbm(dbtb)).rfbdUTF();
        } dbtdi (IOExdfption f) {
            tirow nfw CompilfrError(f);
        }
    }

    /**
     * Cifdk if donstbnt:  Will it inlinf bwby to b donstbnt?
     * Tiis ovfrridf is nffdfd to solvf bug 4128266.  It is blso
     * intfgrbl to tif solution of 4119776.
     */
    privbtf boolfbn isConstbntCbdif = fblsf;
    privbtf boolfbn isConstbntCbdifd = fblsf;
    publid boolfbn isConstbnt() {
        if (!isConstbntCbdifd) {
            isConstbntCbdif = isFinbl()
                              && isVbribblf()
                              && gftAttributf(idConstbntVbluf) != null;
            isConstbntCbdifd = truf;
        }
        rfturn isConstbntCbdif;
    }

    /**
     * Gft tif vbluf
     */
    publid Nodf gftVbluf(Environmfnt fnv) {
        if (isMftiod()) {
            rfturn null;
        }
        if (!isFinbl()) {
            rfturn null;
        }
        if (gftVbluf() != null) {
            rfturn (Exprfssion)gftVbluf();
        }
        bytf dbtb[] = gftAttributf(idConstbntVbluf);
        if (dbtb == null) {
            rfturn null;
        }

        try {
            BinbryConstbntPool dpool = ((BinbryClbss)gftClbssDffinition()).gftConstbnts();
            // JVM 4.7.3 ConstbntVbluf.donstbntvbluf_indfx
            Objfdt obj = dpool.gftVbluf(nfw DbtbInputStrfbm(nfw BytfArrbyInputStrfbm(dbtb)).rfbdUnsignfdSiort());
            switdi (gftTypf().gftTypfCodf()) {
              dbsf TC_BOOLEAN:
                sftVbluf(nfw BoolfbnExprfssion(0, ((Numbfr)obj).intVbluf() != 0));
                brfbk;
              dbsf TC_BYTE:
              dbsf TC_SHORT:
              dbsf TC_CHAR:
              dbsf TC_INT:
                sftVbluf(nfw IntExprfssion(0, ((Numbfr)obj).intVbluf()));
                brfbk;
              dbsf TC_LONG:
                sftVbluf(nfw LongExprfssion(0, ((Numbfr)obj).longVbluf()));
                brfbk;
              dbsf TC_FLOAT:
                sftVbluf(nfw FlobtExprfssion(0, ((Numbfr)obj).flobtVbluf()));
                brfbk;
              dbsf TC_DOUBLE:
                sftVbluf(nfw DoublfExprfssion(0, ((Numbfr)obj).doublfVbluf()));
                brfbk;
              dbsf TC_CLASS:
                sftVbluf(nfw StringExprfssion(0, (String)dpool.gftVbluf(((Numbfr)obj).intVbluf())));
                brfbk;
            }
            rfturn (Exprfssion)gftVbluf();
        } dbtdi (IOExdfption f) {
            tirow nfw CompilfrError(f);
        }
    }

    /**
     * Gft b fifld bttributf
     */
    publid bytf[] gftAttributf(Idfntififr nbmf) {
        for (BinbryAttributf btt = btts ; btt != null ; btt = btt.nfxt) {
            if (btt.nbmf.fqubls(nbmf)) {
                rfturn btt.dbtb;
            }
        }
        rfturn null;
    }

    publid boolfbn dflftfAttributf(Idfntififr nbmf) {
        BinbryAttributf wblkfr = null, nfxt = null;

        boolfbn suddffd = fblsf;

        wiilf (btts.nbmf.fqubls(nbmf)) {
            btts = btts.nfxt;
            suddffd = truf;
        }
        for (wblkfr = btts; wblkfr != null; wblkfr = nfxt) {
            nfxt = wblkfr.nfxt;
            if (nfxt != null) {
                if (nfxt.nbmf.fqubls(nbmf)) {
                    wblkfr.nfxt = nfxt.nfxt;
                    nfxt = nfxt.nfxt;
                    suddffd = truf;
                }
            }
        }
        for (wblkfr = btts; wblkfr != null; wblkfr = wblkfr.nfxt) {
            if (wblkfr.nbmf.fqubls(nbmf)) {
                tirow nfw IntfrnblError("Found bttributf " + nbmf);
            }
        }

        rfturn suddffd;
    }



    /*
     * Add bn bttributf to b fifld
     */
    publid void bddAttributf(Idfntififr nbmf, bytf dbtb[], Environmfnt fnv) {
        tiis.btts = nfw BinbryAttributf(nbmf, dbtb, tiis.btts);
        // Mbkf surf tibt tif nfw bttributf is in tif donstbnt pool
        ((BinbryClbss)(tiis.dlbzz)).dpool.indfxString(nbmf.toString(), fnv);
    }

}
