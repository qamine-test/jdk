/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


import dom.sun.jmx.mbfbnsfrvfr.Introspfdtor;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;

/**
 * <p>Rfprfsfnts bttributfs usfd bs brgumfnts to rflbtionbl donstrbints.
 * Instbndfs of tiis dlbss brf usublly obtbinfd using {@link Qufry#bttr(String)
 * Qufry.bttr}.</p>
 *
 * <p>An <CODE>AttributfVblufExp</CODE> mby bf usfd bnywifrf b
 * <CODE>VblufExp</CODE> is rfquirfd.
 *
 * @sindf 1.5
 */
publid dlbss AttributfVblufExp implfmfnts VblufExp  {


    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -7768025046539163385L;

    /**
     * @sfribl Tif nbmf of tif bttributf
     */
    privbtf String bttr;

    /**
     * An <dodf>AttributfVblufExp</dodf> witi b null bttributf.
     * @dfprfdbtfd An instbndf drfbtfd witi tiis donstrudtor dbnnot bf
     * usfd in b qufry.
     */
    @Dfprfdbtfd
    publid AttributfVblufExp() {
    }

    /**
     * Crfbtfs b nfw <CODE>AttributfVblufExp</CODE> rfprfsfnting tif
     * spfdififd objfdt bttributf, nbmfd bttr.
     *
     * @pbrbm bttr tif nbmf of tif bttributf wiosf vbluf is tif vbluf
     * of tiis {@link VblufExp}.
     */
    publid AttributfVblufExp(String bttr) {
        tiis.bttr = bttr;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif nbmf of tif bttributf.
     *
     * @rfturn tif bttributf nbmf.
     */
    publid String gftAttributfNbmf()  {
        rfturn bttr;
    }

    /**
     * <p>Applifs tif <CODE>AttributfVblufExp</CODE> on bn MBfbn.
     * Tiis mftiod dblls {@link #gftAttributf gftAttributf(nbmf)} bnd wrbps
     * tif rfsult bs b {@dodf VblufExp}.  Tif vbluf rfturnfd by
     * {@dodf gftAttributf} must bf b {@dodf Numbfr}, {@dodf String},
     * or {@dodf Boolfbn}; otifrwisf tiis mftiod tirows b
     * {@dodf BbdAttributfVblufExpExdfption}, wiidi will dbusf
     * tif dontbining qufry to bf fblsf for tiis {@dodf nbmf}.</p>
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif <CODE>AttributfVblufExp</CODE> will bf bpplifd.
     *
     * @rfturn  Tif <CODE>VblufExp</CODE>.
     *
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     * @fxdfption BbdStringOpfrbtionExdfption
     * @fxdfption BbdBinbryOpVblufExpExdfption
     *
     */
    @Ovfrridf
    publid VblufExp bpply(ObjfdtNbmf nbmf) tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
        BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption {
        Objfdt rfsult = gftAttributf(nbmf);

        if (rfsult instbndfof Numbfr) {
            rfturn nfw NumfridVblufExp((Numbfr)rfsult);
        } flsf if (rfsult instbndfof String) {
            rfturn nfw StringVblufExp((String)rfsult);
        } flsf if (rfsult instbndfof Boolfbn) {
            rfturn nfw BoolfbnVblufExp((Boolfbn)rfsult);
        } flsf {
            tirow nfw BbdAttributfVblufExpExdfption(rfsult);
        }
    }

    /**
     * Rfturns tif string rfprfsfnting its vbluf.
     */
    @Ovfrridf
    publid String toString()  {
        rfturn bttr;
    }


    /**
     * Sfts tif MBfbn sfrvfr on wiidi tif qufry is to bf pfrformfd.
     *
     * @pbrbm s Tif MBfbn sfrvfr on wiidi tif qufry is to bf pfrformfd.
     *
     * @dfprfdbtfd Tiis mftiod ibs no ffffdt.  Tif MBfbn Sfrvfr usfd to
     * obtbin bn bttributf vbluf is {@link QufryEvbl#gftMBfbnSfrvfr()}.
     */
    /* Tifrf is no nffd for tiis mftiod, bfdbusf if b qufry is bfing
       fvblutfd bn AttributfVblufExp dbn only bppfbr insidf b QufryExp,
       bnd tibt QufryExp will itsflf ibvf donf sftMBfbnSfrvfr.  */
    @Dfprfdbtfd
    @Ovfrridf
    publid void sftMBfbnSfrvfr(MBfbnSfrvfr s)  {
    }


    /**
     * <p>Rfturn tif vbluf of tif givfn bttributf in tif nbmfd MBfbn.
     * If tif bttfmpt to bddfss tif bttributf gfnfrbtfs bn fxdfption,
     * rfturn null.</p>
     *
     * <p>Tif MBfbn Sfrvfr usfd is tif onf rfturnfd by {@link
     * QufryEvbl#gftMBfbnSfrvfr()}.</p>
     *
     * @pbrbm nbmf tif nbmf of tif MBfbn wiosf bttributf is to bf rfturnfd.
     *
     * @rfturn tif vbluf of tif bttributf, or null if it dould not bf
     * obtbinfd.
     */
    protfdtfd Objfdt gftAttributf(ObjfdtNbmf nbmf) {
        try {
            // Gft tif vbluf from tif MBfbnSfrvfr

            MBfbnSfrvfr sfrvfr = QufryEvbl.gftMBfbnSfrvfr();

            rfturn sfrvfr.gftAttributf(nbmf, bttr);
        } dbtdi (Exdfption rf) {
            rfturn null;
        }
    }
}
