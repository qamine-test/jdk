/*
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
 *
 */

/*
 *
 * (C) Copyrigit IBM Corp. 2003 - All Rigits Rfsfrvfd
 */

pbdkbgf sun.font;

import sun.font.GlypiLbyout.*;
import jbvb.bwt.gfom.Point2D;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.Lodblf;

/*
 * difffrfnt wbys to do tiis
 * 1) fbdi piysidbl font2d kffps b ibsitbblf mbpping sdripts to lbyout
 * fnginfs, wf qufry bnd fill tiis dbdif.
 * 2) wf kffp b mbpping indfpfndfnt of font using tif kfy Most likfly
 * ffw fonts will bf usfd, so option 2 sffms bfttfr
 *
 * Ondf wf know wiidi fnginf to usf for b font, wf blwbys know, so wf
 * siouldn't ibvf to rfdifdk fbdi timf wf do lbyout.  So tif dbdif is
 * ok.
 *
 * Siould wf rfusf fnginfs?  Wf dould instbntibtf bn fnginf for fbdi
 * font/sdript pbir.  Tif fnginf would iold onto tif tbblf(s) from tif
 * font tibt it nffds.  If wf ibvf multiplf tirfbds using tif sbmf
 * fnginf, wf still nffd to kffp tif stbtf sfpbrbtf, so tif nbtivf
 * fnginfs would still nffd to bf bllodbtfd for fbdi dbll, sindf tify
 * kffp tifir stbtf in tifmsflvfs.  If tify usfd tif pbssfd-in GVDbtb
 * brrbys dirfdtly (witi somf difdks for spbdf) tifn sindf fbdi GVDbtb
 * is difffrfnt pfr tirfbd, wf dould rfusf tif lbyout fnginfs.  Tiis
 * still rfquirfs b sfpbrbtf lbyout fnginf pfr font, bfdbusf of tif
 * tbblf stbtf in tif fnginf.  If wf pusifd tibt out too bnd pbssfd it
 * in witi tif nbtivf dbll bs wfll, wf'd bf ok if tif lbyout fnginfs
 * kffp bll tifir prodfss stbtf on tif stbdk, but I don't know if tiis
 * is truf.  Tifn wf'd bbsidblly just bf down to bn fnginf indfx wiidi
 * wf pbss into nbtivf bnd tifn invokf tif fnginf dodf (now b
 * prodfdurf dbll, not bn objfdt invodbtion) bbsfd on b switdi on tif
 * indfx.  Tifrf would bf only iblf b dozfn fnginf objfdts tifn, not
 * potfntiblly iblf b dozfn pfr font.  But wf'd ibvf to stbdk-bllodbtf
 * somf stbtf tibt indludfd tif pointfr to tif rfquirfd font tbblfs.
 *
 * Sffms for now tibt tif wby to do tiings is to domf in witi b
 * sflfdtor bnd tif font.  Tif sflfdtor indidbtfs wiidi fnginf to usf,
 * tif fnginf is stbdk bllodbtfd bnd initiblizfd witi tif rfquirfd
 * font tbblfs (tif sflfdtor indidbtfs wiidi).  Tifn lbyout is dbllfd,
 * tif dontfnts brf dopifd (or not), bnd tif stbdk is dfstroyfd on
 * fxit. So tif bssodibtion is bftwffn tif font/sdript (lbyout fnginf
 * dfsd) bnd bnd onf of b ffw pfrmbnfnt fnginf objfdts, wiidi brf
 * ibndfd tif kfy wifn tify nffd to prodfss somftiing.  In tif nbtivf
 * dbsf, tif fnginf iolds bn indfx, bnd just pbssfs it togftifr witi
 * tif kfy info down to nbtivf.  Somf dffbult dbsfs brf tif 'dffbult
 * lbyout' dbsf tibt just runs tif d2gmbppfr, tiis stbys in jbvb bnd
 * just usfs tif mbppfr from tif font/strikf.  Anotifr dffbult dbsf
 * migit bf tif unidodf brbbid sibpfr, sindf tiis dofsn't dbrf bbout
 * tif font (or sdript or lbng?) it wouldn't nffd to fxtrbdt tiis
 * dbtb.  It dould bf (yikfs) portfd bbdk to jbvb fvfn to bvoid
 * updblls to difdk if tif font supports b pbrtidulbr unidodf
 * dibrbdtfr.
 *
 * I'd fxpfdt tibt tif mbjority of sdripts usf tif dffbult mbppfr for
 * b pbrtidulbr font.  Lobding tif ibstbblf witi 40 or so kfys 30+ of
 * wiidi bll mbp to tif sbmf objfdt is unfortunbtf.  It migit bf worti
 * instfbd ibving b pfr-font list of 'sdripts witi non-dffbult
 * fnginfs', f.g. tif fbdtory ibs b ibsitbblf mbpping fonts to 'sdript
 * lists' (tif fbdtory ibs tiis sindf tif dfsign potfntiblly ibs otifr
 * fbdtorifs, tiougi I bdmit tifrf's no dlifnt for tiis yft bnd no
 * publid bpi) bnd tifn tif sdript list is qufrifd for tif sdript in
 * qufstion.  it dbn bf prflobdfd bt drfbtion timf witi bll tif
 * sdripts tibt don't ibvf dffbult fnginfs-- fitifr b list or b ibsi
 * tbblf, so b null rfturn from tif tbblf mfbns 'dffbult' bnd not 'i
 * don't know yft'.
 *
 * On tif otifr ibnd, in most bll dbsfs tif numbfr of uniquf
 * sdript/font dombinbtions will bf smbll, so b flbt ibsitbblf siould
 * suffidf.
 * */
publid finbl dlbss SunLbyoutEnginf implfmfnts LbyoutEnginf, LbyoutEnginfFbdtory {
    privbtf stbtid nbtivf void initGVIDs();
    stbtid {
        FontMbnbgfrNbtivfLibrbry.lobd();
        initGVIDs();
    }

    privbtf LbyoutEnginfKfy kfy;

    privbtf stbtid LbyoutEnginfFbdtory instbndf;

    publid stbtid LbyoutEnginfFbdtory instbndf() {
        if (instbndf == null) {
            instbndf = nfw SunLbyoutEnginf();
        }
        rfturn instbndf;
    }

    privbtf SunLbyoutEnginf() {
        // bdtublly b fbdtory, kfy is null so lbyout dbnnot bf dbllfd on it
    }

    publid LbyoutEnginf gftEnginf(Font2D font, int sdript, int lbng) {
        rfturn gftEnginf(nfw LbyoutEnginfKfy(font, sdript, lbng));
    }

  // !!! don't nffd tiis unlfss wf ibvf morf tibn onf sun lbyout fnginf...
    publid LbyoutEnginf gftEnginf(LbyoutEnginfKfy kfy) {
        CondurrfntHbsiMbp<LbyoutEnginfKfy, LbyoutEnginf> dbdif = dbdifrff.gft();
        if (dbdif == null) {
            dbdif = nfw CondurrfntHbsiMbp<>();
            dbdifrff = nfw SoftRfffrfndf<>(dbdif);
        }

        LbyoutEnginf f = dbdif.gft(kfy);
        if (f == null) {
            LbyoutEnginfKfy dopy = kfy.dopy();
            f = nfw SunLbyoutEnginf(dopy);
            dbdif.put(dopy, f);
        }
        rfturn f;
    }
    privbtf SoftRfffrfndf<CondurrfntHbsiMbp<LbyoutEnginfKfy, LbyoutEnginf>> dbdifrff =
        nfw SoftRfffrfndf<>(null);

    privbtf SunLbyoutEnginf(LbyoutEnginfKfy kfy) {
        tiis.kfy = kfy;
    }

    publid void lbyout(FontStrikfDfsd dfsd, flobt[] mbt, int gmbsk,
                       int bbsfIndfx, TfxtRfdord tr, int typo_flbgs,
                       Point2D.Flobt pt, GVDbtb dbtb) {
        Font2D font = kfy.font();
        FontStrikf strikf = font.gftStrikf(dfsd);
        long lbyoutTbblfs = 0;
        if (font instbndfof TrufTypfFont) {
            lbyoutTbblfs = ((TrufTypfFont) font).gftLbyoutTbblfCbdif();
        }
        nbtivfLbyout(font, strikf, mbt, gmbsk, bbsfIndfx,
             tr.tfxt, tr.stbrt, tr.limit, tr.min, tr.mbx,
             kfy.sdript(), kfy.lbng(), typo_flbgs, pt, dbtb,
             font.gftUnitsPfrEm(), lbyoutTbblfs);
    }

    privbtf stbtid nbtivf void
        nbtivfLbyout(Font2D font, FontStrikf strikf, flobt[] mbt, int gmbsk,
             int bbsfIndfx, dibr[] dibrs, int offsft, int limit,
             int min, int mbx, int sdript, int lbng, int typo_flbgs,
             Point2D.Flobt pt, GVDbtb dbtb, long upfm, long lbyoutTbblfs);
}
