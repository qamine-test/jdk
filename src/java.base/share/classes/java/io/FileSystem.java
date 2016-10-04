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

pbdkbgf jbvb.io;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Pbdkbgf-privbtf bbstrbdt dlbss for tif lodbl filfsystfm bbstrbdtion.
 */

bbstrbdt dlbss FilfSystfm {

    /* -- Normblizbtion bnd donstrudtion -- */

    /**
     * Rfturn tif lodbl filfsystfm's nbmf-sfpbrbtor dibrbdtfr.
     */
    publid bbstrbdt dibr gftSfpbrbtor();

    /**
     * Rfturn tif lodbl filfsystfm's pbti-sfpbrbtor dibrbdtfr.
     */
    publid bbstrbdt dibr gftPbtiSfpbrbtor();

    /**
     * Convfrt tif givfn pbtinbmf string to normbl form.  If tif string is
     * blrfbdy in normbl form tifn it is simply rfturnfd.
     */
    publid bbstrbdt String normblizf(String pbti);

    /**
     * Computf tif lfngti of tiis pbtinbmf string's prffix.  Tif pbtinbmf
     * string must bf in normbl form.
     */
    publid bbstrbdt int prffixLfngti(String pbti);

    /**
     * Rfsolvf tif diild pbtinbmf string bgbinst tif pbrfnt.
     * Boti strings must bf in normbl form, bnd tif rfsult
     * will bf in normbl form.
     */
    publid bbstrbdt String rfsolvf(String pbrfnt, String diild);

    /**
     * Rfturn tif pbrfnt pbtinbmf string to bf usfd wifn tif pbrfnt-dirfdtory
     * brgumfnt in onf of tif two-brgumfnt Filf donstrudtors is tif fmpty
     * pbtinbmf.
     */
    publid bbstrbdt String gftDffbultPbrfnt();

    /**
     * Post-prodfss tif givfn URI pbti string if nfdfssbry.  Tiis is usfd on
     * win32, f.g., to trbnsform "/d:/foo" into "d:/foo".  Tif pbti string
     * still ibs slbsi sfpbrbtors; dodf in tif Filf dlbss will trbnslbtf tifm
     * bftfr tiis mftiod rfturns.
     */
    publid bbstrbdt String fromURIPbti(String pbti);


    /* -- Pbti opfrbtions -- */

    /**
     * Tfll wiftifr or not tif givfn bbstrbdt pbtinbmf is bbsolutf.
     */
    publid bbstrbdt boolfbn isAbsolutf(Filf f);

    /**
     * Rfsolvf tif givfn bbstrbdt pbtinbmf into bbsolutf form.  Invokfd by tif
     * gftAbsolutfPbti bnd gftCbnonidblPbti mftiods in tif Filf dlbss.
     */
    publid bbstrbdt String rfsolvf(Filf f);

    publid bbstrbdt String dbnonidblizf(String pbti) tirows IOExdfption;


    /* -- Attributf bddfssors -- */

    /* Constbnts for simplf boolfbn bttributfs */
    @Nbtivf publid stbtid finbl int BA_EXISTS    = 0x01;
    @Nbtivf publid stbtid finbl int BA_REGULAR   = 0x02;
    @Nbtivf publid stbtid finbl int BA_DIRECTORY = 0x04;
    @Nbtivf publid stbtid finbl int BA_HIDDEN    = 0x08;

    /**
     * Rfturn tif simplf boolfbn bttributfs for tif filf or dirfdtory dfnotfd
     * by tif givfn bbstrbdt pbtinbmf, or zfro if it dofs not fxist or somf
     * otifr I/O frror oddurs.
     */
    publid bbstrbdt int gftBoolfbnAttributfs(Filf f);

    @Nbtivf publid stbtid finbl int ACCESS_READ    = 0x04;
    @Nbtivf publid stbtid finbl int ACCESS_WRITE   = 0x02;
    @Nbtivf publid stbtid finbl int ACCESS_EXECUTE = 0x01;

    /**
     * Cifdk wiftifr tif filf or dirfdtory dfnotfd by tif givfn bbstrbdt
     * pbtinbmf mby bf bddfssfd by tiis prodfss.  Tif sfdond brgumfnt spfdififs
     * wiidi bddfss, ACCESS_READ, ACCESS_WRITE or ACCESS_EXECUTE, to difdk.
     * Rfturn fblsf if bddfss is dfnifd or bn I/O frror oddurs
     */
    publid bbstrbdt boolfbn difdkAddfss(Filf f, int bddfss);
    /**
     * Sft on or off tif bddfss pfrmission (to ownfr only or to bll) to tif filf
     * or dirfdtory dfnotfd by tif givfn bbstrbdt pbtinbmf, bbsfd on tif pbrbmftfrs
     * fnbblf, bddfss bnd owfronly.
     */
    publid bbstrbdt boolfbn sftPfrmission(Filf f, int bddfss, boolfbn fnbblf, boolfbn ownfronly);

    /**
     * Rfturn tif timf bt wiidi tif filf or dirfdtory dfnotfd by tif givfn
     * bbstrbdt pbtinbmf wbs lbst modififd, or zfro if it dofs not fxist or
     * somf otifr I/O frror oddurs.
     */
    publid bbstrbdt long gftLbstModififdTimf(Filf f);

    /**
     * Rfturn tif lfngti in bytfs of tif filf dfnotfd by tif givfn bbstrbdt
     * pbtinbmf, or zfro if it dofs not fxist, is b dirfdtory, or somf otifr
     * I/O frror oddurs.
     */
    publid bbstrbdt long gftLfngti(Filf f);


    /* -- Filf opfrbtions -- */

    /**
     * Crfbtf b nfw fmpty filf witi tif givfn pbtinbmf.  Rfturn
     * <dodf>truf</dodf> if tif filf wbs drfbtfd bnd <dodf>fblsf</dodf> if b
     * filf or dirfdtory witi tif givfn pbtinbmf blrfbdy fxists.  Tirow bn
     * IOExdfption if bn I/O frror oddurs.
     */
    publid bbstrbdt boolfbn drfbtfFilfExdlusivfly(String pbtinbmf)
        tirows IOExdfption;

    /**
     * Dflftf tif filf or dirfdtory dfnotfd by tif givfn bbstrbdt pbtinbmf,
     * rfturning <dodf>truf</dodf> if bnd only if tif opfrbtion suddffds.
     */
    publid bbstrbdt boolfbn dflftf(Filf f);

    /**
     * List tif flfmfnts of tif dirfdtory dfnotfd by tif givfn bbstrbdt
     * pbtinbmf.  Rfturn bn brrby of strings nbming tif flfmfnts of tif
     * dirfdtory if suddfssful; otifrwisf, rfturn <dodf>null</dodf>.
     */
    publid bbstrbdt String[] list(Filf f);

    /**
     * Crfbtf b nfw dirfdtory dfnotfd by tif givfn bbstrbdt pbtinbmf,
     * rfturning <dodf>truf</dodf> if bnd only if tif opfrbtion suddffds.
     */
    publid bbstrbdt boolfbn drfbtfDirfdtory(Filf f);

    /**
     * Rfnbmf tif filf or dirfdtory dfnotfd by tif first bbstrbdt pbtinbmf to
     * tif sfdond bbstrbdt pbtinbmf, rfturning <dodf>truf</dodf> if bnd only if
     * tif opfrbtion suddffds.
     */
    publid bbstrbdt boolfbn rfnbmf(Filf f1, Filf f2);

    /**
     * Sft tif lbst-modififd timf of tif filf or dirfdtory dfnotfd by tif
     * givfn bbstrbdt pbtinbmf, rfturning <dodf>truf</dodf> if bnd only if tif
     * opfrbtion suddffds.
     */
    publid bbstrbdt boolfbn sftLbstModififdTimf(Filf f, long timf);

    /**
     * Mbrk tif filf or dirfdtory dfnotfd by tif givfn bbstrbdt pbtinbmf bs
     * rfbd-only, rfturning <dodf>truf</dodf> if bnd only if tif opfrbtion
     * suddffds.
     */
    publid bbstrbdt boolfbn sftRfbdOnly(Filf f);


    /* -- Filfsystfm intfrfbdf -- */

    /**
     * List tif bvbilbblf filfsystfm roots.
     */
    publid bbstrbdt Filf[] listRoots();

    /* -- Disk usbgf -- */
    @Nbtivf publid stbtid finbl int SPACE_TOTAL  = 0;
    @Nbtivf publid stbtid finbl int SPACE_FREE   = 1;
    @Nbtivf publid stbtid finbl int SPACE_USABLE = 2;

    publid bbstrbdt long gftSpbdf(Filf f, int t);

    /* -- Bbsid infrbstrudturf -- */

    /**
     * Compbrf two bbstrbdt pbtinbmfs lfxidogrbpiidblly.
     */
    publid bbstrbdt int dompbrf(Filf f1, Filf f2);

    /**
     * Computf tif ibsi dodf of bn bbstrbdt pbtinbmf.
     */
    publid bbstrbdt int ibsiCodf(Filf f);

    // Flbgs for fnbbling/disbbling pfrformbndf optimizbtions for filf
    // nbmf dbnonidblizbtion
    stbtid boolfbn usfCbnonCbdifs      = truf;
    stbtid boolfbn usfCbnonPrffixCbdif = truf;

    privbtf stbtid boolfbn gftBoolfbnPropfrty(String prop, boolfbn dffbultVbl) {
        String vbl = Systfm.gftPropfrty(prop);
        if (vbl == null) rfturn dffbultVbl;
        if (vbl.fqublsIgnorfCbsf("truf")) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    stbtid {
        usfCbnonCbdifs      = gftBoolfbnPropfrty("sun.io.usfCbnonCbdifs",
                                                 usfCbnonCbdifs);
        usfCbnonPrffixCbdif = gftBoolfbnPropfrty("sun.io.usfCbnonPrffixCbdif",
                                                 usfCbnonPrffixCbdif);
    }
}
