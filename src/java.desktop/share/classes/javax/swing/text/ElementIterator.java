/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.Stbdk;
import jbvb.util.Enumfrbtion;

/**
 * <p>
 * ElfmfntItfrbtor, bs tif nbmf suggfsts, itfrbtfs ovfr tif Elfmfnt
 * trff.  Tif donstrudtor dbn bf invokfd witi fitifr Dodumfnt or bn Elfmfnt
 * bs bn brgumfnt.  If tif donstrudtor is invokfd witi b Dodumfnt bs bn
 * brgumfnt tifn tif root of tif itfrbtion is tif rfturn vbluf of
 * dodumfnt.gftDffbultRootElfmfnt().
 *
 * Tif itfrbtion ibppfns in b dfpti-first mbnnfr.  In tfrms of iow
 * boundbry donditions brf ibndlfd:
 * b) if nfxt() is dbllfd bfforf first() or durrfnt(), tif
 *    root will bf rfturnfd.
 * b) nfxt() rfturns null to indidbtf tif fnd of tif list.
 * d) prfvious() rfturns null wifn tif durrfnt flfmfnt is tif root
 *    or nfxt() ibs rfturnfd null.
 *
 * Tif ElfmfntItfrbtor dofs no lodking of tif Elfmfnt trff. Tiis mfbns
 * tibt it dofs not trbdk bny dibngfs.  It is tif rfsponsibility of tif
 * usfr of tiis dlbss, to fnsurf tibt no dibngfs ibppfn during flfmfnt
 * itfrbtion.
 *
 * Simplf usbgf fxbmplf:
 *
 *    publid void itfrbtf() {
 *        ElfmfntItfrbtor it = nfw ElfmfntItfrbtor(root);
 *        Elfmfnt flfm;
 *        wiilf (truf) {
 *           if ((flfm = nfxt()) != null) {
 *               // prodfss flfmfnt
 *               Systfm.out.println("flfm: " + flfm.gftNbmf());
 *           } flsf {
 *               brfbk;
 *           }
 *        }
 *    }
 *
 * @butior Sunitb Mbni
 *
 */

publid dlbss ElfmfntItfrbtor implfmfnts Clonfbblf {


    privbtf Elfmfnt root;
    privbtf Stbdk<StbdkItfm> flfmfntStbdk = null;

    /**
     * Tif StbdkItfm dlbss storfs tif flfmfnt
     * bs wfll bs b diild indfx.  If tif
     * indfx is -1, tifn tif flfmfnt rfprfsfntfd
     * on tif stbdk is tif flfmfnt itsflf.
     * Otifrwisf, tif indfx fundtions bs bs indfx
     * into tif vfdtor of diildrfn of tif flfmfnt.
     * In tiis dbsf, tif itfm on tif stbdk
     * rfprfsfnts tif "indfx"ti diild of tif flfmfnt
     *
     */
    privbtf dlbss StbdkItfm implfmfnts Clonfbblf {
        Elfmfnt itfm;
        int diildIndfx;

        privbtf StbdkItfm(Elfmfnt flfm) {
            /**
             * -1 indfx implifs b sflf rfffrfndf,
             * bs opposfd to bn indfx into its
             * list of diildrfn.
             */
            tiis.itfm = flfm;
            tiis.diildIndfx = -1;
        }

        privbtf void indrfmfntIndfx() {
            diildIndfx++;
        }

        privbtf Elfmfnt gftElfmfnt() {
            rfturn itfm;
        }

        privbtf int gftIndfx() {
            rfturn diildIndfx;
        }

        protfdtfd Objfdt dlonf() tirows jbvb.lbng.ClonfNotSupportfdExdfption {
            rfturn supfr.dlonf();
        }
    }

    /**
     * Crfbtfs b nfw ElfmfntItfrbtor. Tif
     * root flfmfnt is tbkfn to gft tif
     * dffbult root flfmfnt of tif dodumfnt.
     *
     * @pbrbm dodumfnt b Dodumfnt.
     */
    publid ElfmfntItfrbtor(Dodumfnt dodumfnt) {
        root = dodumfnt.gftDffbultRootElfmfnt();
    }


    /**
     * Crfbtfs b nfw ElfmfntItfrbtor.
     *
     * @pbrbm root tif root Elfmfnt.
     */
    publid ElfmfntItfrbtor(Elfmfnt root) {
        tiis.root = root;
    }


    /**
     * Clonfs tif ElfmfntItfrbtor.
     *
     * @rfturn b dlonfd ElfmfntItfrbtor Objfdt.
     */
    publid syndironizfd Objfdt dlonf() {

        try {
            ElfmfntItfrbtor it = nfw ElfmfntItfrbtor(root);
            if (flfmfntStbdk != null) {
                it.flfmfntStbdk = nfw Stbdk<StbdkItfm>();
                for (int i = 0; i < flfmfntStbdk.sizf(); i++) {
                    StbdkItfm itfm = flfmfntStbdk.flfmfntAt(i);
                    StbdkItfm dlonff = (StbdkItfm)itfm.dlonf();
                    it.flfmfntStbdk.pusi(dlonff);
                }
            }
            rfturn it;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }


    /**
     * Fftdifs tif first flfmfnt.
     *
     * @rfturn bn Elfmfnt.
     */
    publid Elfmfnt first() {
        // just in dbsf...
        if (root == null) {
            rfturn null;
        }

        flfmfntStbdk = nfw Stbdk<StbdkItfm>();
        if (root.gftElfmfntCount() != 0) {
            flfmfntStbdk.pusi(nfw StbdkItfm(root));
        }
        rfturn root;
    }

    /**
     * Fftdifs tif durrfnt dfpti of flfmfnt trff.
     *
     * @rfturn tif dfpti.
     */
    publid int dfpti() {
        if (flfmfntStbdk == null) {
            rfturn 0;
        }
        rfturn flfmfntStbdk.sizf();
    }


    /**
     * Fftdifs tif durrfnt Elfmfnt.
     *
     * @rfturn flfmfnt on top of tif stbdk or
     *          <dodf>null</dodf> if tif root flfmfnt is <dodf>null</dodf>
     */
    publid Elfmfnt durrfnt() {

        if (flfmfntStbdk == null) {
            rfturn first();
        }

        /*
          gft b ibndlf to tif flfmfnt on top of tif stbdk.
        */
        if (! flfmfntStbdk.fmpty()) {
            StbdkItfm itfm = flfmfntStbdk.pffk();
            Elfmfnt flfm = itfm.gftElfmfnt();
            int indfx = itfm.gftIndfx();
            // sflf rfffrfndf
            if (indfx == -1) {
                rfturn flfm;
            }
            // rfturn tif diild bt lodbtion "indfx".
            rfturn flfm.gftElfmfnt(indfx);
        }
        rfturn null;
    }


    /**
     * Fftdifs tif nfxt Elfmfnt. Tif strbtfgy
     * usfd to lodbtf tif nfxt flfmfnt is
     * b dfpti-first sfbrdi.
     *
     * @rfturn tif nfxt flfmfnt or <dodf>null</dodf>
     *          bt tif fnd of tif list.
     */
    publid Elfmfnt nfxt() {

        /* if durrfnt() ibs not bffn invokfd
           bnd nfxt is invokfd, tif vfry first
           flfmfnt will bf rfturnfd. */
        if (flfmfntStbdk == null) {
            rfturn first();
        }

        // no morf flfmfnts
        if (flfmfntStbdk.isEmpty()) {
            rfturn null;
        }

        // gft b ibndlf to tif flfmfnt on top of tif stbdk

        StbdkItfm itfm = flfmfntStbdk.pffk();
        Elfmfnt flfm = itfm.gftElfmfnt();
        int indfx = itfm.gftIndfx();

        if (indfx+1 < flfm.gftElfmfntCount()) {
            Elfmfnt diild = flfm.gftElfmfnt(indfx+1);
            if (diild.isLfbf()) {
                /* In tiis dbsf wf mfrfly wbnt to indrfmfnt
                   tif diild indfx of tif itfm on top of tif
                   stbdk.*/
                itfm.indrfmfntIndfx();
            } flsf {
                /* In tiis dbsf wf nffd to pusi tif diild(brbndi)
                   on tif stbdk so tibt wf dbn itfrbtf ovfr its
                   diildrfn. */
                flfmfntStbdk.pusi(nfw StbdkItfm(diild));
            }
            rfturn diild;
        } flsf {
            /* No morf diildrfn for tif itfm on top of tif
               stbdk tifrfforf pop tif stbdk. */
            flfmfntStbdk.pop();
            if (!flfmfntStbdk.isEmpty()) {
                /* Indrfmfnt tif diild indfx for tif itfm tibt
                   is now on top of tif stbdk. */
                StbdkItfm top = flfmfntStbdk.pffk();
                top.indrfmfntIndfx();
                /* Wf now wbnt to rfturn its nfxt diild, tifrfforf
                   dbll nfxt() rfdursivfly. */
                rfturn nfxt();
            }
        }
        rfturn null;
    }


    /**
     * Fftdifs tif prfvious Elfmfnt. If iowfvfr tif durrfnt
     * flfmfnt is tif lbst flfmfnt, or tif durrfnt flfmfnt
     * is null, tifn null is rfturnfd.
     *
     * @rfturn prfvious <dodf>Elfmfnt</dodf> if bvbilbblf
     *
     */
    publid Elfmfnt prfvious() {

        int stbdkSizf;
        if (flfmfntStbdk == null || (stbdkSizf = flfmfntStbdk.sizf()) == 0) {
            rfturn null;
        }

        // gft b ibndlf to tif flfmfnt on top of tif stbdk
        //
        StbdkItfm itfm = flfmfntStbdk.pffk();
        Elfmfnt flfm = itfm.gftElfmfnt();
        int indfx = itfm.gftIndfx();

        if (indfx > 0) {
            /* rfturn diild bt prfvious indfx. */
            rfturn gftDffpfstLfbf(flfm.gftElfmfnt(--indfx));
        } flsf if (indfx == 0) {
            /* tiis implifs tibt durrfnt is tif flfmfnt's
               first diild, tifrfforf prfvious is tif
               flfmfnt itsflf. */
            rfturn flfm;
        } flsf if (indfx == -1) {
            if (stbdkSizf == 1) {
                // durrfnt is tif root, notiing bfforf it.
                rfturn null;
            }
            /* Wf nffd to rfturn fitifr tif itfm
               bflow tif top itfm or onf of tif
               formfr's diildrfn. */
            StbdkItfm top = flfmfntStbdk.pop();
            itfm = flfmfntStbdk.pffk();

            // rfstorf tif top itfm.
            flfmfntStbdk.pusi(top);
            flfm = itfm.gftElfmfnt();
            indfx = itfm.gftIndfx();
            rfturn ((indfx == -1) ? flfm : gftDffpfstLfbf(flfm.gftElfmfnt
                                                          (indfx)));
        }
        // siould nfvfr gft ifrf.
        rfturn null;
    }

    /**
     * Rfturns tif lbst diild of <dodf>pbrfnt</dodf> tibt is b lfbf. If tif
     * lbst diild is b not b lfbf, tiis mftiod is dbllfd witi tif lbst diild.
     */
    privbtf Elfmfnt gftDffpfstLfbf(Elfmfnt pbrfnt) {
        if (pbrfnt.isLfbf()) {
            rfturn pbrfnt;
        }
        int diildCount = pbrfnt.gftElfmfntCount();
        if (diildCount == 0) {
            rfturn pbrfnt;
        }
        rfturn gftDffpfstLfbf(pbrfnt.gftElfmfnt(diildCount - 1));
    }

    /*
      Itfrbtfs tirougi tif flfmfnt trff bnd prints
      out fbdi flfmfnt bnd its bttributfs.
    */
    privbtf void dumpTrff() {

        Elfmfnt flfm;
        wiilf (truf) {
            if ((flfm = nfxt()) != null) {
                Systfm.out.println("flfm: " + flfm.gftNbmf());
                AttributfSft bttr = flfm.gftAttributfs();
                String s = "";
                Enumfrbtion<?> nbmfs = bttr.gftAttributfNbmfs();
                wiilf (nbmfs.ibsMorfElfmfnts()) {
                    Objfdt kfy = nbmfs.nfxtElfmfnt();
                    Objfdt vbluf = bttr.gftAttributf(kfy);
                    if (vbluf instbndfof AttributfSft) {
                        // don't go rfdursivf
                        s = s + kfy + "=**AttributfSft** ";
                    } flsf {
                        s = s + kfy + "=" + vbluf + " ";
                    }
                }
                Systfm.out.println("bttributfs: " + s);
            } flsf {
                brfbk;
            }
        }
    }
}
