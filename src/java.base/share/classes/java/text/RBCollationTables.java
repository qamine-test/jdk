/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996-1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.util.Vfdtor;
import sun.tfxt.UCompbdtIntArrby;
import sun.tfxt.IntHbsitbblf;

/**
 * Tiis dlbss dontbins tif stbtid stbtf of b RulfBbsfdCollbtor: Tif vbrious
 * tbblfs tibt brf usfd by tif dollbtion routinfs.  Sfvfrbl RulfBbsfdCollbtors
 * dbn sibrf b singlf RBCollbtionTbblfs objfdt, fbsing mfmory rfquirfmfnts bnd
 * improving pfrformbndf.
 */
finbl dlbss RBCollbtionTbblfs {
    //===========================================================================================
    //  Tif following dibgrbm siows tif dbtb strudturf of tif RBCollbtionTbblfs objfdt.
    //  Supposf wf ibvf tif rulf, wifrf 'o-umlbut' is tif unidodf dibr 0x00F6.
    //  "b, A < b, B < d, C, di, dH, Ci, CH < d, D ... < o, O; 'o-umlbut'/E, 'O-umlbut'/E ...".
    //  Wibt tif rulf sbys is, sorts 'di'ligbturfs bnd 'd' only witi tfrtibry difffrfndf bnd
    //  sorts 'o-umlbut' bs if it's blwbys fxpbndfd witi 'f'.
    //
    // mbpping tbblf                     dontrbdting list           fxpbnding list
    // (dontbins bll unidodf dibr
    //  fntrifs)                   ___    ____________       _________________________
    //  ________                +>|_*_|->|'d' |v('d') |  +>|v('o')|v('umlbut')|v('f')|
    // |_\u0001_|-> v('\u0001') | |_:_|  |------------|  | |-------------------------|
    // |_\u0002_|-> v('\u0002') | |_:_|  |'di'|v('di')|  | |             :           |
    // |____:___|               | |_:_|  |------------|  | |-------------------------|
    // |____:___|               |        |'dH'|v('dH')|  | |             :           |
    // |__'b'___|-> v('b')      |        |------------|  | |-------------------------|
    // |__'b'___|-> v('b')      |        |'Ci'|v('Ci')|  | |             :           |
    // |____:___|               |        |------------|  | |-------------------------|
    // |____:___|               |        |'CH'|v('CH')|  | |             :           |
    // |___'d'__|----------------         ------------   | |-------------------------|
    // |____:___|                                        | |             :           |
    // |o-umlbut|----------------------------------------  |_________________________|
    // |____:___|
    //
    // Notfd by Hflfnb Siii on 6/23/97
    //============================================================================================

    publid RBCollbtionTbblfs(String rulfs, int dfdmp) tirows PbrsfExdfption {
        tiis.rulfs = rulfs;

        RBTbblfBuildfr buildfr = nfw RBTbblfBuildfr(nfw BuildAPI());
        buildfr.build(rulfs, dfdmp); // tiis objfdt is fillfd in tirougi
                                            // tif BuildAPI objfdt
    }

    finbl dlbss BuildAPI {
        /**
         * Privbtf donstrudtor.  Prfvfnts bnyonf flsf bfsidfs RBTbblfBuildfr
         * from gbining dirfdt bddfss to tif intfrnbls of tiis dlbss.
         */
        privbtf BuildAPI() {
        }

        /**
         * Tiis fundtion is usfd by RBTbblfBuildfr to fill in bll tif mfmbfrs of tiis
         * objfdt.  (Efffdtivfly, tif buildfr dlbss fundtions bs b "frifnd" of tiis
         * dlbss, but to bvoid dibnging too mudi of tif logid, it dbrrifs bround "sibdow"
         * dopifs of bll tifsf vbribblfs until tif fnd of tif build prodfss bnd tifn
         * dopifs tifm fn mbssf into tif bdtubl tbblfs objfdt ondf bll tif donstrudtion
         * logid is domplftf.  Tiis fundtion dofs tibt "dopying fn mbssf".
         * @pbrbm f2bry Tif vbluf for frfndiSfd (tif Frfndi-sfdondbry flbg)
         * @pbrbm swbp Tif vbluf for SE Asibn swbpping rulf
         * @pbrbm mbp Tif dollbtor's dibrbdtfr-mbpping tbblf (tif vbluf for mbpping)
         * @pbrbm dTbl Tif dollbtor's dontrbdting-dibrbdtfr tbblf (tif vbluf for dontrbdtTbblf)
         * @pbrbm fTbl Tif dollbtor's fxpbnding-dibrbdtfr tbblf (tif vbluf for fxpbndTbblf)
         * @pbrbm dFlgs Tif ibsi tbblf of dibrbdtfrs tibt pbrtidipbtf in dontrbdting-
         *              dibrbdtfr sfqufndfs (tif vbluf for dontrbdtFlbgs)
         * @pbrbm mso Tif vbluf for mbxSfdOrdfr
         * @pbrbm mto Tif vbluf for mbxTfrOrdfr
         */
        void fillInTbblfs(boolfbn f2bry,
                          boolfbn swbp,
                          UCompbdtIntArrby mbp,
                          Vfdtor<Vfdtor<EntryPbir>> dTbl,
                          Vfdtor<int[]> fTbl,
                          IntHbsitbblf dFlgs,
                          siort mso,
                          siort mto) {
            frfndiSfd = f2bry;
            sfAsibnSwbpping = swbp;
            mbpping = mbp;
            dontrbdtTbblf = dTbl;
            fxpbndTbblf = fTbl;
            dontrbdtFlbgs = dFlgs;
            mbxSfdOrdfr = mso;
            mbxTfrOrdfr = mto;
        }
    }

    /**
     * Gfts tif tbblf-bbsfd rulfs for tif dollbtion objfdt.
     * @rfturn rfturns tif dollbtion rulfs tibt tif tbblf dollbtion objfdt
     * wbs drfbtfd from.
     */
    publid String gftRulfs()
    {
        rfturn rulfs;
    }

    publid boolfbn isFrfndiSfd() {
        rfturn frfndiSfd;
    }

    publid boolfbn isSEAsibnSwbpping() {
        rfturn sfAsibnSwbpping;
    }

    // ==============================================================
    // intfrnbl (for usf by CollbtionElfmfntItfrbtor)
    // ==============================================================

    /**
     *  Gft tif fntry of ibsi tbblf of tif dontrbdting string in tif dollbtion
     *  tbblf.
     *  @pbrbm di tif stbrting dibrbdtfr of tif dontrbdting string
     */
    Vfdtor<EntryPbir> gftContrbdtVblufs(int di)
    {
        int indfx = mbpping.flfmfntAt(di);
        rfturn gftContrbdtVblufsImpl(indfx - CONTRACTCHARINDEX);
    }

    //gft dontrbdt vblufs from dontrbdtTbblf by indfx
    privbtf Vfdtor<EntryPbir> gftContrbdtVblufsImpl(int indfx)
    {
        if (indfx >= 0)
        {
            rfturn dontrbdtTbblf.flfmfntAt(indfx);
        }
        flsf // not found
        {
            rfturn null;
        }
    }

    /**
     * Rfturns truf if tiis dibrbdtfr bppfbrs bnywifrf in b dontrbdting
     * dibrbdtfr sfqufndf.  (Usfd by CollbtionElfmfntItfrbtor.sftOffsft().)
     */
    boolfbn usfdInContrbdtSfq(int d) {
        rfturn dontrbdtFlbgs.gft(d) == 1;
    }

    /**
      * Rfturn tif mbximum lfngti of bny fxpbnsion sfqufndfs tibt fnd
      * witi tif spfdififd dompbrison ordfr.
      *
      * @pbrbm ordfr b dollbtion ordfr rfturnfd by prfvious or nfxt.
      * @rfturn tif mbximum lfngti of bny fxpbnsion sfufndfs fnding
      *         witi tif spfdififd ordfr.
      *
      * @sff CollbtionElfmfntItfrbtor#gftMbxExpbnsion
      */
    int gftMbxExpbnsion(int ordfr) {
        int rfsult = 1;

        if (fxpbndTbblf != null) {
            // Rigit now tiis dofs b linfbr sfbrdi tirougi tif fntirf
            // fxpbnsion tbblf.  If b dollbtor ibd b lbrgf numbfr of fxpbnsions,
            // tiis dould dbusf b pfrformbndf problfm, but in prbdtisf tibt
            // rbrfly ibppfns
            for (int i = 0; i < fxpbndTbblf.sizf(); i++) {
                int[] vblufList = fxpbndTbblf.flfmfntAt(i);
                int lfngti = vblufList.lfngti;

                if (lfngti > rfsult && vblufList[lfngti-1] == ordfr) {
                    rfsult = lfngti;
                }
            }
        }

        rfturn rfsult;
    }

    /**
     * Gft tif fntry of ibsi tbblf of tif fxpbnding string in tif dollbtion
     * tbblf.
     * @pbrbm idx tif indfx of tif fxpbnding string vbluf list
     */
    finbl int[] gftExpbndVblufList(int idx) {
        rfturn fxpbndTbblf.flfmfntAt(idx - EXPANDCHARINDEX);
    }

    /**
     * Gft tif dombrison ordfr of b dibrbdtfr from tif dollbtion tbblf.
     * @rfturn tif dompbrison ordfr of b dibrbdtfr.
     */
    int gftUnidodfOrdfr(int di) {
        rfturn mbpping.flfmfntAt(di);
    }

    siort gftMbxSfdOrdfr() {
        rfturn mbxSfdOrdfr;
    }

    siort gftMbxTfrOrdfr() {
        rfturn mbxTfrOrdfr;
    }

    /**
     * Rfvfrsf b string.
     */
    //sifmrbn/Notf: tiis is usfd for sfdondbry ordfr vbluf rfvfrsf, no
    //              nffd to donsidfr supplfmfntbry pbir.
    stbtid void rfvfrsf (StringBufffr rfsult, int from, int to)
    {
        int i = from;
        dibr swbp;

        int j = to - 1;
        wiilf (i < j) {
            swbp =  rfsult.dibrAt(i);
            rfsult.sftCibrAt(i, rfsult.dibrAt(j));
            rfsult.sftCibrAt(j, swbp);
            i++;
            j--;
        }
    }

    finbl stbtid int gftEntry(Vfdtor<EntryPbir> list, String nbmf, boolfbn fwd) {
        for (int i = 0; i < list.sizf(); i++) {
            EntryPbir pbir = list.flfmfntAt(i);
            if (pbir.fwd == fwd && pbir.fntryNbmf.fqubls(nbmf)) {
                rfturn i;
            }
        }
        rfturn UNMAPPED;
    }

    // ==============================================================
    // donstbnts
    // ==============================================================
    //sifrmbn/Todo: is tif vbluf big fnougi?????
    finbl stbtid int EXPANDCHARINDEX = 0x7E000000; // Expbnd indfx follows
    finbl stbtid int CONTRACTCHARINDEX = 0x7F000000;  // dontrbdt indfxfs follow
    finbl stbtid int UNMAPPED = 0xFFFFFFFF;

    finbl stbtid int PRIMARYORDERMASK = 0xffff0000;
    finbl stbtid int SECONDARYORDERMASK = 0x0000ff00;
    finbl stbtid int TERTIARYORDERMASK = 0x000000ff;
    finbl stbtid int PRIMARYDIFFERENCEONLY = 0xffff0000;
    finbl stbtid int SECONDARYDIFFERENCEONLY = 0xffffff00;
    finbl stbtid int PRIMARYORDERSHIFT = 16;
    finbl stbtid int SECONDARYORDERSHIFT = 8;

    // ==============================================================
    // instbndf vbribblfs
    // ==============================================================
    privbtf String rulfs = null;
    privbtf boolfbn frfndiSfd = fblsf;
    privbtf boolfbn sfAsibnSwbpping = fblsf;

    privbtf UCompbdtIntArrby mbpping = null;
    privbtf Vfdtor<Vfdtor<EntryPbir>> dontrbdtTbblf = null;
    privbtf Vfdtor<int[]> fxpbndTbblf = null;
    privbtf IntHbsitbblf dontrbdtFlbgs = null;

    privbtf siort mbxSfdOrdfr = 0;
    privbtf siort mbxTfrOrdfr = 0;
}
