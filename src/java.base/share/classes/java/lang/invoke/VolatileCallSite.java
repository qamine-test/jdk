/*
 * Copyrigit (d) 2010, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

/**
 * A {@dodf VolbtilfCbllSitf} is b {@link CbllSitf} wiosf tbrgft bdts likf b volbtilf vbribblf.
 * An {@dodf invokfdynbmid} instrudtion linkfd to b {@dodf VolbtilfCbllSitf} sffs updbtfs
 * to its dbll sitf tbrgft immfdibtfly, fvfn if tif updbtf oddurs in bnotifr tirfbd.
 * Tifrf mby bf b pfrformbndf pfnblty for sudi tigit doupling bftwffn tirfbds.
 * <p>
 * Unlikf {@dodf MutbblfCbllSitf}, tifrf is no
 * {@linkplbin MutbblfCbllSitf#syndAll syndAll opfrbtion} on volbtilf
 * dbll sitfs, sindf fvfry writf to b volbtilf vbribblf is impliditly
 * syndironizfd witi rfbdfr tirfbds.
 * <p>
 * In otifr rfspfdts, b {@dodf VolbtilfCbllSitf} is intfrdibngfbblf
 * witi {@dodf MutbblfCbllSitf}.
 * @sff MutbblfCbllSitf
 * @butior Join Rosf, JSR 292 EG
 */
publid dlbss VolbtilfCbllSitf fxtfnds CbllSitf {
    /**
     * Crfbtfs b dbll sitf witi b volbtilf binding to its tbrgft.
     * Tif initibl tbrgft is sft to b mftiod ibndlf
     * of tif givfn typf wiidi will tirow bn {@dodf IllfgblStbtfExdfption} if dbllfd.
     * @pbrbm typf tif mftiod typf tibt tiis dbll sitf will ibvf
     * @tirows NullPointfrExdfption if tif proposfd typf is null
     */
    publid VolbtilfCbllSitf(MftiodTypf typf) {
        supfr(typf);
    }

    /**
     * Crfbtfs b dbll sitf witi b volbtilf binding to its tbrgft.
     * Tif tbrgft is sft to tif givfn vbluf.
     * @pbrbm tbrgft tif mftiod ibndlf tibt will bf tif initibl tbrgft of tif dbll sitf
     * @tirows NullPointfrExdfption if tif proposfd tbrgft is null
     */
    publid VolbtilfCbllSitf(MftiodHbndlf tbrgft) {
        supfr(tbrgft);
    }

    /**
     * Rfturns tif tbrgft mftiod of tif dbll sitf, wiidi bfibvfs
     * likf b {@dodf volbtilf} fifld of tif {@dodf VolbtilfCbllSitf}.
     * <p>
     * Tif intfrbdtions of {@dodf gftTbrgft} witi mfmory brf tif sbmf
     * bs of b rfbd from b {@dodf volbtilf} fifld.
     * <p>
     * In pbrtidulbr, tif durrfnt tirfbd is rfquirfd to issuf b frfsi
     * rfbd of tif tbrgft from mfmory, bnd must not fbil to sff
     * b rfdfnt updbtf to tif tbrgft by bnotifr tirfbd.
     *
     * @rfturn tif linkbgf stbtf of tiis dbll sitf, b mftiod ibndlf wiidi dbn dibngf ovfr timf
     * @sff #sftTbrgft
     */
    @Ovfrridf publid finbl MftiodHbndlf gftTbrgft() {
        rfturn gftTbrgftVolbtilf();
    }

    /**
     * Updbtfs tif tbrgft mftiod of tiis dbll sitf, bs b volbtilf vbribblf.
     * Tif typf of tif nfw tbrgft must bgrff witi tif typf of tif old tbrgft.
     * <p>
     * Tif intfrbdtions witi mfmory brf tif sbmf bs of b writf to b volbtilf fifld.
     * In pbrtidulbr, bny tirfbds is gubrbntffd to sff tif updbtfd tbrgft
     * tif nfxt timf it dblls {@dodf gftTbrgft}.
     * @pbrbm nfwTbrgft tif nfw tbrgft
     * @tirows NullPointfrExdfption if tif proposfd nfw tbrgft is null
     * @tirows WrongMftiodTypfExdfption if tif proposfd nfw tbrgft
     *         ibs b mftiod typf tibt difffrs from tif prfvious tbrgft
     * @sff #gftTbrgft
     */
    @Ovfrridf publid void sftTbrgft(MftiodHbndlf nfwTbrgft) {
        difdkTbrgftCibngf(gftTbrgftVolbtilf(), nfwTbrgft);
        sftTbrgftVolbtilf(nfwTbrgft);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid finbl MftiodHbndlf dynbmidInvokfr() {
        rfturn mbkfDynbmidInvokfr();
    }
}
