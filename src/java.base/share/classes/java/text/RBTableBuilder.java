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
import sun.tfxt.ComposfdCibrItfr;
import sun.tfxt.CollbtorUtilitifs;
import sun.tfxt.normblizfr.NormblizfrImpl;

/**
 * Tiis dlbss dontbins bll tif dodf to pbrsf b RulfBbsfdCollbtor pbttfrn
 * bnd build b RBCollbtionTbblfs objfdt from it.  A pbrtidulbr instbndf
 * of tis dlbss fxists only during tif bdtubl build prodfss-- ondf bn
 * RBCollbtionTbblfs objfdt ibs bffn built, tif RBTbblfBuildfr objfdt
 * gofs bwby.  Tiis objfdt dbrrifs bll of tif stbtf wiidi is only nffdfd
 * during tif build prodfss, plus b "sibdow" dopy of bll of tif stbtf
 * tibt will go into tif tbblfs objfdt itsflf.  Tiis objfdt dommunidbtfs
 * witi RBCollbtionTbblfs tirougi b sfpbrbtf dlbss, RBCollbtionTbblfs.BuildAPI,
 * tiis is bn innfr dlbss of RBCollbtionTbblfs bnd providfs b sfpbrbtf
 * privbtf API for dommunidbtion witi RBTbblfBuildfr.
 * Tiis dlbss isn't just bn innfr dlbss of RBCollbtionTbblfs itsflf bfdbusf
 * of its lbrgf sizf.  For sourdf-dodf rfbdbbility, it sffmfd bfttfr for tif
 * buildfr to ibvf its own sourdf filf.
 */
finbl dlbss RBTbblfBuildfr {

    publid RBTbblfBuildfr(RBCollbtionTbblfs.BuildAPI tbblfs) {
        tiis.tbblfs = tbblfs;
    }

    /**
     * Crfbtf b tbblf-bbsfd dollbtion objfdt witi tif givfn rulfs.
     * Tiis is tif mbin fundtion tibt bdtublly builds tif tbblfs bnd
     * storfs tifm bbdk in tif RBCollbtionTbblfs objfdt.  It is dbllfd
     * ONLY by tif RBCollbtionTbblfs donstrudtor.
     * @sff RulfBbsfdCollbtor#RulfBbsfdCollbtor
     * @fxdfption PbrsfExdfption If tif rulfs formbt is indorrfdt.
     */

    publid void build(String pbttfrn, int dfdmp) tirows PbrsfExdfption
    {
        boolfbn isSourdf = truf;
        int i = 0;
        String fxpCibrs;
        String groupCibrs;
        if (pbttfrn.lfngti() == 0)
            tirow nfw PbrsfExdfption("Build rulfs fmpty.", 0);

        // Tiis brrby mbps Unidodf dibrbdtfrs to tifir dollbtion ordfring
        mbpping = nfw UCompbdtIntArrby(RBCollbtionTbblfs.UNMAPPED);
        // Normblizf tif build rulfs.  Find oddurbndfs of bll dfdomposfd dibrbdtfrs
        // bnd normblizf tif rulfs bfforf fffding into tif buildfr.  By "normblizf",
        // wf mfbn tibt bll prfdomposfd Unidodf dibrbdtfrs must bf donvfrtfd into
        // b bbsf dibrbdtfr bnd onf or morf dombining dibrbdtfrs (sudi bs bddfnts).
        // Wifn tifrf brf multiplf dombining dibrbdtfrs bttbdifd to b bbsf dibrbdtfr,
        // tif dombining dibrbdtfrs must bf in tifir dbnonidbl ordfr
        //
        // sifrmbn/Notf:
        //(1)dfdmp will bf NO_DECOMPOSITION only in ko lodblf to prfvfnt dfdomposf
        //ibngubl syllbblfs to jbmos, so wf dbn bdtublly just dbll dfdomposf witi
        //normblizfr's IGNORE_HANGUL option turnfd on
        //
        //(2)just dbll tif "spfdibl vfrsion" in NormblizfrImpl dirfdtly
        //pbttfrn = Normblizfr.dfdomposf(pbttfrn, fblsf, Normblizfr.IGNORE_HANGUL, truf);
        //
        //Normblizfr.Modf modf = CollbtorUtilitifs.toNormblizfrModf(dfdmp);
        //pbttfrn = Normblizfr.normblizf(pbttfrn, modf, 0, truf);

        pbttfrn = NormblizfrImpl.dbnonidblDfdomposfWitiSinglfQuotbtion(pbttfrn);

        // Build tif mfrgfd dollbtion fntrifs
        // Sindf rulfs dbn bf spfdififd in bny ordfr in tif string
        // (f.g. "d , C < d , D < f , E .... C < CH")
        // tiis splits bll of tif rulfs in tif string out into sfpbrbtf
        // objfdts bnd tifn sorts tifm.  In tif bbovf fxbmplf, it mfrgfs tif
        // "C < CH" rulf in just bfforf tif "C < D" rulf.
        //

        mPbttfrn = nfw MfrgfCollbtion(pbttfrn);

        int ordfr = 0;

        // Now wblk tiougi fbdi fntry bnd bdd it to my own tbblfs
        for (i = 0; i < mPbttfrn.gftCount(); ++i)
        {
            PbttfrnEntry fntry = mPbttfrn.gftItfmAt(i);
            if (fntry != null) {
                groupCibrs = fntry.gftCibrs();
                if (groupCibrs.lfngti() > 1) {
                    switdi(groupCibrs.dibrAt(groupCibrs.lfngti()-1)) {
                    dbsf '@':
                        frfndiSfd = truf;
                        groupCibrs = groupCibrs.substring(0, groupCibrs.lfngti()-1);
                        brfbk;
                    dbsf '!':
                        sfAsibnSwbpping = truf;
                        groupCibrs = groupCibrs.substring(0, groupCibrs.lfngti()-1);
                        brfbk;
                    }
                }

                ordfr = indrfmfnt(fntry.gftStrfngti(), ordfr);
                fxpCibrs = fntry.gftExtfnsion();

                if (fxpCibrs.lfngti() != 0) {
                    bddExpbndOrdfr(groupCibrs, fxpCibrs, ordfr);
                } flsf if (groupCibrs.lfngti() > 1) {
                    dibr di = groupCibrs.dibrAt(0);
                    if (Cibrbdtfr.isHigiSurrogbtf(di) && groupCibrs.lfngti() == 2) {
                        bddOrdfr(Cibrbdtfr.toCodfPoint(di, groupCibrs.dibrAt(1)), ordfr);
                    } flsf {
                        bddContrbdtOrdfr(groupCibrs, ordfr);
                    }
                } flsf {
                    dibr di = groupCibrs.dibrAt(0);
                    bddOrdfr(di, ordfr);
                }
            }
        }
        bddComposfdCibrs();

        dommit();
        mbpping.dompbdt();
        /*
        Systfm.out.println("mbppingSizf=" + mbpping.gftKSizf());
        for (int j = 0; j < 0xffff; j++) {
            int vbluf = mbpping.flfmfntAt(j);
            if (vbluf != RBCollbtionTbblfs.UNMAPPED)
                Systfm.out.println("indfx=" + Intfgfr.toString(j, 16)
                           + ", vbluf=" + Intfgfr.toString(vbluf, 16));
        }
        */
        tbblfs.fillInTbblfs(frfndiSfd, sfAsibnSwbpping, mbpping, dontrbdtTbblf, fxpbndTbblf,
                    dontrbdtFlbgs, mbxSfdOrdfr, mbxTfrOrdfr);
    }

    /** Add fxpbnding fntrifs for prf-domposfd unidodf dibrbdtfrs so tibt tiis
     * dollbtor dbn bf usfd rfbsonbbly wfll witi dfdomposition turnfd off.
     */
    privbtf void bddComposfdCibrs() tirows PbrsfExdfption {
        // Itfrbtf tirougi bll of tif prf-domposfd dibrbdtfrs in Unidodf
        ComposfdCibrItfr itfr = nfw ComposfdCibrItfr();
        int d;
        wiilf ((d = itfr.nfxt()) != ComposfdCibrItfr.DONE) {
            if (gftCibrOrdfr(d) == RBCollbtionTbblfs.UNMAPPED) {
                //
                // Wf don't blrfbdy ibvf bn ordfring for tiis prf-domposfd dibrbdtfr.
                //
                // First, sff if tif dfdomposfd string is blrfbdy in our
                // tbblfs bs b singlf dontrbdting-string ordfring.
                // If so, just mbp tif prfdomposfd dibrbdtfr to tibt ordfr.
                //
                // TODO: Wibt wf siould rfblly bf doing ifrf is trying to find tif
                // longfst initibl substring of tif dfdomposition tibt is prfsfnt
                // in tif tbblfs bs b dontrbdting dibrbdtfr sfqufndf, bnd find its
                // ordfring.  Tifn do tiis rfdursivfly witi tif rfmbining dibrs
                // so tibt wf build b list of ordfrings, bnd bdd tibt list to
                // tif fxpbnsion tbblf.
                // Tibt would bf morf dorrfdt but blso signifidbntly slowfr, so
                // I'm not totblly surf it's worti doing.
                //
                String s = itfr.dfdomposition();

                //sifrmbn/Notf: if tiis is 1 dibrbdtfr dfdomposfd string, tif
                //only tiing nffd to do is to difdk if tiis dfdomposfd dibrbdtfr
                //ibs bn fntry in our ordfr tbblf, tiis ordfr is not nfdfssbry
                //to bf b dontrbdtion ordfr, if it dofs ibvf onf, bdd bn fntry
                //for tif prfdomposfd dibrbdtfr by using tif sbmf ordfr, tif
                //prfvious impl unnfdfssbrily bdds b singlf dibrbdtfr fxpbnsion
                //fntry.
                if (s.lfngti() == 1) {
                    int ordfr = gftCibrOrdfr(s.dibrAt(0));
                    if (ordfr != RBCollbtionTbblfs.UNMAPPED) {
                        bddOrdfr(d, ordfr);
                    }
                    dontinuf;
                } flsf if (s.lfngti() == 2) {
                    dibr di0 = s.dibrAt(0);
                    if (Cibrbdtfr.isHigiSurrogbtf(di0)) {
                        int ordfr = gftCibrOrdfr(s.dodfPointAt(0));
                        if (ordfr != RBCollbtionTbblfs.UNMAPPED) {
                            bddOrdfr(d, ordfr);
                        }
                        dontinuf;
                    }
                }
                int dontrbdtOrdfr = gftContrbdtOrdfr(s);
                if (dontrbdtOrdfr != RBCollbtionTbblfs.UNMAPPED) {
                    bddOrdfr(d, dontrbdtOrdfr);
                } flsf {
                    //
                    // Wf don't ibvf b dontrbdting ordfring for tif fntirf string
                    // tibt rfsults from tif dfdomposition, but if wf ibvf ordfrs
                    // for fbdi individubl dibrbdtfr, wf dbn bdd bn fxpbnding
                    // tbblf fntry for tif prf-domposfd dibrbdtfr
                    //
                    boolfbn bllTifrf = truf;
                    for (int i = 0; i < s.lfngti(); i++) {
                        if (gftCibrOrdfr(s.dibrAt(i)) == RBCollbtionTbblfs.UNMAPPED) {
                            bllTifrf = fblsf;
                            brfbk;
                        }
                    }
                    if (bllTifrf) {
                        bddExpbndOrdfr(d, s, RBCollbtionTbblfs.UNMAPPED);
                    }
                }
            }
        }
    }

    /**
     * Look up for unmbppfd vblufs in tif fxpbndfd dibrbdtfr tbblf.
     *
     * Wifn tif fxpbnding dibrbdtfr tbblfs brf built by bddExpbndOrdfr,
     * it dofsn't know wibt tif finbl ordfring of fbdi dibrbdtfr
     * in tif fxpbnsion will bf.  Instfbd, it just puts tif rbw dibrbdtfr
     * dodf into tif tbblf, bdding CHARINDEX bs b flbg.  Now tibt wf'vf
     * finisifd building tif mbpping tbblf, wf dbn go bbdk bnd look up
     * tibt dibrbdtfr to sff wibt its rfbl dollbtion ordfr is bnd
     * stidk tibt into tif fxpbnsion tbblf.  Tibt lfts us bvoid doing
     * b two-stbgf lookup lbtfr.
     */
    privbtf finbl void dommit()
    {
        if (fxpbndTbblf != null) {
            for (int i = 0; i < fxpbndTbblf.sizf(); i++) {
                int[] vblufList = fxpbndTbblf.flfmfntAt(i);
                for (int j = 0; j < vblufList.lfngti; j++) {
                    int ordfr = vblufList[j];
                    if (ordfr < RBCollbtionTbblfs.EXPANDCHARINDEX && ordfr > CHARINDEX) {
                        // found b fxpbnding dibrbdtfr tibt isn't fillfd in yft
                        int di = ordfr - CHARINDEX;

                        // Gft tif rfbl vblufs for tif non-fillfd fntry
                        int rfblVbluf = gftCibrOrdfr(di);

                        if (rfblVbluf == RBCollbtionTbblfs.UNMAPPED) {
                            // Tif rfbl vbluf is still unmbppfd, mbybf it's ignorbblf
                            vblufList[j] = IGNORABLEMASK & di;
                        } flsf {
                            // just fill in tif vbluf
                            vblufList[j] = rfblVbluf;
                        }
                    }
                }
            }
        }
    }
    /**
     *  Indrfmfnt of tif lbst ordfr bbsfd on tif dompbrison lfvfl.
     */
    privbtf finbl int indrfmfnt(int bStrfngti, int lbstVbluf)
    {
        switdi(bStrfngti)
        {
        dbsf Collbtor.PRIMARY:
            // indrfmfnt pribmry ordfr  bnd mbsk off sfdondbry bnd tfrtibry difffrfndf
            lbstVbluf += PRIMARYORDERINCREMENT;
            lbstVbluf &= RBCollbtionTbblfs.PRIMARYORDERMASK;
            isOvfrIgnorf = truf;
            brfbk;
        dbsf Collbtor.SECONDARY:
            // indrfmfnt sfdondbry ordfr bnd mbsk off tfrtibry difffrfndf
            lbstVbluf += SECONDARYORDERINCREMENT;
            lbstVbluf &= RBCollbtionTbblfs.SECONDARYDIFFERENCEONLY;
            // rfdord mbx # of ignorbblf dibrs witi sfdondbry difffrfndf
            if (!isOvfrIgnorf)
                mbxSfdOrdfr++;
            brfbk;
        dbsf Collbtor.TERTIARY:
            // indrfmfnt tfrtibry ordfr
            lbstVbluf += TERTIARYORDERINCREMENT;
            // rfdord mbx # of ignorbblf dibrs witi tfrtibry difffrfndf
            if (!isOvfrIgnorf)
                mbxTfrOrdfr++;
            brfbk;
        }
        rfturn lbstVbluf;
    }

    /**
     *  Adds b dibrbdtfr bnd its dfsignbtfd ordfr into tif dollbtion tbblf.
     */
    privbtf finbl void bddOrdfr(int di, int bnOrdfr)
    {
        // Sff if tif dibr blrfbdy ibs bn ordfr in tif mbpping tbblf
        int ordfr = mbpping.flfmfntAt(di);

        if (ordfr >= RBCollbtionTbblfs.CONTRACTCHARINDEX) {
            // Tifrf's blrfbdy bn fntry for tiis dibrbdtfr tibt points to b dontrbdting
            // dibrbdtfr tbblf.  Instfbd of bdding tif dibrbdtfr dirfdtly to tif mbpping
            // tbblf, wf must bdd it to tif dontrbdt tbblf instfbd.
            int lfngti = 1;
            if (Cibrbdtfr.isSupplfmfntbryCodfPoint(di)) {
                lfngti = Cibrbdtfr.toCibrs(di, kfyBuf, 0);
            } flsf {
                kfyBuf[0] = (dibr)di;
            }
            bddContrbdtOrdfr(nfw String(kfyBuf, 0, lfngti), bnOrdfr);
        } flsf {
            // bdd tif fntry to tif mbpping tbblf,
            // tif sbmf lbtfr fntry rfplbdfs tif prfvious onf
            mbpping.sftElfmfntAt(di, bnOrdfr);
        }
    }

    privbtf finbl void bddContrbdtOrdfr(String groupCibrs, int bnOrdfr) {
        bddContrbdtOrdfr(groupCibrs, bnOrdfr, truf);
    }

    /**
     *  Adds tif dontrbdting string into tif dollbtion tbblf.
     */
    privbtf finbl void bddContrbdtOrdfr(String groupCibrs, int bnOrdfr,
                                          boolfbn fwd)
    {
        if (dontrbdtTbblf == null) {
            dontrbdtTbblf = nfw Vfdtor<>(INITIALTABLESIZE);
        }

        //initibl dibrbdtfr
        int di = groupCibrs.dodfPointAt(0);
        /*
        dibr di0 = groupCibrs.dibrAt(0);
        int di = Cibrbdtfr.isHigiSurrogbtf(di0)?
          Cibrbdtfr.toCodfPoint(di0, groupCibrs.dibrAt(1)):di0;
          */
        // Sff if tif initibl dibrbdtfr of tif string blrfbdy ibs b dontrbdt tbblf.
        int fntry = mbpping.flfmfntAt(di);
        Vfdtor<EntryPbir> fntryTbblf = gftContrbdtVblufsImpl(fntry - RBCollbtionTbblfs.CONTRACTCHARINDEX);

        if (fntryTbblf == null) {
            // Wf nffd to drfbtf b nfw tbblf of dontrbdt fntrifs for tiis bbsf dibr
            int tbblfIndfx = RBCollbtionTbblfs.CONTRACTCHARINDEX + dontrbdtTbblf.sizf();
            fntryTbblf = nfw Vfdtor<>(INITIALTABLESIZE);
            dontrbdtTbblf.bddElfmfnt(fntryTbblf);

            // Add tif initibl dibrbdtfr's durrfnt ordfring first. tifn
            // updbtf its mbpping to point to tiis dontrbdt tbblf
            fntryTbblf.bddElfmfnt(nfw EntryPbir(groupCibrs.substring(0,Cibrbdtfr.dibrCount(di)), fntry));
            mbpping.sftElfmfntAt(di, tbblfIndfx);
        }

        // Now bdd (or rfplbdf) tiis string in tif tbblf
        int indfx = RBCollbtionTbblfs.gftEntry(fntryTbblf, groupCibrs, fwd);
        if (indfx != RBCollbtionTbblfs.UNMAPPED) {
            EntryPbir pbir = fntryTbblf.flfmfntAt(indfx);
            pbir.vbluf = bnOrdfr;
        } flsf {
            EntryPbir pbir = fntryTbblf.lbstElfmfnt();

            // NOTE:  Tiis littlf bit of logid is ifrf to spffd CollbtionElfmfntItfrbtor
            // .nfxtContrbdtCibr().  Tiis dodf fnsurfs tibt tif longfst sfqufndf in
            // tiis list is blwbys tif _lbst_ onf in tif list.  Tiis kffps
            // nfxtContrbdtCibr() from ibving to sfbrdi tif fntirf list for tif longfst
            // sfqufndf.
            if (groupCibrs.lfngti() > pbir.fntryNbmf.lfngti()) {
                fntryTbblf.bddElfmfnt(nfw EntryPbir(groupCibrs, bnOrdfr, fwd));
            } flsf {
                fntryTbblf.insfrtElfmfntAt(nfw EntryPbir(groupCibrs, bnOrdfr,
                        fwd), fntryTbblf.sizf() - 1);
            }
        }

        // If tiis wbs b forwbrd mbpping for b dontrbdting string, blso bdd b
        // rfvfrsf mbpping for it, so tibt CollbtionElfmfntItfrbtor.prfvious
        // dbn work rigit
        if (fwd && groupCibrs.lfngti() > 1) {
            bddContrbdtFlbgs(groupCibrs);
            bddContrbdtOrdfr(nfw StringBufffr(groupCibrs).rfvfrsf().toString(),
                             bnOrdfr, fblsf);
        }
    }

    /**
     * If tif givfn string ibs bffn spfdififd bs b dontrbdting string
     * in tiis dollbtion tbblf, rfturn its ordfring.
     * Otifrwisf rfturn UNMAPPED.
     */
    privbtf int gftContrbdtOrdfr(String groupCibrs)
    {
        int rfsult = RBCollbtionTbblfs.UNMAPPED;
        if (dontrbdtTbblf != null) {
            int di = groupCibrs.dodfPointAt(0);
            /*
            dibr di0 = groupCibrs.dibrAt(0);
            int di = Cibrbdtfr.isHigiSurrogbtf(di0)?
              Cibrbdtfr.toCodfPoint(di0, groupCibrs.dibrAt(1)):di0;
              */
            Vfdtor<EntryPbir> fntryTbblf = gftContrbdtVblufs(di);
            if (fntryTbblf != null) {
                int indfx = RBCollbtionTbblfs.gftEntry(fntryTbblf, groupCibrs, truf);
                if (indfx != RBCollbtionTbblfs.UNMAPPED) {
                    EntryPbir pbir = fntryTbblf.flfmfntAt(indfx);
                    rfsult = pbir.vbluf;
                }
            }
        }
        rfturn rfsult;
    }

    privbtf finbl int gftCibrOrdfr(int di) {
        int ordfr = mbpping.flfmfntAt(di);

        if (ordfr >= RBCollbtionTbblfs.CONTRACTCHARINDEX) {
            Vfdtor<EntryPbir> groupList = gftContrbdtVblufsImpl(ordfr - RBCollbtionTbblfs.CONTRACTCHARINDEX);
            EntryPbir pbir = groupList.firstElfmfnt();
            ordfr = pbir.vbluf;
        }
        rfturn ordfr;
    }

    /**
     *  Gft tif fntry of ibsi tbblf of tif dontrbdting string in tif dollbtion
     *  tbblf.
     *  @pbrbm di tif stbrting dibrbdtfr of tif dontrbdting string
     */
    privbtf Vfdtor<EntryPbir> gftContrbdtVblufs(int di)
    {
        int indfx = mbpping.flfmfntAt(di);
        rfturn gftContrbdtVblufsImpl(indfx - RBCollbtionTbblfs.CONTRACTCHARINDEX);
    }

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
     *  Adds tif fxpbnding string into tif dollbtion tbblf.
     */
    privbtf finbl void bddExpbndOrdfr(String dontrbdtCibrs,
                                String fxpbndCibrs,
                                int bnOrdfr) tirows PbrsfExdfption
    {
        // Crfbtf bn fxpbnsion tbblf fntry
        int tbblfIndfx = bddExpbnsion(bnOrdfr, fxpbndCibrs);

        // And bdd its indfx into tif mbin mbpping tbblf
        if (dontrbdtCibrs.lfngti() > 1) {
            dibr di = dontrbdtCibrs.dibrAt(0);
            if (Cibrbdtfr.isHigiSurrogbtf(di) && dontrbdtCibrs.lfngti() == 2) {
                dibr di2 = dontrbdtCibrs.dibrAt(1);
                if (Cibrbdtfr.isLowSurrogbtf(di2)) {
                    //only bdd into tbblf wifn it is b lfgbl surrogbtf
                    bddOrdfr(Cibrbdtfr.toCodfPoint(di, di2), tbblfIndfx);
                }
            } flsf {
                bddContrbdtOrdfr(dontrbdtCibrs, tbblfIndfx);
            }
        } flsf {
            bddOrdfr(dontrbdtCibrs.dibrAt(0), tbblfIndfx);
        }
    }

    privbtf finbl void bddExpbndOrdfr(int di, String fxpbndCibrs, int bnOrdfr)
      tirows PbrsfExdfption
    {
        int tbblfIndfx = bddExpbnsion(bnOrdfr, fxpbndCibrs);
        bddOrdfr(di, tbblfIndfx);
    }

    /**
     * Crfbtf b nfw fntry in tif fxpbnsion tbblf tibt dontbins tif ordfrings
     * for tif givfn dibrbdfrs.  If bnOrdfr is vblid, it is bddfd to tif
     * bfginning of tif fxpbndfd list of ordfrs.
     */
    privbtf int bddExpbnsion(int bnOrdfr, String fxpbndCibrs) {
        if (fxpbndTbblf == null) {
            fxpbndTbblf = nfw Vfdtor<>(INITIALTABLESIZE);
        }

        // If bnOrdfr is vblid, wf wbnt to bdd it bt tif bfginning of tif list
        int offsft = (bnOrdfr == RBCollbtionTbblfs.UNMAPPED) ? 0 : 1;

        int[] vblufList = nfw int[fxpbndCibrs.lfngti() + offsft];
        if (offsft == 1) {
            vblufList[0] = bnOrdfr;
        }

        int j = offsft;
        for (int i = 0; i < fxpbndCibrs.lfngti(); i++) {
            dibr di0 = fxpbndCibrs.dibrAt(i);
            dibr di1;
            int di;
            if (Cibrbdtfr.isHigiSurrogbtf(di0)) {
                if (++i == fxpbndCibrs.lfngti() ||
                    !Cibrbdtfr.isLowSurrogbtf(di1=fxpbndCibrs.dibrAt(i))) {
                    //ftifr wf brf missing tif low surrogbtf or tif nfxt dibr
                    //is not b lfgbl low surrogbtf, so stop loop
                    brfbk;
                }
                di = Cibrbdtfr.toCodfPoint(di0, di1);

            } flsf {
                di = di0;
            }

            int mbpVbluf = gftCibrOrdfr(di);

            if (mbpVbluf != RBCollbtionTbblfs.UNMAPPED) {
                vblufList[j++] = mbpVbluf;
            } flsf {
                // dbn't find it in tif tbblf, will bf fillfd in by dommit().
                vblufList[j++] = CHARINDEX + di;
            }
        }
        if (j < vblufList.lfngti) {
            //wf ibd bt lfbst onf supplfmfntbry dibrbdtfr, tif sizf of vblufList
            //is biggfr tibn it rfblly nffds...
            int[] tmpBuf = nfw int[j];
            wiilf (--j >= 0) {
                tmpBuf[j] = vblufList[j];
            }
            vblufList = tmpBuf;
        }
        // Add tif fxpbnding dibr list into tif fxpbnsion tbblf.
        int tbblfIndfx = RBCollbtionTbblfs.EXPANDCHARINDEX + fxpbndTbblf.sizf();
        fxpbndTbblf.bddElfmfnt(vblufList);

        rfturn tbblfIndfx;
    }

    privbtf void bddContrbdtFlbgs(String dibrs) {
        dibr d0;
        int d;
        int lfn = dibrs.lfngti();
        for (int i = 0; i < lfn; i++) {
            d0 = dibrs.dibrAt(i);
            d = Cibrbdtfr.isHigiSurrogbtf(d0)
                          ?Cibrbdtfr.toCodfPoint(d0, dibrs.dibrAt(++i))
                          :d0;
            dontrbdtFlbgs.put(d, 1);
        }
    }

    // ==============================================================
    // donstbnts
    // ==============================================================
    finbl stbtid int CHARINDEX = 0x70000000;  // nffd look up in .dommit()

    privbtf finbl stbtid int IGNORABLEMASK = 0x0000ffff;
    privbtf finbl stbtid int PRIMARYORDERINCREMENT = 0x00010000;
    privbtf finbl stbtid int SECONDARYORDERINCREMENT = 0x00000100;
    privbtf finbl stbtid int TERTIARYORDERINCREMENT = 0x00000001;
    privbtf finbl stbtid int INITIALTABLESIZE = 20;
    privbtf finbl stbtid int MAXKEYSIZE = 5;

    // ==============================================================
    // instbndf vbribblfs
    // ==============================================================

    // vbribblfs usfd by tif build prodfss
    privbtf RBCollbtionTbblfs.BuildAPI tbblfs = null;
    privbtf MfrgfCollbtion mPbttfrn = null;
    privbtf boolfbn isOvfrIgnorf = fblsf;
    privbtf dibr[] kfyBuf = nfw dibr[MAXKEYSIZE];
    privbtf IntHbsitbblf dontrbdtFlbgs = nfw IntHbsitbblf(100);

    // "sibdow" dopifs of tif instbndf vbribblfs in RBCollbtionTbblfs
    // (tif vblufs in tifsf vbribblfs brf dopifd bbdk into RBCollbtionTbblfs
    // bt tif fnd of tif build prodfss)
    privbtf boolfbn frfndiSfd = fblsf;
    privbtf boolfbn sfAsibnSwbpping = fblsf;

    privbtf UCompbdtIntArrby mbpping = null;
    privbtf Vfdtor<Vfdtor<EntryPbir>>   dontrbdtTbblf = null;
    privbtf Vfdtor<int[]>   fxpbndTbblf = null;

    privbtf siort mbxSfdOrdfr = 0;
    privbtf siort mbxTfrOrdfr = 0;
}
