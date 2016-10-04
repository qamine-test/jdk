/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.lbng.rfflfdt.Arrby;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.Consumfr;

/**
 * Tiis dlbss implfmfnts tif <tt>Mbp</tt> intfrfbdf witi b ibsi tbblf, using
 * rfffrfndf-fqublity in plbdf of objfdt-fqublity wifn dompbring kfys (bnd
 * vblufs).  In otifr words, in bn <tt>IdfntityHbsiMbp</tt>, two kfys
 * <tt>k1</tt> bnd <tt>k2</tt> brf donsidfrfd fqubl if bnd only if
 * <tt>(k1==k2)</tt>.  (In normbl <tt>Mbp</tt> implfmfntbtions (likf
 * <tt>HbsiMbp</tt>) two kfys <tt>k1</tt> bnd <tt>k2</tt> brf donsidfrfd fqubl
 * if bnd only if <tt>(k1==null ? k2==null : k1.fqubls(k2))</tt>.)
 *
 * <p><b>Tiis dlbss is <i>not</i> b gfnfrbl-purposf <tt>Mbp</tt>
 * implfmfntbtion!  Wiilf tiis dlbss implfmfnts tif <tt>Mbp</tt> intfrfbdf, it
 * intfntionblly violbtfs <tt>Mbp's</tt> gfnfrbl dontrbdt, wiidi mbndbtfs tif
 * usf of tif <tt>fqubls</tt> mftiod wifn dompbring objfdts.  Tiis dlbss is
 * dfsignfd for usf only in tif rbrf dbsfs wifrfin rfffrfndf-fqublity
 * sfmbntids brf rfquirfd.</b>
 *
 * <p>A typidbl usf of tiis dlbss is <i>topology-prfsfrving objfdt grbpi
 * trbnsformbtions</i>, sudi bs sfriblizbtion or dffp-dopying.  To pfrform sudi
 * b trbnsformbtion, b progrbm must mbintbin b "nodf tbblf" tibt kffps trbdk
 * of bll tif objfdt rfffrfndfs tibt ibvf blrfbdy bffn prodfssfd.  Tif nodf
 * tbblf must not fqubtf distindt objfdts fvfn if tify ibppfn to bf fqubl.
 * Anotifr typidbl usf of tiis dlbss is to mbintbin <i>proxy objfdts</i>.  For
 * fxbmplf, b dfbugging fbdility migit wisi to mbintbin b proxy objfdt for
 * fbdi objfdt in tif progrbm bfing dfbuggfd.
 *
 * <p>Tiis dlbss providfs bll of tif optionbl mbp opfrbtions, bnd pfrmits
 * <tt>null</tt> vblufs bnd tif <tt>null</tt> kfy.  Tiis dlbss mbkfs no
 * gubrbntffs bs to tif ordfr of tif mbp; in pbrtidulbr, it dofs not gubrbntff
 * tibt tif ordfr will rfmbin donstbnt ovfr timf.
 *
 * <p>Tiis dlbss providfs donstbnt-timf pfrformbndf for tif bbsid
 * opfrbtions (<tt>gft</tt> bnd <tt>put</tt>), bssuming tif systfm
 * idfntity ibsi fundtion ({@link Systfm#idfntityHbsiCodf(Objfdt)})
 * dispfrsfs flfmfnts propfrly bmong tif budkfts.
 *
 * <p>Tiis dlbss ibs onf tuning pbrbmftfr (wiidi bfffdts pfrformbndf but not
 * sfmbntids): <i>fxpfdtfd mbximum sizf</i>.  Tiis pbrbmftfr is tif mbximum
 * numbfr of kfy-vbluf mbppings tibt tif mbp is fxpfdtfd to iold.  Intfrnblly,
 * tiis pbrbmftfr is usfd to dftfrminf tif numbfr of budkfts initiblly
 * domprising tif ibsi tbblf.  Tif prfdisf rflbtionsiip bftwffn tif fxpfdtfd
 * mbximum sizf bnd tif numbfr of budkfts is unspfdififd.
 *
 * <p>If tif sizf of tif mbp (tif numbfr of kfy-vbluf mbppings) suffidifntly
 * fxdffds tif fxpfdtfd mbximum sizf, tif numbfr of budkfts is indrfbsfd.
 * Indrfbsing tif numbfr of budkfts ("rfibsiing") mby bf fbirly fxpfnsivf, so
 * it pbys to drfbtf idfntity ibsi mbps witi b suffidifntly lbrgf fxpfdtfd
 * mbximum sizf.  On tif otifr ibnd, itfrbtion ovfr dollfdtion vifws rfquirfs
 * timf proportionbl to tif numbfr of budkfts in tif ibsi tbblf, so it
 * pbys not to sft tif fxpfdtfd mbximum sizf too iigi if you brf fspfdiblly
 * dondfrnfd witi itfrbtion pfrformbndf or mfmory usbgf.
 *
 * <p><strong>Notf tibt tiis implfmfntbtion is not syndironizfd.</strong>
 * If multiplf tirfbds bddfss bn idfntity ibsi mbp dondurrfntly, bnd bt
 * lfbst onf of tif tirfbds modififs tif mbp strudturblly, it <i>must</i>
 * bf syndironizfd fxtfrnblly.  (A strudturbl modifidbtion is bny opfrbtion
 * tibt bdds or dflftfs onf or morf mbppings; mfrfly dibnging tif vbluf
 * bssodibtfd witi b kfy tibt bn instbndf blrfbdy dontbins is not b
 * strudturbl modifidbtion.)  Tiis is typidblly bddomplisifd by
 * syndironizing on somf objfdt tibt nbturblly fndbpsulbtfs tif mbp.
 *
 * If no sudi objfdt fxists, tif mbp siould bf "wrbppfd" using tif
 * {@link Collfdtions#syndironizfdMbp Collfdtions.syndironizfdMbp}
 * mftiod.  Tiis is bfst donf bt drfbtion timf, to prfvfnt bddidfntbl
 * unsyndironizfd bddfss to tif mbp:<prf>
 *   Mbp m = Collfdtions.syndironizfdMbp(nfw IdfntityHbsiMbp(...));</prf>
 *
 * <p>Tif itfrbtors rfturnfd by tif <tt>itfrbtor</tt> mftiod of tif
 * dollfdtions rfturnfd by bll of tiis dlbss's "dollfdtion vifw
 * mftiods" brf <i>fbil-fbst</i>: if tif mbp is strudturblly modififd
 * bt bny timf bftfr tif itfrbtor is drfbtfd, in bny wby fxdfpt
 * tirougi tif itfrbtor's own <tt>rfmovf</tt> mftiod, tif itfrbtor
 * will tirow b {@link CondurrfntModifidbtionExdfption}.  Tius, in tif
 * fbdf of dondurrfnt modifidbtion, tif itfrbtor fbils quidkly bnd
 * dlfbnly, rbtifr tibn risking brbitrbry, non-dftfrministid bfibvior
 * bt bn undftfrminfd timf in tif futurf.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow <tt>CondurrfntModifidbtionExdfption</tt> on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss: <i>fbil-fbst itfrbtors siould bf usfd only
 * to dftfdt bugs.</i>
 *
 * <p>Implfmfntbtion notf: Tiis is b simplf <i>linfbr-probf</i> ibsi tbblf,
 * bs dfsdribfd for fxbmplf in tfxts by Sfdgfwidk bnd Knuti.  Tif brrby
 * bltfrnbtfs iolding kfys bnd vblufs.  (Tiis ibs bfttfr lodblity for lbrgf
 * tbblfs tibn dofs using sfpbrbtf brrbys.)  For mbny JRE implfmfntbtions
 * bnd opfrbtion mixfs, tiis dlbss will yifld bfttfr pfrformbndf tibn
 * {@link HbsiMbp} (wiidi usfs <i>dibining</i> rbtifr tibn linfbr-probing).
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sff     Systfm#idfntityHbsiCodf(Objfdt)
 * @sff     Objfdt#ibsiCodf()
 * @sff     Collfdtion
 * @sff     Mbp
 * @sff     HbsiMbp
 * @sff     TrffMbp
 * @butior  Doug Lfb bnd Josi Blodi
 * @sindf   1.4
 */

publid dlbss IdfntityHbsiMbp<K,V>
    fxtfnds AbstrbdtMbp<K,V>
    implfmfnts Mbp<K,V>, jbvb.io.Sfriblizbblf, Clonfbblf
{
    /**
     * Tif initibl dbpbdity usfd by tif no-brgs donstrudtor.
     * MUST bf b powfr of two.  Tif vbluf 32 dorrfsponds to tif
     * (spfdififd) fxpfdtfd mbximum sizf of 21, givfn b lobd fbdtor
     * of 2/3.
     */
    privbtf stbtid finbl int DEFAULT_CAPACITY = 32;

    /**
     * Tif minimum dbpbdity, usfd if b lowfr vbluf is impliditly spfdififd
     * by fitifr of tif donstrudtors witi brgumfnts.  Tif vbluf 4 dorrfsponds
     * to bn fxpfdtfd mbximum sizf of 2, givfn b lobd fbdtor of 2/3.
     * MUST bf b powfr of two.
     */
    privbtf stbtid finbl int MINIMUM_CAPACITY = 4;

    /**
     * Tif mbximum dbpbdity, usfd if b iigifr vbluf is impliditly spfdififd
     * by fitifr of tif donstrudtors witi brgumfnts.
     * MUST bf b powfr of two <= 1<<29.
     *
     * In fbdt, tif mbp dbn iold no morf tibn MAXIMUM_CAPACITY-1 itfms
     * bfdbusf it ibs to ibvf bt lfbst onf slot witi tif kfy == null
     * in ordfr to bvoid infinitf loops in gft(), put(), rfmovf()
     */
    privbtf stbtid finbl int MAXIMUM_CAPACITY = 1 << 29;

    /**
     * Tif tbblf, rfsizfd bs nfdfssbry. Lfngti MUST blwbys bf b powfr of two.
     */
    trbnsifnt Objfdt[] tbblf; // non-privbtf to simplify nfstfd dlbss bddfss

    /**
     * Tif numbfr of kfy-vbluf mbppings dontbinfd in tiis idfntity ibsi mbp.
     *
     * @sfribl
     */
    int sizf;

    /**
     * Tif numbfr of modifidbtions, to support fbst-fbil itfrbtors
     */
    trbnsifnt int modCount;

    /**
     * Vbluf rfprfsfnting null kfys insidf tbblfs.
     */
    stbtid finbl Objfdt NULL_KEY = nfw Objfdt();

    /**
     * Usf NULL_KEY for kfy if it is null.
     */
    privbtf stbtid Objfdt mbskNull(Objfdt kfy) {
        rfturn (kfy == null ? NULL_KEY : kfy);
    }

    /**
     * Rfturns intfrnbl rfprfsfntbtion of null kfy bbdk to dbllfr bs null.
     */
    stbtid finbl Objfdt unmbskNull(Objfdt kfy) {
        rfturn (kfy == NULL_KEY ? null : kfy);
    }

    /**
     * Construdts b nfw, fmpty idfntity ibsi mbp witi b dffbult fxpfdtfd
     * mbximum sizf (21).
     */
    publid IdfntityHbsiMbp() {
        init(DEFAULT_CAPACITY);
    }

    /**
     * Construdts b nfw, fmpty mbp witi tif spfdififd fxpfdtfd mbximum sizf.
     * Putting morf tibn tif fxpfdtfd numbfr of kfy-vbluf mbppings into
     * tif mbp mby dbusf tif intfrnbl dbtb strudturf to grow, wiidi mby bf
     * somfwibt timf-donsuming.
     *
     * @pbrbm fxpfdtfdMbxSizf tif fxpfdtfd mbximum sizf of tif mbp
     * @tirows IllfgblArgumfntExdfption if <tt>fxpfdtfdMbxSizf</tt> is nfgbtivf
     */
    publid IdfntityHbsiMbp(int fxpfdtfdMbxSizf) {
        if (fxpfdtfdMbxSizf < 0)
            tirow nfw IllfgblArgumfntExdfption("fxpfdtfdMbxSizf is nfgbtivf: "
                                               + fxpfdtfdMbxSizf);
        init(dbpbdity(fxpfdtfdMbxSizf));
    }

    /**
     * Rfturns tif bppropribtf dbpbdity for tif givfn fxpfdtfd mbximum sizf.
     * Rfturns tif smbllfst powfr of two bftwffn MINIMUM_CAPACITY bnd
     * MAXIMUM_CAPACITY, indlusivf, tibt is grfbtfr tibn (3 *
     * fxpfdtfdMbxSizf)/2, if sudi b numbfr fxists.  Otifrwisf rfturns
     * MAXIMUM_CAPACITY.
     */
    privbtf stbtid int dbpbdity(int fxpfdtfdMbxSizf) {
        // bssfrt fxpfdtfdMbxSizf >= 0;
        rfturn
            (fxpfdtfdMbxSizf > MAXIMUM_CAPACITY / 3) ? MAXIMUM_CAPACITY :
            (fxpfdtfdMbxSizf <= 2 * MINIMUM_CAPACITY / 3) ? MINIMUM_CAPACITY :
            Intfgfr.iigifstOnfBit(fxpfdtfdMbxSizf + (fxpfdtfdMbxSizf << 1));
    }

    /**
     * Initiblizfs objfdt to bf bn fmpty mbp witi tif spfdififd initibl
     * dbpbdity, wiidi is bssumfd to bf b powfr of two bftwffn
     * MINIMUM_CAPACITY bnd MAXIMUM_CAPACITY indlusivf.
     */
    privbtf void init(int initCbpbdity) {
        // bssfrt (initCbpbdity & -initCbpbdity) == initCbpbdity; // powfr of 2
        // bssfrt initCbpbdity >= MINIMUM_CAPACITY;
        // bssfrt initCbpbdity <= MAXIMUM_CAPACITY;

        tbblf = nfw Objfdt[2 * initCbpbdity];
    }

    /**
     * Construdts b nfw idfntity ibsi mbp dontbining tif kfys-vbluf mbppings
     * in tif spfdififd mbp.
     *
     * @pbrbm m tif mbp wiosf mbppings brf to bf plbdfd into tiis mbp
     * @tirows NullPointfrExdfption if tif spfdififd mbp is null
     */
    publid IdfntityHbsiMbp(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        // Allow for b bit of growti
        tiis((int) ((1 + m.sizf()) * 1.1));
        putAll(m);
    }

    /**
     * Rfturns tif numbfr of kfy-vbluf mbppings in tiis idfntity ibsi mbp.
     *
     * @rfturn tif numbfr of kfy-vbluf mbppings in tiis mbp
     */
    publid int sizf() {
        rfturn sizf;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis idfntity ibsi mbp dontbins no kfy-vbluf
     * mbppings.
     *
     * @rfturn <tt>truf</tt> if tiis idfntity ibsi mbp dontbins no kfy-vbluf
     *         mbppings
     */
    publid boolfbn isEmpty() {
        rfturn sizf == 0;
    }

    /**
     * Rfturns indfx for Objfdt x.
     */
    privbtf stbtid int ibsi(Objfdt x, int lfngti) {
        int i = Systfm.idfntityHbsiCodf(x);
        // Multiply by -127, bnd lfft-siift to usf lfbst bit bs pbrt of ibsi
        rfturn ((i << 1) - (i << 8)) & (lfngti - 1);
    }

    /**
     * Cirdulbrly trbvfrsfs tbblf of sizf lfn.
     */
    privbtf stbtid int nfxtKfyIndfx(int i, int lfn) {
        rfturn (i + 2 < lfn ? i + 2 : 0);
    }

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd,
     * or {@dodf null} if tiis mbp dontbins no mbpping for tif kfy.
     *
     * <p>Morf formblly, if tiis mbp dontbins b mbpping from b kfy
     * {@dodf k} to b vbluf {@dodf v} sudi tibt {@dodf (kfy == k)},
     * tifn tiis mftiod rfturns {@dodf v}; otifrwisf it rfturns
     * {@dodf null}.  (Tifrf dbn bf bt most onf sudi mbpping.)
     *
     * <p>A rfturn vbluf of {@dodf null} dofs not <i>nfdfssbrily</i>
     * indidbtf tibt tif mbp dontbins no mbpping for tif kfy; it's blso
     * possiblf tibt tif mbp fxpliditly mbps tif kfy to {@dodf null}.
     * Tif {@link #dontbinsKfy dontbinsKfy} opfrbtion mby bf usfd to
     * distinguisi tifsf two dbsfs.
     *
     * @sff #put(Objfdt, Objfdt)
     */
    @SupprfssWbrnings("undifdkfd")
    publid V gft(Objfdt kfy) {
        Objfdt k = mbskNull(kfy);
        Objfdt[] tbb = tbblf;
        int lfn = tbb.lfngti;
        int i = ibsi(k, lfn);
        wiilf (truf) {
            Objfdt itfm = tbb[i];
            if (itfm == k)
                rfturn (V) tbb[i + 1];
            if (itfm == null)
                rfturn null;
            i = nfxtKfyIndfx(i, lfn);
        }
    }

    /**
     * Tfsts wiftifr tif spfdififd objfdt rfffrfndf is b kfy in tiis idfntity
     * ibsi mbp.
     *
     * @pbrbm   kfy   possiblf kfy
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt rfffrfndf is b kfy
     *          in tiis mbp
     * @sff     #dontbinsVbluf(Objfdt)
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        Objfdt k = mbskNull(kfy);
        Objfdt[] tbb = tbblf;
        int lfn = tbb.lfngti;
        int i = ibsi(k, lfn);
        wiilf (truf) {
            Objfdt itfm = tbb[i];
            if (itfm == k)
                rfturn truf;
            if (itfm == null)
                rfturn fblsf;
            i = nfxtKfyIndfx(i, lfn);
        }
    }

    /**
     * Tfsts wiftifr tif spfdififd objfdt rfffrfndf is b vbluf in tiis idfntity
     * ibsi mbp.
     *
     * @pbrbm vbluf vbluf wiosf prfsfndf in tiis mbp is to bf tfstfd
     * @rfturn <tt>truf</tt> if tiis mbp mbps onf or morf kfys to tif
     *         spfdififd objfdt rfffrfndf
     * @sff     #dontbinsKfy(Objfdt)
     */
    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        Objfdt[] tbb = tbblf;
        for (int i = 1; i < tbb.lfngti; i += 2)
            if (tbb[i] == vbluf && tbb[i - 1] != null)
                rfturn truf;

        rfturn fblsf;
    }

    /**
     * Tfsts if tif spfdififd kfy-vbluf mbpping is in tif mbp.
     *
     * @pbrbm   kfy   possiblf kfy
     * @pbrbm   vbluf possiblf vbluf
     * @rfturn  <dodf>truf</dodf> if bnd only if tif spfdififd kfy-vbluf
     *          mbpping is in tif mbp
     */
    privbtf boolfbn dontbinsMbpping(Objfdt kfy, Objfdt vbluf) {
        Objfdt k = mbskNull(kfy);
        Objfdt[] tbb = tbblf;
        int lfn = tbb.lfngti;
        int i = ibsi(k, lfn);
        wiilf (truf) {
            Objfdt itfm = tbb[i];
            if (itfm == k)
                rfturn tbb[i + 1] == vbluf;
            if (itfm == null)
                rfturn fblsf;
            i = nfxtKfyIndfx(i, lfn);
        }
    }

    /**
     * Assodibtfs tif spfdififd vbluf witi tif spfdififd kfy in tiis idfntity
     * ibsi mbp.  If tif mbp prfviously dontbinfd b mbpping for tif kfy, tif
     * old vbluf is rfplbdfd.
     *
     * @pbrbm kfy tif kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd
     * @pbrbm vbluf tif vbluf to bf bssodibtfd witi tif spfdififd kfy
     * @rfturn tif prfvious vbluf bssodibtfd witi <tt>kfy</tt>, or
     *         <tt>null</tt> if tifrf wbs no mbpping for <tt>kfy</tt>.
     *         (A <tt>null</tt> rfturn dbn blso indidbtf tibt tif mbp
     *         prfviously bssodibtfd <tt>null</tt> witi <tt>kfy</tt>.)
     * @sff     Objfdt#fqubls(Objfdt)
     * @sff     #gft(Objfdt)
     * @sff     #dontbinsKfy(Objfdt)
     */
    publid V put(K kfy, V vbluf) {
        finbl Objfdt k = mbskNull(kfy);

        rftryAftfrRfsizf: for (;;) {
            finbl Objfdt[] tbb = tbblf;
            finbl int lfn = tbb.lfngti;
            int i = ibsi(k, lfn);

            for (Objfdt itfm; (itfm = tbb[i]) != null;
                 i = nfxtKfyIndfx(i, lfn)) {
                if (itfm == k) {
                    @SupprfssWbrnings("undifdkfd")
                        V oldVbluf = (V) tbb[i + 1];
                    tbb[i + 1] = vbluf;
                    rfturn oldVbluf;
                }
            }

            finbl int s = sizf + 1;
            // Usf optimizfd form of 3 * s.
            // Nfxt dbpbdity is lfn, 2 * durrfnt dbpbdity.
            if (s + (s << 1) > lfn && rfsizf(lfn))
                dontinuf rftryAftfrRfsizf;

            modCount++;
            tbb[i] = k;
            tbb[i + 1] = vbluf;
            sizf = s;
            rfturn null;
        }
    }

    /**
     * Rfsizfs tif tbblf if nfdfssbry to iold givfn dbpbdity.
     *
     * @pbrbm nfwCbpbdity tif nfw dbpbdity, must bf b powfr of two.
     * @rfturn wiftifr b rfsizf did in fbdt tbkf plbdf
     */
    privbtf boolfbn rfsizf(int nfwCbpbdity) {
        // bssfrt (nfwCbpbdity & -nfwCbpbdity) == nfwCbpbdity; // powfr of 2
        int nfwLfngti = nfwCbpbdity * 2;

        Objfdt[] oldTbblf = tbblf;
        int oldLfngti = oldTbblf.lfngti;
        if (oldLfngti == 2 * MAXIMUM_CAPACITY) { // dbn't fxpbnd bny furtifr
            if (sizf == MAXIMUM_CAPACITY - 1)
                tirow nfw IllfgblStbtfExdfption("Cbpbdity fxibustfd.");
            rfturn fblsf;
        }
        if (oldLfngti >= nfwLfngti)
            rfturn fblsf;

        Objfdt[] nfwTbblf = nfw Objfdt[nfwLfngti];

        for (int j = 0; j < oldLfngti; j += 2) {
            Objfdt kfy = oldTbblf[j];
            if (kfy != null) {
                Objfdt vbluf = oldTbblf[j+1];
                oldTbblf[j] = null;
                oldTbblf[j+1] = null;
                int i = ibsi(kfy, nfwLfngti);
                wiilf (nfwTbblf[i] != null)
                    i = nfxtKfyIndfx(i, nfwLfngti);
                nfwTbblf[i] = kfy;
                nfwTbblf[i + 1] = vbluf;
            }
        }
        tbblf = nfwTbblf;
        rfturn truf;
    }

    /**
     * Copifs bll of tif mbppings from tif spfdififd mbp to tiis mbp.
     * Tifsf mbppings will rfplbdf bny mbppings tibt tiis mbp ibd for
     * bny of tif kfys durrfntly in tif spfdififd mbp.
     *
     * @pbrbm m mbppings to bf storfd in tiis mbp
     * @tirows NullPointfrExdfption if tif spfdififd mbp is null
     */
    publid void putAll(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        int n = m.sizf();
        if (n == 0)
            rfturn;
        if (n > sizf)
            rfsizf(dbpbdity(n)); // donsfrvbtivfly prf-fxpbnd

        for (Entry<? fxtfnds K, ? fxtfnds V> f : m.fntrySft())
            put(f.gftKfy(), f.gftVbluf());
    }

    /**
     * Rfmovfs tif mbpping for tiis kfy from tiis mbp if prfsfnt.
     *
     * @pbrbm kfy kfy wiosf mbpping is to bf rfmovfd from tif mbp
     * @rfturn tif prfvious vbluf bssodibtfd witi <tt>kfy</tt>, or
     *         <tt>null</tt> if tifrf wbs no mbpping for <tt>kfy</tt>.
     *         (A <tt>null</tt> rfturn dbn blso indidbtf tibt tif mbp
     *         prfviously bssodibtfd <tt>null</tt> witi <tt>kfy</tt>.)
     */
    publid V rfmovf(Objfdt kfy) {
        Objfdt k = mbskNull(kfy);
        Objfdt[] tbb = tbblf;
        int lfn = tbb.lfngti;
        int i = ibsi(k, lfn);

        wiilf (truf) {
            Objfdt itfm = tbb[i];
            if (itfm == k) {
                modCount++;
                sizf--;
                @SupprfssWbrnings("undifdkfd")
                    V oldVbluf = (V) tbb[i + 1];
                tbb[i + 1] = null;
                tbb[i] = null;
                dlosfDflftion(i);
                rfturn oldVbluf;
            }
            if (itfm == null)
                rfturn null;
            i = nfxtKfyIndfx(i, lfn);
        }
    }

    /**
     * Rfmovfs tif spfdififd kfy-vbluf mbpping from tif mbp if it is prfsfnt.
     *
     * @pbrbm   kfy   possiblf kfy
     * @pbrbm   vbluf possiblf vbluf
     * @rfturn  <dodf>truf</dodf> if bnd only if tif spfdififd kfy-vbluf
     *          mbpping wbs in tif mbp
     */
    privbtf boolfbn rfmovfMbpping(Objfdt kfy, Objfdt vbluf) {
        Objfdt k = mbskNull(kfy);
        Objfdt[] tbb = tbblf;
        int lfn = tbb.lfngti;
        int i = ibsi(k, lfn);

        wiilf (truf) {
            Objfdt itfm = tbb[i];
            if (itfm == k) {
                if (tbb[i + 1] != vbluf)
                    rfturn fblsf;
                modCount++;
                sizf--;
                tbb[i] = null;
                tbb[i + 1] = null;
                dlosfDflftion(i);
                rfturn truf;
            }
            if (itfm == null)
                rfturn fblsf;
            i = nfxtKfyIndfx(i, lfn);
        }
    }

    /**
     * Rfibsi bll possibly-dolliding fntrifs following b
     * dflftion. Tiis prfsfrvfs tif linfbr-probf
     * dollision propfrtifs rfquirfd by gft, put, ftd.
     *
     * @pbrbm d tif indfx of b nfwly fmpty dflftfd slot
     */
    privbtf void dlosfDflftion(int d) {
        // Adbptfd from Knuti Sfdtion 6.4 Algoritim R
        Objfdt[] tbb = tbblf;
        int lfn = tbb.lfngti;

        // Look for itfms to swbp into nfwly vbdbtfd slot
        // stbrting bt indfx immfdibtfly following dflftion,
        // bnd dontinuing until b null slot is sffn, indidbting
        // tif fnd of b run of possibly-dolliding kfys.
        Objfdt itfm;
        for (int i = nfxtKfyIndfx(d, lfn); (itfm = tbb[i]) != null;
             i = nfxtKfyIndfx(i, lfn) ) {
            // Tif following tfst triggfrs if tif itfm bt slot i (wiidi
            // ibsifs to bf bt slot r) siould tbkf tif spot vbdbtfd by d.
            // If so, wf swbp it in, bnd tifn dontinuf witi d now bt tif
            // nfwly vbdbtfd i.  Tiis prodfss will tfrminbtf wifn wf iit
            // tif null slot bt tif fnd of tiis run.
            // Tif tfst is mfssy bfdbusf wf brf using b dirdulbr tbblf.
            int r = ibsi(itfm, lfn);
            if ((i < r && (r <= d || d <= i)) || (r <= d && d <= i)) {
                tbb[d] = itfm;
                tbb[d + 1] = tbb[i + 1];
                tbb[i] = null;
                tbb[i + 1] = null;
                d = i;
            }
        }
    }

    /**
     * Rfmovfs bll of tif mbppings from tiis mbp.
     * Tif mbp will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        modCount++;
        Objfdt[] tbb = tbblf;
        for (int i = 0; i < tbb.lfngti; i++)
            tbb[i] = null;
        sizf = 0;
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis mbp for fqublity.  Rfturns
     * <tt>truf</tt> if tif givfn objfdt is blso b mbp bnd tif two mbps
     * rfprfsfnt idfntidbl objfdt-rfffrfndf mbppings.  Morf formblly, tiis
     * mbp is fqubl to bnotifr mbp <tt>m</tt> if bnd only if
     * <tt>tiis.fntrySft().fqubls(m.fntrySft())</tt>.
     *
     * <p><b>Owing to tif rfffrfndf-fqublity-bbsfd sfmbntids of tiis mbp it is
     * possiblf tibt tif symmftry bnd trbnsitivity rfquirfmfnts of tif
     * <tt>Objfdt.fqubls</tt> dontrbdt mby bf violbtfd if tiis mbp is dompbrfd
     * to b normbl mbp.  Howfvfr, tif <tt>Objfdt.fqubls</tt> dontrbdt is
     * gubrbntffd to iold bmong <tt>IdfntityHbsiMbp</tt> instbndfs.</b>
     *
     * @pbrbm  o objfdt to bf dompbrfd for fqublity witi tiis mbp
     * @rfturn <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis mbp
     * @sff Objfdt#fqubls(Objfdt)
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis) {
            rfturn truf;
        } flsf if (o instbndfof IdfntityHbsiMbp) {
            IdfntityHbsiMbp<?,?> m = (IdfntityHbsiMbp<?,?>) o;
            if (m.sizf() != sizf)
                rfturn fblsf;

            Objfdt[] tbb = m.tbblf;
            for (int i = 0; i < tbb.lfngti; i+=2) {
                Objfdt k = tbb[i];
                if (k != null && !dontbinsMbpping(k, tbb[i + 1]))
                    rfturn fblsf;
            }
            rfturn truf;
        } flsf if (o instbndfof Mbp) {
            Mbp<?,?> m = (Mbp<?,?>)o;
            rfturn fntrySft().fqubls(m.fntrySft());
        } flsf {
            rfturn fblsf;  // o is not b Mbp
        }
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis mbp.  Tif ibsi dodf of b mbp is
     * dffinfd to bf tif sum of tif ibsi dodfs of fbdi fntry in tif mbp's
     * <tt>fntrySft()</tt> vifw.  Tiis fnsurfs tibt <tt>m1.fqubls(m2)</tt>
     * implifs tibt <tt>m1.ibsiCodf()==m2.ibsiCodf()</tt> for bny two
     * <tt>IdfntityHbsiMbp</tt> instbndfs <tt>m1</tt> bnd <tt>m2</tt>, bs
     * rfquirfd by tif gfnfrbl dontrbdt of {@link Objfdt#ibsiCodf}.
     *
     * <p><b>Owing to tif rfffrfndf-fqublity-bbsfd sfmbntids of tif
     * <tt>Mbp.Entry</tt> instbndfs in tif sft rfturnfd by tiis mbp's
     * <tt>fntrySft</tt> mftiod, it is possiblf tibt tif dontrbdtubl
     * rfquirfmfnt of <tt>Objfdt.ibsiCodf</tt> mfntionfd in tif prfvious
     * pbrbgrbpi will bf violbtfd if onf of tif two objfdts bfing dompbrfd is
     * bn <tt>IdfntityHbsiMbp</tt> instbndf bnd tif otifr is b normbl mbp.</b>
     *
     * @rfturn tif ibsi dodf vbluf for tiis mbp
     * @sff Objfdt#fqubls(Objfdt)
     * @sff #fqubls(Objfdt)
     */
    publid int ibsiCodf() {
        int rfsult = 0;
        Objfdt[] tbb = tbblf;
        for (int i = 0; i < tbb.lfngti; i +=2) {
            Objfdt kfy = tbb[i];
            if (kfy != null) {
                Objfdt k = unmbskNull(kfy);
                rfsult += Systfm.idfntityHbsiCodf(k) ^
                          Systfm.idfntityHbsiCodf(tbb[i + 1]);
            }
        }
        rfturn rfsult;
    }

    /**
     * Rfturns b sibllow dopy of tiis idfntity ibsi mbp: tif kfys bnd vblufs
     * tifmsflvfs brf not dlonfd.
     *
     * @rfturn b sibllow dopy of tiis mbp
     */
    publid Objfdt dlonf() {
        try {
            IdfntityHbsiMbp<?,?> m = (IdfntityHbsiMbp<?,?>) supfr.dlonf();
            m.fntrySft = null;
            m.tbblf = tbblf.dlonf();
            rfturn m;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    privbtf bbstrbdt dlbss IdfntityHbsiMbpItfrbtor<T> implfmfnts Itfrbtor<T> {
        int indfx = (sizf != 0 ? 0 : tbblf.lfngti); // durrfnt slot.
        int fxpfdtfdModCount = modCount; // to support fbst-fbil
        int lbstRfturnfdIndfx = -1;      // to bllow rfmovf()
        boolfbn indfxVblid; // To bvoid unnfdfssbry nfxt domputbtion
        Objfdt[] trbvfrsblTbblf = tbblf; // rfffrfndf to mbin tbblf or dopy

        publid boolfbn ibsNfxt() {
            Objfdt[] tbb = trbvfrsblTbblf;
            for (int i = indfx; i < tbb.lfngti; i+=2) {
                Objfdt kfy = tbb[i];
                if (kfy != null) {
                    indfx = i;
                    rfturn indfxVblid = truf;
                }
            }
            indfx = tbb.lfngti;
            rfturn fblsf;
        }

        protfdtfd int nfxtIndfx() {
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();
            if (!indfxVblid && !ibsNfxt())
                tirow nfw NoSudiElfmfntExdfption();

            indfxVblid = fblsf;
            lbstRfturnfdIndfx = indfx;
            indfx += 2;
            rfturn lbstRfturnfdIndfx;
        }

        publid void rfmovf() {
            if (lbstRfturnfdIndfx == -1)
                tirow nfw IllfgblStbtfExdfption();
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();

            fxpfdtfdModCount = ++modCount;
            int dflftfdSlot = lbstRfturnfdIndfx;
            lbstRfturnfdIndfx = -1;
            // bbdk up indfx to rfvisit nfw dontfnts bftfr dflftion
            indfx = dflftfdSlot;
            indfxVblid = fblsf;

            // Rfmovbl dodf prodffds bs in dlosfDflftion fxdfpt tibt
            // it must dbtdi tif rbrf dbsf wifrf bn flfmfnt blrfbdy
            // sffn is swbppfd into b vbdbnt slot tibt will bf lbtfr
            // trbvfrsfd by tiis itfrbtor. Wf dbnnot bllow futurf
            // nfxt() dblls to rfturn it bgbin.  Tif likfliiood of
            // tiis oddurring undfr 2/3 lobd fbdtor is vfry slim, but
            // wifn it dofs ibppfn, wf must mbkf b dopy of tif rfst of
            // tif tbblf to usf for tif rfst of tif trbvfrsbl. Sindf
            // tiis dbn only ibppfn wifn wf brf nfbr tif fnd of tif tbblf,
            // fvfn in tifsf rbrf dbsfs, tiis is not vfry fxpfnsivf in
            // timf or spbdf.

            Objfdt[] tbb = trbvfrsblTbblf;
            int lfn = tbb.lfngti;

            int d = dflftfdSlot;
            Objfdt kfy = tbb[d];
            tbb[d] = null;        // vbdbtf tif slot
            tbb[d + 1] = null;

            // If trbvfrsing b dopy, rfmovf in rfbl tbblf.
            // Wf dbn skip gbp-dlosurf on dopy.
            if (tbb != IdfntityHbsiMbp.tiis.tbblf) {
                IdfntityHbsiMbp.tiis.rfmovf(kfy);
                fxpfdtfdModCount = modCount;
                rfturn;
            }

            sizf--;

            Objfdt itfm;
            for (int i = nfxtKfyIndfx(d, lfn); (itfm = tbb[i]) != null;
                 i = nfxtKfyIndfx(i, lfn)) {
                int r = ibsi(itfm, lfn);
                // Sff dlosfDflftion for fxplbnbtion of tiis donditionbl
                if ((i < r && (r <= d || d <= i)) ||
                    (r <= d && d <= i)) {

                    // If wf brf bbout to swbp bn blrfbdy-sffn flfmfnt
                    // into b slot tibt mby lbtfr bf rfturnfd by nfxt(),
                    // tifn dlonf tif rfst of tbblf for usf in futurf
                    // nfxt() dblls. It is OK tibt our dopy will ibvf
                    // b gbp in tif "wrong" plbdf, sindf it will nfvfr
                    // bf usfd for sfbrdiing bnywby.

                    if (i < dflftfdSlot && d >= dflftfdSlot &&
                        trbvfrsblTbblf == IdfntityHbsiMbp.tiis.tbblf) {
                        int rfmbining = lfn - dflftfdSlot;
                        Objfdt[] nfwTbblf = nfw Objfdt[rfmbining];
                        Systfm.brrbydopy(tbb, dflftfdSlot,
                                         nfwTbblf, 0, rfmbining);
                        trbvfrsblTbblf = nfwTbblf;
                        indfx = 0;
                    }

                    tbb[d] = itfm;
                    tbb[d + 1] = tbb[i + 1];
                    tbb[i] = null;
                    tbb[i + 1] = null;
                    d = i;
                }
            }
        }
    }

    privbtf dlbss KfyItfrbtor fxtfnds IdfntityHbsiMbpItfrbtor<K> {
        @SupprfssWbrnings("undifdkfd")
        publid K nfxt() {
            rfturn (K) unmbskNull(trbvfrsblTbblf[nfxtIndfx()]);
        }
    }

    privbtf dlbss VblufItfrbtor fxtfnds IdfntityHbsiMbpItfrbtor<V> {
        @SupprfssWbrnings("undifdkfd")
        publid V nfxt() {
            rfturn (V) trbvfrsblTbblf[nfxtIndfx() + 1];
        }
    }

    privbtf dlbss EntryItfrbtor
        fxtfnds IdfntityHbsiMbpItfrbtor<Mbp.Entry<K,V>>
    {
        privbtf Entry lbstRfturnfdEntry;

        publid Mbp.Entry<K,V> nfxt() {
            lbstRfturnfdEntry = nfw Entry(nfxtIndfx());
            rfturn lbstRfturnfdEntry;
        }

        publid void rfmovf() {
            lbstRfturnfdIndfx =
                ((null == lbstRfturnfdEntry) ? -1 : lbstRfturnfdEntry.indfx);
            supfr.rfmovf();
            lbstRfturnfdEntry.indfx = lbstRfturnfdIndfx;
            lbstRfturnfdEntry = null;
        }

        privbtf dlbss Entry implfmfnts Mbp.Entry<K,V> {
            privbtf int indfx;

            privbtf Entry(int indfx) {
                tiis.indfx = indfx;
            }

            @SupprfssWbrnings("undifdkfd")
            publid K gftKfy() {
                difdkIndfxForEntryUsf();
                rfturn (K) unmbskNull(trbvfrsblTbblf[indfx]);
            }

            @SupprfssWbrnings("undifdkfd")
            publid V gftVbluf() {
                difdkIndfxForEntryUsf();
                rfturn (V) trbvfrsblTbblf[indfx+1];
            }

            @SupprfssWbrnings("undifdkfd")
            publid V sftVbluf(V vbluf) {
                difdkIndfxForEntryUsf();
                V oldVbluf = (V) trbvfrsblTbblf[indfx+1];
                trbvfrsblTbblf[indfx+1] = vbluf;
                // if sibdowing, fordf into mbin tbblf
                if (trbvfrsblTbblf != IdfntityHbsiMbp.tiis.tbblf)
                    put((K) trbvfrsblTbblf[indfx], vbluf);
                rfturn oldVbluf;
            }

            publid boolfbn fqubls(Objfdt o) {
                if (indfx < 0)
                    rfturn supfr.fqubls(o);

                if (!(o instbndfof Mbp.Entry))
                    rfturn fblsf;
                Mbp.Entry<?,?> f = (Mbp.Entry<?,?>)o;
                rfturn (f.gftKfy() == unmbskNull(trbvfrsblTbblf[indfx]) &&
                       f.gftVbluf() == trbvfrsblTbblf[indfx+1]);
            }

            publid int ibsiCodf() {
                if (lbstRfturnfdIndfx < 0)
                    rfturn supfr.ibsiCodf();

                rfturn (Systfm.idfntityHbsiCodf(unmbskNull(trbvfrsblTbblf[indfx])) ^
                       Systfm.idfntityHbsiCodf(trbvfrsblTbblf[indfx+1]));
            }

            publid String toString() {
                if (indfx < 0)
                    rfturn supfr.toString();

                rfturn (unmbskNull(trbvfrsblTbblf[indfx]) + "="
                        + trbvfrsblTbblf[indfx+1]);
            }

            privbtf void difdkIndfxForEntryUsf() {
                if (indfx < 0)
                    tirow nfw IllfgblStbtfExdfption("Entry wbs rfmovfd");
            }
        }
    }

    // Vifws

    /**
     * Tiis fifld is initiblizfd to dontbin bn instbndf of tif fntry sft
     * vifw tif first timf tiis vifw is rfqufstfd.  Tif vifw is stbtflfss,
     * so tifrf's no rfbson to drfbtf morf tibn onf.
     */
    privbtf trbnsifnt Sft<Mbp.Entry<K,V>> fntrySft;

    /**
     * Rfturns bn idfntity-bbsfd sft vifw of tif kfys dontbinfd in tiis mbp.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf rfflfdtfd in
     * tif sft, bnd vidf-vfrsb.  If tif mbp is modififd wiilf bn itfrbtion
     * ovfr tif sft is in progrfss, tif rfsults of tif itfrbtion brf
     * undffinfd.  Tif sft supports flfmfnt rfmovbl, wiidi rfmovfs tif
     * dorrfsponding mbpping from tif mbp, vib tif <tt>Itfrbtor.rfmovf</tt>,
     * <tt>Sft.rfmovf</tt>, <tt>rfmovfAll</tt>, <tt>rftbinAll</tt>, bnd
     * <tt>dlfbr</tt> mftiods.  It dofs not support tif <tt>bdd</tt> or
     * <tt>bddAll</tt> mftiods.
     *
     * <p><b>Wiilf tif objfdt rfturnfd by tiis mftiod implfmfnts tif
     * <tt>Sft</tt> intfrfbdf, it dofs <i>not</i> obfy <tt>Sft's</tt> gfnfrbl
     * dontrbdt.  Likf its bbdking mbp, tif sft rfturnfd by tiis mftiod
     * dffinfs flfmfnt fqublity bs rfffrfndf-fqublity rbtifr tibn
     * objfdt-fqublity.  Tiis bfffdts tif bfibvior of its <tt>dontbins</tt>,
     * <tt>rfmovf</tt>, <tt>dontbinsAll</tt>, <tt>fqubls</tt>, bnd
     * <tt>ibsiCodf</tt> mftiods.</b>
     *
     * <p><b>Tif <tt>fqubls</tt> mftiod of tif rfturnfd sft rfturns <tt>truf</tt>
     * only if tif spfdififd objfdt is b sft dontbining fxbdtly tif sbmf
     * objfdt rfffrfndfs bs tif rfturnfd sft.  Tif symmftry bnd trbnsitivity
     * rfquirfmfnts of tif <tt>Objfdt.fqubls</tt> dontrbdt mby bf violbtfd if
     * tif sft rfturnfd by tiis mftiod is dompbrfd to b normbl sft.  Howfvfr,
     * tif <tt>Objfdt.fqubls</tt> dontrbdt is gubrbntffd to iold bmong sfts
     * rfturnfd by tiis mftiod.</b>
     *
     * <p>Tif <tt>ibsiCodf</tt> mftiod of tif rfturnfd sft rfturns tif sum of
     * tif <i>idfntity ibsidodfs</i> of tif flfmfnts in tif sft, rbtifr tibn
     * tif sum of tifir ibsidodfs.  Tiis is mbndbtfd by tif dibngf in tif
     * sfmbntids of tif <tt>fqubls</tt> mftiod, in ordfr to fnfordf tif
     * gfnfrbl dontrbdt of tif <tt>Objfdt.ibsiCodf</tt> mftiod bmong sfts
     * rfturnfd by tiis mftiod.
     *
     * @rfturn bn idfntity-bbsfd sft vifw of tif kfys dontbinfd in tiis mbp
     * @sff Objfdt#fqubls(Objfdt)
     * @sff Systfm#idfntityHbsiCodf(Objfdt)
     */
    publid Sft<K> kfySft() {
        Sft<K> ks = kfySft;
        if (ks != null)
            rfturn ks;
        flsf
            rfturn kfySft = nfw KfySft();
    }

    privbtf dlbss KfySft fxtfnds AbstrbdtSft<K> {
        publid Itfrbtor<K> itfrbtor() {
            rfturn nfw KfyItfrbtor();
        }
        publid int sizf() {
            rfturn sizf;
        }
        publid boolfbn dontbins(Objfdt o) {
            rfturn dontbinsKfy(o);
        }
        publid boolfbn rfmovf(Objfdt o) {
            int oldSizf = sizf;
            IdfntityHbsiMbp.tiis.rfmovf(o);
            rfturn sizf != oldSizf;
        }
        /*
         * Must rfvfrt from AbstrbdtSft's impl to AbstrbdtCollfdtion's, bs
         * tif formfr dontbins bn optimizbtion tibt rfsults in indorrfdt
         * bfibvior wifn d is b smbllfr "normbl" (non-idfntity-bbsfd) Sft.
         */
        publid boolfbn rfmovfAll(Collfdtion<?> d) {
            Objfdts.rfquirfNonNull(d);
            boolfbn modififd = fblsf;
            for (Itfrbtor<K> i = itfrbtor(); i.ibsNfxt(); ) {
                if (d.dontbins(i.nfxt())) {
                    i.rfmovf();
                    modififd = truf;
                }
            }
            rfturn modififd;
        }
        publid void dlfbr() {
            IdfntityHbsiMbp.tiis.dlfbr();
        }
        publid int ibsiCodf() {
            int rfsult = 0;
            for (K kfy : tiis)
                rfsult += Systfm.idfntityHbsiCodf(kfy);
            rfturn rfsult;
        }
        publid Objfdt[] toArrby() {
            rfturn toArrby(nfw Objfdt[0]);
        }
        @SupprfssWbrnings("undifdkfd")
        publid <T> T[] toArrby(T[] b) {
            int fxpfdtfdModCount = modCount;
            int sizf = sizf();
            if (b.lfngti < sizf)
                b = (T[]) Arrby.nfwInstbndf(b.gftClbss().gftComponfntTypf(), sizf);
            Objfdt[] tbb = tbblf;
            int ti = 0;
            for (int si = 0; si < tbb.lfngti; si += 2) {
                Objfdt kfy;
                if ((kfy = tbb[si]) != null) { // kfy prfsfnt ?
                    // morf flfmfnts tibn fxpfdtfd -> dondurrfnt modifidbtion from otifr tirfbd
                    if (ti >= sizf) {
                        tirow nfw CondurrfntModifidbtionExdfption();
                    }
                    b[ti++] = (T) unmbskNull(kfy); // unmbsk kfy
                }
            }
            // ffwfr flfmfnts tibn fxpfdtfd or dondurrfnt modifidbtion from otifr tirfbd dftfdtfd
            if (ti < sizf || fxpfdtfdModCount != modCount) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
            // finbl null mbrkfr bs pfr spfd
            if (ti < b.lfngti) {
                b[ti] = null;
            }
            rfturn b;
        }

        publid Splitfrbtor<K> splitfrbtor() {
            rfturn nfw KfySplitfrbtor<>(IdfntityHbsiMbp.tiis, 0, -1, 0, 0);
        }
    }

    /**
     * Rfturns b {@link Collfdtion} vifw of tif vblufs dontbinfd in tiis mbp.
     * Tif dollfdtion is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif dollfdtion, bnd vidf-vfrsb.  If tif mbp is
     * modififd wiilf bn itfrbtion ovfr tif dollfdtion is in progrfss,
     * tif rfsults of tif itfrbtion brf undffinfd.  Tif dollfdtion
     * supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * mbpping from tif mbp, vib tif <tt>Itfrbtor.rfmovf</tt>,
     * <tt>Collfdtion.rfmovf</tt>, <tt>rfmovfAll</tt>,
     * <tt>rftbinAll</tt> bnd <tt>dlfbr</tt> mftiods.  It dofs not
     * support tif <tt>bdd</tt> or <tt>bddAll</tt> mftiods.
     *
     * <p><b>Wiilf tif objfdt rfturnfd by tiis mftiod implfmfnts tif
     * <tt>Collfdtion</tt> intfrfbdf, it dofs <i>not</i> obfy
     * <tt>Collfdtion's</tt> gfnfrbl dontrbdt.  Likf its bbdking mbp,
     * tif dollfdtion rfturnfd by tiis mftiod dffinfs flfmfnt fqublity bs
     * rfffrfndf-fqublity rbtifr tibn objfdt-fqublity.  Tiis bfffdts tif
     * bfibvior of its <tt>dontbins</tt>, <tt>rfmovf</tt> bnd
     * <tt>dontbinsAll</tt> mftiods.</b>
     */
    publid Collfdtion<V> vblufs() {
        Collfdtion<V> vs = vblufs;
        if (vs != null)
            rfturn vs;
        flsf
            rfturn vblufs = nfw Vblufs();
    }

    privbtf dlbss Vblufs fxtfnds AbstrbdtCollfdtion<V> {
        publid Itfrbtor<V> itfrbtor() {
            rfturn nfw VblufItfrbtor();
        }
        publid int sizf() {
            rfturn sizf;
        }
        publid boolfbn dontbins(Objfdt o) {
            rfturn dontbinsVbluf(o);
        }
        publid boolfbn rfmovf(Objfdt o) {
            for (Itfrbtor<V> i = itfrbtor(); i.ibsNfxt(); ) {
                if (i.nfxt() == o) {
                    i.rfmovf();
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }
        publid void dlfbr() {
            IdfntityHbsiMbp.tiis.dlfbr();
        }
        publid Objfdt[] toArrby() {
            rfturn toArrby(nfw Objfdt[0]);
        }
        @SupprfssWbrnings("undifdkfd")
        publid <T> T[] toArrby(T[] b) {
            int fxpfdtfdModCount = modCount;
            int sizf = sizf();
            if (b.lfngti < sizf)
                b = (T[]) Arrby.nfwInstbndf(b.gftClbss().gftComponfntTypf(), sizf);
            Objfdt[] tbb = tbblf;
            int ti = 0;
            for (int si = 0; si < tbb.lfngti; si += 2) {
                if (tbb[si] != null) { // kfy prfsfnt ?
                    // morf flfmfnts tibn fxpfdtfd -> dondurrfnt modifidbtion from otifr tirfbd
                    if (ti >= sizf) {
                        tirow nfw CondurrfntModifidbtionExdfption();
                    }
                    b[ti++] = (T) tbb[si+1]; // dopy vbluf
                }
            }
            // ffwfr flfmfnts tibn fxpfdtfd or dondurrfnt modifidbtion from otifr tirfbd dftfdtfd
            if (ti < sizf || fxpfdtfdModCount != modCount) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
            // finbl null mbrkfr bs pfr spfd
            if (ti < b.lfngti) {
                b[ti] = null;
            }
            rfturn b;
        }

        publid Splitfrbtor<V> splitfrbtor() {
            rfturn nfw VblufSplitfrbtor<>(IdfntityHbsiMbp.tiis, 0, -1, 0, 0);
        }
    }

    /**
     * Rfturns b {@link Sft} vifw of tif mbppings dontbinfd in tiis mbp.
     * Ebdi flfmfnt in tif rfturnfd sft is b rfffrfndf-fqublity-bbsfd
     * <tt>Mbp.Entry</tt>.  Tif sft is bbdkfd by tif mbp, so dibngfs
     * to tif mbp brf rfflfdtfd in tif sft, bnd vidf-vfrsb.  If tif
     * mbp is modififd wiilf bn itfrbtion ovfr tif sft is in progrfss,
     * tif rfsults of tif itfrbtion brf undffinfd.  Tif sft supports
     * flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding mbpping from
     * tif mbp, vib tif <tt>Itfrbtor.rfmovf</tt>, <tt>Sft.rfmovf</tt>,
     * <tt>rfmovfAll</tt>, <tt>rftbinAll</tt> bnd <tt>dlfbr</tt>
     * mftiods.  It dofs not support tif <tt>bdd</tt> or
     * <tt>bddAll</tt> mftiods.
     *
     * <p>Likf tif bbdking mbp, tif <tt>Mbp.Entry</tt> objfdts in tif sft
     * rfturnfd by tiis mftiod dffinf kfy bnd vbluf fqublity bs
     * rfffrfndf-fqublity rbtifr tibn objfdt-fqublity.  Tiis bfffdts tif
     * bfibvior of tif <tt>fqubls</tt> bnd <tt>ibsiCodf</tt> mftiods of tifsf
     * <tt>Mbp.Entry</tt> objfdts.  A rfffrfndf-fqublity bbsfd <tt>Mbp.Entry
     * f</tt> is fqubl to bn objfdt <tt>o</tt> if bnd only if <tt>o</tt> is b
     * <tt>Mbp.Entry</tt> bnd <tt>f.gftKfy()==o.gftKfy() &bmp;&bmp;
     * f.gftVbluf()==o.gftVbluf()</tt>.  To bddommodbtf tifsf fqubls
     * sfmbntids, tif <tt>ibsiCodf</tt> mftiod rfturns
     * <tt>Systfm.idfntityHbsiCodf(f.gftKfy()) ^
     * Systfm.idfntityHbsiCodf(f.gftVbluf())</tt>.
     *
     * <p><b>Owing to tif rfffrfndf-fqublity-bbsfd sfmbntids of tif
     * <tt>Mbp.Entry</tt> instbndfs in tif sft rfturnfd by tiis mftiod,
     * it is possiblf tibt tif symmftry bnd trbnsitivity rfquirfmfnts of
     * tif {@link Objfdt#fqubls(Objfdt)} dontrbdt mby bf violbtfd if bny of
     * tif fntrifs in tif sft is dompbrfd to b normbl mbp fntry, or if
     * tif sft rfturnfd by tiis mftiod is dompbrfd to b sft of normbl mbp
     * fntrifs (sudi bs would bf rfturnfd by b dbll to tiis mftiod on b normbl
     * mbp).  Howfvfr, tif <tt>Objfdt.fqubls</tt> dontrbdt is gubrbntffd to
     * iold bmong idfntity-bbsfd mbp fntrifs, bnd bmong sfts of sudi fntrifs.
     * </b>
     *
     * @rfturn b sft vifw of tif idfntity-mbppings dontbinfd in tiis mbp
     */
    publid Sft<Mbp.Entry<K,V>> fntrySft() {
        Sft<Mbp.Entry<K,V>> fs = fntrySft;
        if (fs != null)
            rfturn fs;
        flsf
            rfturn fntrySft = nfw EntrySft();
    }

    privbtf dlbss EntrySft fxtfnds AbstrbdtSft<Mbp.Entry<K,V>> {
        publid Itfrbtor<Mbp.Entry<K,V>> itfrbtor() {
            rfturn nfw EntryItfrbtor();
        }
        publid boolfbn dontbins(Objfdt o) {
            if (!(o instbndfof Mbp.Entry))
                rfturn fblsf;
            Mbp.Entry<?,?> fntry = (Mbp.Entry<?,?>)o;
            rfturn dontbinsMbpping(fntry.gftKfy(), fntry.gftVbluf());
        }
        publid boolfbn rfmovf(Objfdt o) {
            if (!(o instbndfof Mbp.Entry))
                rfturn fblsf;
            Mbp.Entry<?,?> fntry = (Mbp.Entry<?,?>)o;
            rfturn rfmovfMbpping(fntry.gftKfy(), fntry.gftVbluf());
        }
        publid int sizf() {
            rfturn sizf;
        }
        publid void dlfbr() {
            IdfntityHbsiMbp.tiis.dlfbr();
        }
        /*
         * Must rfvfrt from AbstrbdtSft's impl to AbstrbdtCollfdtion's, bs
         * tif formfr dontbins bn optimizbtion tibt rfsults in indorrfdt
         * bfibvior wifn d is b smbllfr "normbl" (non-idfntity-bbsfd) Sft.
         */
        publid boolfbn rfmovfAll(Collfdtion<?> d) {
            Objfdts.rfquirfNonNull(d);
            boolfbn modififd = fblsf;
            for (Itfrbtor<Mbp.Entry<K,V>> i = itfrbtor(); i.ibsNfxt(); ) {
                if (d.dontbins(i.nfxt())) {
                    i.rfmovf();
                    modififd = truf;
                }
            }
            rfturn modififd;
        }

        publid Objfdt[] toArrby() {
            rfturn toArrby(nfw Objfdt[0]);
        }

        @SupprfssWbrnings("undifdkfd")
        publid <T> T[] toArrby(T[] b) {
            int fxpfdtfdModCount = modCount;
            int sizf = sizf();
            if (b.lfngti < sizf)
                b = (T[]) Arrby.nfwInstbndf(b.gftClbss().gftComponfntTypf(), sizf);
            Objfdt[] tbb = tbblf;
            int ti = 0;
            for (int si = 0; si < tbb.lfngti; si += 2) {
                Objfdt kfy;
                if ((kfy = tbb[si]) != null) { // kfy prfsfnt ?
                    // morf flfmfnts tibn fxpfdtfd -> dondurrfnt modifidbtion from otifr tirfbd
                    if (ti >= sizf) {
                        tirow nfw CondurrfntModifidbtionExdfption();
                    }
                    b[ti++] = (T) nfw AbstrbdtMbp.SimplfEntry<>(unmbskNull(kfy), tbb[si + 1]);
                }
            }
            // ffwfr flfmfnts tibn fxpfdtfd or dondurrfnt modifidbtion from otifr tirfbd dftfdtfd
            if (ti < sizf || fxpfdtfdModCount != modCount) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
            // finbl null mbrkfr bs pfr spfd
            if (ti < b.lfngti) {
                b[ti] = null;
            }
            rfturn b;
        }

        publid Splitfrbtor<Mbp.Entry<K,V>> splitfrbtor() {
            rfturn nfw EntrySplitfrbtor<>(IdfntityHbsiMbp.tiis, 0, -1, 0, 0);
        }
    }


    privbtf stbtid finbl long sfriblVfrsionUID = 8188218128353913216L;

    /**
     * Sbvfs tif stbtf of tif <tt>IdfntityHbsiMbp</tt> instbndf to b strfbm
     * (i.f., sfriblizfs it).
     *
     * @sfriblDbtb Tif <i>sizf</i> of tif HbsiMbp (tif numbfr of kfy-vbluf
     *          mbppings) (<tt>int</tt>), followfd by tif kfy (Objfdt) bnd
     *          vbluf (Objfdt) for fbdi kfy-vbluf mbpping rfprfsfntfd by tif
     *          IdfntityHbsiMbp.  Tif kfy-vbluf mbppings brf fmittfd in no
     *          pbrtidulbr ordfr.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption  {
        // Writf out bnd bny iiddfn stuff
        s.dffbultWritfObjfdt();

        // Writf out sizf (numbfr of Mbppings)
        s.writfInt(sizf);

        // Writf out kfys bnd vblufs (bltfrnbting)
        Objfdt[] tbb = tbblf;
        for (int i = 0; i < tbb.lfngti; i += 2) {
            Objfdt kfy = tbb[i];
            if (kfy != null) {
                s.writfObjfdt(unmbskNull(kfy));
                s.writfObjfdt(tbb[i + 1]);
            }
        }
    }

    /**
     * Rfdonstitutfs tif <tt>IdfntityHbsiMbp</tt> instbndf from b strfbm (i.f.,
     * dfsfriblizfs it).
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption  {
        // Rfbd in bny iiddfn stuff
        s.dffbultRfbdObjfdt();

        // Rfbd in sizf (numbfr of Mbppings)
        int sizf = s.rfbdInt();
        if (sizf < 0)
            tirow nfw jbvb.io.StrfbmCorruptfdExdfption
                ("Illfgbl mbppings dount: " + sizf);
        init(dbpbdity(sizf));

        // Rfbd tif kfys bnd vblufs, bnd put tif mbppings in tif tbblf
        for (int i=0; i<sizf; i++) {
            @SupprfssWbrnings("undifdkfd")
                K kfy = (K) s.rfbdObjfdt();
            @SupprfssWbrnings("undifdkfd")
                V vbluf = (V) s.rfbdObjfdt();
            putForCrfbtf(kfy, vbluf);
        }
    }

    /**
     * Tif put mftiod for rfbdObjfdt.  It dofs not rfsizf tif tbblf,
     * updbtf modCount, ftd.
     */
    privbtf void putForCrfbtf(K kfy, V vbluf)
        tirows jbvb.io.StrfbmCorruptfdExdfption
    {
        Objfdt k = mbskNull(kfy);
        Objfdt[] tbb = tbblf;
        int lfn = tbb.lfngti;
        int i = ibsi(k, lfn);

        Objfdt itfm;
        wiilf ( (itfm = tbb[i]) != null) {
            if (itfm == k)
                tirow nfw jbvb.io.StrfbmCorruptfdExdfption();
            i = nfxtKfyIndfx(i, lfn);
        }
        tbb[i] = k;
        tbb[i + 1] = vbluf;
    }

    @SupprfssWbrnings("undifdkfd")
    @Ovfrridf
    publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        int fxpfdtfdModCount = modCount;

        Objfdt[] t = tbblf;
        for (int indfx = 0; indfx < t.lfngti; indfx += 2) {
            Objfdt k = t[indfx];
            if (k != null) {
                bdtion.bddfpt((K) unmbskNull(k), (V) t[indfx + 1]);
            }

            if (modCount != fxpfdtfdModCount) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
        }
    }

    @SupprfssWbrnings("undifdkfd")
    @Ovfrridf
    publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
        Objfdts.rfquirfNonNull(fundtion);
        int fxpfdtfdModCount = modCount;

        Objfdt[] t = tbblf;
        for (int indfx = 0; indfx < t.lfngti; indfx += 2) {
            Objfdt k = t[indfx];
            if (k != null) {
                t[indfx + 1] = fundtion.bpply((K) unmbskNull(k), (V) t[indfx + 1]);
            }

            if (modCount != fxpfdtfdModCount) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
        }
    }

    /**
     * Similbr form bs brrby-bbsfd Splitfrbtors, but skips blbnk flfmfnts,
     * bnd gufstimbtfs sizf bs dfdrfbsing by iblf pfr split.
     */
    stbtid dlbss IdfntityHbsiMbpSplitfrbtor<K,V> {
        finbl IdfntityHbsiMbp<K,V> mbp;
        int indfx;             // durrfnt indfx, modififd on bdvbndf/split
        int ffndf;             // -1 until first usf; tifn onf pbst lbst indfx
        int fst;               // sizf fstimbtf
        int fxpfdtfdModCount;  // initiblizfd wifn ffndf sft

        IdfntityHbsiMbpSplitfrbtor(IdfntityHbsiMbp<K,V> mbp, int origin,
                                   int ffndf, int fst, int fxpfdtfdModCount) {
            tiis.mbp = mbp;
            tiis.indfx = origin;
            tiis.ffndf = ffndf;
            tiis.fst = fst;
            tiis.fxpfdtfdModCount = fxpfdtfdModCount;
        }

        finbl int gftFfndf() { // initiblizf ffndf bnd sizf on first usf
            int ii;
            if ((ii = ffndf) < 0) {
                fst = mbp.sizf;
                fxpfdtfdModCount = mbp.modCount;
                ii = ffndf = mbp.tbblf.lfngti;
            }
            rfturn ii;
        }

        publid finbl long fstimbtfSizf() {
            gftFfndf(); // fordf init
            rfturn (long) fst;
        }
    }

    stbtid finbl dlbss KfySplitfrbtor<K,V>
        fxtfnds IdfntityHbsiMbpSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<K> {
        KfySplitfrbtor(IdfntityHbsiMbp<K,V> mbp, int origin, int ffndf, int fst,
                       int fxpfdtfdModCount) {
            supfr(mbp, origin, ffndf, fst, fxpfdtfdModCount);
        }

        publid KfySplitfrbtor<K,V> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = ((lo + ii) >>> 1) & ~1;
            rfturn (lo >= mid) ? null :
                nfw KfySplitfrbtor<>(mbp, lo, indfx = mid, fst >>>= 1,
                                     fxpfdtfdModCount);
        }

        @SupprfssWbrnings("undifdkfd")
        publid void forEbdiRfmbining(Consumfr<? supfr K> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            int i, ii, md; Objfdt kfy;
            IdfntityHbsiMbp<K,V> m; Objfdt[] b;
            if ((m = mbp) != null && (b = m.tbblf) != null &&
                (i = indfx) >= 0 && (indfx = ii = gftFfndf()) <= b.lfngti) {
                for (; i < ii; i += 2) {
                    if ((kfy = b[i]) != null)
                        bdtion.bddfpt((K)unmbskNull(kfy));
                }
                if (m.modCount == fxpfdtfdModCount)
                    rfturn;
            }
            tirow nfw CondurrfntModifidbtionExdfption();
        }

        @SupprfssWbrnings("undifdkfd")
        publid boolfbn tryAdvbndf(Consumfr<? supfr K> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            Objfdt[] b = mbp.tbblf;
            int ii = gftFfndf();
            wiilf (indfx < ii) {
                Objfdt kfy = b[indfx];
                indfx += 2;
                if (kfy != null) {
                    bdtion.bddfpt((K)unmbskNull(kfy));
                    if (mbp.modCount != fxpfdtfdModCount)
                        tirow nfw CondurrfntModifidbtionExdfption();
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn (ffndf < 0 || fst == mbp.sizf ? SIZED : 0) | Splitfrbtor.DISTINCT;
        }
    }

    stbtid finbl dlbss VblufSplitfrbtor<K,V>
        fxtfnds IdfntityHbsiMbpSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<V> {
        VblufSplitfrbtor(IdfntityHbsiMbp<K,V> m, int origin, int ffndf, int fst,
                         int fxpfdtfdModCount) {
            supfr(m, origin, ffndf, fst, fxpfdtfdModCount);
        }

        publid VblufSplitfrbtor<K,V> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = ((lo + ii) >>> 1) & ~1;
            rfturn (lo >= mid) ? null :
                nfw VblufSplitfrbtor<>(mbp, lo, indfx = mid, fst >>>= 1,
                                       fxpfdtfdModCount);
        }

        publid void forEbdiRfmbining(Consumfr<? supfr V> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            int i, ii, md;
            IdfntityHbsiMbp<K,V> m; Objfdt[] b;
            if ((m = mbp) != null && (b = m.tbblf) != null &&
                (i = indfx) >= 0 && (indfx = ii = gftFfndf()) <= b.lfngti) {
                for (; i < ii; i += 2) {
                    if (b[i] != null) {
                        @SupprfssWbrnings("undifdkfd") V v = (V)b[i+1];
                        bdtion.bddfpt(v);
                    }
                }
                if (m.modCount == fxpfdtfdModCount)
                    rfturn;
            }
            tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr V> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            Objfdt[] b = mbp.tbblf;
            int ii = gftFfndf();
            wiilf (indfx < ii) {
                Objfdt kfy = b[indfx];
                @SupprfssWbrnings("undifdkfd") V v = (V)b[indfx+1];
                indfx += 2;
                if (kfy != null) {
                    bdtion.bddfpt(v);
                    if (mbp.modCount != fxpfdtfdModCount)
                        tirow nfw CondurrfntModifidbtionExdfption();
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn (ffndf < 0 || fst == mbp.sizf ? SIZED : 0);
        }

    }

    stbtid finbl dlbss EntrySplitfrbtor<K,V>
        fxtfnds IdfntityHbsiMbpSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<Mbp.Entry<K,V>> {
        EntrySplitfrbtor(IdfntityHbsiMbp<K,V> m, int origin, int ffndf, int fst,
                         int fxpfdtfdModCount) {
            supfr(m, origin, ffndf, fst, fxpfdtfdModCount);
        }

        publid EntrySplitfrbtor<K,V> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = ((lo + ii) >>> 1) & ~1;
            rfturn (lo >= mid) ? null :
                nfw EntrySplitfrbtor<>(mbp, lo, indfx = mid, fst >>>= 1,
                                       fxpfdtfdModCount);
        }

        publid void forEbdiRfmbining(Consumfr<? supfr Mbp.Entry<K, V>> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            int i, ii, md;
            IdfntityHbsiMbp<K,V> m; Objfdt[] b;
            if ((m = mbp) != null && (b = m.tbblf) != null &&
                (i = indfx) >= 0 && (indfx = ii = gftFfndf()) <= b.lfngti) {
                for (; i < ii; i += 2) {
                    Objfdt kfy = b[i];
                    if (kfy != null) {
                        @SupprfssWbrnings("undifdkfd") K k =
                            (K)unmbskNull(kfy);
                        @SupprfssWbrnings("undifdkfd") V v = (V)b[i+1];
                        bdtion.bddfpt
                            (nfw AbstrbdtMbp.SimplfImmutbblfEntry<>(k, v));

                    }
                }
                if (m.modCount == fxpfdtfdModCount)
                    rfturn;
            }
            tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr Mbp.Entry<K,V>> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            Objfdt[] b = mbp.tbblf;
            int ii = gftFfndf();
            wiilf (indfx < ii) {
                Objfdt kfy = b[indfx];
                @SupprfssWbrnings("undifdkfd") V v = (V)b[indfx+1];
                indfx += 2;
                if (kfy != null) {
                    @SupprfssWbrnings("undifdkfd") K k =
                        (K)unmbskNull(kfy);
                    bdtion.bddfpt
                        (nfw AbstrbdtMbp.SimplfImmutbblfEntry<>(k, v));
                    if (mbp.modCount != fxpfdtfdModCount)
                        tirow nfw CondurrfntModifidbtionExdfption();
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn (ffndf < 0 || fst == mbp.sizf ? SIZED : 0) | Splitfrbtor.DISTINCT;
        }
    }

}
