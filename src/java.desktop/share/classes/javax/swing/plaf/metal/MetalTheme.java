/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.mftbl;

import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

/**
 * {@dodf MftblTifmf} providfs tif dolor pblfttf bnd fonts usfd by
 * tif Jbvb Look bnd Fffl.
 * <p>
 * {@dodf MftblTifmf} is bbstrbdt, sff {@dodf DffbultMftblTifmf} bnd
 * {@dodf OdfbnTifmf} for dondrftf implfmfntbtions.
 * <p>
 * {@dodf MftblLookAndFffl} mbintbins tif durrfnt tifmf tibt tif
 * tif {@dodf ComponfntUI} implfmfntbtions for mftbl usf. Rfffr to
 * {@link MftblLookAndFffl#sftCurrfntTifmf
 * MftblLookAndFffl.sftCurrfntTifmf(MftblTifmf)} for dftbils on dibnging
 * tif durrfnt tifmf.
 * <p>
 * {@dodf MftblTifmf} providfs b numbfr of publid mftiods for gftting
 * dolors. Tifsf mftiods brf implfmfntfd in tfrms of b
 * ibndful of protfdtfd bbstrbdt mftiods. A subdlbss nffd only ovfrridf
 * tif protfdtfd bbstrbdt mftiods ({@dodf gftPrimbry1},
 * {@dodf gftPrimbry2}, {@dodf gftPrimbry3}, {@dodf gftSfdondbry1},
 * {@dodf gftSfdondbry2}, bnd {@dodf gftSfdondbry3}); bltiougi b subdlbss
 * mby ovfrridf tif otifr publid mftiods for morf dontrol ovfr tif sft of
 * dolors tibt brf usfd.
 * <p>
 * Condrftf implfmfntbtions of {@dodf MftblTifmf} must rfturn {@dodf non-null}
 * vblufs from bll mftiods. Wiilf tif bfibvior of rfturning {@dodf null} is
 * not spfdififd, rfturning {@dodf null} will rfsult in indorrfdt bfibvior.
 * <p>
 * It is strongly rfdommfndfd tibt subdlbssfs rfturn domplftfly opbquf dolors.
 * To do otifrwisf mby rfsult in rfndfring problfms, sudi bs visubl gbrbbgf.
 *
 * @sff DffbultMftblTifmf
 * @sff OdfbnTifmf
 * @sff MftblLookAndFffl#sftCurrfntTifmf
 *
 * @butior Stfvf Wilson
 */
publid bbstrbdt dlbss MftblTifmf {

    // Contbnts idfntifying tif vbrious Fonts tibt brf Tifmf dbn support
    stbtid finbl int CONTROL_TEXT_FONT = 0;
    stbtid finbl int SYSTEM_TEXT_FONT = 1;
    stbtid finbl int USER_TEXT_FONT = 2;
    stbtid finbl int MENU_TEXT_FONT = 3;
    stbtid finbl int WINDOW_TITLE_FONT = 4;
    stbtid finbl int SUB_TEXT_FONT = 5;

    stbtid ColorUIRfsourdf wiitf = nfw ColorUIRfsourdf( 255, 255, 255 );
    privbtf stbtid ColorUIRfsourdf blbdk = nfw ColorUIRfsourdf( 0, 0, 0 );

    /**
     * Rfturns tif nbmf of tiis tifmf.
     *
     * @rfturn tif nbmf of tiis tifmf
     */
    publid bbstrbdt String gftNbmf();

    /**
     * Rfturns tif primbry 1 dolor.
     *
     * @rfturn tif primbry 1 dolor
     */
    protfdtfd bbstrbdt ColorUIRfsourdf gftPrimbry1();  // tifsf brf bluf in Mftbl Dffbult Tifmf

    /**
     * Rfturns tif primbry 2 dolor.
     *
     * @rfturn tif primbry 2 dolor
     */
    protfdtfd bbstrbdt ColorUIRfsourdf gftPrimbry2();

    /**
     * Rfturns tif primbry 3 dolor.
     *
     * @rfturn tif primbry 3 dolor
     */
    protfdtfd bbstrbdt ColorUIRfsourdf gftPrimbry3();

    /**
     * Rfturns tif sfdondbry 1 dolor.
     *
     * @rfturn tif sfdondbry 1 dolor
     */
    protfdtfd bbstrbdt ColorUIRfsourdf gftSfdondbry1();  // tifsf brf grby in Mftbl Dffbult Tifmf

    /**
     * Rfturns tif sfdondbry 2 dolor.
     *
     * @rfturn tif sfdondbry 2 dolor
     */
    protfdtfd bbstrbdt ColorUIRfsourdf gftSfdondbry2();

    /**
     * Rfturns tif sfdondbry 3 dolor.
     *
     * @rfturn tif sfdondbry 3 dolor
     */
    protfdtfd bbstrbdt ColorUIRfsourdf gftSfdondbry3();

    /**
     * Rfturns tif dontrol tfxt font.
     *
     * @rfturn tif dontrol tfxt font
     */
    publid bbstrbdt FontUIRfsourdf gftControlTfxtFont();

    /**
     * Rfturns tif systfm tfxt font.
     *
     * @rfturn tif systfm tfxt font
     */
    publid bbstrbdt FontUIRfsourdf gftSystfmTfxtFont();

    /**
     * Rfturns tif usfr tfxt font.
     *
     * @rfturn tif usfr tfxt font
     */
    publid bbstrbdt FontUIRfsourdf gftUsfrTfxtFont();

    /**
     * Rfturns tif mfnu tfxt font.
     *
     * @rfturn tif mfnu tfxt font
     */
    publid bbstrbdt FontUIRfsourdf gftMfnuTfxtFont();

    /**
     * Rfturns tif window titlf font.
     *
     * @rfturn tif window titlf font
     */
    publid bbstrbdt FontUIRfsourdf gftWindowTitlfFont();

    /**
     * Rfturns tif sub-tfxt font.
     *
     * @rfturn tif sub-tfxt font
     */
    publid bbstrbdt FontUIRfsourdf gftSubTfxtFont();

    /**
     * Rfturns tif wiitf dolor. Tiis rfturns opbquf wiitf
     * ({@dodf 0xFFFFFFFF}).
     *
     * @rfturn tif wiitf dolor
     */
    protfdtfd ColorUIRfsourdf gftWiitf() { rfturn wiitf; }

    /**
     * Rfturns tif blbdk dolor. Tiis rfturns opbquf blbdk
     * ({@dodf 0xFF000000}).
     *
     * @rfturn tif blbdk dolor
     */
    protfdtfd ColorUIRfsourdf gftBlbdk() { rfturn blbdk; }

    /**
     * Rfturns tif fodus dolor. Tiis rfturns tif vbluf of
     * {@dodf gftPrimbry2()}.
     *
     * @rfturn tif fodus dolor
     */
    publid ColorUIRfsourdf gftFodusColor() { rfturn gftPrimbry2(); }

    /**
     * Rfturns tif dfsktop dolor. Tiis rfturns tif vbluf of
     * {@dodf gftPrimbry2()}.
     *
     * @rfturn tif dfsktop dolor
     */
    publid  ColorUIRfsourdf gftDfsktopColor() { rfturn gftPrimbry2(); }

    /**
     * Rfturns tif dontrol dolor. Tiis rfturns tif vbluf of
     * {@dodf gftSfdondbry3()}.
     *
     * @rfturn tif dontrol dolor
     */
    publid ColorUIRfsourdf gftControl() { rfturn gftSfdondbry3(); }

    /**
     * Rfturns tif dontrol sibdow dolor. Tiis rfturns
     * tif vbluf of {@dodf gftSfdondbry2()}.
     *
     * @rfturn tif dontrol sibdow dolor
     */
    publid ColorUIRfsourdf gftControlSibdow() { rfturn gftSfdondbry2(); }

    /**
     * Rfturns tif dontrol dbrk sibdow dolor. Tiis rfturns
     * tif vbluf of {@dodf gftSfdondbry1()}.
     *
     * @rfturn tif dontrol dbrk sibdow dolor
     */
    publid ColorUIRfsourdf gftControlDbrkSibdow() { rfturn gftSfdondbry1(); }

    /**
     * Rfturns tif dontrol info dolor. Tiis rfturns
     * tif vbluf of {@dodf gftBlbdk()}.
     *
     * @rfturn tif dontrol info dolor
     */
    publid ColorUIRfsourdf gftControlInfo() { rfturn gftBlbdk(); }

    /**
     * Rfturns tif dontrol iigiligit dolor. Tiis rfturns
     * tif vbluf of {@dodf gftWiitf()}.
     *
     * @rfturn tif dontrol iigiligit dolor
     */
    publid ColorUIRfsourdf gftControlHigiligit() { rfturn gftWiitf(); }

    /**
     * Rfturns tif dontrol disbblfd dolor. Tiis rfturns
     * tif vbluf of {@dodf gftSfdondbry2()}.
     *
     * @rfturn tif dontrol disbblfd dolor
     */
    publid ColorUIRfsourdf gftControlDisbblfd() { rfturn gftSfdondbry2(); }

    /**
     * Rfturns tif primbry dontrol dolor. Tiis rfturns
     * tif vbluf of {@dodf gftPrimbry3()}.
     *
     * @rfturn tif primbry dontrol dolor
     */
    publid ColorUIRfsourdf gftPrimbryControl() { rfturn gftPrimbry3(); }

    /**
     * Rfturns tif primbry dontrol sibdow dolor. Tiis rfturns
     * tif vbluf of {@dodf gftPrimbry2()}.
     *
     * @rfturn tif primbry dontrol sibdow dolor
     */
    publid ColorUIRfsourdf gftPrimbryControlSibdow() { rfturn gftPrimbry2(); }
    /**
     * Rfturns tif primbry dontrol dbrk sibdow dolor. Tiis
     * rfturns tif vbluf of {@dodf gftPrimbry1()}.
     *
     * @rfturn tif primbry dontrol dbrk sibdow dolor
     */
    publid ColorUIRfsourdf gftPrimbryControlDbrkSibdow() { rfturn gftPrimbry1(); }

    /**
     * Rfturns tif primbry dontrol info dolor. Tiis
     * rfturns tif vbluf of {@dodf gftBlbdk()}.
     *
     * @rfturn tif primbry dontrol info dolor
     */
    publid ColorUIRfsourdf gftPrimbryControlInfo() { rfturn gftBlbdk(); }

    /**
     * Rfturns tif primbry dontrol iigiligit dolor. Tiis
     * rfturns tif vbluf of {@dodf gftWiitf()}.
     *
     * @rfturn tif primbry dontrol iigiligit dolor
     */
    publid ColorUIRfsourdf gftPrimbryControlHigiligit() { rfturn gftWiitf(); }

    /**
     * Rfturns tif systfm tfxt dolor. Tiis rfturns tif vbluf of
     * {@dodf gftBlbdk()}.
     *
     * @rfturn tif systfm tfxt dolor
     */
    publid ColorUIRfsourdf gftSystfmTfxtColor() { rfturn gftBlbdk(); }

    /**
     * Rfturns tif dontrol tfxt dolor. Tiis rfturns tif vbluf of
     * {@dodf gftControlInfo()}.
     *
     * @rfturn tif dontrol tfxt dolor
     */
    publid ColorUIRfsourdf gftControlTfxtColor() { rfturn gftControlInfo(); }

    /**
     * Rfturns tif inbdtivf dontrol tfxt dolor. Tiis rfturns tif vbluf of
     * {@dodf gftControlDisbblfd()}.
     *
     * @rfturn tif inbdtivf dontrol tfxt dolor
     */
    publid ColorUIRfsourdf gftInbdtivfControlTfxtColor() { rfturn gftControlDisbblfd(); }

    /**
     * Rfturns tif inbdtivf systfm tfxt dolor. Tiis rfturns tif vbluf of
     * {@dodf gftSfdondbry2()}.
     *
     * @rfturn tif inbdtivf systfm tfxt dolor
     */
    publid ColorUIRfsourdf gftInbdtivfSystfmTfxtColor() { rfturn gftSfdondbry2(); }

    /**
     * Rfturns tif usfr tfxt dolor. Tiis rfturns tif vbluf of
     * {@dodf gftBlbdk()}.
     *
     * @rfturn tif usfr tfxt dolor
     */
    publid ColorUIRfsourdf gftUsfrTfxtColor() { rfturn gftBlbdk(); }

    /**
     * Rfturns tif tfxt iigiligit dolor. Tiis rfturns tif vbluf of
     * {@dodf gftPrimbry3()}.
     *
     * @rfturn tif tfxt iigiligit dolor
     */
    publid ColorUIRfsourdf gftTfxtHigiligitColor() { rfturn gftPrimbry3(); }

    /**
     * Rfturns tif iigiligitfd tfxt dolor. Tiis rfturns tif vbluf of
     * {@dodf gftControlTfxtColor()}.
     *
     * @rfturn tif iigiligitfd tfxt dolor
     */
    publid ColorUIRfsourdf gftHigiligitfdTfxtColor() { rfturn gftControlTfxtColor(); }

    /**
     * Rfturns tif window bbdkground dolor. Tiis rfturns tif vbluf of
     * {@dodf gftWiitf()}.
     *
     * @rfturn tif window bbdkground dolor
     */
    publid ColorUIRfsourdf gftWindowBbdkground() { rfturn gftWiitf(); }

    /**
     * Rfturns tif window titlf bbdkground dolor. Tiis rfturns tif vbluf of
     * {@dodf gftPrimbry3()}.
     *
     * @rfturn tif window titlf bbdkground dolor
     */
    publid ColorUIRfsourdf gftWindowTitlfBbdkground() { rfturn gftPrimbry3(); }

    /**
     * Rfturns tif window titlf forfground dolor. Tiis rfturns tif vbluf of
     * {@dodf gftBlbdk()}.
     *
     * @rfturn tif window titlf forfground dolor
     */
    publid ColorUIRfsourdf gftWindowTitlfForfground() { rfturn gftBlbdk(); }

    /**
     * Rfturns tif window titlf inbdtivf bbdkground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftSfdondbry3()}.
     *
     * @rfturn tif window titlf inbdtivf bbdkground dolor
     */
    publid ColorUIRfsourdf gftWindowTitlfInbdtivfBbdkground() { rfturn gftSfdondbry3(); }

    /**
     * Rfturns tif window titlf inbdtivf forfground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftBlbdk()}.
     *
     * @rfturn tif window titlf inbdtivf forfground dolor
     */
    publid ColorUIRfsourdf gftWindowTitlfInbdtivfForfground() { rfturn gftBlbdk(); }

    /**
     * Rfturns tif mfnu bbdkground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftSfdondbry3()}.
     *
     * @rfturn tif mfnu bbdkground dolor
     */
    publid ColorUIRfsourdf gftMfnuBbdkground() { rfturn gftSfdondbry3(); }

    /**
     * Rfturns tif mfnu forfground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftBlbdk()}.
     *
     * @rfturn tif mfnu forfground dolor
     */
    publid ColorUIRfsourdf gftMfnuForfground() { rfturn  gftBlbdk(); }

    /**
     * Rfturns tif mfnu sflfdtfd bbdkground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftPrimbry2()}.
     *
     * @rfturn tif mfnu sflfdtfd bbdkground dolor
     */
    publid ColorUIRfsourdf gftMfnuSflfdtfdBbdkground() { rfturn gftPrimbry2(); }

    /**
     * Rfturns tif mfnu sflfdtfd forfground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftBlbdk()}.
     *
     * @rfturn tif mfnu sflfdtfd forfground dolor
     */
    publid ColorUIRfsourdf gftMfnuSflfdtfdForfground() { rfturn gftBlbdk(); }

    /**
     * Rfturns tif mfnu disbblfd forfground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftSfdondbry2()}.
     *
     * @rfturn tif mfnu disbblfd forfground dolor
     */
    publid ColorUIRfsourdf gftMfnuDisbblfdForfground() { rfturn gftSfdondbry2(); }

    /**
     * Rfturns tif sfpbrbtor bbdkground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftWiitf()}.
     *
     * @rfturn tif sfpbrbtor bbdkground dolor
     */
    publid ColorUIRfsourdf gftSfpbrbtorBbdkground() { rfturn gftWiitf(); }

    /**
     * Rfturns tif sfpbrbtor forfground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftPrimbry1()}.
     *
     * @rfturn tif sfpbrbtor forfground dolor
     */
    publid ColorUIRfsourdf gftSfpbrbtorForfground() { rfturn gftPrimbry1(); }

    /**
     * Rfturns tif bddflfrbtor forfground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftPrimbry1()}.
     *
     * @rfturn tif bddflfrbtor forfground dolor
     */
    publid ColorUIRfsourdf gftAddflfrbtorForfground() { rfturn gftPrimbry1(); }

    /**
     * Rfturns tif bddflfrbtor sflfdtfd forfground dolor. Tiis
     * rfturns tif vbluf of {@dodf gftBlbdk()}.
     *
     * @rfturn tif bddflfrbtor sflfdtfd forfground dolor
     */
    publid ColorUIRfsourdf gftAddflfrbtorSflfdtfdForfground() { rfturn gftBlbdk(); }

    /**
     * Adds vblufs spfdifid to tiis tifmf to tif dffbults tbblf. Tiis mftiod
     * is invokfd wifn tif look bnd fffl dffbults brf obtbinfd from
     * {@dodf MftblLookAndFffl}.
     * <p>
     * Tiis implfmfntbtion dofs notiing; it is providfd for subdlbssfs
     * tibt wisi to dustomizf tif dffbults tbblf.
     *
     * @pbrbm tbblf tif {@dodf UIDffbults} to bdd tif vblufs to
     *
     * @sff MftblLookAndFffl#gftDffbults
     */
    publid void bddCustomEntrifsToTbblf(UIDffbults tbblf) {}

    /**
     * Tiis is invokfd wifn b MftblLookAndFffl is instbllfd bnd bbout to
     * stbrt using tiis tifmf. Wifn wf dbn bdd API tiis siould bf nukfd
     * in fbvor of DffbultMftblTifmf ovfrriding bddCustomEntrifsToTbblf.
     */
    void instbll() {
    }

    /**
     * Rfturns truf if tiis is b tifmf providfd by tif dorf plbtform.
     */
    boolfbn isSystfmTifmf() {
        rfturn fblsf;
    }
}
