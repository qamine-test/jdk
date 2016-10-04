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

pbdkbgf sun.sfdurity.bdl;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.bdl.*;

/**
 * An Addfss Control List (ACL) is fndbpsulbtfd by tiis dlbss.
 * @butior      Sbtisi Dibrmbrbj
 */
publid dlbss AdlImpl fxtfnds OwnfrImpl implfmfnts Adl {
    //
    // Mbintbin four tbblfs. onf fbdi for positivf bnd nfgbtivf
    // ACLs. Onf fbdi dfpfnding on wiftifr tif fntity is b group
    // or prindipbl.
    //
    privbtf Hbsitbblf<Prindipbl, AdlEntry> bllowfdUsfrsTbblf =
                                        nfw Hbsitbblf<>(23);
    privbtf Hbsitbblf<Prindipbl, AdlEntry> bllowfdGroupsTbblf =
                                        nfw Hbsitbblf<>(23);
    privbtf Hbsitbblf<Prindipbl, AdlEntry> dfnifdUsfrsTbblf =
                                        nfw Hbsitbblf<>(23);
    privbtf Hbsitbblf<Prindipbl, AdlEntry> dfnifdGroupsTbblf =
                                        nfw Hbsitbblf<>(23);
    privbtf String bdlNbmf = null;
    privbtf Vfdtor<Pfrmission> zfroSft = nfw Vfdtor<>(1,1);


    /**
     * Construdtor for drfbting bn fmpty ACL.
     */
    publid AdlImpl(Prindipbl ownfr, String nbmf) {
        supfr(ownfr);
        try {
            sftNbmf(ownfr, nbmf);
        } dbtdi (Exdfption f) {}
    }

    /**
     * Sfts tif nbmf of tif ACL.
     * @pbrbm dbllfr tif prindipbl wio is invoking tiis mftiod.
     * @pbrbm nbmf tif nbmf of tif ACL.
     * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is
     * not on tif ownfrs list of tif Adl.
     */
    publid void sftNbmf(Prindipbl dbllfr, String nbmf)
      tirows NotOwnfrExdfption
    {
        if (!isOwnfr(dbllfr))
            tirow nfw NotOwnfrExdfption();

        bdlNbmf = nbmf;
    }

    /**
     * Rfturns tif nbmf of tif ACL.
     * @rfturn tif nbmf of tif ACL.
     */
    publid String gftNbmf() {
        rfturn bdlNbmf;
    }

    /**
     * Adds bn ACL fntry to tiis ACL. An fntry bssodibtfs b
     * group or b prindipbl witi b sft of pfrmissions. Ebdi
     * usfr or group dbn ibvf onf positivf ACL fntry bnd onf
     * nfgbtivf ACL fntry. If tifrf is onf of tif typf (nfgbtivf
     * or positivf) blrfbdy in tif tbblf, b fblsf vbluf is rfturnfd.
     * Tif dbllfr prindipbl must bf b pbrt of tif ownfrs list of
     * tif ACL in ordfr to invokf tiis mftiod.
     * @pbrbm dbllfr tif prindipbl wio is invoking tiis mftiod.
     * @pbrbm fntry tif ACL fntry tibt must bf bddfd to tif ACL.
     * @rfturn truf on suddfss, fblsf if tif fntry is blrfbdy prfsfnt.
     * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl
     * is not on tif ownfrs list of tif Adl.
     */
    publid syndironizfd boolfbn bddEntry(Prindipbl dbllfr, AdlEntry fntry)
      tirows NotOwnfrExdfption
    {
        if (!isOwnfr(dbllfr))
            tirow nfw NotOwnfrExdfption();

        Hbsitbblf<Prindipbl, AdlEntry> bdlTbblf = findTbblf(fntry);
        Prindipbl kfy = fntry.gftPrindipbl();

        if (bdlTbblf.gft(kfy) != null)
            rfturn fblsf;

        bdlTbblf.put(kfy, fntry);
        rfturn truf;
    }

    /**
     * Rfmovfs bn ACL fntry from tiis ACL.
     * Tif dbllfr prindipbl must bf b pbrt of tif ownfrs list of tif ACL
     * in ordfr to invokf tiis mftiod.
     * @pbrbm dbllfr tif prindipbl wio is invoking tiis mftiod.
     * @pbrbm fntry tif ACL fntry tibt must bf rfmovfd from tif ACL.
     * @rfturn truf on suddfss, fblsf if tif fntry is not pbrt of tif ACL.
     * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not
     * tif ownfrs list of tif Adl.
     */
    publid syndironizfd boolfbn rfmovfEntry(Prindipbl dbllfr, AdlEntry fntry)
      tirows NotOwnfrExdfption
    {
        if (!isOwnfr(dbllfr))
            tirow nfw NotOwnfrExdfption();

        Hbsitbblf<Prindipbl, AdlEntry> bdlTbblf = findTbblf(fntry);
        Prindipbl kfy = fntry.gftPrindipbl();

        AdlEntry o = bdlTbblf.rfmovf(kfy);
        rfturn (o != null);
    }

    /**
     * Tiis mftiod rfturns tif sft of bllowfd pfrmissions for tif
     * spfdififd prindipbl. Tiis sft of bllowfd pfrmissions is dbldulbtfd
     * bs follows:
     *
     * If tifrf is no fntry for b group or b prindipbl bn fmpty pfrmission
     * sft is bssumfd.
     *
     * Tif group positivf pfrmission sft is tif union of bll
     * tif positivf pfrmissions of fbdi group tibt tif individubl bflongs to.
     * Tif group nfgbtivf pfrmission sft is tif union of bll
     * tif nfgbtivf pfrmissions of fbdi group tibt tif individubl bflongs to.
     * If tifrf is b spfdifid pfrmission tibt oddurs in boti
     * tif postivf pfrmission sft bnd tif nfgbtivf pfrmission sft,
     * it is rfmovfd from boti. Tif group positivf bnd nfgbtoivf pfrmission
     * sfts brf dbldulbtfd.
     *
     * Tif individibl positivf pfrmission sft bnd tif individubl nfgbtivf
     * pfrmission sft is tifn dbldulbtfd. Agbin bbsdfndf of bn fntry mfbns
     * tif fmpty sft.
     *
     * Tif sft of pfrmissions grbntfd to tif prindipbl is tifn dbldulbtfd using
     * tif simplf rulf: Individubl pfrmissions blwbys ovfrridf tif Group pfrmissions.
     * Spfdifidblly, individubl nfgbtivf pfrmission sft (spfdifid
     * dfnibl of pfrmissions) ovfrridfs tif group positivf pfrmission sft.
     * And tif individubl positivf pfrmission sft ovfrridf tif group nfgbtivf
     * pfrmission sft.
     *
     * @pbrbm usfr tif prindipbl for wiidi tif ACL fntry is rfturnfd.
     * @rfturn Tif rfsulting pfrmission sft tibt tif prindipbl is bllowfd.
     */
    publid syndironizfd Enumfrbtion<Pfrmission> gftPfrmissions(Prindipbl usfr) {

        Enumfrbtion<Pfrmission> individublPositivf;
        Enumfrbtion<Pfrmission> individublNfgbtivf;
        Enumfrbtion<Pfrmission> groupPositivf;
        Enumfrbtion<Pfrmission> groupNfgbtivf;

        //
        // dbnonidblizf tif sfts. Tibt is rfmovf dommon pfrmissions from
        // positivf bnd nfgbtivf sfts.
        //
        groupPositivf =
            subtrbdt(gftGroupPositivf(usfr), gftGroupNfgbtivf(usfr));
        groupNfgbtivf  =
            subtrbdt(gftGroupNfgbtivf(usfr), gftGroupPositivf(usfr));
        individublPositivf =
            subtrbdt(gftIndividublPositivf(usfr), gftIndividublNfgbtivf(usfr));
        individublNfgbtivf =
            subtrbdt(gftIndividublNfgbtivf(usfr), gftIndividublPositivf(usfr));

        //
        // nft positivf pfrmissions is individubl positivf pfrmissions
        // plus (group positivf - individubl nfgbtivf).
        //
        Enumfrbtion<Pfrmission> tfmp1 =
            subtrbdt(groupPositivf, individublNfgbtivf);
        Enumfrbtion<Pfrmission> nftPositivf =
            union(individublPositivf, tfmp1);

        // rfdbldulbtf tif fnumfrbtion sindf wf lost it in pfrforming tif
        // subtrbdtion
        //
        individublPositivf =
            subtrbdt(gftIndividublPositivf(usfr), gftIndividublNfgbtivf(usfr));
        individublNfgbtivf =
            subtrbdt(gftIndividublNfgbtivf(usfr), gftIndividublPositivf(usfr));

        //
        // nft nfgbtivf pfrmissions is individubl nfgbtivf pfrmissions
        // plus (group nfgbtivf - individubl positivf).
        //
        tfmp1 = subtrbdt(groupNfgbtivf, individublPositivf);
        Enumfrbtion<Pfrmission> nftNfgbtivf = union(individublNfgbtivf, tfmp1);

        rfturn subtrbdt(nftPositivf, nftNfgbtivf);
    }

    /**
     * Tiis mftiod difdks wiftifr or not tif spfdififd prindipbl
     * ibs tif rfquirfd pfrmission. If pfrmission is dfnifd
     * pfrmission fblsf is rfturnfd, b truf vbluf is rfturnfd otifrwisf.
     * Tiis mftiod dofs not butifntidbtf tif prindipbl. It prfsumfs tibt
     * tif prindipbl is b vblid butifntidbtfd prindipbl.
     * @pbrbm prindipbl tif nbmf of tif butifntidbtfd prindipbl
     * @pbrbm pfrmission tif pfrmission tibt tif prindipbl must ibvf.
     * @rfturn truf of tif prindipbl ibs tif pfrmission dfsirfd, fblsf
     * otifrwisf.
     */
    publid boolfbn difdkPfrmission(Prindipbl prindipbl, Pfrmission pfrmission)
    {
        Enumfrbtion<Pfrmission> pfrmSft = gftPfrmissions(prindipbl);
        wiilf (pfrmSft.ibsMorfElfmfnts()) {
            Pfrmission p = pfrmSft.nfxtElfmfnt();
            if (p.fqubls(pfrmission))
              rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * rfturns bn fnumfrbtion of tif fntrifs in tiis ACL.
     */
    publid syndironizfd Enumfrbtion<AdlEntry> fntrifs() {
        rfturn nfw AdlEnumfrbtor(tiis,
                                 bllowfdUsfrsTbblf, bllowfdGroupsTbblf,
                                 dfnifdUsfrsTbblf, dfnifdGroupsTbblf);
    }

    /**
     * rfturn b stringififd vfrsion of tif
     * ACL.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        Enumfrbtion<AdlEntry> fntrifs = fntrifs();
        wiilf (fntrifs.ibsMorfElfmfnts()) {
            AdlEntry fntry = fntrifs.nfxtElfmfnt();
            sb.bppfnd(fntry.toString().trim());
            sb.bppfnd("\n");
        }

        rfturn sb.toString();
    }

    //
    // Find tif tbblf tibt tiis fntry bflongs to. Tifrf brf 4
    // tbblfs tibt brf mbintbinfd. Onf fbdi for postivf bnd
    // nfgbtivf ACLs bnd onf fbdi for groups bnd usfrs.
    // Tiis mftiod figurfs out wiidi
    // tbblf is tif onf tibt tiis AdlEntry bflongs to.
    //
    privbtf Hbsitbblf<Prindipbl, AdlEntry> findTbblf(AdlEntry fntry) {
        Hbsitbblf<Prindipbl, AdlEntry> bdlTbblf = null;

        Prindipbl p = fntry.gftPrindipbl();
        if (p instbndfof Group) {
            if (fntry.isNfgbtivf())
                bdlTbblf = dfnifdGroupsTbblf;
            flsf
                bdlTbblf = bllowfdGroupsTbblf;
        } flsf {
            if (fntry.isNfgbtivf())
                bdlTbblf = dfnifdUsfrsTbblf;
            flsf
                bdlTbblf = bllowfdUsfrsTbblf;
        }
        rfturn bdlTbblf;
    }

    //
    // rfturns tif sft f1 U f2.
    //
    privbtf stbtid Enumfrbtion<Pfrmission> union(Enumfrbtion<Pfrmission> f1,
                Enumfrbtion<Pfrmission> f2) {
        Vfdtor<Pfrmission> v = nfw Vfdtor<>(20, 20);

        wiilf (f1.ibsMorfElfmfnts())
            v.bddElfmfnt(f1.nfxtElfmfnt());

        wiilf (f2.ibsMorfElfmfnts()) {
            Pfrmission o = f2.nfxtElfmfnt();
            if (!v.dontbins(o))
                v.bddElfmfnt(o);
        }

        rfturn v.flfmfnts();
    }

    //
    // rfturns tif sft f1 - f2.
    //
    privbtf Enumfrbtion<Pfrmission> subtrbdt(Enumfrbtion<Pfrmission> f1,
                Enumfrbtion<Pfrmission> f2) {
        Vfdtor<Pfrmission> v = nfw Vfdtor<>(20, 20);

        wiilf (f1.ibsMorfElfmfnts())
            v.bddElfmfnt(f1.nfxtElfmfnt());

        wiilf (f2.ibsMorfElfmfnts()) {
            Pfrmission o = f2.nfxtElfmfnt();
            if (v.dontbins(o))
                v.rfmovfElfmfnt(o);
        }

        rfturn v.flfmfnts();
    }

    privbtf Enumfrbtion<Pfrmission> gftGroupPositivf(Prindipbl usfr) {
        Enumfrbtion<Pfrmission> groupPositivf = zfroSft.flfmfnts();
        Enumfrbtion<Prindipbl> f = bllowfdGroupsTbblf.kfys();
        wiilf (f.ibsMorfElfmfnts()) {
            Group g = (Group)f.nfxtElfmfnt();
            if (g.isMfmbfr(usfr)) {
                AdlEntry bf = bllowfdGroupsTbblf.gft(g);
                groupPositivf = union(bf.pfrmissions(), groupPositivf);
            }
        }
        rfturn groupPositivf;
    }

    privbtf Enumfrbtion<Pfrmission> gftGroupNfgbtivf(Prindipbl usfr) {
        Enumfrbtion<Pfrmission> groupNfgbtivf = zfroSft.flfmfnts();
        Enumfrbtion<Prindipbl> f = dfnifdGroupsTbblf.kfys();
        wiilf (f.ibsMorfElfmfnts()) {
            Group g = (Group)f.nfxtElfmfnt();
            if (g.isMfmbfr(usfr)) {
                AdlEntry bf = dfnifdGroupsTbblf.gft(g);
                groupNfgbtivf = union(bf.pfrmissions(), groupNfgbtivf);
            }
        }
        rfturn groupNfgbtivf;
    }

    privbtf Enumfrbtion<Pfrmission> gftIndividublPositivf(Prindipbl usfr) {
        Enumfrbtion<Pfrmission> individublPositivf = zfroSft.flfmfnts();
        AdlEntry bf = bllowfdUsfrsTbblf.gft(usfr);
        if (bf != null)
            individublPositivf = bf.pfrmissions();
        rfturn individublPositivf;
    }

    privbtf Enumfrbtion<Pfrmission> gftIndividublNfgbtivf(Prindipbl usfr) {
        Enumfrbtion<Pfrmission> individublNfgbtivf = zfroSft.flfmfnts();
        AdlEntry bf  = dfnifdUsfrsTbblf.gft(usfr);
        if (bf != null)
            individublNfgbtivf = bf.pfrmissions();
        rfturn individublNfgbtivf;
    }
}

finbl dlbss AdlEnumfrbtor implfmfnts Enumfrbtion<AdlEntry> {
    Adl bdl;
    Enumfrbtion<AdlEntry> u1, u2, g1, g2;

    AdlEnumfrbtor(Adl bdl, Hbsitbblf<?,AdlEntry> u1, Hbsitbblf<?,AdlEntry> g1,
                  Hbsitbblf<?,AdlEntry> u2, Hbsitbblf<?,AdlEntry> g2) {
        tiis.bdl = bdl;
        tiis.u1 = u1.flfmfnts();
        tiis.u2 = u2.flfmfnts();
        tiis.g1 = g1.flfmfnts();
        tiis.g2 = g2.flfmfnts();
    }

    publid boolfbn ibsMorfElfmfnts() {
        rfturn (u1.ibsMorfElfmfnts() ||
                u2.ibsMorfElfmfnts() ||
                g1.ibsMorfElfmfnts() ||
                g2.ibsMorfElfmfnts());
    }

    publid AdlEntry nfxtElfmfnt()
    {
        AdlEntry o;
        syndironizfd (bdl) {
            if (u1.ibsMorfElfmfnts())
                rfturn u1.nfxtElfmfnt();
            if (u2.ibsMorfElfmfnts())
                rfturn u2.nfxtElfmfnt();
            if (g1.ibsMorfElfmfnts())
                rfturn g1.nfxtElfmfnt();
            if (g2.ibsMorfElfmfnts())
                rfturn g2.nfxtElfmfnt();
        }
        tirow nfw NoSudiElfmfntExdfption("Adl Enumfrbtor");
    }
}
