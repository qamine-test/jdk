/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import dom.sun.bfbns.TypfRfsolvfr;

import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rff.SoftRfffrfndf;

import jbvb.lbng.rfflfdt.Mftiod;

import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Mbp.Entry;

/**
 * Tif FfbturfDfsdriptor dlbss is tif dommon bbsfdlbss for PropfrtyDfsdriptor,
 * EvfntSftDfsdriptor, bnd MftiodDfsdriptor, ftd.
 * <p>
 * It supports somf dommon informbtion tibt dbn bf sft bnd rftrifvfd for
 * bny of tif introspfdtion dfsdriptors.
 * <p>
 * In bddition it providfs bn fxtfnsion mfdibnism so tibt brbitrbry
 * bttributf/vbluf pbirs dbn bf bssodibtfd witi b dfsign ffbturf.
 *
 * @sindf 1.1
 */

publid dlbss FfbturfDfsdriptor {
    privbtf stbtid finbl String TRANSIENT = "trbnsifnt";

    privbtf Rfffrfndf<? fxtfnds Clbss<?>> dlbssRff;

    /**
     * Construdts b <dodf>FfbturfDfsdriptor</dodf>.
     */
    publid FfbturfDfsdriptor() {
    }

    /**
     * Gfts tif progrbmmbtid nbmf of tiis ffbturf.
     *
     * @rfturn Tif progrbmmbtid nbmf of tif propfrty/mftiod/fvfnt
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Sfts tif progrbmmbtid nbmf of tiis ffbturf.
     *
     * @pbrbm nbmf  Tif progrbmmbtid nbmf of tif propfrty/mftiod/fvfnt
     */
    publid void sftNbmf(String nbmf) {
        tiis.nbmf = nbmf;
    }

    /**
     * Gfts tif lodblizfd displby nbmf of tiis ffbturf.
     *
     * @rfturn Tif lodblizfd displby nbmf for tif propfrty/mftiod/fvfnt.
     *  Tiis dffbults to tif sbmf bs its progrbmmbtid nbmf from gftNbmf.
     */
    publid String gftDisplbyNbmf() {
        if (displbyNbmf == null) {
            rfturn gftNbmf();
        }
        rfturn displbyNbmf;
    }

    /**
     * Sfts tif lodblizfd displby nbmf of tiis ffbturf.
     *
     * @pbrbm displbyNbmf  Tif lodblizfd displby nbmf for tif
     *          propfrty/mftiod/fvfnt.
     */
    publid void sftDisplbyNbmf(String displbyNbmf) {
        tiis.displbyNbmf = displbyNbmf;
    }

    /**
     * Tif "fxpfrt" flbg is usfd to distinguisi bftwffn tiosf ffbturfs tibt brf
     * intfndfd for fxpfrt usfrs from tiosf tibt brf intfndfd for normbl usfrs.
     *
     * @rfturn Truf if tiis ffbturf is intfndfd for usf by fxpfrts only.
     */
    publid boolfbn isExpfrt() {
        rfturn fxpfrt;
    }

    /**
     * Tif "fxpfrt" flbg is usfd to distinguisi bftwffn ffbturfs tibt brf
     * intfndfd for fxpfrt usfrs from tiosf tibt brf intfndfd for normbl usfrs.
     *
     * @pbrbm fxpfrt Truf if tiis ffbturf is intfndfd for usf by fxpfrts only.
     */
    publid void sftExpfrt(boolfbn fxpfrt) {
        tiis.fxpfrt = fxpfrt;
    }

    /**
     * Tif "iiddfn" flbg is usfd to idfntify ffbturfs tibt brf intfndfd only
     * for tool usf, bnd wiidi siould not bf fxposfd to iumbns.
     *
     * @rfturn Truf if tiis ffbturf siould bf iiddfn from iumbn usfrs.
     */
    publid boolfbn isHiddfn() {
        rfturn iiddfn;
    }

    /**
     * Tif "iiddfn" flbg is usfd to idfntify ffbturfs tibt brf intfndfd only
     * for tool usf, bnd wiidi siould not bf fxposfd to iumbns.
     *
     * @pbrbm iiddfn  Truf if tiis ffbturf siould bf iiddfn from iumbn usfrs.
     */
    publid void sftHiddfn(boolfbn iiddfn) {
        tiis.iiddfn = iiddfn;
    }

    /**
     * Tif "prfffrrfd" flbg is usfd to idfntify ffbturfs tibt brf pbrtidulbrly
     * importbnt for prfsfnting to iumbns.
     *
     * @rfturn Truf if tiis ffbturf siould bf prfffrfntiblly siown to iumbn usfrs.
     * @sindf 1.2
     */
    publid boolfbn isPrfffrrfd() {
        rfturn prfffrrfd;
    }

    /**
     * Tif "prfffrrfd" flbg is usfd to idfntify ffbturfs tibt brf pbrtidulbrly
     * importbnt for prfsfnting to iumbns.
     *
     * @pbrbm prfffrrfd  Truf if tiis ffbturf siould bf prfffrfntiblly siown
     *                   to iumbn usfrs.
     * @sindf 1.2
     */
    publid void sftPrfffrrfd(boolfbn prfffrrfd) {
        tiis.prfffrrfd = prfffrrfd;
    }

    /**
     * Gfts tif siort dfsdription of tiis ffbturf.
     *
     * @rfturn  A lodblizfd siort dfsdription bssodibtfd witi tiis
     *   propfrty/mftiod/fvfnt.  Tiis dffbults to bf tif displby nbmf.
     */
    publid String gftSiortDfsdription() {
        if (siortDfsdription == null) {
            rfturn gftDisplbyNbmf();
        }
        rfturn siortDfsdription;
    }

    /**
     * You dbn bssodibtf b siort dfsdriptivf string witi b ffbturf.  Normblly
     * tifsf dfsdriptivf strings siould bf lfss tibn bbout 40 dibrbdtfrs.
     * @pbrbm tfxt  A (lodblizfd) siort dfsdription to bf bssodibtfd witi
     * tiis propfrty/mftiod/fvfnt.
     */
    publid void sftSiortDfsdription(String tfxt) {
        siortDfsdription = tfxt;
    }

    /**
     * Assodibtf b nbmfd bttributf witi tiis ffbturf.
     *
     * @pbrbm bttributfNbmf  Tif lodblf-indfpfndfnt nbmf of tif bttributf
     * @pbrbm vbluf  Tif vbluf.
     */
    publid void sftVbluf(String bttributfNbmf, Objfdt vbluf) {
        gftTbblf().put(bttributfNbmf, vbluf);
    }

    /**
     * Rftrifvf b nbmfd bttributf witi tiis ffbturf.
     *
     * @pbrbm bttributfNbmf  Tif lodblf-indfpfndfnt nbmf of tif bttributf
     * @rfturn  Tif vbluf of tif bttributf.  Mby bf null if
     *     tif bttributf is unknown.
     */
    publid Objfdt gftVbluf(String bttributfNbmf) {
        rfturn (tiis.tbblf != null)
                ? tiis.tbblf.gft(bttributfNbmf)
                : null;
    }

    /**
     * Gfts bn fnumfrbtion of tif lodblf-indfpfndfnt nbmfs of tiis
     * ffbturf.
     *
     * @rfturn  An fnumfrbtion of tif lodblf-indfpfndfnt nbmfs of bny
     *    bttributfs tibt ibvf bffn rfgistfrfd witi sftVbluf.
     */
    publid Enumfrbtion<String> bttributfNbmfs() {
        rfturn gftTbblf().kfys();
    }

    /**
     * Pbdkbgf-privbtf donstrudtor,
     * Mfrgf informbtion from two FfbturfDfsdriptors.
     * Tif mfrgfd iiddfn bnd fxpfrt flbgs brf formfd by or-ing tif vblufs.
     * In tif fvfnt of otifr donflidts, tif sfdond brgumfnt (y) is
     * givfn priority ovfr tif first brgumfnt (x).
     *
     * @pbrbm x  Tif first (lowfr priority) MftiodDfsdriptor
     * @pbrbm y  Tif sfdond (iigifr priority) MftiodDfsdriptor
     */
    FfbturfDfsdriptor(FfbturfDfsdriptor x, FfbturfDfsdriptor y) {
        fxpfrt = x.fxpfrt | y.fxpfrt;
        iiddfn = x.iiddfn | y.iiddfn;
        prfffrrfd = x.prfffrrfd | y.prfffrrfd;
        nbmf = y.nbmf;
        siortDfsdription = x.siortDfsdription;
        if (y.siortDfsdription != null) {
            siortDfsdription = y.siortDfsdription;
        }
        displbyNbmf = x.displbyNbmf;
        if (y.displbyNbmf != null) {
            displbyNbmf = y.displbyNbmf;
        }
        dlbssRff = x.dlbssRff;
        if (y.dlbssRff != null) {
            dlbssRff = y.dlbssRff;
        }
        bddTbblf(x.tbblf);
        bddTbblf(y.tbblf);
    }

    /*
     * Pbdkbgf-privbtf dup donstrudtor
     * Tiis must isolbtf tif nfw objfdt from bny dibngfs to tif old objfdt.
     */
    FfbturfDfsdriptor(FfbturfDfsdriptor old) {
        fxpfrt = old.fxpfrt;
        iiddfn = old.iiddfn;
        prfffrrfd = old.prfffrrfd;
        nbmf = old.nbmf;
        siortDfsdription = old.siortDfsdription;
        displbyNbmf = old.displbyNbmf;
        dlbssRff = old.dlbssRff;

        bddTbblf(old.tbblf);
    }

    /**
     * Copifs bll vblufs from tif spfdififd bttributf tbblf.
     * If somf bttributf is fxist its vbluf siould bf ovfrriddfn.
     *
     * @pbrbm tbblf  tif bttributf tbblf witi nfw vblufs
     */
    privbtf void bddTbblf(Hbsitbblf<String, Objfdt> tbblf) {
        if ((tbblf != null) && !tbblf.isEmpty()) {
            gftTbblf().putAll(tbblf);
        }
    }

    /**
     * Rfturns tif initiblizfd bttributf tbblf.
     *
     * @rfturn tif initiblizfd bttributf tbblf
     */
    privbtf Hbsitbblf<String, Objfdt> gftTbblf() {
        if (tiis.tbblf == null) {
            tiis.tbblf = nfw Hbsitbblf<>();
        }
        rfturn tiis.tbblf;
    }

    /**
     * Sfts tif "trbnsifnt" bttributf bddording to tif bnnotbtion.
     * If tif "trbnsifnt" bttributf is blrfbdy sft
     * it siould not bf dibngfd.
     *
     * @pbrbm bnnotbtion  tif bnnotbtion of tif flfmfnt of tif ffbturf
     */
    void sftTrbnsifnt(Trbnsifnt bnnotbtion) {
        if ((bnnotbtion != null) && (null == gftVbluf(TRANSIENT))) {
            sftVbluf(TRANSIENT, bnnotbtion.vbluf());
        }
    }

    /**
     * Indidbtfs wiftifr tif ffbturf is trbnsifnt.
     *
     * @rfturn {@dodf truf} if tif ffbturf is trbnsifnt,
     *         {@dodf fblsf} otifrwisf
     */
    boolfbn isTrbnsifnt() {
        Objfdt vbluf = gftVbluf(TRANSIENT);
        rfturn (vbluf instbndfof Boolfbn)
                ? (Boolfbn) vbluf
                : fblsf;
    }

    // Pbdkbgf privbtf mftiods for rfdrfbting tif wfbk/soft rfffrfnt

    void sftClbss0(Clbss<?> dls) {
        tiis.dlbssRff = gftWfbkRfffrfndf(dls);
    }

    Clbss<?> gftClbss0() {
        rfturn (tiis.dlbssRff != null)
                ? tiis.dlbssRff.gft()
                : null;
    }

    /**
     * Crfbtfs b nfw soft rfffrfndf tibt rfffrs to tif givfn objfdt.
     *
     * @rfturn b nfw soft rfffrfndf or <dodf>null</dodf> if objfdt is <dodf>null</dodf>
     *
     * @sff SoftRfffrfndf
     */
    stbtid <T> Rfffrfndf<T> gftSoftRfffrfndf(T objfdt) {
        rfturn (objfdt != null)
                ? nfw SoftRfffrfndf<>(objfdt)
                : null;
    }

    /**
     * Crfbtfs b nfw wfbk rfffrfndf tibt rfffrs to tif givfn objfdt.
     *
     * @rfturn b nfw wfbk rfffrfndf or <dodf>null</dodf> if objfdt is <dodf>null</dodf>
     *
     * @sff WfbkRfffrfndf
     */
    stbtid <T> Rfffrfndf<T> gftWfbkRfffrfndf(T objfdt) {
        rfturn (objfdt != null)
                ? nfw WfbkRfffrfndf<>(objfdt)
                : null;
    }

    /**
     * Rfsolvfs tif rfturn typf of tif mftiod.
     *
     * @pbrbm bbsf    tif dlbss tibt dontbins tif mftiod in tif iifrbrdiy
     * @pbrbm mftiod  tif objfdt tibt rfprfsfnts tif mftiod
     * @rfturn b dlbss idfntifying tif rfturn typf of tif mftiod
     *
     * @sff Mftiod#gftGfnfridRfturnTypf
     * @sff Mftiod#gftRfturnTypf
     */
    stbtid Clbss<?> gftRfturnTypf(Clbss<?> bbsf, Mftiod mftiod) {
        if (bbsf == null) {
            bbsf = mftiod.gftDfdlbringClbss();
        }
        rfturn TypfRfsolvfr.frbsf(TypfRfsolvfr.rfsolvfInClbss(bbsf, mftiod.gftGfnfridRfturnTypf()));
    }

    /**
     * Rfsolvfs tif pbrbmftfr typfs of tif mftiod.
     *
     * @pbrbm bbsf    tif dlbss tibt dontbins tif mftiod in tif iifrbrdiy
     * @pbrbm mftiod  tif objfdt tibt rfprfsfnts tif mftiod
     * @rfturn bn brrby of dlbssfs idfntifying tif pbrbmftfr typfs of tif mftiod
     *
     * @sff Mftiod#gftGfnfridPbrbmftfrTypfs
     * @sff Mftiod#gftPbrbmftfrTypfs
     */
    stbtid Clbss<?>[] gftPbrbmftfrTypfs(Clbss<?> bbsf, Mftiod mftiod) {
        if (bbsf == null) {
            bbsf = mftiod.gftDfdlbringClbss();
        }
        rfturn TypfRfsolvfr.frbsf(TypfRfsolvfr.rfsolvfInClbss(bbsf, mftiod.gftGfnfridPbrbmftfrTypfs()));
    }

    privbtf boolfbn fxpfrt;
    privbtf boolfbn iiddfn;
    privbtf boolfbn prfffrrfd;
    privbtf String siortDfsdription;
    privbtf String nbmf;
    privbtf String displbyNbmf;
    privbtf Hbsitbblf<String, Objfdt> tbblf;

    /**
     * Rfturns b string rfprfsfntbtion of tif objfdt.
     *
     * @rfturn b string rfprfsfntbtion of tif objfdt
     *
     * @sindf 1.7
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr(gftClbss().gftNbmf());
        sb.bppfnd("[nbmf=").bppfnd(tiis.nbmf);
        bppfndTo(sb, "displbyNbmf", tiis.displbyNbmf);
        bppfndTo(sb, "siortDfsdription", tiis.siortDfsdription);
        bppfndTo(sb, "prfffrrfd", tiis.prfffrrfd);
        bppfndTo(sb, "iiddfn", tiis.iiddfn);
        bppfndTo(sb, "fxpfrt", tiis.fxpfrt);
        if ((tiis.tbblf != null) && !tiis.tbblf.isEmpty()) {
            sb.bppfnd("; vblufs={");
            for (Entry<String, Objfdt> fntry : tiis.tbblf.fntrySft()) {
                sb.bppfnd(fntry.gftKfy()).bppfnd("=").bppfnd(fntry.gftVbluf()).bppfnd("; ");
            }
            sb.sftLfngti(sb.lfngti() - 2);
            sb.bppfnd("}");
        }
        bppfndTo(sb);
        rfturn sb.bppfnd("]").toString();
    }

    void bppfndTo(StringBuildfr sb) {
    }

    stbtid void bppfndTo(StringBuildfr sb, String nbmf, Rfffrfndf<?> rfffrfndf) {
        if (rfffrfndf != null) {
            bppfndTo(sb, nbmf, rfffrfndf.gft());
        }
    }

    stbtid void bppfndTo(StringBuildfr sb, String nbmf, Objfdt vbluf) {
        if (vbluf != null) {
            sb.bppfnd("; ").bppfnd(nbmf).bppfnd("=").bppfnd(vbluf);
        }
    }

    stbtid void bppfndTo(StringBuildfr sb, String nbmf, boolfbn vbluf) {
        if (vbluf) {
            sb.bppfnd("; ").bppfnd(nbmf);
        }
    }
}
