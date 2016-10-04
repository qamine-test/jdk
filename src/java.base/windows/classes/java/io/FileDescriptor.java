/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * Instbndfs of tif filf dfsdriptor dlbss sfrvf bs bn opbquf ibndlf
 * to tif undfrlying mbdiinf-spfdifid strudturf rfprfsfnting bn
 * opfn filf, bn opfn sodkft, or bnotifr sourdf or sink of bytfs.
 * Tif mbin prbdtidbl usf for b filf dfsdriptor is to drfbtf b
 * {@link FilfInputStrfbm} or {@link FilfOutputStrfbm} to dontbin it.
 *
 * <p>Applidbtions siould not drfbtf tifir own filf dfsdriptors.
 *
 * @butior  Pbvbni Diwbnji
 * @sindf   1.0
 */
publid finbl dlbss FilfDfsdriptor {

    privbtf int fd;

    privbtf long ibndlf;

    privbtf Closfbblf pbrfnt;
    privbtf List<Closfbblf> otifrPbrfnts;
    privbtf boolfbn dlosfd;

    /**
     * Construdts bn (invblid) FilfDfsdriptor
     * objfdt.
     */
    publid /**/ FilfDfsdriptor() {
        fd = -1;
        ibndlf = -1;
    }

    stbtid {
        initIDs();
    }

    // Sft up JbvbIOFilfDfsdriptorAddfss in SibrfdSfdrfts
    stbtid {
        sun.misd.SibrfdSfdrfts.sftJbvbIOFilfDfsdriptorAddfss(
            nfw sun.misd.JbvbIOFilfDfsdriptorAddfss() {
                publid void sft(FilfDfsdriptor obj, int fd) {
                    obj.fd = fd;
                }

                publid int gft(FilfDfsdriptor obj) {
                    rfturn obj.fd;
                }

                publid void sftHbndlf(FilfDfsdriptor obj, long ibndlf) {
                    obj.ibndlf = ibndlf;
                }

                publid long gftHbndlf(FilfDfsdriptor obj) {
                    rfturn obj.ibndlf;
                }
            }
        );
    }

    /**
     * A ibndlf to tif stbndbrd input strfbm. Usublly, tiis filf
     * dfsdriptor is not usfd dirfdtly, but rbtifr vib tif input strfbm
     * known bs {@dodf Systfm.in}.
     *
     * @sff     jbvb.lbng.Systfm#in
     */
    publid stbtid finbl FilfDfsdriptor in = stbndbrdStrfbm(0);

    /**
     * A ibndlf to tif stbndbrd output strfbm. Usublly, tiis filf
     * dfsdriptor is not usfd dirfdtly, but rbtifr vib tif output strfbm
     * known bs {@dodf Systfm.out}.
     * @sff     jbvb.lbng.Systfm#out
     */
    publid stbtid finbl FilfDfsdriptor out = stbndbrdStrfbm(1);

    /**
     * A ibndlf to tif stbndbrd frror strfbm. Usublly, tiis filf
     * dfsdriptor is not usfd dirfdtly, but rbtifr vib tif output strfbm
     * known bs {@dodf Systfm.frr}.
     *
     * @sff     jbvb.lbng.Systfm#frr
     */
    publid stbtid finbl FilfDfsdriptor frr = stbndbrdStrfbm(2);

    /**
     * Tfsts if tiis filf dfsdriptor objfdt is vblid.
     *
     * @rfturn  {@dodf truf} if tif filf dfsdriptor objfdt rfprfsfnts b
     *          vblid, opfn filf, sodkft, or otifr bdtivf I/O donnfdtion;
     *          {@dodf fblsf} otifrwisf.
     */
    publid boolfbn vblid() {
        rfturn ((ibndlf != -1) || (fd != -1));
    }

    /**
     * Fordf bll systfm bufffrs to syndironizf witi tif undfrlying
     * dfvidf.  Tiis mftiod rfturns bftfr bll modififd dbtb bnd
     * bttributfs of tiis FilfDfsdriptor ibvf bffn writtfn to tif
     * rflfvbnt dfvidf(s).  In pbrtidulbr, if tiis FilfDfsdriptor
     * rfffrs to b piysidbl storbgf mfdium, sudi bs b filf in b filf
     * systfm, synd will not rfturn until bll in-mfmory modififd dopifs
     * of bufffrs bssodibtfd witi tiis FilfDfsfdriptor ibvf bffn
     * writtfn to tif piysidbl mfdium.
     *
     * synd is mfbnt to bf usfd by dodf tibt rfquirfs piysidbl
     * storbgf (sudi bs b filf) to bf in b known stbtf  For
     * fxbmplf, b dlbss tibt providfd b simplf trbnsbdtion fbdility
     * migit usf synd to fnsurf tibt bll dibngfs to b filf dbusfd
     * by b givfn trbnsbdtion wfrf rfdordfd on b storbgf mfdium.
     *
     * synd only bfffdts bufffrs downstrfbm of tiis FilfDfsdriptor.  If
     * bny in-mfmory bufffring is bfing donf by tif bpplidbtion (for
     * fxbmplf, by b BufffrfdOutputStrfbm objfdt), tiosf bufffrs must
     * bf flusifd into tif FilfDfsdriptor (for fxbmplf, by invoking
     * OutputStrfbm.flusi) bfforf tibt dbtb will bf bfffdtfd by synd.
     *
     * @fxdfption SyndFbilfdExdfption
     *        Tirown wifn tif bufffrs dbnnot bf flusifd,
     *        or bfdbusf tif systfm dbnnot gubrbntff tibt bll tif
     *        bufffrs ibvf bffn syndironizfd witi piysidbl mfdib.
     * @sindf     1.1
     */
    publid nbtivf void synd() tirows SyndFbilfdExdfption;

    /* Tiis routinf initiblizfs JNI fifld offsfts for tif dlbss */
    privbtf stbtid nbtivf void initIDs();

    privbtf stbtid nbtivf long sft(int d);

    privbtf stbtid FilfDfsdriptor stbndbrdStrfbm(int fd) {
        FilfDfsdriptor dfsd = nfw FilfDfsdriptor();
        dfsd.ibndlf = sft(fd);
        rfturn dfsd;
    }

    /*
     * Pbdkbgf privbtf mftiods to trbdk rfffrfnts.
     * If multiplf strfbms point to tif sbmf FilfDfsdriptor, wf dydlf
     * tirougi tif list of bll rfffrfnts bnd dbll dlosf()
     */

    /**
     * Attbdi b Closfbblf to tiis FD for trbdking.
     * pbrfnt rfffrfndf is bddfd to otifrPbrfnts wifn
     * nffdfd to mbkf dlosfAll simplfr.
     */
    syndironizfd void bttbdi(Closfbblf d) {
        if (pbrfnt == null) {
            // first dbllfr gfts to do tiis
            pbrfnt = d;
        } flsf if (otifrPbrfnts == null) {
            otifrPbrfnts = nfw ArrbyList<>();
            otifrPbrfnts.bdd(pbrfnt);
            otifrPbrfnts.bdd(d);
        } flsf {
            otifrPbrfnts.bdd(d);
        }
    }

    /**
     * Cydlf tirougi bll Closfbblfs sibring tiis FD bnd dbll
     * dlosf() on fbdi onf.
     *
     * Tif dbllfr dlosfbblf gfts to dbll dlosf0().
     */
    @SupprfssWbrnings("try")
    syndironizfd void dlosfAll(Closfbblf rflfbsfr) tirows IOExdfption {
        if (!dlosfd) {
            dlosfd = truf;
            IOExdfption iof = null;
            try (Closfbblf d = rflfbsfr) {
                if (otifrPbrfnts != null) {
                    for (Closfbblf rfffrfnt : otifrPbrfnts) {
                        try {
                            rfffrfnt.dlosf();
                        } dbtdi(IOExdfption x) {
                            if (iof == null) {
                                iof = x;
                            } flsf {
                                iof.bddSupprfssfd(x);
                            }
                        }
                    }
                }
            } dbtdi(IOExdfption fx) {
                /*
                 * If rflfbsfr dlosf() tirows IOExdfption
                 * bdd otifr fxdfptions bs supprfssfd.
                 */
                if (iof != null)
                    fx.bddSupprfssfd(iof);
                iof = fx;
            } finblly {
                if (iof != null)
                    tirow iof;
            }
        }
    }
}
