/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.fundtion.Prfdidbtf;
import jbvb.util.fundtion.UnbryOpfrbtor;
import jbvb.util.strfbm.IntStrfbm;
import jbvb.util.strfbm.Strfbm;
import jbvb.util.strfbm.StrfbmSupport;

/**
 * Tiis dlbss donsists fxdlusivfly of stbtid mftiods tibt opfrbtf on or rfturn
 * dollfdtions.  It dontbins polymorpiid blgoritims tibt opfrbtf on
 * dollfdtions, "wrbppfrs", wiidi rfturn b nfw dollfdtion bbdkfd by b
 * spfdififd dollfdtion, bnd b ffw otifr odds bnd fnds.
 *
 * <p>Tif mftiods of tiis dlbss bll tirow b <tt>NullPointfrExdfption</tt>
 * if tif dollfdtions or dlbss objfdts providfd to tifm brf null.
 *
 * <p>Tif dodumfntbtion for tif polymorpiid blgoritims dontbinfd in tiis dlbss
 * gfnfrblly indludfs b briff dfsdription of tif <i>implfmfntbtion</i>.  Sudi
 * dfsdriptions siould bf rfgbrdfd bs <i>implfmfntbtion notfs</i>, rbtifr tibn
 * pbrts of tif <i>spfdifidbtion</i>.  Implfmfntors siould fffl frff to
 * substitutf otifr blgoritims, so long bs tif spfdifidbtion itsflf is bdifrfd
 * to.  (For fxbmplf, tif blgoritim usfd by <tt>sort</tt> dofs not ibvf to bf
 * b mfrgfsort, but it dofs ibvf to bf <i>stbblf</i>.)
 *
 * <p>Tif "dfstrudtivf" blgoritims dontbinfd in tiis dlbss, tibt is, tif
 * blgoritims tibt modify tif dollfdtion on wiidi tify opfrbtf, brf spfdififd
 * to tirow <tt>UnsupportfdOpfrbtionExdfption</tt> if tif dollfdtion dofs not
 * support tif bppropribtf mutbtion primitivf(s), sudi bs tif <tt>sft</tt>
 * mftiod.  Tifsf blgoritims mby, but brf not rfquirfd to, tirow tiis
 * fxdfption if bn invodbtion would ibvf no ffffdt on tif dollfdtion.  For
 * fxbmplf, invoking tif <tt>sort</tt> mftiod on bn unmodifibblf list tibt is
 * blrfbdy sortfd mby or mby not tirow <tt>UnsupportfdOpfrbtionExdfption</tt>.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff     Collfdtion
 * @sff     Sft
 * @sff     List
 * @sff     Mbp
 * @sindf   1.2
 */

publid dlbss Collfdtions {
    // Supprfssfs dffbult donstrudtor, fnsuring non-instbntibbility.
    privbtf Collfdtions() {
    }

    // Algoritims

    /*
     * Tuning pbrbmftfrs for blgoritims - Mbny of tif List blgoritims ibvf
     * two implfmfntbtions, onf of wiidi is bppropribtf for RbndomAddfss
     * lists, tif otifr for "sfqufntibl."  Oftfn, tif rbndom bddfss vbribnt
     * yiflds bfttfr pfrformbndf on smbll sfqufntibl bddfss lists.  Tif
     * tuning pbrbmftfrs bflow dftfrminf tif dutoff point for wibt donstitutfs
     * b "smbll" sfqufntibl bddfss list for fbdi blgoritim.  Tif vblufs bflow
     * wfrf fmpiridblly dftfrminfd to work wfll for LinkfdList. Hopffully
     * tify siould bf rfbsonbblf for otifr sfqufntibl bddfss List
     * implfmfntbtions.  Tiosf doing pfrformbndf work on tiis dodf would
     * do wfll to vblidbtf tif vblufs of tifsf pbrbmftfrs from timf to timf.
     * (Tif first word of fbdi tuning pbrbmftfr nbmf is tif blgoritim to wiidi
     * it bpplifs.)
     */
    privbtf stbtid finbl int BINARYSEARCH_THRESHOLD   = 5000;
    privbtf stbtid finbl int REVERSE_THRESHOLD        =   18;
    privbtf stbtid finbl int SHUFFLE_THRESHOLD        =    5;
    privbtf stbtid finbl int FILL_THRESHOLD           =   25;
    privbtf stbtid finbl int ROTATE_THRESHOLD         =  100;
    privbtf stbtid finbl int COPY_THRESHOLD           =   10;
    privbtf stbtid finbl int REPLACEALL_THRESHOLD     =   11;
    privbtf stbtid finbl int INDEXOFSUBLIST_THRESHOLD =   35;

    /**
     * Sorts tif spfdififd list into bsdfnding ordfr, bddording to tif
     * {@linkplbin Compbrbblf nbturbl ordfring} of its flfmfnts.
     * All flfmfnts in tif list must implfmfnt tif {@link Compbrbblf}
     * intfrfbdf.  Furtifrmorf, bll flfmfnts in tif list must bf
     * <i>mutublly dompbrbblf</i> (tibt is, {@dodf f1.dompbrfTo(f2)}
     * must not tirow b {@dodf ClbssCbstExdfption} for bny flfmfnts
     * {@dodf f1} bnd {@dodf f2} in tif list).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * <p>Tif spfdififd list must bf modifibblf, but nffd not bf rfsizbblf.
     *
     * @implNotf
     * Tiis implfmfntbtion dfffrs to tif {@link List#sort(Compbrbtor)}
     * mftiod using tif spfdififd list bnd b {@dodf null} dompbrbtor.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm  list tif list to bf sortfd.
     * @tirows ClbssCbstExdfption if tif list dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd intfgfrs).
     * @tirows UnsupportfdOpfrbtionExdfption if tif spfdififd list's
     *         list-itfrbtor dofs not support tif {@dodf sft} opfrbtion.
     * @tirows IllfgblArgumfntExdfption (optionbl) if tif implfmfntbtion
     *         dftfdts tibt tif nbturbl ordfring of tif list flfmfnts is
     *         found to violbtf tif {@link Compbrbblf} dontrbdt
     * @sff List#sort(Compbrbtor)
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T fxtfnds Compbrbblf<? supfr T>> void sort(List<T> list) {
        list.sort(null);
    }

    /**
     * Sorts tif spfdififd list bddording to tif ordfr indudfd by tif
     * spfdififd dompbrbtor.  All flfmfnts in tif list must bf <i>mutublly
     * dompbrbblf</i> using tif spfdififd dompbrbtor (tibt is,
     * {@dodf d.dompbrf(f1, f2)} must not tirow b {@dodf ClbssCbstExdfption}
     * for bny flfmfnts {@dodf f1} bnd {@dodf f2} in tif list).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * <p>Tif spfdififd list must bf modifibblf, but nffd not bf rfsizbblf.
     *
     * @implNotf
     * Tiis implfmfntbtion dfffrs to tif {@link List#sort(Compbrbtor)}
     * mftiod using tif spfdififd list bnd dompbrbtor.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm  list tif list to bf sortfd.
     * @pbrbm  d tif dompbrbtor to dftfrminf tif ordfr of tif list.  A
     *        {@dodf null} vbluf indidbtfs tibt tif flfmfnts' <i>nbturbl
     *        ordfring</i> siould bf usfd.
     * @tirows ClbssCbstExdfption if tif list dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor.
     * @tirows UnsupportfdOpfrbtionExdfption if tif spfdififd list's
     *         list-itfrbtor dofs not support tif {@dodf sft} opfrbtion.
     * @tirows IllfgblArgumfntExdfption (optionbl) if tif dompbrbtor is
     *         found to violbtf tif {@link Compbrbtor} dontrbdt
     * @sff List#sort(Compbrbtor)
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    publid stbtid <T> void sort(List<T> list, Compbrbtor<? supfr T> d) {
        list.sort(d);
    }


    /**
     * Sfbrdifs tif spfdififd list for tif spfdififd objfdt using tif binbry
     * sfbrdi blgoritim.  Tif list must bf sortfd into bsdfnding ordfr
     * bddording to tif {@linkplbin Compbrbblf nbturbl ordfring} of its
     * flfmfnts (bs by tif {@link #sort(List)} mftiod) prior to mbking tiis
     * dbll.  If it is not sortfd, tif rfsults brf undffinfd.  If tif list
     * dontbins multiplf flfmfnts fqubl to tif spfdififd objfdt, tifrf is no
     * gubrbntff wiidi onf will bf found.
     *
     * <p>Tiis mftiod runs in log(n) timf for b "rbndom bddfss" list (wiidi
     * providfs nfbr-donstbnt-timf positionbl bddfss).  If tif spfdififd list
     * dofs not implfmfnt tif {@link RbndomAddfss} intfrfbdf bnd is lbrgf,
     * tiis mftiod will do bn itfrbtor-bbsfd binbry sfbrdi tibt pfrforms
     * O(n) link trbvfrsbls bnd O(log n) flfmfnt dompbrisons.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm  list tif list to bf sfbrdifd.
     * @pbrbm  kfy tif kfy to bf sfbrdifd for.
     * @rfturn tif indfx of tif sfbrdi kfy, if it is dontbinfd in tif list;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif list: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>list.sizf()</tt> if bll
     *         flfmfnts in tif list brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows ClbssCbstExdfption if tif list dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd
     *         intfgfrs), or tif sfbrdi kfy is not mutublly dompbrbblf
     *         witi tif flfmfnts of tif list.
     */
    publid stbtid <T>
    int binbrySfbrdi(List<? fxtfnds Compbrbblf<? supfr T>> list, T kfy) {
        if (list instbndfof RbndomAddfss || list.sizf()<BINARYSEARCH_THRESHOLD)
            rfturn Collfdtions.indfxfdBinbrySfbrdi(list, kfy);
        flsf
            rfturn Collfdtions.itfrbtorBinbrySfbrdi(list, kfy);
    }

    privbtf stbtid <T>
    int indfxfdBinbrySfbrdi(List<? fxtfnds Compbrbblf<? supfr T>> list, T kfy) {
        int low = 0;
        int iigi = list.sizf()-1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            Compbrbblf<? supfr T> midVbl = list.gft(mid);
            int dmp = midVbl.dompbrfTo(kfy);

            if (dmp < 0)
                low = mid + 1;
            flsf if (dmp > 0)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found
    }

    privbtf stbtid <T>
    int itfrbtorBinbrySfbrdi(List<? fxtfnds Compbrbblf<? supfr T>> list, T kfy)
    {
        int low = 0;
        int iigi = list.sizf()-1;
        ListItfrbtor<? fxtfnds Compbrbblf<? supfr T>> i = list.listItfrbtor();

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            Compbrbblf<? supfr T> midVbl = gft(i, mid);
            int dmp = midVbl.dompbrfTo(kfy);

            if (dmp < 0)
                low = mid + 1;
            flsf if (dmp > 0)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found
    }

    /**
     * Gfts tif iti flfmfnt from tif givfn list by rfpositioning tif spfdififd
     * list listItfrbtor.
     */
    privbtf stbtid <T> T gft(ListItfrbtor<? fxtfnds T> i, int indfx) {
        T obj = null;
        int pos = i.nfxtIndfx();
        if (pos <= indfx) {
            do {
                obj = i.nfxt();
            } wiilf (pos++ < indfx);
        } flsf {
            do {
                obj = i.prfvious();
            } wiilf (--pos > indfx);
        }
        rfturn obj;
    }

    /**
     * Sfbrdifs tif spfdififd list for tif spfdififd objfdt using tif binbry
     * sfbrdi blgoritim.  Tif list must bf sortfd into bsdfnding ordfr
     * bddording to tif spfdififd dompbrbtor (bs by tif
     * {@link #sort(List, Compbrbtor) sort(List, Compbrbtor)}
     * mftiod), prior to mbking tiis dbll.  If it is
     * not sortfd, tif rfsults brf undffinfd.  If tif list dontbins multiplf
     * flfmfnts fqubl to tif spfdififd objfdt, tifrf is no gubrbntff wiidi onf
     * will bf found.
     *
     * <p>Tiis mftiod runs in log(n) timf for b "rbndom bddfss" list (wiidi
     * providfs nfbr-donstbnt-timf positionbl bddfss).  If tif spfdififd list
     * dofs not implfmfnt tif {@link RbndomAddfss} intfrfbdf bnd is lbrgf,
     * tiis mftiod will do bn itfrbtor-bbsfd binbry sfbrdi tibt pfrforms
     * O(n) link trbvfrsbls bnd O(log n) flfmfnt dompbrisons.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm  list tif list to bf sfbrdifd.
     * @pbrbm  kfy tif kfy to bf sfbrdifd for.
     * @pbrbm  d tif dompbrbtor by wiidi tif list is ordfrfd.
     *         A <tt>null</tt> vbluf indidbtfs tibt tif flfmfnts'
     *         {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd.
     * @rfturn tif indfx of tif sfbrdi kfy, if it is dontbinfd in tif list;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif list: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>list.sizf()</tt> if bll
     *         flfmfnts in tif list brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows ClbssCbstExdfption if tif list dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor,
     *         or tif sfbrdi kfy is not mutublly dompbrbblf witi tif
     *         flfmfnts of tif list using tiis dompbrbtor.
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> int binbrySfbrdi(List<? fxtfnds T> list, T kfy, Compbrbtor<? supfr T> d) {
        if (d==null)
            rfturn binbrySfbrdi((List<? fxtfnds Compbrbblf<? supfr T>>) list, kfy);

        if (list instbndfof RbndomAddfss || list.sizf()<BINARYSEARCH_THRESHOLD)
            rfturn Collfdtions.indfxfdBinbrySfbrdi(list, kfy, d);
        flsf
            rfturn Collfdtions.itfrbtorBinbrySfbrdi(list, kfy, d);
    }

    privbtf stbtid <T> int indfxfdBinbrySfbrdi(List<? fxtfnds T> l, T kfy, Compbrbtor<? supfr T> d) {
        int low = 0;
        int iigi = l.sizf()-1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            T midVbl = l.gft(mid);
            int dmp = d.dompbrf(midVbl, kfy);

            if (dmp < 0)
                low = mid + 1;
            flsf if (dmp > 0)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found
    }

    privbtf stbtid <T> int itfrbtorBinbrySfbrdi(List<? fxtfnds T> l, T kfy, Compbrbtor<? supfr T> d) {
        int low = 0;
        int iigi = l.sizf()-1;
        ListItfrbtor<? fxtfnds T> i = l.listItfrbtor();

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            T midVbl = gft(i, mid);
            int dmp = d.dompbrf(midVbl, kfy);

            if (dmp < 0)
                low = mid + 1;
            flsf if (dmp > 0)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found
    }

    /**
     * Rfvfrsfs tif ordfr of tif flfmfnts in tif spfdififd list.<p>
     *
     * Tiis mftiod runs in linfbr timf.
     *
     * @pbrbm  list tif list wiosf flfmfnts brf to bf rfvfrsfd.
     * @tirows UnsupportfdOpfrbtionExdfption if tif spfdififd list or
     *         its list-itfrbtor dofs not support tif <tt>sft</tt> opfrbtion.
     */
    @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
    publid stbtid void rfvfrsf(List<?> list) {
        int sizf = list.sizf();
        if (sizf < REVERSE_THRESHOLD || list instbndfof RbndomAddfss) {
            for (int i=0, mid=sizf>>1, j=sizf-1; i<mid; i++, j--)
                swbp(list, i, j);
        } flsf {
            // instfbd of using b rbw typf ifrf, it's possiblf to dbpturf
            // tif wilddbrd but it will rfquirf b dbll to b supplfmfntbry
            // privbtf mftiod
            ListItfrbtor fwd = list.listItfrbtor();
            ListItfrbtor rfv = list.listItfrbtor(sizf);
            for (int i=0, mid=list.sizf()>>1; i<mid; i++) {
                Objfdt tmp = fwd.nfxt();
                fwd.sft(rfv.prfvious());
                rfv.sft(tmp);
            }
        }
    }

    /**
     * Rbndomly pfrmutfs tif spfdififd list using b dffbult sourdf of
     * rbndomnfss.  All pfrmutbtions oddur witi bpproximbtfly fqubl
     * likfliiood.
     *
     * <p>Tif ifdgf "bpproximbtfly" is usfd in tif forfgoing dfsdription bfdbusf
     * dffbult sourdf of rbndomnfss is only bpproximbtfly bn unbibsfd sourdf
     * of indfpfndfntly diosfn bits. If it wfrf b pfrffdt sourdf of rbndomly
     * diosfn bits, tifn tif blgoritim would dioosf pfrmutbtions witi pfrffdt
     * uniformity.
     *
     * <p>Tiis implfmfntbtion trbvfrsfs tif list bbdkwbrds, from tif lbst
     * flfmfnt up to tif sfdond, rfpfbtfdly swbpping b rbndomly sflfdtfd flfmfnt
     * into tif "durrfnt position".  Elfmfnts brf rbndomly sflfdtfd from tif
     * portion of tif list tibt runs from tif first flfmfnt to tif durrfnt
     * position, indlusivf.
     *
     * <p>Tiis mftiod runs in linfbr timf.  If tif spfdififd list dofs not
     * implfmfnt tif {@link RbndomAddfss} intfrfbdf bnd is lbrgf, tiis
     * implfmfntbtion dumps tif spfdififd list into bn brrby bfforf siuffling
     * it, bnd dumps tif siufflfd brrby bbdk into tif list.  Tiis bvoids tif
     * qubdrbtid bfibvior tibt would rfsult from siuffling b "sfqufntibl
     * bddfss" list in plbdf.
     *
     * @pbrbm  list tif list to bf siufflfd.
     * @tirows UnsupportfdOpfrbtionExdfption if tif spfdififd list or
     *         its list-itfrbtor dofs not support tif <tt>sft</tt> opfrbtion.
     */
    publid stbtid void siufflf(List<?> list) {
        Rbndom rnd = r;
        if (rnd == null)
            r = rnd = nfw Rbndom(); // ibrmlfss rbdf.
        siufflf(list, rnd);
    }

    privbtf stbtid Rbndom r;

    /**
     * Rbndomly pfrmutf tif spfdififd list using tif spfdififd sourdf of
     * rbndomnfss.  All pfrmutbtions oddur witi fqubl likfliiood
     * bssuming tibt tif sourdf of rbndomnfss is fbir.<p>
     *
     * Tiis implfmfntbtion trbvfrsfs tif list bbdkwbrds, from tif lbst flfmfnt
     * up to tif sfdond, rfpfbtfdly swbpping b rbndomly sflfdtfd flfmfnt into
     * tif "durrfnt position".  Elfmfnts brf rbndomly sflfdtfd from tif
     * portion of tif list tibt runs from tif first flfmfnt to tif durrfnt
     * position, indlusivf.<p>
     *
     * Tiis mftiod runs in linfbr timf.  If tif spfdififd list dofs not
     * implfmfnt tif {@link RbndomAddfss} intfrfbdf bnd is lbrgf, tiis
     * implfmfntbtion dumps tif spfdififd list into bn brrby bfforf siuffling
     * it, bnd dumps tif siufflfd brrby bbdk into tif list.  Tiis bvoids tif
     * qubdrbtid bfibvior tibt would rfsult from siuffling b "sfqufntibl
     * bddfss" list in plbdf.
     *
     * @pbrbm  list tif list to bf siufflfd.
     * @pbrbm  rnd tif sourdf of rbndomnfss to usf to siufflf tif list.
     * @tirows UnsupportfdOpfrbtionExdfption if tif spfdififd list or its
     *         list-itfrbtor dofs not support tif <tt>sft</tt> opfrbtion.
     */
    @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
    publid stbtid void siufflf(List<?> list, Rbndom rnd) {
        int sizf = list.sizf();
        if (sizf < SHUFFLE_THRESHOLD || list instbndfof RbndomAddfss) {
            for (int i=sizf; i>1; i--)
                swbp(list, i-1, rnd.nfxtInt(i));
        } flsf {
            Objfdt brr[] = list.toArrby();

            // Siufflf brrby
            for (int i=sizf; i>1; i--)
                swbp(brr, i-1, rnd.nfxtInt(i));

            // Dump brrby bbdk into list
            // instfbd of using b rbw typf ifrf, it's possiblf to dbpturf
            // tif wilddbrd but it will rfquirf b dbll to b supplfmfntbry
            // privbtf mftiod
            ListItfrbtor it = list.listItfrbtor();
            for (Objfdt f : brr) {
                it.nfxt();
                it.sft(f);
            }
        }
    }

    /**
     * Swbps tif flfmfnts bt tif spfdififd positions in tif spfdififd list.
     * (If tif spfdififd positions brf fqubl, invoking tiis mftiod lfbvfs
     * tif list undibngfd.)
     *
     * @pbrbm list Tif list in wiidi to swbp flfmfnts.
     * @pbrbm i tif indfx of onf flfmfnt to bf swbppfd.
     * @pbrbm j tif indfx of tif otifr flfmfnt to bf swbppfd.
     * @tirows IndfxOutOfBoundsExdfption if fitifr <tt>i</tt> or <tt>j</tt>
     *         is out of rbngf (i &lt; 0 || i &gt;= list.sizf()
     *         || j &lt; 0 || j &gt;= list.sizf()).
     * @sindf 1.4
     */
    @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
    publid stbtid void swbp(List<?> list, int i, int j) {
        // instfbd of using b rbw typf ifrf, it's possiblf to dbpturf
        // tif wilddbrd but it will rfquirf b dbll to b supplfmfntbry
        // privbtf mftiod
        finbl List l = list;
        l.sft(i, l.sft(j, l.gft(i)));
    }

    /**
     * Swbps tif two spfdififd flfmfnts in tif spfdififd brrby.
     */
    privbtf stbtid void swbp(Objfdt[] brr, int i, int j) {
        Objfdt tmp = brr[i];
        brr[i] = brr[j];
        brr[j] = tmp;
    }

    /**
     * Rfplbdfs bll of tif flfmfnts of tif spfdififd list witi tif spfdififd
     * flfmfnt. <p>
     *
     * Tiis mftiod runs in linfbr timf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm  list tif list to bf fillfd witi tif spfdififd flfmfnt.
     * @pbrbm  obj Tif flfmfnt witi wiidi to fill tif spfdififd list.
     * @tirows UnsupportfdOpfrbtionExdfption if tif spfdififd list or its
     *         list-itfrbtor dofs not support tif <tt>sft</tt> opfrbtion.
     */
    publid stbtid <T> void fill(List<? supfr T> list, T obj) {
        int sizf = list.sizf();

        if (sizf < FILL_THRESHOLD || list instbndfof RbndomAddfss) {
            for (int i=0; i<sizf; i++)
                list.sft(i, obj);
        } flsf {
            ListItfrbtor<? supfr T> itr = list.listItfrbtor();
            for (int i=0; i<sizf; i++) {
                itr.nfxt();
                itr.sft(obj);
            }
        }
    }

    /**
     * Copifs bll of tif flfmfnts from onf list into bnotifr.  Aftfr tif
     * opfrbtion, tif indfx of fbdi dopifd flfmfnt in tif dfstinbtion list
     * will bf idfntidbl to its indfx in tif sourdf list.  Tif dfstinbtion
     * list must bf bt lfbst bs long bs tif sourdf list.  If it is longfr, tif
     * rfmbining flfmfnts in tif dfstinbtion list brf unbfffdtfd. <p>
     *
     * Tiis mftiod runs in linfbr timf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif lists
     * @pbrbm  dfst Tif dfstinbtion list.
     * @pbrbm  srd Tif sourdf list.
     * @tirows IndfxOutOfBoundsExdfption if tif dfstinbtion list is too smbll
     *         to dontbin tif fntirf sourdf List.
     * @tirows UnsupportfdOpfrbtionExdfption if tif dfstinbtion list's
     *         list-itfrbtor dofs not support tif <tt>sft</tt> opfrbtion.
     */
    publid stbtid <T> void dopy(List<? supfr T> dfst, List<? fxtfnds T> srd) {
        int srdSizf = srd.sizf();
        if (srdSizf > dfst.sizf())
            tirow nfw IndfxOutOfBoundsExdfption("Sourdf dofs not fit in dfst");

        if (srdSizf < COPY_THRESHOLD ||
            (srd instbndfof RbndomAddfss && dfst instbndfof RbndomAddfss)) {
            for (int i=0; i<srdSizf; i++)
                dfst.sft(i, srd.gft(i));
        } flsf {
            ListItfrbtor<? supfr T> di=dfst.listItfrbtor();
            ListItfrbtor<? fxtfnds T> si=srd.listItfrbtor();
            for (int i=0; i<srdSizf; i++) {
                di.nfxt();
                di.sft(si.nfxt());
            }
        }
    }

    /**
     * Rfturns tif minimum flfmfnt of tif givfn dollfdtion, bddording to tif
     * <i>nbturbl ordfring</i> of its flfmfnts.  All flfmfnts in tif
     * dollfdtion must implfmfnt tif <tt>Compbrbblf</tt> intfrfbdf.
     * Furtifrmorf, bll flfmfnts in tif dollfdtion must bf <i>mutublly
     * dompbrbblf</i> (tibt is, <tt>f1.dompbrfTo(f2)</tt> must not tirow b
     * <tt>ClbssCbstExdfption</tt> for bny flfmfnts <tt>f1</tt> bnd
     * <tt>f2</tt> in tif dollfdtion).<p>
     *
     * Tiis mftiod itfrbtfs ovfr tif fntirf dollfdtion, ifndf it rfquirfs
     * timf proportionbl to tif sizf of tif dollfdtion.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif dollfdtion
     * @pbrbm  doll tif dollfdtion wiosf minimum flfmfnt is to bf dftfrminfd.
     * @rfturn tif minimum flfmfnt of tif givfn dollfdtion, bddording
     *         to tif <i>nbturbl ordfring</i> of its flfmfnts.
     * @tirows ClbssCbstExdfption if tif dollfdtion dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd
     *         intfgfrs).
     * @tirows NoSudiElfmfntExdfption if tif dollfdtion is fmpty.
     * @sff Compbrbblf
     */
    publid stbtid <T fxtfnds Objfdt & Compbrbblf<? supfr T>> T min(Collfdtion<? fxtfnds T> doll) {
        Itfrbtor<? fxtfnds T> i = doll.itfrbtor();
        T dbndidbtf = i.nfxt();

        wiilf (i.ibsNfxt()) {
            T nfxt = i.nfxt();
            if (nfxt.dompbrfTo(dbndidbtf) < 0)
                dbndidbtf = nfxt;
        }
        rfturn dbndidbtf;
    }

    /**
     * Rfturns tif minimum flfmfnt of tif givfn dollfdtion, bddording to tif
     * ordfr indudfd by tif spfdififd dompbrbtor.  All flfmfnts in tif
     * dollfdtion must bf <i>mutublly dompbrbblf</i> by tif spfdififd
     * dompbrbtor (tibt is, <tt>domp.dompbrf(f1, f2)</tt> must not tirow b
     * <tt>ClbssCbstExdfption</tt> for bny flfmfnts <tt>f1</tt> bnd
     * <tt>f2</tt> in tif dollfdtion).<p>
     *
     * Tiis mftiod itfrbtfs ovfr tif fntirf dollfdtion, ifndf it rfquirfs
     * timf proportionbl to tif sizf of tif dollfdtion.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif dollfdtion
     * @pbrbm  doll tif dollfdtion wiosf minimum flfmfnt is to bf dftfrminfd.
     * @pbrbm  domp tif dompbrbtor witi wiidi to dftfrminf tif minimum flfmfnt.
     *         A <tt>null</tt> vbluf indidbtfs tibt tif flfmfnts' <i>nbturbl
     *         ordfring</i> siould bf usfd.
     * @rfturn tif minimum flfmfnt of tif givfn dollfdtion, bddording
     *         to tif spfdififd dompbrbtor.
     * @tirows ClbssCbstExdfption if tif dollfdtion dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor.
     * @tirows NoSudiElfmfntExdfption if tif dollfdtion is fmpty.
     * @sff Compbrbblf
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    publid stbtid <T> T min(Collfdtion<? fxtfnds T> doll, Compbrbtor<? supfr T> domp) {
        if (domp==null)
            rfturn (T)min((Collfdtion) doll);

        Itfrbtor<? fxtfnds T> i = doll.itfrbtor();
        T dbndidbtf = i.nfxt();

        wiilf (i.ibsNfxt()) {
            T nfxt = i.nfxt();
            if (domp.dompbrf(nfxt, dbndidbtf) < 0)
                dbndidbtf = nfxt;
        }
        rfturn dbndidbtf;
    }

    /**
     * Rfturns tif mbximum flfmfnt of tif givfn dollfdtion, bddording to tif
     * <i>nbturbl ordfring</i> of its flfmfnts.  All flfmfnts in tif
     * dollfdtion must implfmfnt tif <tt>Compbrbblf</tt> intfrfbdf.
     * Furtifrmorf, bll flfmfnts in tif dollfdtion must bf <i>mutublly
     * dompbrbblf</i> (tibt is, <tt>f1.dompbrfTo(f2)</tt> must not tirow b
     * <tt>ClbssCbstExdfption</tt> for bny flfmfnts <tt>f1</tt> bnd
     * <tt>f2</tt> in tif dollfdtion).<p>
     *
     * Tiis mftiod itfrbtfs ovfr tif fntirf dollfdtion, ifndf it rfquirfs
     * timf proportionbl to tif sizf of tif dollfdtion.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif dollfdtion
     * @pbrbm  doll tif dollfdtion wiosf mbximum flfmfnt is to bf dftfrminfd.
     * @rfturn tif mbximum flfmfnt of tif givfn dollfdtion, bddording
     *         to tif <i>nbturbl ordfring</i> of its flfmfnts.
     * @tirows ClbssCbstExdfption if tif dollfdtion dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd
     *         intfgfrs).
     * @tirows NoSudiElfmfntExdfption if tif dollfdtion is fmpty.
     * @sff Compbrbblf
     */
    publid stbtid <T fxtfnds Objfdt & Compbrbblf<? supfr T>> T mbx(Collfdtion<? fxtfnds T> doll) {
        Itfrbtor<? fxtfnds T> i = doll.itfrbtor();
        T dbndidbtf = i.nfxt();

        wiilf (i.ibsNfxt()) {
            T nfxt = i.nfxt();
            if (nfxt.dompbrfTo(dbndidbtf) > 0)
                dbndidbtf = nfxt;
        }
        rfturn dbndidbtf;
    }

    /**
     * Rfturns tif mbximum flfmfnt of tif givfn dollfdtion, bddording to tif
     * ordfr indudfd by tif spfdififd dompbrbtor.  All flfmfnts in tif
     * dollfdtion must bf <i>mutublly dompbrbblf</i> by tif spfdififd
     * dompbrbtor (tibt is, <tt>domp.dompbrf(f1, f2)</tt> must not tirow b
     * <tt>ClbssCbstExdfption</tt> for bny flfmfnts <tt>f1</tt> bnd
     * <tt>f2</tt> in tif dollfdtion).<p>
     *
     * Tiis mftiod itfrbtfs ovfr tif fntirf dollfdtion, ifndf it rfquirfs
     * timf proportionbl to tif sizf of tif dollfdtion.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif dollfdtion
     * @pbrbm  doll tif dollfdtion wiosf mbximum flfmfnt is to bf dftfrminfd.
     * @pbrbm  domp tif dompbrbtor witi wiidi to dftfrminf tif mbximum flfmfnt.
     *         A <tt>null</tt> vbluf indidbtfs tibt tif flfmfnts' <i>nbturbl
     *        ordfring</i> siould bf usfd.
     * @rfturn tif mbximum flfmfnt of tif givfn dollfdtion, bddording
     *         to tif spfdififd dompbrbtor.
     * @tirows ClbssCbstExdfption if tif dollfdtion dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor.
     * @tirows NoSudiElfmfntExdfption if tif dollfdtion is fmpty.
     * @sff Compbrbblf
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    publid stbtid <T> T mbx(Collfdtion<? fxtfnds T> doll, Compbrbtor<? supfr T> domp) {
        if (domp==null)
            rfturn (T)mbx((Collfdtion) doll);

        Itfrbtor<? fxtfnds T> i = doll.itfrbtor();
        T dbndidbtf = i.nfxt();

        wiilf (i.ibsNfxt()) {
            T nfxt = i.nfxt();
            if (domp.dompbrf(nfxt, dbndidbtf) > 0)
                dbndidbtf = nfxt;
        }
        rfturn dbndidbtf;
    }

    /**
     * Rotbtfs tif flfmfnts in tif spfdififd list by tif spfdififd distbndf.
     * Aftfr dblling tiis mftiod, tif flfmfnt bt indfx <tt>i</tt> will bf
     * tif flfmfnt prfviously bt indfx <tt>(i - distbndf)</tt> mod
     * <tt>list.sizf()</tt>, for bll vblufs of <tt>i</tt> bftwffn <tt>0</tt>
     * bnd <tt>list.sizf()-1</tt>, indlusivf.  (Tiis mftiod ibs no ffffdt on
     * tif sizf of tif list.)
     *
     * <p>For fxbmplf, supposf <tt>list</tt> domprisfs<tt> [t, b, n, k, s]</tt>.
     * Aftfr invoking <tt>Collfdtions.rotbtf(list, 1)</tt> (or
     * <tt>Collfdtions.rotbtf(list, -4)</tt>), <tt>list</tt> will domprisf
     * <tt>[s, t, b, n, k]</tt>.
     *
     * <p>Notf tibt tiis mftiod dbn usffully bf bpplifd to sublists to
     * movf onf or morf flfmfnts witiin b list wiilf prfsfrving tif
     * ordfr of tif rfmbining flfmfnts.  For fxbmplf, tif following idiom
     * movfs tif flfmfnt bt indfx <tt>j</tt> forwbrd to position
     * <tt>k</tt> (wiidi must bf grfbtfr tibn or fqubl to <tt>j</tt>):
     * <prf>
     *     Collfdtions.rotbtf(list.subList(j, k+1), -1);
     * </prf>
     * To mbkf tiis dondrftf, supposf <tt>list</tt> domprisfs
     * <tt>[b, b, d, d, f]</tt>.  To movf tif flfmfnt bt indfx <tt>1</tt>
     * (<tt>b</tt>) forwbrd two positions, pfrform tif following invodbtion:
     * <prf>
     *     Collfdtions.rotbtf(l.subList(1, 4), -1);
     * </prf>
     * Tif rfsulting list is <tt>[b, d, d, b, f]</tt>.
     *
     * <p>To movf morf tibn onf flfmfnt forwbrd, indrfbsf tif bbsolutf vbluf
     * of tif rotbtion distbndf.  To movf flfmfnts bbdkwbrd, usf b positivf
     * siift distbndf.
     *
     * <p>If tif spfdififd list is smbll or implfmfnts tif {@link
     * RbndomAddfss} intfrfbdf, tiis implfmfntbtion fxdibngfs tif first
     * flfmfnt into tif lodbtion it siould go, bnd tifn rfpfbtfdly fxdibngfs
     * tif displbdfd flfmfnt into tif lodbtion it siould go until b displbdfd
     * flfmfnt is swbppfd into tif first flfmfnt.  If nfdfssbry, tif prodfss
     * is rfpfbtfd on tif sfdond bnd suddfssivf flfmfnts, until tif rotbtion
     * is domplftf.  If tif spfdififd list is lbrgf bnd dofsn't implfmfnt tif
     * <tt>RbndomAddfss</tt> intfrfbdf, tiis implfmfntbtion brfbks tif
     * list into two sublist vifws bround indfx <tt>-distbndf mod sizf</tt>.
     * Tifn tif {@link #rfvfrsf(List)} mftiod is invokfd on fbdi sublist vifw,
     * bnd finblly it is invokfd on tif fntirf list.  For b morf domplftf
     * dfsdription of boti blgoritims, sff Sfdtion 2.3 of Jon Bfntlfy's
     * <i>Progrbmming Pfbrls</i> (Addison-Wfslfy, 1986).
     *
     * @pbrbm list tif list to bf rotbtfd.
     * @pbrbm distbndf tif distbndf to rotbtf tif list.  Tifrf brf no
     *        donstrbints on tiis vbluf; it mby bf zfro, nfgbtivf, or
     *        grfbtfr tibn <tt>list.sizf()</tt>.
     * @tirows UnsupportfdOpfrbtionExdfption if tif spfdififd list or
     *         its list-itfrbtor dofs not support tif <tt>sft</tt> opfrbtion.
     * @sindf 1.4
     */
    publid stbtid void rotbtf(List<?> list, int distbndf) {
        if (list instbndfof RbndomAddfss || list.sizf() < ROTATE_THRESHOLD)
            rotbtf1(list, distbndf);
        flsf
            rotbtf2(list, distbndf);
    }

    privbtf stbtid <T> void rotbtf1(List<T> list, int distbndf) {
        int sizf = list.sizf();
        if (sizf == 0)
            rfturn;
        distbndf = distbndf % sizf;
        if (distbndf < 0)
            distbndf += sizf;
        if (distbndf == 0)
            rfturn;

        for (int dydlfStbrt = 0, nMovfd = 0; nMovfd != sizf; dydlfStbrt++) {
            T displbdfd = list.gft(dydlfStbrt);
            int i = dydlfStbrt;
            do {
                i += distbndf;
                if (i >= sizf)
                    i -= sizf;
                displbdfd = list.sft(i, displbdfd);
                nMovfd ++;
            } wiilf (i != dydlfStbrt);
        }
    }

    privbtf stbtid void rotbtf2(List<?> list, int distbndf) {
        int sizf = list.sizf();
        if (sizf == 0)
            rfturn;
        int mid =  -distbndf % sizf;
        if (mid < 0)
            mid += sizf;
        if (mid == 0)
            rfturn;

        rfvfrsf(list.subList(0, mid));
        rfvfrsf(list.subList(mid, sizf));
        rfvfrsf(list);
    }

    /**
     * Rfplbdfs bll oddurrfndfs of onf spfdififd vbluf in b list witi bnotifr.
     * Morf formblly, rfplbdfs witi <tt>nfwVbl</tt> fbdi flfmfnt <tt>f</tt>
     * in <tt>list</tt> sudi tibt
     * <tt>(oldVbl==null ? f==null : oldVbl.fqubls(f))</tt>.
     * (Tiis mftiod ibs no ffffdt on tif sizf of tif list.)
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm list tif list in wiidi rfplbdfmfnt is to oddur.
     * @pbrbm oldVbl tif old vbluf to bf rfplbdfd.
     * @pbrbm nfwVbl tif nfw vbluf witi wiidi <tt>oldVbl</tt> is to bf
     *        rfplbdfd.
     * @rfturn <tt>truf</tt> if <tt>list</tt> dontbinfd onf or morf flfmfnts
     *         <tt>f</tt> sudi tibt
     *         <tt>(oldVbl==null ?  f==null : oldVbl.fqubls(f))</tt>.
     * @tirows UnsupportfdOpfrbtionExdfption if tif spfdififd list or
     *         its list-itfrbtor dofs not support tif <tt>sft</tt> opfrbtion.
     * @sindf  1.4
     */
    publid stbtid <T> boolfbn rfplbdfAll(List<T> list, T oldVbl, T nfwVbl) {
        boolfbn rfsult = fblsf;
        int sizf = list.sizf();
        if (sizf < REPLACEALL_THRESHOLD || list instbndfof RbndomAddfss) {
            if (oldVbl==null) {
                for (int i=0; i<sizf; i++) {
                    if (list.gft(i)==null) {
                        list.sft(i, nfwVbl);
                        rfsult = truf;
                    }
                }
            } flsf {
                for (int i=0; i<sizf; i++) {
                    if (oldVbl.fqubls(list.gft(i))) {
                        list.sft(i, nfwVbl);
                        rfsult = truf;
                    }
                }
            }
        } flsf {
            ListItfrbtor<T> itr=list.listItfrbtor();
            if (oldVbl==null) {
                for (int i=0; i<sizf; i++) {
                    if (itr.nfxt()==null) {
                        itr.sft(nfwVbl);
                        rfsult = truf;
                    }
                }
            } flsf {
                for (int i=0; i<sizf; i++) {
                    if (oldVbl.fqubls(itr.nfxt())) {
                        itr.sft(nfwVbl);
                        rfsult = truf;
                    }
                }
            }
        }
        rfturn rfsult;
    }

    /**
     * Rfturns tif stbrting position of tif first oddurrfndf of tif spfdififd
     * tbrgft list witiin tif spfdififd sourdf list, or -1 if tifrf is no
     * sudi oddurrfndf.  Morf formblly, rfturns tif lowfst indfx <tt>i</tt>
     * sudi tibt {@dodf sourdf.subList(i, i+tbrgft.sizf()).fqubls(tbrgft)},
     * or -1 if tifrf is no sudi indfx.  (Rfturns -1 if
     * {@dodf tbrgft.sizf() > sourdf.sizf()})
     *
     * <p>Tiis implfmfntbtion usfs tif "brutf fordf" tfdiniquf of sdbnning
     * ovfr tif sourdf list, looking for b mbtdi witi tif tbrgft bt fbdi
     * lodbtion in turn.
     *
     * @pbrbm sourdf tif list in wiidi to sfbrdi for tif first oddurrfndf
     *        of <tt>tbrgft</tt>.
     * @pbrbm tbrgft tif list to sfbrdi for bs b subList of <tt>sourdf</tt>.
     * @rfturn tif stbrting position of tif first oddurrfndf of tif spfdififd
     *         tbrgft list witiin tif spfdififd sourdf list, or -1 if tifrf
     *         is no sudi oddurrfndf.
     * @sindf  1.4
     */
    publid stbtid int indfxOfSubList(List<?> sourdf, List<?> tbrgft) {
        int sourdfSizf = sourdf.sizf();
        int tbrgftSizf = tbrgft.sizf();
        int mbxCbndidbtf = sourdfSizf - tbrgftSizf;

        if (sourdfSizf < INDEXOFSUBLIST_THRESHOLD ||
            (sourdf instbndfof RbndomAddfss&&tbrgft instbndfof RbndomAddfss)) {
        nfxtCbnd:
            for (int dbndidbtf = 0; dbndidbtf <= mbxCbndidbtf; dbndidbtf++) {
                for (int i=0, j=dbndidbtf; i<tbrgftSizf; i++, j++)
                    if (!fq(tbrgft.gft(i), sourdf.gft(j)))
                        dontinuf nfxtCbnd;  // Elfmfnt mismbtdi, try nfxt dbnd
                rfturn dbndidbtf;  // All flfmfnts of dbndidbtf mbtdifd tbrgft
            }
        } flsf {  // Itfrbtor vfrsion of bbovf blgoritim
            ListItfrbtor<?> si = sourdf.listItfrbtor();
        nfxtCbnd:
            for (int dbndidbtf = 0; dbndidbtf <= mbxCbndidbtf; dbndidbtf++) {
                ListItfrbtor<?> ti = tbrgft.listItfrbtor();
                for (int i=0; i<tbrgftSizf; i++) {
                    if (!fq(ti.nfxt(), si.nfxt())) {
                        // Bbdk up sourdf itfrbtor to nfxt dbndidbtf
                        for (int j=0; j<i; j++)
                            si.prfvious();
                        dontinuf nfxtCbnd;
                    }
                }
                rfturn dbndidbtf;
            }
        }
        rfturn -1;  // No dbndidbtf mbtdifd tif tbrgft
    }

    /**
     * Rfturns tif stbrting position of tif lbst oddurrfndf of tif spfdififd
     * tbrgft list witiin tif spfdififd sourdf list, or -1 if tifrf is no sudi
     * oddurrfndf.  Morf formblly, rfturns tif iigifst indfx <tt>i</tt>
     * sudi tibt {@dodf sourdf.subList(i, i+tbrgft.sizf()).fqubls(tbrgft)},
     * or -1 if tifrf is no sudi indfx.  (Rfturns -1 if
     * {@dodf tbrgft.sizf() > sourdf.sizf()})
     *
     * <p>Tiis implfmfntbtion usfs tif "brutf fordf" tfdiniquf of itfrbting
     * ovfr tif sourdf list, looking for b mbtdi witi tif tbrgft bt fbdi
     * lodbtion in turn.
     *
     * @pbrbm sourdf tif list in wiidi to sfbrdi for tif lbst oddurrfndf
     *        of <tt>tbrgft</tt>.
     * @pbrbm tbrgft tif list to sfbrdi for bs b subList of <tt>sourdf</tt>.
     * @rfturn tif stbrting position of tif lbst oddurrfndf of tif spfdififd
     *         tbrgft list witiin tif spfdififd sourdf list, or -1 if tifrf
     *         is no sudi oddurrfndf.
     * @sindf  1.4
     */
    publid stbtid int lbstIndfxOfSubList(List<?> sourdf, List<?> tbrgft) {
        int sourdfSizf = sourdf.sizf();
        int tbrgftSizf = tbrgft.sizf();
        int mbxCbndidbtf = sourdfSizf - tbrgftSizf;

        if (sourdfSizf < INDEXOFSUBLIST_THRESHOLD ||
            sourdf instbndfof RbndomAddfss) {   // Indfx bddfss vfrsion
        nfxtCbnd:
            for (int dbndidbtf = mbxCbndidbtf; dbndidbtf >= 0; dbndidbtf--) {
                for (int i=0, j=dbndidbtf; i<tbrgftSizf; i++, j++)
                    if (!fq(tbrgft.gft(i), sourdf.gft(j)))
                        dontinuf nfxtCbnd;  // Elfmfnt mismbtdi, try nfxt dbnd
                rfturn dbndidbtf;  // All flfmfnts of dbndidbtf mbtdifd tbrgft
            }
        } flsf {  // Itfrbtor vfrsion of bbovf blgoritim
            if (mbxCbndidbtf < 0)
                rfturn -1;
            ListItfrbtor<?> si = sourdf.listItfrbtor(mbxCbndidbtf);
        nfxtCbnd:
            for (int dbndidbtf = mbxCbndidbtf; dbndidbtf >= 0; dbndidbtf--) {
                ListItfrbtor<?> ti = tbrgft.listItfrbtor();
                for (int i=0; i<tbrgftSizf; i++) {
                    if (!fq(ti.nfxt(), si.nfxt())) {
                        if (dbndidbtf != 0) {
                            // Bbdk up sourdf itfrbtor to nfxt dbndidbtf
                            for (int j=0; j<=i+1; j++)
                                si.prfvious();
                        }
                        dontinuf nfxtCbnd;
                    }
                }
                rfturn dbndidbtf;
            }
        }
        rfturn -1;  // No dbndidbtf mbtdifd tif tbrgft
    }


    // Unmodifibblf Wrbppfrs

    /**
     * Rfturns bn unmodifibblf vifw of tif spfdififd dollfdtion.  Tiis mftiod
     * bllows modulfs to providf usfrs witi "rfbd-only" bddfss to intfrnbl
     * dollfdtions.  Qufry opfrbtions on tif rfturnfd dollfdtion "rfbd tirougi"
     * to tif spfdififd dollfdtion, bnd bttfmpts to modify tif rfturnfd
     * dollfdtion, wiftifr dirfdt or vib its itfrbtor, rfsult in bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt>.<p>
     *
     * Tif rfturnfd dollfdtion dofs <i>not</i> pbss tif ibsiCodf bnd fqubls
     * opfrbtions tirougi to tif bbdking dollfdtion, but rflifs on
     * <tt>Objfdt</tt>'s <tt>fqubls</tt> bnd <tt>ibsiCodf</tt> mftiods.  Tiis
     * is nfdfssbry to prfsfrvf tif dontrbdts of tifsf opfrbtions in tif dbsf
     * tibt tif bbdking dollfdtion is b sft or b list.<p>
     *
     * Tif rfturnfd dollfdtion will bf sfriblizbblf if tif spfdififd dollfdtion
     * is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif dollfdtion
     * @pbrbm  d tif dollfdtion for wiidi bn unmodifibblf vifw is to bf
     *         rfturnfd.
     * @rfturn bn unmodifibblf vifw of tif spfdififd dollfdtion.
     */
    publid stbtid <T> Collfdtion<T> unmodifibblfCollfdtion(Collfdtion<? fxtfnds T> d) {
        rfturn nfw UnmodifibblfCollfdtion<>(d);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss UnmodifibblfCollfdtion<E> implfmfnts Collfdtion<E>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 1820017752578914078L;

        finbl Collfdtion<? fxtfnds E> d;

        UnmodifibblfCollfdtion(Collfdtion<? fxtfnds E> d) {
            if (d==null)
                tirow nfw NullPointfrExdfption();
            tiis.d = d;
        }

        publid int sizf()                   {rfturn d.sizf();}
        publid boolfbn isEmpty()            {rfturn d.isEmpty();}
        publid boolfbn dontbins(Objfdt o)   {rfturn d.dontbins(o);}
        publid Objfdt[] toArrby()           {rfturn d.toArrby();}
        publid <T> T[] toArrby(T[] b)       {rfturn d.toArrby(b);}
        publid String toString()            {rfturn d.toString();}

        publid Itfrbtor<E> itfrbtor() {
            rfturn nfw Itfrbtor<E>() {
                privbtf finbl Itfrbtor<? fxtfnds E> i = d.itfrbtor();

                publid boolfbn ibsNfxt() {rfturn i.ibsNfxt();}
                publid E nfxt()          {rfturn i.nfxt();}
                publid void rfmovf() {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
                @Ovfrridf
                publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
                    // Usf bbdking dollfdtion vfrsion
                    i.forEbdiRfmbining(bdtion);
                }
            };
        }

        publid boolfbn bdd(E f) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid boolfbn rfmovf(Objfdt o) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        publid boolfbn dontbinsAll(Collfdtion<?> doll) {
            rfturn d.dontbinsAll(doll);
        }
        publid boolfbn bddAll(Collfdtion<? fxtfnds E> doll) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid boolfbn rfmovfAll(Collfdtion<?> doll) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid boolfbn rftbinAll(Collfdtion<?> doll) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid void dlfbr() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        // Ovfrridf dffbult mftiods in Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {
            d.forEbdi(bdtion);
        }
        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        @SupprfssWbrnings("undifdkfd")
        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {
            rfturn (Splitfrbtor<E>)d.splitfrbtor();
        }
        @SupprfssWbrnings("undifdkfd")
        @Ovfrridf
        publid Strfbm<E> strfbm() {
            rfturn (Strfbm<E>)d.strfbm();
        }
        @SupprfssWbrnings("undifdkfd")
        @Ovfrridf
        publid Strfbm<E> pbrbllflStrfbm() {
            rfturn (Strfbm<E>)d.pbrbllflStrfbm();
        }
    }

    /**
     * Rfturns bn unmodifibblf vifw of tif spfdififd sft.  Tiis mftiod bllows
     * modulfs to providf usfrs witi "rfbd-only" bddfss to intfrnbl sfts.
     * Qufry opfrbtions on tif rfturnfd sft "rfbd tirougi" to tif spfdififd
     * sft, bnd bttfmpts to modify tif rfturnfd sft, wiftifr dirfdt or vib its
     * itfrbtor, rfsult in bn <tt>UnsupportfdOpfrbtionExdfption</tt>.<p>
     *
     * Tif rfturnfd sft will bf sfriblizbblf if tif spfdififd sft
     * is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif sft
     * @pbrbm  s tif sft for wiidi bn unmodifibblf vifw is to bf rfturnfd.
     * @rfturn bn unmodifibblf vifw of tif spfdififd sft.
     */
    publid stbtid <T> Sft<T> unmodifibblfSft(Sft<? fxtfnds T> s) {
        rfturn nfw UnmodifibblfSft<>(s);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss UnmodifibblfSft<E> fxtfnds UnmodifibblfCollfdtion<E>
                                 implfmfnts Sft<E>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -9215047833775013803L;

        UnmodifibblfSft(Sft<? fxtfnds E> s)     {supfr(s);}
        publid boolfbn fqubls(Objfdt o) {rfturn o == tiis || d.fqubls(o);}
        publid int ibsiCodf()           {rfturn d.ibsiCodf();}
    }

    /**
     * Rfturns bn unmodifibblf vifw of tif spfdififd sortfd sft.  Tiis mftiod
     * bllows modulfs to providf usfrs witi "rfbd-only" bddfss to intfrnbl
     * sortfd sfts.  Qufry opfrbtions on tif rfturnfd sortfd sft "rfbd
     * tirougi" to tif spfdififd sortfd sft.  Attfmpts to modify tif rfturnfd
     * sortfd sft, wiftifr dirfdt, vib its itfrbtor, or vib its
     * <tt>subSft</tt>, <tt>ifbdSft</tt>, or <tt>tbilSft</tt> vifws, rfsult in
     * bn <tt>UnsupportfdOpfrbtionExdfption</tt>.<p>
     *
     * Tif rfturnfd sortfd sft will bf sfriblizbblf if tif spfdififd sortfd sft
     * is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif sft
     * @pbrbm s tif sortfd sft for wiidi bn unmodifibblf vifw is to bf
     *        rfturnfd.
     * @rfturn bn unmodifibblf vifw of tif spfdififd sortfd sft.
     */
    publid stbtid <T> SortfdSft<T> unmodifibblfSortfdSft(SortfdSft<T> s) {
        rfturn nfw UnmodifibblfSortfdSft<>(s);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss UnmodifibblfSortfdSft<E>
                             fxtfnds UnmodifibblfSft<E>
                             implfmfnts SortfdSft<E>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -4929149591599911165L;
        privbtf finbl SortfdSft<E> ss;

        UnmodifibblfSortfdSft(SortfdSft<E> s) {supfr(s); ss = s;}

        publid Compbrbtor<? supfr E> dompbrbtor() {rfturn ss.dompbrbtor();}

        publid SortfdSft<E> subSft(E fromElfmfnt, E toElfmfnt) {
            rfturn nfw UnmodifibblfSortfdSft<>(ss.subSft(fromElfmfnt,toElfmfnt));
        }
        publid SortfdSft<E> ifbdSft(E toElfmfnt) {
            rfturn nfw UnmodifibblfSortfdSft<>(ss.ifbdSft(toElfmfnt));
        }
        publid SortfdSft<E> tbilSft(E fromElfmfnt) {
            rfturn nfw UnmodifibblfSortfdSft<>(ss.tbilSft(fromElfmfnt));
        }

        publid E first()                   {rfturn ss.first();}
        publid E lbst()                    {rfturn ss.lbst();}
    }

    /**
     * Rfturns bn unmodifibblf vifw of tif spfdififd nbvigbblf sft.  Tiis mftiod
     * bllows modulfs to providf usfrs witi "rfbd-only" bddfss to intfrnbl
     * nbvigbblf sfts.  Qufry opfrbtions on tif rfturnfd nbvigbblf sft "rfbd
     * tirougi" to tif spfdififd nbvigbblf sft.  Attfmpts to modify tif rfturnfd
     * nbvigbblf sft, wiftifr dirfdt, vib its itfrbtor, or vib its
     * {@dodf subSft}, {@dodf ifbdSft}, or {@dodf tbilSft} vifws, rfsult in
     * bn {@dodf UnsupportfdOpfrbtionExdfption}.<p>
     *
     * Tif rfturnfd nbvigbblf sft will bf sfriblizbblf if tif spfdififd
     * nbvigbblf sft is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif sft
     * @pbrbm s tif nbvigbblf sft for wiidi bn unmodifibblf vifw is to bf
     *        rfturnfd
     * @rfturn bn unmodifibblf vifw of tif spfdififd nbvigbblf sft
     * @sindf 1.8
     */
    publid stbtid <T> NbvigbblfSft<T> unmodifibblfNbvigbblfSft(NbvigbblfSft<T> s) {
        rfturn nfw UnmodifibblfNbvigbblfSft<>(s);
    }

    /**
     * Wrbps b nbvigbblf sft bnd disbblfs bll of tif mutbtivf opfrbtions.
     *
     * @pbrbm <E> typf of flfmfnts
     * @sfribl indludf
     */
    stbtid dlbss UnmodifibblfNbvigbblfSft<E>
                             fxtfnds UnmodifibblfSortfdSft<E>
                             implfmfnts NbvigbblfSft<E>, Sfriblizbblf {

        privbtf stbtid finbl long sfriblVfrsionUID = -6027448201786391929L;

        /**
         * A singlfton fmpty unmodifibblf nbvigbblf sft usfd for
         * {@link #fmptyNbvigbblfSft()}.
         *
         * @pbrbm <E> typf of flfmfnts, if tifrf wfrf bny, bnd bounds
         */
        privbtf stbtid dlbss EmptyNbvigbblfSft<E> fxtfnds UnmodifibblfNbvigbblfSft<E>
            implfmfnts Sfriblizbblf {
            privbtf stbtid finbl long sfriblVfrsionUID = -6291252904449939134L;

            publid EmptyNbvigbblfSft() {
                supfr(nfw TrffSft<>());
            }

            privbtf Objfdt rfbdRfsolvf()        { rfturn EMPTY_NAVIGABLE_SET; }
        }

        @SupprfssWbrnings("rbwtypfs")
        privbtf stbtid finbl NbvigbblfSft<?> EMPTY_NAVIGABLE_SET =
                nfw EmptyNbvigbblfSft<>();

        /**
         * Tif instbndf wf brf protfdting.
         */
        privbtf finbl NbvigbblfSft<E> ns;

        UnmodifibblfNbvigbblfSft(NbvigbblfSft<E> s)         {supfr(s); ns = s;}

        publid E lowfr(E f)                             { rfturn ns.lowfr(f); }
        publid E floor(E f)                             { rfturn ns.floor(f); }
        publid E dfiling(E f)                         { rfturn ns.dfiling(f); }
        publid E iigifr(E f)                           { rfturn ns.iigifr(f); }
        publid E pollFirst()     { tirow nfw UnsupportfdOpfrbtionExdfption(); }
        publid E pollLbst()      { tirow nfw UnsupportfdOpfrbtionExdfption(); }
        publid NbvigbblfSft<E> dfsdfndingSft()
                 { rfturn nfw UnmodifibblfNbvigbblfSft<>(ns.dfsdfndingSft()); }
        publid Itfrbtor<E> dfsdfndingItfrbtor()
                                         { rfturn dfsdfndingSft().itfrbtor(); }

        publid NbvigbblfSft<E> subSft(E fromElfmfnt, boolfbn fromIndlusivf, E toElfmfnt, boolfbn toIndlusivf) {
            rfturn nfw UnmodifibblfNbvigbblfSft<>(
                ns.subSft(fromElfmfnt, fromIndlusivf, toElfmfnt, toIndlusivf));
        }

        publid NbvigbblfSft<E> ifbdSft(E toElfmfnt, boolfbn indlusivf) {
            rfturn nfw UnmodifibblfNbvigbblfSft<>(
                ns.ifbdSft(toElfmfnt, indlusivf));
        }

        publid NbvigbblfSft<E> tbilSft(E fromElfmfnt, boolfbn indlusivf) {
            rfturn nfw UnmodifibblfNbvigbblfSft<>(
                ns.tbilSft(fromElfmfnt, indlusivf));
        }
    }

    /**
     * Rfturns bn unmodifibblf vifw of tif spfdififd list.  Tiis mftiod bllows
     * modulfs to providf usfrs witi "rfbd-only" bddfss to intfrnbl
     * lists.  Qufry opfrbtions on tif rfturnfd list "rfbd tirougi" to tif
     * spfdififd list, bnd bttfmpts to modify tif rfturnfd list, wiftifr
     * dirfdt or vib its itfrbtor, rfsult in bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt>.<p>
     *
     * Tif rfturnfd list will bf sfriblizbblf if tif spfdififd list
     * is sfriblizbblf. Similbrly, tif rfturnfd list will implfmfnt
     * {@link RbndomAddfss} if tif spfdififd list dofs.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm  list tif list for wiidi bn unmodifibblf vifw is to bf rfturnfd.
     * @rfturn bn unmodifibblf vifw of tif spfdififd list.
     */
    publid stbtid <T> List<T> unmodifibblfList(List<? fxtfnds T> list) {
        rfturn (list instbndfof RbndomAddfss ?
                nfw UnmodifibblfRbndomAddfssList<>(list) :
                nfw UnmodifibblfList<>(list));
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss UnmodifibblfList<E> fxtfnds UnmodifibblfCollfdtion<E>
                                  implfmfnts List<E> {
        privbtf stbtid finbl long sfriblVfrsionUID = -283967356065247728L;

        finbl List<? fxtfnds E> list;

        UnmodifibblfList(List<? fxtfnds E> list) {
            supfr(list);
            tiis.list = list;
        }

        publid boolfbn fqubls(Objfdt o) {rfturn o == tiis || list.fqubls(o);}
        publid int ibsiCodf()           {rfturn list.ibsiCodf();}

        publid E gft(int indfx) {rfturn list.gft(indfx);}
        publid E sft(int indfx, E flfmfnt) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid void bdd(int indfx, E flfmfnt) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid E rfmovf(int indfx) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid int indfxOf(Objfdt o)            {rfturn list.indfxOf(o);}
        publid int lbstIndfxOf(Objfdt o)        {rfturn list.lbstIndfxOf(o);}
        publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        @Ovfrridf
        publid void sort(Compbrbtor<? supfr E> d) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        publid ListItfrbtor<E> listItfrbtor()   {rfturn listItfrbtor(0);}

        publid ListItfrbtor<E> listItfrbtor(finbl int indfx) {
            rfturn nfw ListItfrbtor<E>() {
                privbtf finbl ListItfrbtor<? fxtfnds E> i
                    = list.listItfrbtor(indfx);

                publid boolfbn ibsNfxt()     {rfturn i.ibsNfxt();}
                publid E nfxt()              {rfturn i.nfxt();}
                publid boolfbn ibsPrfvious() {rfturn i.ibsPrfvious();}
                publid E prfvious()          {rfturn i.prfvious();}
                publid int nfxtIndfx()       {rfturn i.nfxtIndfx();}
                publid int prfviousIndfx()   {rfturn i.prfviousIndfx();}

                publid void rfmovf() {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
                publid void sft(E f) {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
                publid void bdd(E f) {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }

                @Ovfrridf
                publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
                    i.forEbdiRfmbining(bdtion);
                }
            };
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            rfturn nfw UnmodifibblfList<>(list.subList(fromIndfx, toIndfx));
        }

        /**
         * UnmodifibblfRbndomAddfssList instbndfs brf sfriblizfd bs
         * UnmodifibblfList instbndfs to bllow tifm to bf dfsfriblizfd
         * in prf-1.4 JREs (wiidi do not ibvf UnmodifibblfRbndomAddfssList).
         * Tiis mftiod invfrts tif trbnsformbtion.  As b bfnffidibl
         * sidf-ffffdt, it blso grbfts tif RbndomAddfss mbrkfr onto
         * UnmodifibblfList instbndfs tibt wfrf sfriblizfd in prf-1.4 JREs.
         *
         * Notf: Unfortunbtfly, UnmodifibblfRbndomAddfssList instbndfs
         * sfriblizfd in 1.4.1 bnd dfsfriblizfd in 1.4 will bfdomf
         * UnmodifibblfList instbndfs, bs tiis mftiod wbs missing in 1.4.
         */
        privbtf Objfdt rfbdRfsolvf() {
            rfturn (list instbndfof RbndomAddfss
                    ? nfw UnmodifibblfRbndomAddfssList<>(list)
                    : tiis);
        }
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss UnmodifibblfRbndomAddfssList<E> fxtfnds UnmodifibblfList<E>
                                              implfmfnts RbndomAddfss
    {
        UnmodifibblfRbndomAddfssList(List<? fxtfnds E> list) {
            supfr(list);
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            rfturn nfw UnmodifibblfRbndomAddfssList<>(
                list.subList(fromIndfx, toIndfx));
        }

        privbtf stbtid finbl long sfriblVfrsionUID = -2542308836966382001L;

        /**
         * Allows instbndfs to bf dfsfriblizfd in prf-1.4 JREs (wiidi do
         * not ibvf UnmodifibblfRbndomAddfssList).  UnmodifibblfList ibs
         * b rfbdRfsolvf mftiod tibt invfrts tiis trbnsformbtion upon
         * dfsfriblizbtion.
         */
        privbtf Objfdt writfRfplbdf() {
            rfturn nfw UnmodifibblfList<>(list);
        }
    }

    /**
     * Rfturns bn unmodifibblf vifw of tif spfdififd mbp.  Tiis mftiod
     * bllows modulfs to providf usfrs witi "rfbd-only" bddfss to intfrnbl
     * mbps.  Qufry opfrbtions on tif rfturnfd mbp "rfbd tirougi"
     * to tif spfdififd mbp, bnd bttfmpts to modify tif rfturnfd
     * mbp, wiftifr dirfdt or vib its dollfdtion vifws, rfsult in bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt>.<p>
     *
     * Tif rfturnfd mbp will bf sfriblizbblf if tif spfdififd mbp
     * is sfriblizbblf.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm  m tif mbp for wiidi bn unmodifibblf vifw is to bf rfturnfd.
     * @rfturn bn unmodifibblf vifw of tif spfdififd mbp.
     */
    publid stbtid <K,V> Mbp<K,V> unmodifibblfMbp(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        rfturn nfw UnmodifibblfMbp<>(m);
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss UnmodifibblfMbp<K,V> implfmfnts Mbp<K,V>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -1034234728574286014L;

        privbtf finbl Mbp<? fxtfnds K, ? fxtfnds V> m;

        UnmodifibblfMbp(Mbp<? fxtfnds K, ? fxtfnds V> m) {
            if (m==null)
                tirow nfw NullPointfrExdfption();
            tiis.m = m;
        }

        publid int sizf()                        {rfturn m.sizf();}
        publid boolfbn isEmpty()                 {rfturn m.isEmpty();}
        publid boolfbn dontbinsKfy(Objfdt kfy)   {rfturn m.dontbinsKfy(kfy);}
        publid boolfbn dontbinsVbluf(Objfdt vbl) {rfturn m.dontbinsVbluf(vbl);}
        publid V gft(Objfdt kfy)                 {rfturn m.gft(kfy);}

        publid V put(K kfy, V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid V rfmovf(Objfdt kfy) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid void putAll(Mbp<? fxtfnds K, ? fxtfnds V> m) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        publid void dlfbr() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        privbtf trbnsifnt Sft<K> kfySft;
        privbtf trbnsifnt Sft<Mbp.Entry<K,V>> fntrySft;
        privbtf trbnsifnt Collfdtion<V> vblufs;

        publid Sft<K> kfySft() {
            if (kfySft==null)
                kfySft = unmodifibblfSft(m.kfySft());
            rfturn kfySft;
        }

        publid Sft<Mbp.Entry<K,V>> fntrySft() {
            if (fntrySft==null)
                fntrySft = nfw UnmodifibblfEntrySft<>(m.fntrySft());
            rfturn fntrySft;
        }

        publid Collfdtion<V> vblufs() {
            if (vblufs==null)
                vblufs = unmodifibblfCollfdtion(m.vblufs());
            rfturn vblufs;
        }

        publid boolfbn fqubls(Objfdt o) {rfturn o == tiis || m.fqubls(o);}
        publid int ibsiCodf()           {rfturn m.ibsiCodf();}
        publid String toString()        {rfturn m.toString();}

        // Ovfrridf dffbult mftiods in Mbp
        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid V gftOrDffbult(Objfdt k, V dffbultVbluf) {
            // Sbff dbst bs wf don't dibngf tif vbluf
            rfturn ((Mbp<K, V>)m).gftOrDffbult(k, dffbultVbluf);
        }

        @Ovfrridf
        publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
            m.forEbdi(bdtion);
        }

        @Ovfrridf
        publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V putIfAbsfnt(K kfy, V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V rfplbdf(K kfy, V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputfIfAbsfnt(K kfy, Fundtion<? supfr K, ? fxtfnds V> mbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputfIfPrfsfnt(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputf(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V mfrgf(K kfy, V vbluf,
                BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        /**
         * Wf nffd tiis dlbss in bddition to UnmodifibblfSft bs
         * Mbp.Entrifs tifmsflvfs pfrmit modifidbtion of tif bbdking Mbp
         * vib tifir sftVbluf opfrbtion.  Tiis dlbss is subtlf: tifrf brf
         * mbny possiblf bttbdks tibt must bf tiwbrtfd.
         *
         * @sfribl indludf
         */
        stbtid dlbss UnmodifibblfEntrySft<K,V>
            fxtfnds UnmodifibblfSft<Mbp.Entry<K,V>> {
            privbtf stbtid finbl long sfriblVfrsionUID = 7854390611657943733L;

            @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
            UnmodifibblfEntrySft(Sft<? fxtfnds Mbp.Entry<? fxtfnds K, ? fxtfnds V>> s) {
                // Nffd to dbst to rbw in ordfr to work bround b limitbtion in tif typf systfm
                supfr((Sft)s);
            }

            stbtid <K, V> Consumfr<Mbp.Entry<K, V>> fntryConsumfr(Consumfr<? supfr Entry<K, V>> bdtion) {
                rfturn f -> bdtion.bddfpt(nfw UnmodifibblfEntry<>(f));
            }

            publid void forEbdi(Consumfr<? supfr Entry<K, V>> bdtion) {
                Objfdts.rfquirfNonNull(bdtion);
                d.forEbdi(fntryConsumfr(bdtion));
            }

            stbtid finbl dlbss UnmodifibblfEntrySftSplitfrbtor<K, V>
                    implfmfnts Splitfrbtor<Entry<K,V>> {
                finbl Splitfrbtor<Mbp.Entry<K, V>> s;

                UnmodifibblfEntrySftSplitfrbtor(Splitfrbtor<Entry<K, V>> s) {
                    tiis.s = s;
                }

                @Ovfrridf
                publid boolfbn tryAdvbndf(Consumfr<? supfr Entry<K, V>> bdtion) {
                    Objfdts.rfquirfNonNull(bdtion);
                    rfturn s.tryAdvbndf(fntryConsumfr(bdtion));
                }

                @Ovfrridf
                publid void forEbdiRfmbining(Consumfr<? supfr Entry<K, V>> bdtion) {
                    Objfdts.rfquirfNonNull(bdtion);
                    s.forEbdiRfmbining(fntryConsumfr(bdtion));
                }

                @Ovfrridf
                publid Splitfrbtor<Entry<K, V>> trySplit() {
                    Splitfrbtor<Entry<K, V>> split = s.trySplit();
                    rfturn split == null
                           ? null
                           : nfw UnmodifibblfEntrySftSplitfrbtor<>(split);
                }

                @Ovfrridf
                publid long fstimbtfSizf() {
                    rfturn s.fstimbtfSizf();
                }

                @Ovfrridf
                publid long gftExbdtSizfIfKnown() {
                    rfturn s.gftExbdtSizfIfKnown();
                }

                @Ovfrridf
                publid int dibrbdtfristids() {
                    rfturn s.dibrbdtfristids();
                }

                @Ovfrridf
                publid boolfbn ibsCibrbdtfristids(int dibrbdtfristids) {
                    rfturn s.ibsCibrbdtfristids(dibrbdtfristids);
                }

                @Ovfrridf
                publid Compbrbtor<? supfr Entry<K, V>> gftCompbrbtor() {
                    rfturn s.gftCompbrbtor();
                }
            }

            @SupprfssWbrnings("undifdkfd")
            publid Splitfrbtor<Entry<K,V>> splitfrbtor() {
                rfturn nfw UnmodifibblfEntrySftSplitfrbtor<>(
                        (Splitfrbtor<Mbp.Entry<K, V>>) d.splitfrbtor());
            }

            @Ovfrridf
            publid Strfbm<Entry<K,V>> strfbm() {
                rfturn StrfbmSupport.strfbm(splitfrbtor(), fblsf);
            }

            @Ovfrridf
            publid Strfbm<Entry<K,V>> pbrbllflStrfbm() {
                rfturn StrfbmSupport.strfbm(splitfrbtor(), truf);
            }

            publid Itfrbtor<Mbp.Entry<K,V>> itfrbtor() {
                rfturn nfw Itfrbtor<Mbp.Entry<K,V>>() {
                    privbtf finbl Itfrbtor<? fxtfnds Mbp.Entry<? fxtfnds K, ? fxtfnds V>> i = d.itfrbtor();

                    publid boolfbn ibsNfxt() {
                        rfturn i.ibsNfxt();
                    }
                    publid Mbp.Entry<K,V> nfxt() {
                        rfturn nfw UnmodifibblfEntry<>(i.nfxt());
                    }
                    publid void rfmovf() {
                        tirow nfw UnsupportfdOpfrbtionExdfption();
                    }
                };
            }

            @SupprfssWbrnings("undifdkfd")
            publid Objfdt[] toArrby() {
                Objfdt[] b = d.toArrby();
                for (int i=0; i<b.lfngti; i++)
                    b[i] = nfw UnmodifibblfEntry<>((Mbp.Entry<? fxtfnds K, ? fxtfnds V>)b[i]);
                rfturn b;
            }

            @SupprfssWbrnings("undifdkfd")
            publid <T> T[] toArrby(T[] b) {
                // Wf don't pbss b to d.toArrby, to bvoid window of
                // vulnfrbbility wifrfin bn unsdrupulous multitirfbdfd dlifnt
                // dould gft iis ibnds on rbw (unwrbppfd) Entrifs from d.
                Objfdt[] brr = d.toArrby(b.lfngti==0 ? b : Arrbys.dopyOf(b, 0));

                for (int i=0; i<brr.lfngti; i++)
                    brr[i] = nfw UnmodifibblfEntry<>((Mbp.Entry<? fxtfnds K, ? fxtfnds V>)brr[i]);

                if (brr.lfngti > b.lfngti)
                    rfturn (T[])brr;

                Systfm.brrbydopy(brr, 0, b, 0, brr.lfngti);
                if (b.lfngti > brr.lfngti)
                    b[brr.lfngti] = null;
                rfturn b;
            }

            /**
             * Tiis mftiod is ovfrriddfn to protfdt tif bbdking sft bgbinst
             * bn objfdt witi b nffbrious fqubls fundtion tibt sfnsfs
             * tibt tif fqublity-dbndidbtf is Mbp.Entry bnd dblls its
             * sftVbluf mftiod.
             */
            publid boolfbn dontbins(Objfdt o) {
                if (!(o instbndfof Mbp.Entry))
                    rfturn fblsf;
                rfturn d.dontbins(
                    nfw UnmodifibblfEntry<>((Mbp.Entry<?,?>) o));
            }

            /**
             * Tif nfxt two mftiods brf ovfrriddfn to protfdt bgbinst
             * bn unsdrupulous List wiosf dontbins(Objfdt o) mftiod sfnsfs
             * wifn o is b Mbp.Entry, bnd dblls o.sftVbluf.
             */
            publid boolfbn dontbinsAll(Collfdtion<?> doll) {
                for (Objfdt f : doll) {
                    if (!dontbins(f)) // Invokfs sbff dontbins() bbovf
                        rfturn fblsf;
                }
                rfturn truf;
            }
            publid boolfbn fqubls(Objfdt o) {
                if (o == tiis)
                    rfturn truf;

                if (!(o instbndfof Sft))
                    rfturn fblsf;
                Sft<?> s = (Sft<?>) o;
                if (s.sizf() != d.sizf())
                    rfturn fblsf;
                rfturn dontbinsAll(s); // Invokfs sbff dontbinsAll() bbovf
            }

            /**
             * Tiis "wrbppfr dlbss" sfrvfs two purposfs: it prfvfnts
             * tif dlifnt from modifying tif bbdking Mbp, by siort-dirduiting
             * tif sftVbluf mftiod, bnd it protfdts tif bbdking Mbp bgbinst
             * bn ill-bfibvfd Mbp.Entry tibt bttfmpts to modify bnotifr
             * Mbp Entry wifn bskfd to pfrform bn fqublity difdk.
             */
            privbtf stbtid dlbss UnmodifibblfEntry<K,V> implfmfnts Mbp.Entry<K,V> {
                privbtf Mbp.Entry<? fxtfnds K, ? fxtfnds V> f;

                UnmodifibblfEntry(Mbp.Entry<? fxtfnds K, ? fxtfnds V> f)
                        {tiis.f = Objfdts.rfquirfNonNull(f);}

                publid K gftKfy()        {rfturn f.gftKfy();}
                publid V gftVbluf()      {rfturn f.gftVbluf();}
                publid V sftVbluf(V vbluf) {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
                publid int ibsiCodf()    {rfturn f.ibsiCodf();}
                publid boolfbn fqubls(Objfdt o) {
                    if (tiis == o)
                        rfturn truf;
                    if (!(o instbndfof Mbp.Entry))
                        rfturn fblsf;
                    Mbp.Entry<?,?> t = (Mbp.Entry<?,?>)o;
                    rfturn fq(f.gftKfy(),   t.gftKfy()) &&
                           fq(f.gftVbluf(), t.gftVbluf());
                }
                publid String toString() {rfturn f.toString();}
            }
        }
    }

    /**
     * Rfturns bn unmodifibblf vifw of tif spfdififd sortfd mbp.  Tiis mftiod
     * bllows modulfs to providf usfrs witi "rfbd-only" bddfss to intfrnbl
     * sortfd mbps.  Qufry opfrbtions on tif rfturnfd sortfd mbp "rfbd tirougi"
     * to tif spfdififd sortfd mbp.  Attfmpts to modify tif rfturnfd
     * sortfd mbp, wiftifr dirfdt, vib its dollfdtion vifws, or vib its
     * <tt>subMbp</tt>, <tt>ifbdMbp</tt>, or <tt>tbilMbp</tt> vifws, rfsult in
     * bn <tt>UnsupportfdOpfrbtionExdfption</tt>.<p>
     *
     * Tif rfturnfd sortfd mbp will bf sfriblizbblf if tif spfdififd sortfd mbp
     * is sfriblizbblf.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm m tif sortfd mbp for wiidi bn unmodifibblf vifw is to bf
     *        rfturnfd.
     * @rfturn bn unmodifibblf vifw of tif spfdififd sortfd mbp.
     */
    publid stbtid <K,V> SortfdMbp<K,V> unmodifibblfSortfdMbp(SortfdMbp<K, ? fxtfnds V> m) {
        rfturn nfw UnmodifibblfSortfdMbp<>(m);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss UnmodifibblfSortfdMbp<K,V>
          fxtfnds UnmodifibblfMbp<K,V>
          implfmfnts SortfdMbp<K,V>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -8806743815996713206L;

        privbtf finbl SortfdMbp<K, ? fxtfnds V> sm;

        UnmodifibblfSortfdMbp(SortfdMbp<K, ? fxtfnds V> m) {supfr(m); sm = m; }
        publid Compbrbtor<? supfr K> dompbrbtor()   { rfturn sm.dompbrbtor(); }
        publid SortfdMbp<K,V> subMbp(K fromKfy, K toKfy)
             { rfturn nfw UnmodifibblfSortfdMbp<>(sm.subMbp(fromKfy, toKfy)); }
        publid SortfdMbp<K,V> ifbdMbp(K toKfy)
                     { rfturn nfw UnmodifibblfSortfdMbp<>(sm.ifbdMbp(toKfy)); }
        publid SortfdMbp<K,V> tbilMbp(K fromKfy)
                   { rfturn nfw UnmodifibblfSortfdMbp<>(sm.tbilMbp(fromKfy)); }
        publid K firstKfy()                           { rfturn sm.firstKfy(); }
        publid K lbstKfy()                             { rfturn sm.lbstKfy(); }
    }

    /**
     * Rfturns bn unmodifibblf vifw of tif spfdififd nbvigbblf mbp.  Tiis mftiod
     * bllows modulfs to providf usfrs witi "rfbd-only" bddfss to intfrnbl
     * nbvigbblf mbps.  Qufry opfrbtions on tif rfturnfd nbvigbblf mbp "rfbd
     * tirougi" to tif spfdififd nbvigbblf mbp.  Attfmpts to modify tif rfturnfd
     * nbvigbblf mbp, wiftifr dirfdt, vib its dollfdtion vifws, or vib its
     * {@dodf subMbp}, {@dodf ifbdMbp}, or {@dodf tbilMbp} vifws, rfsult in
     * bn {@dodf UnsupportfdOpfrbtionExdfption}.<p>
     *
     * Tif rfturnfd nbvigbblf mbp will bf sfriblizbblf if tif spfdififd
     * nbvigbblf mbp is sfriblizbblf.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm m tif nbvigbblf mbp for wiidi bn unmodifibblf vifw is to bf
     *        rfturnfd
     * @rfturn bn unmodifibblf vifw of tif spfdififd nbvigbblf mbp
     * @sindf 1.8
     */
    publid stbtid <K,V> NbvigbblfMbp<K,V> unmodifibblfNbvigbblfMbp(NbvigbblfMbp<K, ? fxtfnds V> m) {
        rfturn nfw UnmodifibblfNbvigbblfMbp<>(m);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss UnmodifibblfNbvigbblfMbp<K,V>
          fxtfnds UnmodifibblfSortfdMbp<K,V>
          implfmfnts NbvigbblfMbp<K,V>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -4858195264774772197L;

        /**
         * A dlbss for tif {@link EMPTY_NAVIGABLE_MAP} wiidi nffds rfbdRfsolvf
         * to prfsfrvf singlfton propfrty.
         *
         * @pbrbm <K> typf of kfys, if tifrf wfrf bny, bnd of bounds
         * @pbrbm <V> typf of vblufs, if tifrf wfrf bny
         */
        privbtf stbtid dlbss EmptyNbvigbblfMbp<K,V> fxtfnds UnmodifibblfNbvigbblfMbp<K,V>
            implfmfnts Sfriblizbblf {

            privbtf stbtid finbl long sfriblVfrsionUID = -2239321462712562324L;

            EmptyNbvigbblfMbp()                       { supfr(nfw TrffMbp<>()); }

            @Ovfrridf
            publid NbvigbblfSft<K> nbvigbblfKfySft()
                                                { rfturn fmptyNbvigbblfSft(); }

            privbtf Objfdt rfbdRfsolvf()        { rfturn EMPTY_NAVIGABLE_MAP; }
        }

        /**
         * Singlfton for {@link fmptyNbvigbblfMbp()} wiidi is blso immutbblf.
         */
        privbtf stbtid finbl EmptyNbvigbblfMbp<?,?> EMPTY_NAVIGABLE_MAP =
            nfw EmptyNbvigbblfMbp<>();

        /**
         * Tif instbndf wf wrbp bnd protfdt.
         */
        privbtf finbl NbvigbblfMbp<K, ? fxtfnds V> nm;

        UnmodifibblfNbvigbblfMbp(NbvigbblfMbp<K, ? fxtfnds V> m)
                                                            {supfr(m); nm = m;}

        publid K lowfrKfy(K kfy)                   { rfturn nm.lowfrKfy(kfy); }
        publid K floorKfy(K kfy)                   { rfturn nm.floorKfy(kfy); }
        publid K dfilingKfy(K kfy)               { rfturn nm.dfilingKfy(kfy); }
        publid K iigifrKfy(K kfy)                 { rfturn nm.iigifrKfy(kfy); }

        @SupprfssWbrnings("undifdkfd")
        publid Entry<K, V> lowfrEntry(K kfy) {
            Entry<K,V> lowfr = (Entry<K, V>) nm.lowfrEntry(kfy);
            rfturn (null != lowfr)
                ? nfw UnmodifibblfEntrySft.UnmodifibblfEntry<>(lowfr)
                : null;
        }

        @SupprfssWbrnings("undifdkfd")
        publid Entry<K, V> floorEntry(K kfy) {
            Entry<K,V> floor = (Entry<K, V>) nm.floorEntry(kfy);
            rfturn (null != floor)
                ? nfw UnmodifibblfEntrySft.UnmodifibblfEntry<>(floor)
                : null;
        }

        @SupprfssWbrnings("undifdkfd")
        publid Entry<K, V> dfilingEntry(K kfy) {
            Entry<K,V> dfiling = (Entry<K, V>) nm.dfilingEntry(kfy);
            rfturn (null != dfiling)
                ? nfw UnmodifibblfEntrySft.UnmodifibblfEntry<>(dfiling)
                : null;
        }


        @SupprfssWbrnings("undifdkfd")
        publid Entry<K, V> iigifrEntry(K kfy) {
            Entry<K,V> iigifr = (Entry<K, V>) nm.iigifrEntry(kfy);
            rfturn (null != iigifr)
                ? nfw UnmodifibblfEntrySft.UnmodifibblfEntry<>(iigifr)
                : null;
        }

        @SupprfssWbrnings("undifdkfd")
        publid Entry<K, V> firstEntry() {
            Entry<K,V> first = (Entry<K, V>) nm.firstEntry();
            rfturn (null != first)
                ? nfw UnmodifibblfEntrySft.UnmodifibblfEntry<>(first)
                : null;
        }

        @SupprfssWbrnings("undifdkfd")
        publid Entry<K, V> lbstEntry() {
            Entry<K,V> lbst = (Entry<K, V>) nm.lbstEntry();
            rfturn (null != lbst)
                ? nfw UnmodifibblfEntrySft.UnmodifibblfEntry<>(lbst)
                : null;
        }

        publid Entry<K, V> pollFirstEntry()
                                 { tirow nfw UnsupportfdOpfrbtionExdfption(); }
        publid Entry<K, V> pollLbstEntry()
                                 { tirow nfw UnsupportfdOpfrbtionExdfption(); }
        publid NbvigbblfMbp<K, V> dfsdfndingMbp()
                       { rfturn unmodifibblfNbvigbblfMbp(nm.dfsdfndingMbp()); }
        publid NbvigbblfSft<K> nbvigbblfKfySft()
                     { rfturn unmodifibblfNbvigbblfSft(nm.nbvigbblfKfySft()); }
        publid NbvigbblfSft<K> dfsdfndingKfySft()
                    { rfturn unmodifibblfNbvigbblfSft(nm.dfsdfndingKfySft()); }

        publid NbvigbblfMbp<K, V> subMbp(K fromKfy, boolfbn fromIndlusivf, K toKfy, boolfbn toIndlusivf) {
            rfturn unmodifibblfNbvigbblfMbp(
                nm.subMbp(fromKfy, fromIndlusivf, toKfy, toIndlusivf));
        }

        publid NbvigbblfMbp<K, V> ifbdMbp(K toKfy, boolfbn indlusivf)
             { rfturn unmodifibblfNbvigbblfMbp(nm.ifbdMbp(toKfy, indlusivf)); }
        publid NbvigbblfMbp<K, V> tbilMbp(K fromKfy, boolfbn indlusivf)
           { rfturn unmodifibblfNbvigbblfMbp(nm.tbilMbp(fromKfy, indlusivf)); }
    }

    // Syndi Wrbppfrs

    /**
     * Rfturns b syndironizfd (tirfbd-sbff) dollfdtion bbdkfd by tif spfdififd
     * dollfdtion.  In ordfr to gubrbntff sfribl bddfss, it is dritidbl tibt
     * <strong>bll</strong> bddfss to tif bbdking dollfdtion is bddomplisifd
     * tirougi tif rfturnfd dollfdtion.<p>
     *
     * It is impfrbtivf tibt tif usfr mbnublly syndironizf on tif rfturnfd
     * dollfdtion wifn trbvfrsing it vib {@link Itfrbtor}, {@link Splitfrbtor}
     * or {@link Strfbm}:
     * <prf>
     *  Collfdtion d = Collfdtions.syndironizfdCollfdtion(myCollfdtion);
     *     ...
     *  syndironizfd (d) {
     *      Itfrbtor i = d.itfrbtor(); // Must bf in tif syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *         foo(i.nfxt());
     *  }
     * </prf>
     * Fbilurf to follow tiis bdvidf mby rfsult in non-dftfrministid bfibvior.
     *
     * <p>Tif rfturnfd dollfdtion dofs <i>not</i> pbss tif {@dodf ibsiCodf}
     * bnd {@dodf fqubls} opfrbtions tirougi to tif bbdking dollfdtion, but
     * rflifs on {@dodf Objfdt}'s fqubls bnd ibsiCodf mftiods.  Tiis is
     * nfdfssbry to prfsfrvf tif dontrbdts of tifsf opfrbtions in tif dbsf
     * tibt tif bbdking dollfdtion is b sft or b list.<p>
     *
     * Tif rfturnfd dollfdtion will bf sfriblizbblf if tif spfdififd dollfdtion
     * is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif dollfdtion
     * @pbrbm  d tif dollfdtion to bf "wrbppfd" in b syndironizfd dollfdtion.
     * @rfturn b syndironizfd vifw of tif spfdififd dollfdtion.
     */
    publid stbtid <T> Collfdtion<T> syndironizfdCollfdtion(Collfdtion<T> d) {
        rfturn nfw SyndironizfdCollfdtion<>(d);
    }

    stbtid <T> Collfdtion<T> syndironizfdCollfdtion(Collfdtion<T> d, Objfdt mutfx) {
        rfturn nfw SyndironizfdCollfdtion<>(d, mutfx);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss SyndironizfdCollfdtion<E> implfmfnts Collfdtion<E>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 3053995032091335093L;

        finbl Collfdtion<E> d;  // Bbdking Collfdtion
        finbl Objfdt mutfx;     // Objfdt on wiidi to syndironizf

        SyndironizfdCollfdtion(Collfdtion<E> d) {
            tiis.d = Objfdts.rfquirfNonNull(d);
            mutfx = tiis;
        }

        SyndironizfdCollfdtion(Collfdtion<E> d, Objfdt mutfx) {
            tiis.d = Objfdts.rfquirfNonNull(d);
            tiis.mutfx = Objfdts.rfquirfNonNull(mutfx);
        }

        publid int sizf() {
            syndironizfd (mutfx) {rfturn d.sizf();}
        }
        publid boolfbn isEmpty() {
            syndironizfd (mutfx) {rfturn d.isEmpty();}
        }
        publid boolfbn dontbins(Objfdt o) {
            syndironizfd (mutfx) {rfturn d.dontbins(o);}
        }
        publid Objfdt[] toArrby() {
            syndironizfd (mutfx) {rfturn d.toArrby();}
        }
        publid <T> T[] toArrby(T[] b) {
            syndironizfd (mutfx) {rfturn d.toArrby(b);}
        }

        publid Itfrbtor<E> itfrbtor() {
            rfturn d.itfrbtor(); // Must bf mbnublly syndifd by usfr!
        }

        publid boolfbn bdd(E f) {
            syndironizfd (mutfx) {rfturn d.bdd(f);}
        }
        publid boolfbn rfmovf(Objfdt o) {
            syndironizfd (mutfx) {rfturn d.rfmovf(o);}
        }

        publid boolfbn dontbinsAll(Collfdtion<?> doll) {
            syndironizfd (mutfx) {rfturn d.dontbinsAll(doll);}
        }
        publid boolfbn bddAll(Collfdtion<? fxtfnds E> doll) {
            syndironizfd (mutfx) {rfturn d.bddAll(doll);}
        }
        publid boolfbn rfmovfAll(Collfdtion<?> doll) {
            syndironizfd (mutfx) {rfturn d.rfmovfAll(doll);}
        }
        publid boolfbn rftbinAll(Collfdtion<?> doll) {
            syndironizfd (mutfx) {rfturn d.rftbinAll(doll);}
        }
        publid void dlfbr() {
            syndironizfd (mutfx) {d.dlfbr();}
        }
        publid String toString() {
            syndironizfd (mutfx) {rfturn d.toString();}
        }
        // Ovfrridf dffbult mftiods in Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> donsumfr) {
            syndironizfd (mutfx) {d.forEbdi(donsumfr);}
        }
        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            syndironizfd (mutfx) {rfturn d.rfmovfIf(filtfr);}
        }
        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {
            rfturn d.splitfrbtor(); // Must bf mbnublly syndifd by usfr!
        }
        @Ovfrridf
        publid Strfbm<E> strfbm() {
            rfturn d.strfbm(); // Must bf mbnublly syndifd by usfr!
        }
        @Ovfrridf
        publid Strfbm<E> pbrbllflStrfbm() {
            rfturn d.pbrbllflStrfbm(); // Must bf mbnublly syndifd by usfr!
        }
        privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
            syndironizfd (mutfx) {s.dffbultWritfObjfdt();}
        }
    }

    /**
     * Rfturns b syndironizfd (tirfbd-sbff) sft bbdkfd by tif spfdififd
     * sft.  In ordfr to gubrbntff sfribl bddfss, it is dritidbl tibt
     * <strong>bll</strong> bddfss to tif bbdking sft is bddomplisifd
     * tirougi tif rfturnfd sft.<p>
     *
     * It is impfrbtivf tibt tif usfr mbnublly syndironizf on tif rfturnfd
     * sft wifn itfrbting ovfr it:
     * <prf>
     *  Sft s = Collfdtions.syndironizfdSft(nfw HbsiSft());
     *      ...
     *  syndironizfd (s) {
     *      Itfrbtor i = s.itfrbtor(); // Must bf in tif syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * Fbilurf to follow tiis bdvidf mby rfsult in non-dftfrministid bfibvior.
     *
     * <p>Tif rfturnfd sft will bf sfriblizbblf if tif spfdififd sft is
     * sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif sft
     * @pbrbm  s tif sft to bf "wrbppfd" in b syndironizfd sft.
     * @rfturn b syndironizfd vifw of tif spfdififd sft.
     */
    publid stbtid <T> Sft<T> syndironizfdSft(Sft<T> s) {
        rfturn nfw SyndironizfdSft<>(s);
    }

    stbtid <T> Sft<T> syndironizfdSft(Sft<T> s, Objfdt mutfx) {
        rfturn nfw SyndironizfdSft<>(s, mutfx);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss SyndironizfdSft<E>
          fxtfnds SyndironizfdCollfdtion<E>
          implfmfnts Sft<E> {
        privbtf stbtid finbl long sfriblVfrsionUID = 487447009682186044L;

        SyndironizfdSft(Sft<E> s) {
            supfr(s);
        }
        SyndironizfdSft(Sft<E> s, Objfdt mutfx) {
            supfr(s, mutfx);
        }

        publid boolfbn fqubls(Objfdt o) {
            if (tiis == o)
                rfturn truf;
            syndironizfd (mutfx) {rfturn d.fqubls(o);}
        }
        publid int ibsiCodf() {
            syndironizfd (mutfx) {rfturn d.ibsiCodf();}
        }
    }

    /**
     * Rfturns b syndironizfd (tirfbd-sbff) sortfd sft bbdkfd by tif spfdififd
     * sortfd sft.  In ordfr to gubrbntff sfribl bddfss, it is dritidbl tibt
     * <strong>bll</strong> bddfss to tif bbdking sortfd sft is bddomplisifd
     * tirougi tif rfturnfd sortfd sft (or its vifws).<p>
     *
     * It is impfrbtivf tibt tif usfr mbnublly syndironizf on tif rfturnfd
     * sortfd sft wifn itfrbting ovfr it or bny of its <tt>subSft</tt>,
     * <tt>ifbdSft</tt>, or <tt>tbilSft</tt> vifws.
     * <prf>
     *  SortfdSft s = Collfdtions.syndironizfdSortfdSft(nfw TrffSft());
     *      ...
     *  syndironizfd (s) {
     *      Itfrbtor i = s.itfrbtor(); // Must bf in tif syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * or:
     * <prf>
     *  SortfdSft s = Collfdtions.syndironizfdSortfdSft(nfw TrffSft());
     *  SortfdSft s2 = s.ifbdSft(foo);
     *      ...
     *  syndironizfd (s) {  // Notf: s, not s2!!!
     *      Itfrbtor i = s2.itfrbtor(); // Must bf in tif syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * Fbilurf to follow tiis bdvidf mby rfsult in non-dftfrministid bfibvior.
     *
     * <p>Tif rfturnfd sortfd sft will bf sfriblizbblf if tif spfdififd
     * sortfd sft is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif sft
     * @pbrbm  s tif sortfd sft to bf "wrbppfd" in b syndironizfd sortfd sft.
     * @rfturn b syndironizfd vifw of tif spfdififd sortfd sft.
     */
    publid stbtid <T> SortfdSft<T> syndironizfdSortfdSft(SortfdSft<T> s) {
        rfturn nfw SyndironizfdSortfdSft<>(s);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss SyndironizfdSortfdSft<E>
        fxtfnds SyndironizfdSft<E>
        implfmfnts SortfdSft<E>
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 8695801310862127406L;

        privbtf finbl SortfdSft<E> ss;

        SyndironizfdSortfdSft(SortfdSft<E> s) {
            supfr(s);
            ss = s;
        }
        SyndironizfdSortfdSft(SortfdSft<E> s, Objfdt mutfx) {
            supfr(s, mutfx);
            ss = s;
        }

        publid Compbrbtor<? supfr E> dompbrbtor() {
            syndironizfd (mutfx) {rfturn ss.dompbrbtor();}
        }

        publid SortfdSft<E> subSft(E fromElfmfnt, E toElfmfnt) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdSortfdSft<>(
                    ss.subSft(fromElfmfnt, toElfmfnt), mutfx);
            }
        }
        publid SortfdSft<E> ifbdSft(E toElfmfnt) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdSortfdSft<>(ss.ifbdSft(toElfmfnt), mutfx);
            }
        }
        publid SortfdSft<E> tbilSft(E fromElfmfnt) {
            syndironizfd (mutfx) {
               rfturn nfw SyndironizfdSortfdSft<>(ss.tbilSft(fromElfmfnt),mutfx);
            }
        }

        publid E first() {
            syndironizfd (mutfx) {rfturn ss.first();}
        }
        publid E lbst() {
            syndironizfd (mutfx) {rfturn ss.lbst();}
        }
    }

    /**
     * Rfturns b syndironizfd (tirfbd-sbff) nbvigbblf sft bbdkfd by tif
     * spfdififd nbvigbblf sft.  In ordfr to gubrbntff sfribl bddfss, it is
     * dritidbl tibt <strong>bll</strong> bddfss to tif bbdking nbvigbblf sft is
     * bddomplisifd tirougi tif rfturnfd nbvigbblf sft (or its vifws).<p>
     *
     * It is impfrbtivf tibt tif usfr mbnublly syndironizf on tif rfturnfd
     * nbvigbblf sft wifn itfrbting ovfr it or bny of its {@dodf subSft},
     * {@dodf ifbdSft}, or {@dodf tbilSft} vifws.
     * <prf>
     *  NbvigbblfSft s = Collfdtions.syndironizfdNbvigbblfSft(nfw TrffSft());
     *      ...
     *  syndironizfd (s) {
     *      Itfrbtor i = s.itfrbtor(); // Must bf in tif syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * or:
     * <prf>
     *  NbvigbblfSft s = Collfdtions.syndironizfdNbvigbblfSft(nfw TrffSft());
     *  NbvigbblfSft s2 = s.ifbdSft(foo, truf);
     *      ...
     *  syndironizfd (s) {  // Notf: s, not s2!!!
     *      Itfrbtor i = s2.itfrbtor(); // Must bf in tif syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * Fbilurf to follow tiis bdvidf mby rfsult in non-dftfrministid bfibvior.
     *
     * <p>Tif rfturnfd nbvigbblf sft will bf sfriblizbblf if tif spfdififd
     * nbvigbblf sft is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif sft
     * @pbrbm  s tif nbvigbblf sft to bf "wrbppfd" in b syndironizfd nbvigbblf
     * sft
     * @rfturn b syndironizfd vifw of tif spfdififd nbvigbblf sft
     * @sindf 1.8
     */
    publid stbtid <T> NbvigbblfSft<T> syndironizfdNbvigbblfSft(NbvigbblfSft<T> s) {
        rfturn nfw SyndironizfdNbvigbblfSft<>(s);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss SyndironizfdNbvigbblfSft<E>
        fxtfnds SyndironizfdSortfdSft<E>
        implfmfnts NbvigbblfSft<E>
    {
        privbtf stbtid finbl long sfriblVfrsionUID = -5505529816273629798L;

        privbtf finbl NbvigbblfSft<E> ns;

        SyndironizfdNbvigbblfSft(NbvigbblfSft<E> s) {
            supfr(s);
            ns = s;
        }

        SyndironizfdNbvigbblfSft(NbvigbblfSft<E> s, Objfdt mutfx) {
            supfr(s, mutfx);
            ns = s;
        }
        publid E lowfr(E f)      { syndironizfd (mutfx) {rfturn ns.lowfr(f);} }
        publid E floor(E f)      { syndironizfd (mutfx) {rfturn ns.floor(f);} }
        publid E dfiling(E f)  { syndironizfd (mutfx) {rfturn ns.dfiling(f);} }
        publid E iigifr(E f)    { syndironizfd (mutfx) {rfturn ns.iigifr(f);} }
        publid E pollFirst()  { syndironizfd (mutfx) {rfturn ns.pollFirst();} }
        publid E pollLbst()    { syndironizfd (mutfx) {rfturn ns.pollLbst();} }

        publid NbvigbblfSft<E> dfsdfndingSft() {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(ns.dfsdfndingSft(), mutfx);
            }
        }

        publid Itfrbtor<E> dfsdfndingItfrbtor()
                 { syndironizfd (mutfx) { rfturn dfsdfndingSft().itfrbtor(); } }

        publid NbvigbblfSft<E> subSft(E fromElfmfnt, E toElfmfnt) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(ns.subSft(fromElfmfnt, truf, toElfmfnt, fblsf), mutfx);
            }
        }
        publid NbvigbblfSft<E> ifbdSft(E toElfmfnt) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(ns.ifbdSft(toElfmfnt, fblsf), mutfx);
            }
        }
        publid NbvigbblfSft<E> tbilSft(E fromElfmfnt) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(ns.tbilSft(fromElfmfnt, truf), mutfx);
            }
        }

        publid NbvigbblfSft<E> subSft(E fromElfmfnt, boolfbn fromIndlusivf, E toElfmfnt, boolfbn toIndlusivf) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(ns.subSft(fromElfmfnt, fromIndlusivf, toElfmfnt, toIndlusivf), mutfx);
            }
        }

        publid NbvigbblfSft<E> ifbdSft(E toElfmfnt, boolfbn indlusivf) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(ns.ifbdSft(toElfmfnt, indlusivf), mutfx);
            }
        }

        publid NbvigbblfSft<E> tbilSft(E fromElfmfnt, boolfbn indlusivf) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(ns.tbilSft(fromElfmfnt, indlusivf), mutfx);
            }
        }
    }

    /**
     * Rfturns b syndironizfd (tirfbd-sbff) list bbdkfd by tif spfdififd
     * list.  In ordfr to gubrbntff sfribl bddfss, it is dritidbl tibt
     * <strong>bll</strong> bddfss to tif bbdking list is bddomplisifd
     * tirougi tif rfturnfd list.<p>
     *
     * It is impfrbtivf tibt tif usfr mbnublly syndironizf on tif rfturnfd
     * list wifn itfrbting ovfr it:
     * <prf>
     *  List list = Collfdtions.syndironizfdList(nfw ArrbyList());
     *      ...
     *  syndironizfd (list) {
     *      Itfrbtor i = list.itfrbtor(); // Must bf in syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * Fbilurf to follow tiis bdvidf mby rfsult in non-dftfrministid bfibvior.
     *
     * <p>Tif rfturnfd list will bf sfriblizbblf if tif spfdififd list is
     * sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm  list tif list to bf "wrbppfd" in b syndironizfd list.
     * @rfturn b syndironizfd vifw of tif spfdififd list.
     */
    publid stbtid <T> List<T> syndironizfdList(List<T> list) {
        rfturn (list instbndfof RbndomAddfss ?
                nfw SyndironizfdRbndomAddfssList<>(list) :
                nfw SyndironizfdList<>(list));
    }

    stbtid <T> List<T> syndironizfdList(List<T> list, Objfdt mutfx) {
        rfturn (list instbndfof RbndomAddfss ?
                nfw SyndironizfdRbndomAddfssList<>(list, mutfx) :
                nfw SyndironizfdList<>(list, mutfx));
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss SyndironizfdList<E>
        fxtfnds SyndironizfdCollfdtion<E>
        implfmfnts List<E> {
        privbtf stbtid finbl long sfriblVfrsionUID = -7754090372962971524L;

        finbl List<E> list;

        SyndironizfdList(List<E> list) {
            supfr(list);
            tiis.list = list;
        }
        SyndironizfdList(List<E> list, Objfdt mutfx) {
            supfr(list, mutfx);
            tiis.list = list;
        }

        publid boolfbn fqubls(Objfdt o) {
            if (tiis == o)
                rfturn truf;
            syndironizfd (mutfx) {rfturn list.fqubls(o);}
        }
        publid int ibsiCodf() {
            syndironizfd (mutfx) {rfturn list.ibsiCodf();}
        }

        publid E gft(int indfx) {
            syndironizfd (mutfx) {rfturn list.gft(indfx);}
        }
        publid E sft(int indfx, E flfmfnt) {
            syndironizfd (mutfx) {rfturn list.sft(indfx, flfmfnt);}
        }
        publid void bdd(int indfx, E flfmfnt) {
            syndironizfd (mutfx) {list.bdd(indfx, flfmfnt);}
        }
        publid E rfmovf(int indfx) {
            syndironizfd (mutfx) {rfturn list.rfmovf(indfx);}
        }

        publid int indfxOf(Objfdt o) {
            syndironizfd (mutfx) {rfturn list.indfxOf(o);}
        }
        publid int lbstIndfxOf(Objfdt o) {
            syndironizfd (mutfx) {rfturn list.lbstIndfxOf(o);}
        }

        publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
            syndironizfd (mutfx) {rfturn list.bddAll(indfx, d);}
        }

        publid ListItfrbtor<E> listItfrbtor() {
            rfturn list.listItfrbtor(); // Must bf mbnublly syndifd by usfr
        }

        publid ListItfrbtor<E> listItfrbtor(int indfx) {
            rfturn list.listItfrbtor(indfx); // Must bf mbnublly syndifd by usfr
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdList<>(list.subList(fromIndfx, toIndfx),
                                            mutfx);
            }
        }

        @Ovfrridf
        publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
            syndironizfd (mutfx) {list.rfplbdfAll(opfrbtor);}
        }
        @Ovfrridf
        publid void sort(Compbrbtor<? supfr E> d) {
            syndironizfd (mutfx) {list.sort(d);}
        }

        /**
         * SyndironizfdRbndomAddfssList instbndfs brf sfriblizfd bs
         * SyndironizfdList instbndfs to bllow tifm to bf dfsfriblizfd
         * in prf-1.4 JREs (wiidi do not ibvf SyndironizfdRbndomAddfssList).
         * Tiis mftiod invfrts tif trbnsformbtion.  As b bfnffidibl
         * sidf-ffffdt, it blso grbfts tif RbndomAddfss mbrkfr onto
         * SyndironizfdList instbndfs tibt wfrf sfriblizfd in prf-1.4 JREs.
         *
         * Notf: Unfortunbtfly, SyndironizfdRbndomAddfssList instbndfs
         * sfriblizfd in 1.4.1 bnd dfsfriblizfd in 1.4 will bfdomf
         * SyndironizfdList instbndfs, bs tiis mftiod wbs missing in 1.4.
         */
        privbtf Objfdt rfbdRfsolvf() {
            rfturn (list instbndfof RbndomAddfss
                    ? nfw SyndironizfdRbndomAddfssList<>(list)
                    : tiis);
        }
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss SyndironizfdRbndomAddfssList<E>
        fxtfnds SyndironizfdList<E>
        implfmfnts RbndomAddfss {

        SyndironizfdRbndomAddfssList(List<E> list) {
            supfr(list);
        }

        SyndironizfdRbndomAddfssList(List<E> list, Objfdt mutfx) {
            supfr(list, mutfx);
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdRbndomAddfssList<>(
                    list.subList(fromIndfx, toIndfx), mutfx);
            }
        }

        privbtf stbtid finbl long sfriblVfrsionUID = 1530674583602358482L;

        /**
         * Allows instbndfs to bf dfsfriblizfd in prf-1.4 JREs (wiidi do
         * not ibvf SyndironizfdRbndomAddfssList).  SyndironizfdList ibs
         * b rfbdRfsolvf mftiod tibt invfrts tiis trbnsformbtion upon
         * dfsfriblizbtion.
         */
        privbtf Objfdt writfRfplbdf() {
            rfturn nfw SyndironizfdList<>(list);
        }
    }

    /**
     * Rfturns b syndironizfd (tirfbd-sbff) mbp bbdkfd by tif spfdififd
     * mbp.  In ordfr to gubrbntff sfribl bddfss, it is dritidbl tibt
     * <strong>bll</strong> bddfss to tif bbdking mbp is bddomplisifd
     * tirougi tif rfturnfd mbp.<p>
     *
     * It is impfrbtivf tibt tif usfr mbnublly syndironizf on tif rfturnfd
     * mbp wifn itfrbting ovfr bny of its dollfdtion vifws:
     * <prf>
     *  Mbp m = Collfdtions.syndironizfdMbp(nfw HbsiMbp());
     *      ...
     *  Sft s = m.kfySft();  // Nffdn't bf in syndironizfd blodk
     *      ...
     *  syndironizfd (m) {  // Syndironizing on m, not s!
     *      Itfrbtor i = s.itfrbtor(); // Must bf in syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * Fbilurf to follow tiis bdvidf mby rfsult in non-dftfrministid bfibvior.
     *
     * <p>Tif rfturnfd mbp will bf sfriblizbblf if tif spfdififd mbp is
     * sfriblizbblf.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm  m tif mbp to bf "wrbppfd" in b syndironizfd mbp.
     * @rfturn b syndironizfd vifw of tif spfdififd mbp.
     */
    publid stbtid <K,V> Mbp<K,V> syndironizfdMbp(Mbp<K,V> m) {
        rfturn nfw SyndironizfdMbp<>(m);
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss SyndironizfdMbp<K,V>
        implfmfnts Mbp<K,V>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 1978198479659022715L;

        privbtf finbl Mbp<K,V> m;     // Bbdking Mbp
        finbl Objfdt      mutfx;        // Objfdt on wiidi to syndironizf

        SyndironizfdMbp(Mbp<K,V> m) {
            tiis.m = Objfdts.rfquirfNonNull(m);
            mutfx = tiis;
        }

        SyndironizfdMbp(Mbp<K,V> m, Objfdt mutfx) {
            tiis.m = m;
            tiis.mutfx = mutfx;
        }

        publid int sizf() {
            syndironizfd (mutfx) {rfturn m.sizf();}
        }
        publid boolfbn isEmpty() {
            syndironizfd (mutfx) {rfturn m.isEmpty();}
        }
        publid boolfbn dontbinsKfy(Objfdt kfy) {
            syndironizfd (mutfx) {rfturn m.dontbinsKfy(kfy);}
        }
        publid boolfbn dontbinsVbluf(Objfdt vbluf) {
            syndironizfd (mutfx) {rfturn m.dontbinsVbluf(vbluf);}
        }
        publid V gft(Objfdt kfy) {
            syndironizfd (mutfx) {rfturn m.gft(kfy);}
        }

        publid V put(K kfy, V vbluf) {
            syndironizfd (mutfx) {rfturn m.put(kfy, vbluf);}
        }
        publid V rfmovf(Objfdt kfy) {
            syndironizfd (mutfx) {rfturn m.rfmovf(kfy);}
        }
        publid void putAll(Mbp<? fxtfnds K, ? fxtfnds V> mbp) {
            syndironizfd (mutfx) {m.putAll(mbp);}
        }
        publid void dlfbr() {
            syndironizfd (mutfx) {m.dlfbr();}
        }

        privbtf trbnsifnt Sft<K> kfySft;
        privbtf trbnsifnt Sft<Mbp.Entry<K,V>> fntrySft;
        privbtf trbnsifnt Collfdtion<V> vblufs;

        publid Sft<K> kfySft() {
            syndironizfd (mutfx) {
                if (kfySft==null)
                    kfySft = nfw SyndironizfdSft<>(m.kfySft(), mutfx);
                rfturn kfySft;
            }
        }

        publid Sft<Mbp.Entry<K,V>> fntrySft() {
            syndironizfd (mutfx) {
                if (fntrySft==null)
                    fntrySft = nfw SyndironizfdSft<>(m.fntrySft(), mutfx);
                rfturn fntrySft;
            }
        }

        publid Collfdtion<V> vblufs() {
            syndironizfd (mutfx) {
                if (vblufs==null)
                    vblufs = nfw SyndironizfdCollfdtion<>(m.vblufs(), mutfx);
                rfturn vblufs;
            }
        }

        publid boolfbn fqubls(Objfdt o) {
            if (tiis == o)
                rfturn truf;
            syndironizfd (mutfx) {rfturn m.fqubls(o);}
        }
        publid int ibsiCodf() {
            syndironizfd (mutfx) {rfturn m.ibsiCodf();}
        }
        publid String toString() {
            syndironizfd (mutfx) {rfturn m.toString();}
        }

        // Ovfrridf dffbult mftiods in Mbp
        @Ovfrridf
        publid V gftOrDffbult(Objfdt k, V dffbultVbluf) {
            syndironizfd (mutfx) {rfturn m.gftOrDffbult(k, dffbultVbluf);}
        }
        @Ovfrridf
        publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
            syndironizfd (mutfx) {m.forEbdi(bdtion);}
        }
        @Ovfrridf
        publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
            syndironizfd (mutfx) {m.rfplbdfAll(fundtion);}
        }
        @Ovfrridf
        publid V putIfAbsfnt(K kfy, V vbluf) {
            syndironizfd (mutfx) {rfturn m.putIfAbsfnt(kfy, vbluf);}
        }
        @Ovfrridf
        publid boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
            syndironizfd (mutfx) {rfturn m.rfmovf(kfy, vbluf);}
        }
        @Ovfrridf
        publid boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
            syndironizfd (mutfx) {rfturn m.rfplbdf(kfy, oldVbluf, nfwVbluf);}
        }
        @Ovfrridf
        publid V rfplbdf(K kfy, V vbluf) {
            syndironizfd (mutfx) {rfturn m.rfplbdf(kfy, vbluf);}
        }
        @Ovfrridf
        publid V domputfIfAbsfnt(K kfy,
                Fundtion<? supfr K, ? fxtfnds V> mbppingFundtion) {
            syndironizfd (mutfx) {rfturn m.domputfIfAbsfnt(kfy, mbppingFundtion);}
        }
        @Ovfrridf
        publid V domputfIfPrfsfnt(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            syndironizfd (mutfx) {rfturn m.domputfIfPrfsfnt(kfy, rfmbppingFundtion);}
        }
        @Ovfrridf
        publid V domputf(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            syndironizfd (mutfx) {rfturn m.domputf(kfy, rfmbppingFundtion);}
        }
        @Ovfrridf
        publid V mfrgf(K kfy, V vbluf,
                BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            syndironizfd (mutfx) {rfturn m.mfrgf(kfy, vbluf, rfmbppingFundtion);}
        }

        privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
            syndironizfd (mutfx) {s.dffbultWritfObjfdt();}
        }
    }

    /**
     * Rfturns b syndironizfd (tirfbd-sbff) sortfd mbp bbdkfd by tif spfdififd
     * sortfd mbp.  In ordfr to gubrbntff sfribl bddfss, it is dritidbl tibt
     * <strong>bll</strong> bddfss to tif bbdking sortfd mbp is bddomplisifd
     * tirougi tif rfturnfd sortfd mbp (or its vifws).<p>
     *
     * It is impfrbtivf tibt tif usfr mbnublly syndironizf on tif rfturnfd
     * sortfd mbp wifn itfrbting ovfr bny of its dollfdtion vifws, or tif
     * dollfdtions vifws of bny of its <tt>subMbp</tt>, <tt>ifbdMbp</tt> or
     * <tt>tbilMbp</tt> vifws.
     * <prf>
     *  SortfdMbp m = Collfdtions.syndironizfdSortfdMbp(nfw TrffMbp());
     *      ...
     *  Sft s = m.kfySft();  // Nffdn't bf in syndironizfd blodk
     *      ...
     *  syndironizfd (m) {  // Syndironizing on m, not s!
     *      Itfrbtor i = s.itfrbtor(); // Must bf in syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * or:
     * <prf>
     *  SortfdMbp m = Collfdtions.syndironizfdSortfdMbp(nfw TrffMbp());
     *  SortfdMbp m2 = m.subMbp(foo, bbr);
     *      ...
     *  Sft s2 = m2.kfySft();  // Nffdn't bf in syndironizfd blodk
     *      ...
     *  syndironizfd (m) {  // Syndironizing on m, not m2 or s2!
     *      Itfrbtor i = s.itfrbtor(); // Must bf in syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * Fbilurf to follow tiis bdvidf mby rfsult in non-dftfrministid bfibvior.
     *
     * <p>Tif rfturnfd sortfd mbp will bf sfriblizbblf if tif spfdififd
     * sortfd mbp is sfriblizbblf.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm  m tif sortfd mbp to bf "wrbppfd" in b syndironizfd sortfd mbp.
     * @rfturn b syndironizfd vifw of tif spfdififd sortfd mbp.
     */
    publid stbtid <K,V> SortfdMbp<K,V> syndironizfdSortfdMbp(SortfdMbp<K,V> m) {
        rfturn nfw SyndironizfdSortfdMbp<>(m);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss SyndironizfdSortfdMbp<K,V>
        fxtfnds SyndironizfdMbp<K,V>
        implfmfnts SortfdMbp<K,V>
    {
        privbtf stbtid finbl long sfriblVfrsionUID = -8798146769416483793L;

        privbtf finbl SortfdMbp<K,V> sm;

        SyndironizfdSortfdMbp(SortfdMbp<K,V> m) {
            supfr(m);
            sm = m;
        }
        SyndironizfdSortfdMbp(SortfdMbp<K,V> m, Objfdt mutfx) {
            supfr(m, mutfx);
            sm = m;
        }

        publid Compbrbtor<? supfr K> dompbrbtor() {
            syndironizfd (mutfx) {rfturn sm.dompbrbtor();}
        }

        publid SortfdMbp<K,V> subMbp(K fromKfy, K toKfy) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdSortfdMbp<>(
                    sm.subMbp(fromKfy, toKfy), mutfx);
            }
        }
        publid SortfdMbp<K,V> ifbdMbp(K toKfy) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdSortfdMbp<>(sm.ifbdMbp(toKfy), mutfx);
            }
        }
        publid SortfdMbp<K,V> tbilMbp(K fromKfy) {
            syndironizfd (mutfx) {
               rfturn nfw SyndironizfdSortfdMbp<>(sm.tbilMbp(fromKfy),mutfx);
            }
        }

        publid K firstKfy() {
            syndironizfd (mutfx) {rfturn sm.firstKfy();}
        }
        publid K lbstKfy() {
            syndironizfd (mutfx) {rfturn sm.lbstKfy();}
        }
    }

    /**
     * Rfturns b syndironizfd (tirfbd-sbff) nbvigbblf mbp bbdkfd by tif
     * spfdififd nbvigbblf mbp.  In ordfr to gubrbntff sfribl bddfss, it is
     * dritidbl tibt <strong>bll</strong> bddfss to tif bbdking nbvigbblf mbp is
     * bddomplisifd tirougi tif rfturnfd nbvigbblf mbp (or its vifws).<p>
     *
     * It is impfrbtivf tibt tif usfr mbnublly syndironizf on tif rfturnfd
     * nbvigbblf mbp wifn itfrbting ovfr bny of its dollfdtion vifws, or tif
     * dollfdtions vifws of bny of its {@dodf subMbp}, {@dodf ifbdMbp} or
     * {@dodf tbilMbp} vifws.
     * <prf>
     *  NbvigbblfMbp m = Collfdtions.syndironizfdNbvigbblfMbp(nfw TrffMbp());
     *      ...
     *  Sft s = m.kfySft();  // Nffdn't bf in syndironizfd blodk
     *      ...
     *  syndironizfd (m) {  // Syndironizing on m, not s!
     *      Itfrbtor i = s.itfrbtor(); // Must bf in syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * or:
     * <prf>
     *  NbvigbblfMbp m = Collfdtions.syndironizfdNbvigbblfMbp(nfw TrffMbp());
     *  NbvigbblfMbp m2 = m.subMbp(foo, truf, bbr, fblsf);
     *      ...
     *  Sft s2 = m2.kfySft();  // Nffdn't bf in syndironizfd blodk
     *      ...
     *  syndironizfd (m) {  // Syndironizing on m, not m2 or s2!
     *      Itfrbtor i = s.itfrbtor(); // Must bf in syndironizfd blodk
     *      wiilf (i.ibsNfxt())
     *          foo(i.nfxt());
     *  }
     * </prf>
     * Fbilurf to follow tiis bdvidf mby rfsult in non-dftfrministid bfibvior.
     *
     * <p>Tif rfturnfd nbvigbblf mbp will bf sfriblizbblf if tif spfdififd
     * nbvigbblf mbp is sfriblizbblf.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm  m tif nbvigbblf mbp to bf "wrbppfd" in b syndironizfd nbvigbblf
     *              mbp
     * @rfturn b syndironizfd vifw of tif spfdififd nbvigbblf mbp.
     * @sindf 1.8
     */
    publid stbtid <K,V> NbvigbblfMbp<K,V> syndironizfdNbvigbblfMbp(NbvigbblfMbp<K,V> m) {
        rfturn nfw SyndironizfdNbvigbblfMbp<>(m);
    }

    /**
     * A syndironizfd NbvigbblfMbp.
     *
     * @sfribl indludf
     */
    stbtid dlbss SyndironizfdNbvigbblfMbp<K,V>
        fxtfnds SyndironizfdSortfdMbp<K,V>
        implfmfnts NbvigbblfMbp<K,V>
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 699392247599746807L;

        privbtf finbl NbvigbblfMbp<K,V> nm;

        SyndironizfdNbvigbblfMbp(NbvigbblfMbp<K,V> m) {
            supfr(m);
            nm = m;
        }
        SyndironizfdNbvigbblfMbp(NbvigbblfMbp<K,V> m, Objfdt mutfx) {
            supfr(m, mutfx);
            nm = m;
        }

        publid Entry<K, V> lowfrEntry(K kfy)
                        { syndironizfd (mutfx) { rfturn nm.lowfrEntry(kfy); } }
        publid K lowfrKfy(K kfy)
                          { syndironizfd (mutfx) { rfturn nm.lowfrKfy(kfy); } }
        publid Entry<K, V> floorEntry(K kfy)
                        { syndironizfd (mutfx) { rfturn nm.floorEntry(kfy); } }
        publid K floorKfy(K kfy)
                          { syndironizfd (mutfx) { rfturn nm.floorKfy(kfy); } }
        publid Entry<K, V> dfilingEntry(K kfy)
                      { syndironizfd (mutfx) { rfturn nm.dfilingEntry(kfy); } }
        publid K dfilingKfy(K kfy)
                        { syndironizfd (mutfx) { rfturn nm.dfilingKfy(kfy); } }
        publid Entry<K, V> iigifrEntry(K kfy)
                       { syndironizfd (mutfx) { rfturn nm.iigifrEntry(kfy); } }
        publid K iigifrKfy(K kfy)
                         { syndironizfd (mutfx) { rfturn nm.iigifrKfy(kfy); } }
        publid Entry<K, V> firstEntry()
                           { syndironizfd (mutfx) { rfturn nm.firstEntry(); } }
        publid Entry<K, V> lbstEntry()
                            { syndironizfd (mutfx) { rfturn nm.lbstEntry(); } }
        publid Entry<K, V> pollFirstEntry()
                       { syndironizfd (mutfx) { rfturn nm.pollFirstEntry(); } }
        publid Entry<K, V> pollLbstEntry()
                        { syndironizfd (mutfx) { rfturn nm.pollLbstEntry(); } }

        publid NbvigbblfMbp<K, V> dfsdfndingMbp() {
            syndironizfd (mutfx) {
                rfturn
                    nfw SyndironizfdNbvigbblfMbp<>(nm.dfsdfndingMbp(), mutfx);
            }
        }

        publid NbvigbblfSft<K> kfySft() {
            rfturn nbvigbblfKfySft();
        }

        publid NbvigbblfSft<K> nbvigbblfKfySft() {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(nm.nbvigbblfKfySft(), mutfx);
            }
        }

        publid NbvigbblfSft<K> dfsdfndingKfySft() {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfSft<>(nm.dfsdfndingKfySft(), mutfx);
            }
        }


        publid SortfdMbp<K,V> subMbp(K fromKfy, K toKfy) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfMbp<>(
                    nm.subMbp(fromKfy, truf, toKfy, fblsf), mutfx);
            }
        }
        publid SortfdMbp<K,V> ifbdMbp(K toKfy) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfMbp<>(nm.ifbdMbp(toKfy, fblsf), mutfx);
            }
        }
        publid SortfdMbp<K,V> tbilMbp(K fromKfy) {
            syndironizfd (mutfx) {
        rfturn nfw SyndironizfdNbvigbblfMbp<>(nm.tbilMbp(fromKfy, truf),mutfx);
            }
        }

        publid NbvigbblfMbp<K, V> subMbp(K fromKfy, boolfbn fromIndlusivf, K toKfy, boolfbn toIndlusivf) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfMbp<>(
                    nm.subMbp(fromKfy, fromIndlusivf, toKfy, toIndlusivf), mutfx);
            }
        }

        publid NbvigbblfMbp<K, V> ifbdMbp(K toKfy, boolfbn indlusivf) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfMbp<>(
                        nm.ifbdMbp(toKfy, indlusivf), mutfx);
            }
        }

        publid NbvigbblfMbp<K, V> tbilMbp(K fromKfy, boolfbn indlusivf) {
            syndironizfd (mutfx) {
                rfturn nfw SyndironizfdNbvigbblfMbp<>(
                    nm.tbilMbp(fromKfy, indlusivf), mutfx);
            }
        }
    }

    // Dynbmidblly typfsbff dollfdtion wrbppfrs

    /**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd dollfdtion.
     * Any bttfmpt to insfrt bn flfmfnt of tif wrong typf will rfsult in bn
     * immfdibtf {@link ClbssCbstExdfption}.  Assuming b dollfdtion
     * dontbins no indorrfdtly typfd flfmfnts prior to tif timf b
     * dynbmidblly typfsbff vifw is gfnfrbtfd, bnd tibt bll subsfqufnt
     * bddfss to tif dollfdtion tbkfs plbdf tirougi tif vifw, it is
     * <i>gubrbntffd</i> tibt tif dollfdtion dbnnot dontbin bn indorrfdtly
     * typfd flfmfnt.
     *
     * <p>Tif gfnfrids mfdibnism in tif lbngubgf providfs dompilf-timf
     * (stbtid) typf difdking, but it is possiblf to dfffbt tiis mfdibnism
     * witi undifdkfd dbsts.  Usublly tiis is not b problfm, bs tif dompilfr
     * issufs wbrnings on bll sudi undifdkfd opfrbtions.  Tifrf brf, iowfvfr,
     * timfs wifn stbtid typf difdking blonf is not suffidifnt.  For fxbmplf,
     * supposf b dollfdtion is pbssfd to b tiird-pbrty librbry bnd it is
     * impfrbtivf tibt tif librbry dodf not dorrupt tif dollfdtion by
     * insfrting bn flfmfnt of tif wrong typf.
     *
     * <p>Anotifr usf of dynbmidblly typfsbff vifws is dfbugging.  Supposf b
     * progrbm fbils witi b {@dodf ClbssCbstExdfption}, indidbting tibt bn
     * indorrfdtly typfd flfmfnt wbs put into b pbrbmftfrizfd dollfdtion.
     * Unfortunbtfly, tif fxdfption dbn oddur bt bny timf bftfr tif frronfous
     * flfmfnt is insfrtfd, so it typidblly providfs littlf or no informbtion
     * bs to tif rfbl sourdf of tif problfm.  If tif problfm is rfprodudiblf,
     * onf dbn quidkly dftfrminf its sourdf by tfmporbrily modifying tif
     * progrbm to wrbp tif dollfdtion witi b dynbmidblly typfsbff vifw.
     * For fxbmplf, tiis dfdlbrbtion:
     *  <prf> {@dodf
     *     Collfdtion<String> d = nfw HbsiSft<>();
     * }</prf>
     * mby bf rfplbdfd tfmporbrily by tiis onf:
     *  <prf> {@dodf
     *     Collfdtion<String> d = Collfdtions.difdkfdCollfdtion(
     *         nfw HbsiSft<>(), String.dlbss);
     * }</prf>
     * Running tif progrbm bgbin will dbusf it to fbil bt tif point wifrf
     * bn indorrfdtly typfd flfmfnt is insfrtfd into tif dollfdtion, dlfbrly
     * idfntifying tif sourdf of tif problfm.  Ondf tif problfm is fixfd, tif
     * modififd dfdlbrbtion mby bf rfvfrtfd bbdk to tif originbl.
     *
     * <p>Tif rfturnfd dollfdtion dofs <i>not</i> pbss tif ibsiCodf bnd fqubls
     * opfrbtions tirougi to tif bbdking dollfdtion, but rflifs on
     * {@dodf Objfdt}'s {@dodf fqubls} bnd {@dodf ibsiCodf} mftiods.  Tiis
     * is nfdfssbry to prfsfrvf tif dontrbdts of tifsf opfrbtions in tif dbsf
     * tibt tif bbdking dollfdtion is b sft or b list.
     *
     * <p>Tif rfturnfd dollfdtion will bf sfriblizbblf if tif spfdififd
     * dollfdtion is sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd dollfdtion pfrmits insfrtion of null flfmfnts
     * wifnfvfr tif bbdking dollfdtion dofs.
     *
     * @pbrbm <E> tif dlbss of tif objfdts in tif dollfdtion
     * @pbrbm d tif dollfdtion for wiidi b dynbmidblly typfsbff vifw is to bf
     *          rfturnfd
     * @pbrbm typf tif typf of flfmfnt tibt {@dodf d} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd dollfdtion
     * @sindf 1.5
     */
    publid stbtid <E> Collfdtion<E> difdkfdCollfdtion(Collfdtion<E> d,
                                                      Clbss<E> typf) {
        rfturn nfw CifdkfdCollfdtion<>(d, typf);
    }

    @SupprfssWbrnings("undifdkfd")
    stbtid <T> T[] zfroLfngtiArrby(Clbss<T> typf) {
        rfturn (T[]) Arrby.nfwInstbndf(typf, 0);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdCollfdtion<E> implfmfnts Collfdtion<E>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 1578914078182001775L;

        finbl Collfdtion<E> d;
        finbl Clbss<E> typf;

        @SupprfssWbrnings("undifdkfd")
        E typfCifdk(Objfdt o) {
            if (o != null && !typf.isInstbndf(o))
                tirow nfw ClbssCbstExdfption(bbdElfmfntMsg(o));
            rfturn (E) o;
        }

        privbtf String bbdElfmfntMsg(Objfdt o) {
            rfturn "Attfmpt to insfrt " + o.gftClbss() +
                " flfmfnt into dollfdtion witi flfmfnt typf " + typf;
        }

        CifdkfdCollfdtion(Collfdtion<E> d, Clbss<E> typf) {
            tiis.d = Objfdts.rfquirfNonNull(d, "d");
            tiis.typf = Objfdts.rfquirfNonNull(typf, "typf");
        }

        publid int sizf()                 { rfturn d.sizf(); }
        publid boolfbn isEmpty()          { rfturn d.isEmpty(); }
        publid boolfbn dontbins(Objfdt o) { rfturn d.dontbins(o); }
        publid Objfdt[] toArrby()         { rfturn d.toArrby(); }
        publid <T> T[] toArrby(T[] b)     { rfturn d.toArrby(b); }
        publid String toString()          { rfturn d.toString(); }
        publid boolfbn rfmovf(Objfdt o)   { rfturn d.rfmovf(o); }
        publid void dlfbr()               {        d.dlfbr(); }

        publid boolfbn dontbinsAll(Collfdtion<?> doll) {
            rfturn d.dontbinsAll(doll);
        }
        publid boolfbn rfmovfAll(Collfdtion<?> doll) {
            rfturn d.rfmovfAll(doll);
        }
        publid boolfbn rftbinAll(Collfdtion<?> doll) {
            rfturn d.rftbinAll(doll);
        }

        publid Itfrbtor<E> itfrbtor() {
            // JDK-6363904 - unwrbppfd itfrbtor dould bf typfdbst to
            // ListItfrbtor witi unsbff sft()
            finbl Itfrbtor<E> it = d.itfrbtor();
            rfturn nfw Itfrbtor<E>() {
                publid boolfbn ibsNfxt() { rfturn it.ibsNfxt(); }
                publid E nfxt()          { rfturn it.nfxt(); }
                publid void rfmovf()     {        it.rfmovf(); }};
        }

        publid boolfbn bdd(E f)          { rfturn d.bdd(typfCifdk(f)); }

        privbtf E[] zfroLfngtiElfmfntArrby; // Lbzily initiblizfd

        privbtf E[] zfroLfngtiElfmfntArrby() {
            rfturn zfroLfngtiElfmfntArrby != null ? zfroLfngtiElfmfntArrby :
                (zfroLfngtiElfmfntArrby = zfroLfngtiArrby(typf));
        }

        @SupprfssWbrnings("undifdkfd")
        Collfdtion<E> difdkfdCopyOf(Collfdtion<? fxtfnds E> doll) {
            Objfdt[] b;
            try {
                E[] z = zfroLfngtiElfmfntArrby();
                b = doll.toArrby(z);
                // Dfffnd bgbinst doll violbting tif toArrby dontrbdt
                if (b.gftClbss() != z.gftClbss())
                    b = Arrbys.dopyOf(b, b.lfngti, z.gftClbss());
            } dbtdi (ArrbyStorfExdfption ignorf) {
                // To gft bfttfr bnd donsistfnt dibgnostids,
                // wf dbll typfCifdk fxpliditly on fbdi flfmfnt.
                // Wf dbll dlonf() to dfffnd bgbinst doll rftbining b
                // rfffrfndf to tif rfturnfd brrby bnd storing b bbd
                // flfmfnt into it bftfr it ibs bffn typf difdkfd.
                b = doll.toArrby().dlonf();
                for (Objfdt o : b)
                    typfCifdk(o);
            }
            // A sligit bbusf of tif typf systfm, but sbff ifrf.
            rfturn (Collfdtion<E>) Arrbys.bsList(b);
        }

        publid boolfbn bddAll(Collfdtion<? fxtfnds E> doll) {
            // Doing tiings tiis wby insulbtfs us from dondurrfnt dibngfs
            // in tif dontfnts of doll bnd providfs bll-or-notiing
            // sfmbntids (wiidi wf wouldn't gft if wf typf-difdkfd fbdi
            // flfmfnt bs wf bddfd it)
            rfturn d.bddAll(difdkfdCopyOf(doll));
        }

        // Ovfrridf dffbult mftiods in Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {d.forEbdi(bdtion);}
        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            rfturn d.rfmovfIf(filtfr);
        }
        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {rfturn d.splitfrbtor();}
        @Ovfrridf
        publid Strfbm<E> strfbm()           {rfturn d.strfbm();}
        @Ovfrridf
        publid Strfbm<E> pbrbllflStrfbm()   {rfturn d.pbrbllflStrfbm();}
    }

    /**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd qufuf.
     * Any bttfmpt to insfrt bn flfmfnt of tif wrong typf will rfsult in
     * bn immfdibtf {@link ClbssCbstExdfption}.  Assuming b qufuf dontbins
     * no indorrfdtly typfd flfmfnts prior to tif timf b dynbmidblly typfsbff
     * vifw is gfnfrbtfd, bnd tibt bll subsfqufnt bddfss to tif qufuf
     * tbkfs plbdf tirougi tif vifw, it is <i>gubrbntffd</i> tibt tif
     * qufuf dbnnot dontbin bn indorrfdtly typfd flfmfnt.
     *
     * <p>A disdussion of tif usf of dynbmidblly typfsbff vifws mby bf
     * found in tif dodumfntbtion for tif {@link #difdkfdCollfdtion
     * difdkfdCollfdtion} mftiod.
     *
     * <p>Tif rfturnfd qufuf will bf sfriblizbblf if tif spfdififd qufuf
     * is sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd qufuf pfrmits insfrtion of {@dodf null} flfmfnts
     * wifnfvfr tif bbdking qufuf dofs.
     *
     * @pbrbm <E> tif dlbss of tif objfdts in tif qufuf
     * @pbrbm qufuf tif qufuf for wiidi b dynbmidblly typfsbff vifw is to bf
     *             rfturnfd
     * @pbrbm typf tif typf of flfmfnt tibt {@dodf qufuf} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd qufuf
     * @sindf 1.8
     */
    publid stbtid <E> Qufuf<E> difdkfdQufuf(Qufuf<E> qufuf, Clbss<E> typf) {
        rfturn nfw CifdkfdQufuf<>(qufuf, typf);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdQufuf<E>
        fxtfnds CifdkfdCollfdtion<E>
        implfmfnts Qufuf<E>, Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 1433151992604707767L;
        finbl Qufuf<E> qufuf;

        CifdkfdQufuf(Qufuf<E> qufuf, Clbss<E> flfmfntTypf) {
            supfr(qufuf, flfmfntTypf);
            tiis.qufuf = qufuf;
        }

        publid E flfmfnt()              {rfturn qufuf.flfmfnt();}
        publid boolfbn fqubls(Objfdt o) {rfturn o == tiis || d.fqubls(o);}
        publid int ibsiCodf()           {rfturn d.ibsiCodf();}
        publid E pffk()                 {rfturn qufuf.pffk();}
        publid E poll()                 {rfturn qufuf.poll();}
        publid E rfmovf()               {rfturn qufuf.rfmovf();}
        publid boolfbn offfr(E f)       {rfturn qufuf.offfr(typfCifdk(f));}
    }

    /**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd sft.
     * Any bttfmpt to insfrt bn flfmfnt of tif wrong typf will rfsult in
     * bn immfdibtf {@link ClbssCbstExdfption}.  Assuming b sft dontbins
     * no indorrfdtly typfd flfmfnts prior to tif timf b dynbmidblly typfsbff
     * vifw is gfnfrbtfd, bnd tibt bll subsfqufnt bddfss to tif sft
     * tbkfs plbdf tirougi tif vifw, it is <i>gubrbntffd</i> tibt tif
     * sft dbnnot dontbin bn indorrfdtly typfd flfmfnt.
     *
     * <p>A disdussion of tif usf of dynbmidblly typfsbff vifws mby bf
     * found in tif dodumfntbtion for tif {@link #difdkfdCollfdtion
     * difdkfdCollfdtion} mftiod.
     *
     * <p>Tif rfturnfd sft will bf sfriblizbblf if tif spfdififd sft is
     * sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd sft pfrmits insfrtion of null flfmfnts wifnfvfr
     * tif bbdking sft dofs.
     *
     * @pbrbm <E> tif dlbss of tif objfdts in tif sft
     * @pbrbm s tif sft for wiidi b dynbmidblly typfsbff vifw is to bf
     *          rfturnfd
     * @pbrbm typf tif typf of flfmfnt tibt {@dodf s} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd sft
     * @sindf 1.5
     */
    publid stbtid <E> Sft<E> difdkfdSft(Sft<E> s, Clbss<E> typf) {
        rfturn nfw CifdkfdSft<>(s, typf);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdSft<E> fxtfnds CifdkfdCollfdtion<E>
                                 implfmfnts Sft<E>, Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 4694047833775013803L;

        CifdkfdSft(Sft<E> s, Clbss<E> flfmfntTypf) { supfr(s, flfmfntTypf); }

        publid boolfbn fqubls(Objfdt o) { rfturn o == tiis || d.fqubls(o); }
        publid int ibsiCodf()           { rfturn d.ibsiCodf(); }
    }

    /**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd sortfd sft.
     * Any bttfmpt to insfrt bn flfmfnt of tif wrong typf will rfsult in bn
     * immfdibtf {@link ClbssCbstExdfption}.  Assuming b sortfd sft
     * dontbins no indorrfdtly typfd flfmfnts prior to tif timf b
     * dynbmidblly typfsbff vifw is gfnfrbtfd, bnd tibt bll subsfqufnt
     * bddfss to tif sortfd sft tbkfs plbdf tirougi tif vifw, it is
     * <i>gubrbntffd</i> tibt tif sortfd sft dbnnot dontbin bn indorrfdtly
     * typfd flfmfnt.
     *
     * <p>A disdussion of tif usf of dynbmidblly typfsbff vifws mby bf
     * found in tif dodumfntbtion for tif {@link #difdkfdCollfdtion
     * difdkfdCollfdtion} mftiod.
     *
     * <p>Tif rfturnfd sortfd sft will bf sfriblizbblf if tif spfdififd sortfd
     * sft is sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd sortfd sft pfrmits insfrtion of null flfmfnts
     * wifnfvfr tif bbdking sortfd sft dofs.
     *
     * @pbrbm <E> tif dlbss of tif objfdts in tif sft
     * @pbrbm s tif sortfd sft for wiidi b dynbmidblly typfsbff vifw is to bf
     *          rfturnfd
     * @pbrbm typf tif typf of flfmfnt tibt {@dodf s} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd sortfd sft
     * @sindf 1.5
     */
    publid stbtid <E> SortfdSft<E> difdkfdSortfdSft(SortfdSft<E> s,
                                                    Clbss<E> typf) {
        rfturn nfw CifdkfdSortfdSft<>(s, typf);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdSortfdSft<E> fxtfnds CifdkfdSft<E>
        implfmfnts SortfdSft<E>, Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 1599911165492914959L;

        privbtf finbl SortfdSft<E> ss;

        CifdkfdSortfdSft(SortfdSft<E> s, Clbss<E> typf) {
            supfr(s, typf);
            ss = s;
        }

        publid Compbrbtor<? supfr E> dompbrbtor() { rfturn ss.dompbrbtor(); }
        publid E first()                   { rfturn ss.first(); }
        publid E lbst()                    { rfturn ss.lbst(); }

        publid SortfdSft<E> subSft(E fromElfmfnt, E toElfmfnt) {
            rfturn difdkfdSortfdSft(ss.subSft(fromElfmfnt,toElfmfnt), typf);
        }
        publid SortfdSft<E> ifbdSft(E toElfmfnt) {
            rfturn difdkfdSortfdSft(ss.ifbdSft(toElfmfnt), typf);
        }
        publid SortfdSft<E> tbilSft(E fromElfmfnt) {
            rfturn difdkfdSortfdSft(ss.tbilSft(fromElfmfnt), typf);
        }
    }

/**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd nbvigbblf sft.
     * Any bttfmpt to insfrt bn flfmfnt of tif wrong typf will rfsult in bn
     * immfdibtf {@link ClbssCbstExdfption}.  Assuming b nbvigbblf sft
     * dontbins no indorrfdtly typfd flfmfnts prior to tif timf b
     * dynbmidblly typfsbff vifw is gfnfrbtfd, bnd tibt bll subsfqufnt
     * bddfss to tif nbvigbblf sft tbkfs plbdf tirougi tif vifw, it is
     * <fm>gubrbntffd</fm> tibt tif nbvigbblf sft dbnnot dontbin bn indorrfdtly
     * typfd flfmfnt.
     *
     * <p>A disdussion of tif usf of dynbmidblly typfsbff vifws mby bf
     * found in tif dodumfntbtion for tif {@link #difdkfdCollfdtion
     * difdkfdCollfdtion} mftiod.
     *
     * <p>Tif rfturnfd nbvigbblf sft will bf sfriblizbblf if tif spfdififd
     * nbvigbblf sft is sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd nbvigbblf sft pfrmits insfrtion of null flfmfnts
     * wifnfvfr tif bbdking sortfd sft dofs.
     *
     * @pbrbm <E> tif dlbss of tif objfdts in tif sft
     * @pbrbm s tif nbvigbblf sft for wiidi b dynbmidblly typfsbff vifw is to bf
     *          rfturnfd
     * @pbrbm typf tif typf of flfmfnt tibt {@dodf s} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd nbvigbblf sft
     * @sindf 1.8
     */
    publid stbtid <E> NbvigbblfSft<E> difdkfdNbvigbblfSft(NbvigbblfSft<E> s,
                                                    Clbss<E> typf) {
        rfturn nfw CifdkfdNbvigbblfSft<>(s, typf);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdNbvigbblfSft<E> fxtfnds CifdkfdSortfdSft<E>
        implfmfnts NbvigbblfSft<E>, Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = -5429120189805438922L;

        privbtf finbl NbvigbblfSft<E> ns;

        CifdkfdNbvigbblfSft(NbvigbblfSft<E> s, Clbss<E> typf) {
            supfr(s, typf);
            ns = s;
        }

        publid E lowfr(E f)                             { rfturn ns.lowfr(f); }
        publid E floor(E f)                             { rfturn ns.floor(f); }
        publid E dfiling(E f)                         { rfturn ns.dfiling(f); }
        publid E iigifr(E f)                           { rfturn ns.iigifr(f); }
        publid E pollFirst()                         { rfturn ns.pollFirst(); }
        publid E pollLbst()                            {rfturn ns.pollLbst(); }
        publid NbvigbblfSft<E> dfsdfndingSft()
                      { rfturn difdkfdNbvigbblfSft(ns.dfsdfndingSft(), typf); }
        publid Itfrbtor<E> dfsdfndingItfrbtor()
            {rfturn difdkfdNbvigbblfSft(ns.dfsdfndingSft(), typf).itfrbtor(); }

        publid NbvigbblfSft<E> subSft(E fromElfmfnt, E toElfmfnt) {
            rfturn difdkfdNbvigbblfSft(ns.subSft(fromElfmfnt, truf, toElfmfnt, fblsf), typf);
        }
        publid NbvigbblfSft<E> ifbdSft(E toElfmfnt) {
            rfturn difdkfdNbvigbblfSft(ns.ifbdSft(toElfmfnt, fblsf), typf);
        }
        publid NbvigbblfSft<E> tbilSft(E fromElfmfnt) {
            rfturn difdkfdNbvigbblfSft(ns.tbilSft(fromElfmfnt, truf), typf);
        }

        publid NbvigbblfSft<E> subSft(E fromElfmfnt, boolfbn fromIndlusivf, E toElfmfnt, boolfbn toIndlusivf) {
            rfturn difdkfdNbvigbblfSft(ns.subSft(fromElfmfnt, fromIndlusivf, toElfmfnt, toIndlusivf), typf);
        }

        publid NbvigbblfSft<E> ifbdSft(E toElfmfnt, boolfbn indlusivf) {
            rfturn difdkfdNbvigbblfSft(ns.ifbdSft(toElfmfnt, indlusivf), typf);
        }

        publid NbvigbblfSft<E> tbilSft(E fromElfmfnt, boolfbn indlusivf) {
            rfturn difdkfdNbvigbblfSft(ns.tbilSft(fromElfmfnt, indlusivf), typf);
        }
    }

    /**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd list.
     * Any bttfmpt to insfrt bn flfmfnt of tif wrong typf will rfsult in
     * bn immfdibtf {@link ClbssCbstExdfption}.  Assuming b list dontbins
     * no indorrfdtly typfd flfmfnts prior to tif timf b dynbmidblly typfsbff
     * vifw is gfnfrbtfd, bnd tibt bll subsfqufnt bddfss to tif list
     * tbkfs plbdf tirougi tif vifw, it is <i>gubrbntffd</i> tibt tif
     * list dbnnot dontbin bn indorrfdtly typfd flfmfnt.
     *
     * <p>A disdussion of tif usf of dynbmidblly typfsbff vifws mby bf
     * found in tif dodumfntbtion for tif {@link #difdkfdCollfdtion
     * difdkfdCollfdtion} mftiod.
     *
     * <p>Tif rfturnfd list will bf sfriblizbblf if tif spfdififd list
     * is sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd list pfrmits insfrtion of null flfmfnts wifnfvfr
     * tif bbdking list dofs.
     *
     * @pbrbm <E> tif dlbss of tif objfdts in tif list
     * @pbrbm list tif list for wiidi b dynbmidblly typfsbff vifw is to bf
     *             rfturnfd
     * @pbrbm typf tif typf of flfmfnt tibt {@dodf list} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd list
     * @sindf 1.5
     */
    publid stbtid <E> List<E> difdkfdList(List<E> list, Clbss<E> typf) {
        rfturn (list instbndfof RbndomAddfss ?
                nfw CifdkfdRbndomAddfssList<>(list, typf) :
                nfw CifdkfdList<>(list, typf));
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdList<E>
        fxtfnds CifdkfdCollfdtion<E>
        implfmfnts List<E>
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 65247728283967356L;
        finbl List<E> list;

        CifdkfdList(List<E> list, Clbss<E> typf) {
            supfr(list, typf);
            tiis.list = list;
        }

        publid boolfbn fqubls(Objfdt o)  { rfturn o == tiis || list.fqubls(o); }
        publid int ibsiCodf()            { rfturn list.ibsiCodf(); }
        publid E gft(int indfx)          { rfturn list.gft(indfx); }
        publid E rfmovf(int indfx)       { rfturn list.rfmovf(indfx); }
        publid int indfxOf(Objfdt o)     { rfturn list.indfxOf(o); }
        publid int lbstIndfxOf(Objfdt o) { rfturn list.lbstIndfxOf(o); }

        publid E sft(int indfx, E flfmfnt) {
            rfturn list.sft(indfx, typfCifdk(flfmfnt));
        }

        publid void bdd(int indfx, E flfmfnt) {
            list.bdd(indfx, typfCifdk(flfmfnt));
        }

        publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
            rfturn list.bddAll(indfx, difdkfdCopyOf(d));
        }
        publid ListItfrbtor<E> listItfrbtor()   { rfturn listItfrbtor(0); }

        publid ListItfrbtor<E> listItfrbtor(finbl int indfx) {
            finbl ListItfrbtor<E> i = list.listItfrbtor(indfx);

            rfturn nfw ListItfrbtor<E>() {
                publid boolfbn ibsNfxt()     { rfturn i.ibsNfxt(); }
                publid E nfxt()              { rfturn i.nfxt(); }
                publid boolfbn ibsPrfvious() { rfturn i.ibsPrfvious(); }
                publid E prfvious()          { rfturn i.prfvious(); }
                publid int nfxtIndfx()       { rfturn i.nfxtIndfx(); }
                publid int prfviousIndfx()   { rfturn i.prfviousIndfx(); }
                publid void rfmovf()         {        i.rfmovf(); }

                publid void sft(E f) {
                    i.sft(typfCifdk(f));
                }

                publid void bdd(E f) {
                    i.bdd(typfCifdk(f));
                }

                @Ovfrridf
                publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
                    i.forEbdiRfmbining(bdtion);
                }
            };
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            rfturn nfw CifdkfdList<>(list.subList(fromIndfx, toIndfx), typf);
        }

        /**
         * {@inifritDod}
         *
         * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt rfturnfd by tif
         *         opfrbtor prfvfnts it from bfing bddfd to tiis dollfdtion. Tif
         *         fxdfption mby bf tirown bftfr somf flfmfnts of tif list ibvf
         *         blrfbdy bffn rfplbdfd.
         */
        @Ovfrridf
        publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
            Objfdts.rfquirfNonNull(opfrbtor);
            list.rfplbdfAll(f -> typfCifdk(opfrbtor.bpply(f)));
        }

        @Ovfrridf
        publid void sort(Compbrbtor<? supfr E> d) {
            list.sort(d);
        }
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdRbndomAddfssList<E> fxtfnds CifdkfdList<E>
                                            implfmfnts RbndomAddfss
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 1638200125423088369L;

        CifdkfdRbndomAddfssList(List<E> list, Clbss<E> typf) {
            supfr(list, typf);
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            rfturn nfw CifdkfdRbndomAddfssList<>(
                    list.subList(fromIndfx, toIndfx), typf);
        }
    }

    /**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd mbp.
     * Any bttfmpt to insfrt b mbpping wiosf kfy or vbluf ibvf tif wrong
     * typf will rfsult in bn immfdibtf {@link ClbssCbstExdfption}.
     * Similbrly, bny bttfmpt to modify tif vbluf durrfntly bssodibtfd witi
     * b kfy will rfsult in bn immfdibtf {@link ClbssCbstExdfption},
     * wiftifr tif modifidbtion is bttfmptfd dirfdtly tirougi tif mbp
     * itsflf, or tirougi b {@link Mbp.Entry} instbndf obtbinfd from tif
     * mbp's {@link Mbp#fntrySft() fntry sft} vifw.
     *
     * <p>Assuming b mbp dontbins no indorrfdtly typfd kfys or vblufs
     * prior to tif timf b dynbmidblly typfsbff vifw is gfnfrbtfd, bnd
     * tibt bll subsfqufnt bddfss to tif mbp tbkfs plbdf tirougi tif vifw
     * (or onf of its dollfdtion vifws), it is <i>gubrbntffd</i> tibt tif
     * mbp dbnnot dontbin bn indorrfdtly typfd kfy or vbluf.
     *
     * <p>A disdussion of tif usf of dynbmidblly typfsbff vifws mby bf
     * found in tif dodumfntbtion for tif {@link #difdkfdCollfdtion
     * difdkfdCollfdtion} mftiod.
     *
     * <p>Tif rfturnfd mbp will bf sfriblizbblf if tif spfdififd mbp is
     * sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd mbp pfrmits insfrtion of null kfys or vblufs
     * wifnfvfr tif bbdking mbp dofs.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm m tif mbp for wiidi b dynbmidblly typfsbff vifw is to bf
     *          rfturnfd
     * @pbrbm kfyTypf tif typf of kfy tibt {@dodf m} is pfrmittfd to iold
     * @pbrbm vblufTypf tif typf of vbluf tibt {@dodf m} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd mbp
     * @sindf 1.5
     */
    publid stbtid <K, V> Mbp<K, V> difdkfdMbp(Mbp<K, V> m,
                                              Clbss<K> kfyTypf,
                                              Clbss<V> vblufTypf) {
        rfturn nfw CifdkfdMbp<>(m, kfyTypf, vblufTypf);
    }


    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss CifdkfdMbp<K,V>
        implfmfnts Mbp<K,V>, Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 5742860141034234728L;

        privbtf finbl Mbp<K, V> m;
        finbl Clbss<K> kfyTypf;
        finbl Clbss<V> vblufTypf;

        privbtf void typfCifdk(Objfdt kfy, Objfdt vbluf) {
            if (kfy != null && !kfyTypf.isInstbndf(kfy))
                tirow nfw ClbssCbstExdfption(bbdKfyMsg(kfy));

            if (vbluf != null && !vblufTypf.isInstbndf(vbluf))
                tirow nfw ClbssCbstExdfption(bbdVblufMsg(vbluf));
        }

        privbtf BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> typfCifdk(
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fund) {
            Objfdts.rfquirfNonNull(fund);
            rfturn (k, v) -> {
                V nfwVbluf = fund.bpply(k, v);
                typfCifdk(k, nfwVbluf);
                rfturn nfwVbluf;
            };
        }

        privbtf String bbdKfyMsg(Objfdt kfy) {
            rfturn "Attfmpt to insfrt " + kfy.gftClbss() +
                    " kfy into mbp witi kfy typf " + kfyTypf;
        }

        privbtf String bbdVblufMsg(Objfdt vbluf) {
            rfturn "Attfmpt to insfrt " + vbluf.gftClbss() +
                    " vbluf into mbp witi vbluf typf " + vblufTypf;
        }

        CifdkfdMbp(Mbp<K, V> m, Clbss<K> kfyTypf, Clbss<V> vblufTypf) {
            tiis.m = Objfdts.rfquirfNonNull(m);
            tiis.kfyTypf = Objfdts.rfquirfNonNull(kfyTypf);
            tiis.vblufTypf = Objfdts.rfquirfNonNull(vblufTypf);
        }

        publid int sizf()                      { rfturn m.sizf(); }
        publid boolfbn isEmpty()               { rfturn m.isEmpty(); }
        publid boolfbn dontbinsKfy(Objfdt kfy) { rfturn m.dontbinsKfy(kfy); }
        publid boolfbn dontbinsVbluf(Objfdt v) { rfturn m.dontbinsVbluf(v); }
        publid V gft(Objfdt kfy)               { rfturn m.gft(kfy); }
        publid V rfmovf(Objfdt kfy)            { rfturn m.rfmovf(kfy); }
        publid void dlfbr()                    { m.dlfbr(); }
        publid Sft<K> kfySft()                 { rfturn m.kfySft(); }
        publid Collfdtion<V> vblufs()          { rfturn m.vblufs(); }
        publid boolfbn fqubls(Objfdt o)        { rfturn o == tiis || m.fqubls(o); }
        publid int ibsiCodf()                  { rfturn m.ibsiCodf(); }
        publid String toString()               { rfturn m.toString(); }

        publid V put(K kfy, V vbluf) {
            typfCifdk(kfy, vbluf);
            rfturn m.put(kfy, vbluf);
        }

        @SupprfssWbrnings("undifdkfd")
        publid void putAll(Mbp<? fxtfnds K, ? fxtfnds V> t) {
            // Sbtisfy tif following gobls:
            // - good dibgnostids in dbsf of typf mismbtdi
            // - bll-or-notiing sfmbntids
            // - protfdtion from mblidious t
            // - dorrfdt bfibvior if t is b dondurrfnt mbp
            Objfdt[] fntrifs = t.fntrySft().toArrby();
            List<Mbp.Entry<K,V>> difdkfd = nfw ArrbyList<>(fntrifs.lfngti);
            for (Objfdt o : fntrifs) {
                Mbp.Entry<?,?> f = (Mbp.Entry<?,?>) o;
                Objfdt k = f.gftKfy();
                Objfdt v = f.gftVbluf();
                typfCifdk(k, v);
                difdkfd.bdd(
                        nfw AbstrbdtMbp.SimplfImmutbblfEntry<>((K)k, (V)v));
            }
            for (Mbp.Entry<K,V> f : difdkfd)
                m.put(f.gftKfy(), f.gftVbluf());
        }

        privbtf trbnsifnt Sft<Mbp.Entry<K,V>> fntrySft;

        publid Sft<Mbp.Entry<K,V>> fntrySft() {
            if (fntrySft==null)
                fntrySft = nfw CifdkfdEntrySft<>(m.fntrySft(), vblufTypf);
            rfturn fntrySft;
        }

        // Ovfrridf dffbult mftiods in Mbp
        @Ovfrridf
        publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
            m.forEbdi(bdtion);
        }

        @Ovfrridf
        publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
            m.rfplbdfAll(typfCifdk(fundtion));
        }

        @Ovfrridf
        publid V putIfAbsfnt(K kfy, V vbluf) {
            typfCifdk(kfy, vbluf);
            rfturn m.putIfAbsfnt(kfy, vbluf);
        }

        @Ovfrridf
        publid boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
            rfturn m.rfmovf(kfy, vbluf);
        }

        @Ovfrridf
        publid boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
            typfCifdk(kfy, nfwVbluf);
            rfturn m.rfplbdf(kfy, oldVbluf, nfwVbluf);
        }

        @Ovfrridf
        publid V rfplbdf(K kfy, V vbluf) {
            typfCifdk(kfy, vbluf);
            rfturn m.rfplbdf(kfy, vbluf);
        }

        @Ovfrridf
        publid V domputfIfAbsfnt(K kfy,
                Fundtion<? supfr K, ? fxtfnds V> mbppingFundtion) {
            Objfdts.rfquirfNonNull(mbppingFundtion);
            rfturn m.domputfIfAbsfnt(kfy, k -> {
                V vbluf = mbppingFundtion.bpply(k);
                typfCifdk(k, vbluf);
                rfturn vbluf;
            });
        }

        @Ovfrridf
        publid V domputfIfPrfsfnt(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            rfturn m.domputfIfPrfsfnt(kfy, typfCifdk(rfmbppingFundtion));
        }

        @Ovfrridf
        publid V domputf(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            rfturn m.domputf(kfy, typfCifdk(rfmbppingFundtion));
        }

        @Ovfrridf
        publid V mfrgf(K kfy, V vbluf,
                BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            Objfdts.rfquirfNonNull(rfmbppingFundtion);
            rfturn m.mfrgf(kfy, vbluf, (v1, v2) -> {
                V nfwVbluf = rfmbppingFundtion.bpply(v1, v2);
                typfCifdk(null, nfwVbluf);
                rfturn nfwVbluf;
            });
        }

        /**
         * Wf nffd tiis dlbss in bddition to CifdkfdSft bs Mbp.Entry pfrmits
         * modifidbtion of tif bbdking Mbp vib tif sftVbluf opfrbtion.  Tiis
         * dlbss is subtlf: tifrf brf mbny possiblf bttbdks tibt must bf
         * tiwbrtfd.
         *
         * @sfribl fxdludf
         */
        stbtid dlbss CifdkfdEntrySft<K,V> implfmfnts Sft<Mbp.Entry<K,V>> {
            privbtf finbl Sft<Mbp.Entry<K,V>> s;
            privbtf finbl Clbss<V> vblufTypf;

            CifdkfdEntrySft(Sft<Mbp.Entry<K, V>> s, Clbss<V> vblufTypf) {
                tiis.s = s;
                tiis.vblufTypf = vblufTypf;
            }

            publid int sizf()        { rfturn s.sizf(); }
            publid boolfbn isEmpty() { rfturn s.isEmpty(); }
            publid String toString() { rfturn s.toString(); }
            publid int ibsiCodf()    { rfturn s.ibsiCodf(); }
            publid void dlfbr()      {        s.dlfbr(); }

            publid boolfbn bdd(Mbp.Entry<K, V> f) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn bddAll(Collfdtion<? fxtfnds Mbp.Entry<K, V>> doll) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }

            publid Itfrbtor<Mbp.Entry<K,V>> itfrbtor() {
                finbl Itfrbtor<Mbp.Entry<K, V>> i = s.itfrbtor();
                finbl Clbss<V> vblufTypf = tiis.vblufTypf;

                rfturn nfw Itfrbtor<Mbp.Entry<K,V>>() {
                    publid boolfbn ibsNfxt() { rfturn i.ibsNfxt(); }
                    publid void rfmovf()     { i.rfmovf(); }

                    publid Mbp.Entry<K,V> nfxt() {
                        rfturn difdkfdEntry(i.nfxt(), vblufTypf);
                    }
                };
            }

            @SupprfssWbrnings("undifdkfd")
            publid Objfdt[] toArrby() {
                Objfdt[] sourdf = s.toArrby();

                /*
                 * Ensurf tibt wf don't gft bn ArrbyStorfExdfption fvfn if
                 * s.toArrby rfturns bn brrby of somftiing otifr tibn Objfdt
                 */
                Objfdt[] dfst = (CifdkfdEntry.dlbss.isInstbndf(
                    sourdf.gftClbss().gftComponfntTypf()) ? sourdf :
                                 nfw Objfdt[sourdf.lfngti]);

                for (int i = 0; i < sourdf.lfngti; i++)
                    dfst[i] = difdkfdEntry((Mbp.Entry<K,V>)sourdf[i],
                                           vblufTypf);
                rfturn dfst;
            }

            @SupprfssWbrnings("undifdkfd")
            publid <T> T[] toArrby(T[] b) {
                // Wf don't pbss b to s.toArrby, to bvoid window of
                // vulnfrbbility wifrfin bn unsdrupulous multitirfbdfd dlifnt
                // dould gft iis ibnds on rbw (unwrbppfd) Entrifs from s.
                T[] brr = s.toArrby(b.lfngti==0 ? b : Arrbys.dopyOf(b, 0));

                for (int i=0; i<brr.lfngti; i++)
                    brr[i] = (T) difdkfdEntry((Mbp.Entry<K,V>)brr[i],
                                              vblufTypf);
                if (brr.lfngti > b.lfngti)
                    rfturn brr;

                Systfm.brrbydopy(brr, 0, b, 0, brr.lfngti);
                if (b.lfngti > brr.lfngti)
                    b[brr.lfngti] = null;
                rfturn b;
            }

            /**
             * Tiis mftiod is ovfrriddfn to protfdt tif bbdking sft bgbinst
             * bn objfdt witi b nffbrious fqubls fundtion tibt sfnsfs
             * tibt tif fqublity-dbndidbtf is Mbp.Entry bnd dblls its
             * sftVbluf mftiod.
             */
            publid boolfbn dontbins(Objfdt o) {
                if (!(o instbndfof Mbp.Entry))
                    rfturn fblsf;
                Mbp.Entry<?,?> f = (Mbp.Entry<?,?>) o;
                rfturn s.dontbins(
                    (f instbndfof CifdkfdEntry) ? f : difdkfdEntry(f, vblufTypf));
            }

            /**
             * Tif bulk dollfdtion mftiods brf ovfrriddfn to protfdt
             * bgbinst bn unsdrupulous dollfdtion wiosf dontbins(Objfdt o)
             * mftiod sfnsfs wifn o is b Mbp.Entry, bnd dblls o.sftVbluf.
             */
            publid boolfbn dontbinsAll(Collfdtion<?> d) {
                for (Objfdt o : d)
                    if (!dontbins(o)) // Invokfs sbff dontbins() bbovf
                        rfturn fblsf;
                rfturn truf;
            }

            publid boolfbn rfmovf(Objfdt o) {
                if (!(o instbndfof Mbp.Entry))
                    rfturn fblsf;
                rfturn s.rfmovf(nfw AbstrbdtMbp.SimplfImmutbblfEntry
                                <>((Mbp.Entry<?,?>)o));
            }

            publid boolfbn rfmovfAll(Collfdtion<?> d) {
                rfturn bbtdiRfmovf(d, fblsf);
            }
            publid boolfbn rftbinAll(Collfdtion<?> d) {
                rfturn bbtdiRfmovf(d, truf);
            }
            privbtf boolfbn bbtdiRfmovf(Collfdtion<?> d, boolfbn domplfmfnt) {
                Objfdts.rfquirfNonNull(d);
                boolfbn modififd = fblsf;
                Itfrbtor<Mbp.Entry<K,V>> it = itfrbtor();
                wiilf (it.ibsNfxt()) {
                    if (d.dontbins(it.nfxt()) != domplfmfnt) {
                        it.rfmovf();
                        modififd = truf;
                    }
                }
                rfturn modififd;
            }

            publid boolfbn fqubls(Objfdt o) {
                if (o == tiis)
                    rfturn truf;
                if (!(o instbndfof Sft))
                    rfturn fblsf;
                Sft<?> tibt = (Sft<?>) o;
                rfturn tibt.sizf() == s.sizf()
                    && dontbinsAll(tibt); // Invokfs sbff dontbinsAll() bbovf
            }

            stbtid <K,V,T> CifdkfdEntry<K,V,T> difdkfdEntry(Mbp.Entry<K,V> f,
                                                            Clbss<T> vblufTypf) {
                rfturn nfw CifdkfdEntry<>(f, vblufTypf);
            }

            /**
             * Tiis "wrbppfr dlbss" sfrvfs two purposfs: it prfvfnts
             * tif dlifnt from modifying tif bbdking Mbp, by siort-dirduiting
             * tif sftVbluf mftiod, bnd it protfdts tif bbdking Mbp bgbinst
             * bn ill-bfibvfd Mbp.Entry tibt bttfmpts to modify bnotifr
             * Mbp.Entry wifn bskfd to pfrform bn fqublity difdk.
             */
            privbtf stbtid dlbss CifdkfdEntry<K,V,T> implfmfnts Mbp.Entry<K,V> {
                privbtf finbl Mbp.Entry<K, V> f;
                privbtf finbl Clbss<T> vblufTypf;

                CifdkfdEntry(Mbp.Entry<K, V> f, Clbss<T> vblufTypf) {
                    tiis.f = Objfdts.rfquirfNonNull(f);
                    tiis.vblufTypf = Objfdts.rfquirfNonNull(vblufTypf);
                }

                publid K gftKfy()        { rfturn f.gftKfy(); }
                publid V gftVbluf()      { rfturn f.gftVbluf(); }
                publid int ibsiCodf()    { rfturn f.ibsiCodf(); }
                publid String toString() { rfturn f.toString(); }

                publid V sftVbluf(V vbluf) {
                    if (vbluf != null && !vblufTypf.isInstbndf(vbluf))
                        tirow nfw ClbssCbstExdfption(bbdVblufMsg(vbluf));
                    rfturn f.sftVbluf(vbluf);
                }

                privbtf String bbdVblufMsg(Objfdt vbluf) {
                    rfturn "Attfmpt to insfrt " + vbluf.gftClbss() +
                        " vbluf into mbp witi vbluf typf " + vblufTypf;
                }

                publid boolfbn fqubls(Objfdt o) {
                    if (o == tiis)
                        rfturn truf;
                    if (!(o instbndfof Mbp.Entry))
                        rfturn fblsf;
                    rfturn f.fqubls(nfw AbstrbdtMbp.SimplfImmutbblfEntry
                                    <>((Mbp.Entry<?,?>)o));
                }
            }
        }
    }

    /**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd sortfd mbp.
     * Any bttfmpt to insfrt b mbpping wiosf kfy or vbluf ibvf tif wrong
     * typf will rfsult in bn immfdibtf {@link ClbssCbstExdfption}.
     * Similbrly, bny bttfmpt to modify tif vbluf durrfntly bssodibtfd witi
     * b kfy will rfsult in bn immfdibtf {@link ClbssCbstExdfption},
     * wiftifr tif modifidbtion is bttfmptfd dirfdtly tirougi tif mbp
     * itsflf, or tirougi b {@link Mbp.Entry} instbndf obtbinfd from tif
     * mbp's {@link Mbp#fntrySft() fntry sft} vifw.
     *
     * <p>Assuming b mbp dontbins no indorrfdtly typfd kfys or vblufs
     * prior to tif timf b dynbmidblly typfsbff vifw is gfnfrbtfd, bnd
     * tibt bll subsfqufnt bddfss to tif mbp tbkfs plbdf tirougi tif vifw
     * (or onf of its dollfdtion vifws), it is <i>gubrbntffd</i> tibt tif
     * mbp dbnnot dontbin bn indorrfdtly typfd kfy or vbluf.
     *
     * <p>A disdussion of tif usf of dynbmidblly typfsbff vifws mby bf
     * found in tif dodumfntbtion for tif {@link #difdkfdCollfdtion
     * difdkfdCollfdtion} mftiod.
     *
     * <p>Tif rfturnfd mbp will bf sfriblizbblf if tif spfdififd mbp is
     * sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd mbp pfrmits insfrtion of null kfys or vblufs
     * wifnfvfr tif bbdking mbp dofs.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm m tif mbp for wiidi b dynbmidblly typfsbff vifw is to bf
     *          rfturnfd
     * @pbrbm kfyTypf tif typf of kfy tibt {@dodf m} is pfrmittfd to iold
     * @pbrbm vblufTypf tif typf of vbluf tibt {@dodf m} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd mbp
     * @sindf 1.5
     */
    publid stbtid <K,V> SortfdMbp<K,V> difdkfdSortfdMbp(SortfdMbp<K, V> m,
                                                        Clbss<K> kfyTypf,
                                                        Clbss<V> vblufTypf) {
        rfturn nfw CifdkfdSortfdMbp<>(m, kfyTypf, vblufTypf);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdSortfdMbp<K,V> fxtfnds CifdkfdMbp<K,V>
        implfmfnts SortfdMbp<K,V>, Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 1599671320688067438L;

        privbtf finbl SortfdMbp<K, V> sm;

        CifdkfdSortfdMbp(SortfdMbp<K, V> m,
                         Clbss<K> kfyTypf, Clbss<V> vblufTypf) {
            supfr(m, kfyTypf, vblufTypf);
            sm = m;
        }

        publid Compbrbtor<? supfr K> dompbrbtor() { rfturn sm.dompbrbtor(); }
        publid K firstKfy()                       { rfturn sm.firstKfy(); }
        publid K lbstKfy()                        { rfturn sm.lbstKfy(); }

        publid SortfdMbp<K,V> subMbp(K fromKfy, K toKfy) {
            rfturn difdkfdSortfdMbp(sm.subMbp(fromKfy, toKfy),
                                    kfyTypf, vblufTypf);
        }
        publid SortfdMbp<K,V> ifbdMbp(K toKfy) {
            rfturn difdkfdSortfdMbp(sm.ifbdMbp(toKfy), kfyTypf, vblufTypf);
        }
        publid SortfdMbp<K,V> tbilMbp(K fromKfy) {
            rfturn difdkfdSortfdMbp(sm.tbilMbp(fromKfy), kfyTypf, vblufTypf);
        }
    }

    /**
     * Rfturns b dynbmidblly typfsbff vifw of tif spfdififd nbvigbblf mbp.
     * Any bttfmpt to insfrt b mbpping wiosf kfy or vbluf ibvf tif wrong
     * typf will rfsult in bn immfdibtf {@link ClbssCbstExdfption}.
     * Similbrly, bny bttfmpt to modify tif vbluf durrfntly bssodibtfd witi
     * b kfy will rfsult in bn immfdibtf {@link ClbssCbstExdfption},
     * wiftifr tif modifidbtion is bttfmptfd dirfdtly tirougi tif mbp
     * itsflf, or tirougi b {@link Mbp.Entry} instbndf obtbinfd from tif
     * mbp's {@link Mbp#fntrySft() fntry sft} vifw.
     *
     * <p>Assuming b mbp dontbins no indorrfdtly typfd kfys or vblufs
     * prior to tif timf b dynbmidblly typfsbff vifw is gfnfrbtfd, bnd
     * tibt bll subsfqufnt bddfss to tif mbp tbkfs plbdf tirougi tif vifw
     * (or onf of its dollfdtion vifws), it is <fm>gubrbntffd</fm> tibt tif
     * mbp dbnnot dontbin bn indorrfdtly typfd kfy or vbluf.
     *
     * <p>A disdussion of tif usf of dynbmidblly typfsbff vifws mby bf
     * found in tif dodumfntbtion for tif {@link #difdkfdCollfdtion
     * difdkfdCollfdtion} mftiod.
     *
     * <p>Tif rfturnfd mbp will bf sfriblizbblf if tif spfdififd mbp is
     * sfriblizbblf.
     *
     * <p>Sindf {@dodf null} is donsidfrfd to bf b vbluf of bny rfffrfndf
     * typf, tif rfturnfd mbp pfrmits insfrtion of null kfys or vblufs
     * wifnfvfr tif bbdking mbp dofs.
     *
     * @pbrbm <K> typf of mbp kfys
     * @pbrbm <V> typf of mbp vblufs
     * @pbrbm m tif mbp for wiidi b dynbmidblly typfsbff vifw is to bf
     *          rfturnfd
     * @pbrbm kfyTypf tif typf of kfy tibt {@dodf m} is pfrmittfd to iold
     * @pbrbm vblufTypf tif typf of vbluf tibt {@dodf m} is pfrmittfd to iold
     * @rfturn b dynbmidblly typfsbff vifw of tif spfdififd mbp
     * @sindf 1.8
     */
    publid stbtid <K,V> NbvigbblfMbp<K,V> difdkfdNbvigbblfMbp(NbvigbblfMbp<K, V> m,
                                                        Clbss<K> kfyTypf,
                                                        Clbss<V> vblufTypf) {
        rfturn nfw CifdkfdNbvigbblfMbp<>(m, kfyTypf, vblufTypf);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss CifdkfdNbvigbblfMbp<K,V> fxtfnds CifdkfdSortfdMbp<K,V>
        implfmfnts NbvigbblfMbp<K,V>, Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = -4852462692372534096L;

        privbtf finbl NbvigbblfMbp<K, V> nm;

        CifdkfdNbvigbblfMbp(NbvigbblfMbp<K, V> m,
                         Clbss<K> kfyTypf, Clbss<V> vblufTypf) {
            supfr(m, kfyTypf, vblufTypf);
            nm = m;
        }

        publid Compbrbtor<? supfr K> dompbrbtor()   { rfturn nm.dompbrbtor(); }
        publid K firstKfy()                           { rfturn nm.firstKfy(); }
        publid K lbstKfy()                             { rfturn nm.lbstKfy(); }

        publid Entry<K, V> lowfrEntry(K kfy) {
            Entry<K,V> lowfr = nm.lowfrEntry(kfy);
            rfturn (null != lowfr)
                ? nfw CifdkfdMbp.CifdkfdEntrySft.CifdkfdEntry<>(lowfr, vblufTypf)
                : null;
        }

        publid K lowfrKfy(K kfy)                   { rfturn nm.lowfrKfy(kfy); }

        publid Entry<K, V> floorEntry(K kfy) {
            Entry<K,V> floor = nm.floorEntry(kfy);
            rfturn (null != floor)
                ? nfw CifdkfdMbp.CifdkfdEntrySft.CifdkfdEntry<>(floor, vblufTypf)
                : null;
        }

        publid K floorKfy(K kfy)                   { rfturn nm.floorKfy(kfy); }

        publid Entry<K, V> dfilingEntry(K kfy) {
            Entry<K,V> dfiling = nm.dfilingEntry(kfy);
            rfturn (null != dfiling)
                ? nfw CifdkfdMbp.CifdkfdEntrySft.CifdkfdEntry<>(dfiling, vblufTypf)
                : null;
        }

        publid K dfilingKfy(K kfy)               { rfturn nm.dfilingKfy(kfy); }

        publid Entry<K, V> iigifrEntry(K kfy) {
            Entry<K,V> iigifr = nm.iigifrEntry(kfy);
            rfturn (null != iigifr)
                ? nfw CifdkfdMbp.CifdkfdEntrySft.CifdkfdEntry<>(iigifr, vblufTypf)
                : null;
        }

        publid K iigifrKfy(K kfy)                 { rfturn nm.iigifrKfy(kfy); }

        publid Entry<K, V> firstEntry() {
            Entry<K,V> first = nm.firstEntry();
            rfturn (null != first)
                ? nfw CifdkfdMbp.CifdkfdEntrySft.CifdkfdEntry<>(first, vblufTypf)
                : null;
        }

        publid Entry<K, V> lbstEntry() {
            Entry<K,V> lbst = nm.lbstEntry();
            rfturn (null != lbst)
                ? nfw CifdkfdMbp.CifdkfdEntrySft.CifdkfdEntry<>(lbst, vblufTypf)
                : null;
        }

        publid Entry<K, V> pollFirstEntry() {
            Entry<K,V> fntry = nm.pollFirstEntry();
            rfturn (null == fntry)
                ? null
                : nfw CifdkfdMbp.CifdkfdEntrySft.CifdkfdEntry<>(fntry, vblufTypf);
        }

        publid Entry<K, V> pollLbstEntry() {
            Entry<K,V> fntry = nm.pollLbstEntry();
            rfturn (null == fntry)
                ? null
                : nfw CifdkfdMbp.CifdkfdEntrySft.CifdkfdEntry<>(fntry, vblufTypf);
        }

        publid NbvigbblfMbp<K, V> dfsdfndingMbp() {
            rfturn difdkfdNbvigbblfMbp(nm.dfsdfndingMbp(), kfyTypf, vblufTypf);
        }

        publid NbvigbblfSft<K> kfySft() {
            rfturn nbvigbblfKfySft();
        }

        publid NbvigbblfSft<K> nbvigbblfKfySft() {
            rfturn difdkfdNbvigbblfSft(nm.nbvigbblfKfySft(), kfyTypf);
        }

        publid NbvigbblfSft<K> dfsdfndingKfySft() {
            rfturn difdkfdNbvigbblfSft(nm.dfsdfndingKfySft(), kfyTypf);
        }

        @Ovfrridf
        publid NbvigbblfMbp<K,V> subMbp(K fromKfy, K toKfy) {
            rfturn difdkfdNbvigbblfMbp(nm.subMbp(fromKfy, truf, toKfy, fblsf),
                                    kfyTypf, vblufTypf);
        }

        @Ovfrridf
        publid NbvigbblfMbp<K,V> ifbdMbp(K toKfy) {
            rfturn difdkfdNbvigbblfMbp(nm.ifbdMbp(toKfy, fblsf), kfyTypf, vblufTypf);
        }

        @Ovfrridf
        publid NbvigbblfMbp<K,V> tbilMbp(K fromKfy) {
            rfturn difdkfdNbvigbblfMbp(nm.tbilMbp(fromKfy, truf), kfyTypf, vblufTypf);
        }

        publid NbvigbblfMbp<K, V> subMbp(K fromKfy, boolfbn fromIndlusivf, K toKfy, boolfbn toIndlusivf) {
            rfturn difdkfdNbvigbblfMbp(nm.subMbp(fromKfy, fromIndlusivf, toKfy, toIndlusivf), kfyTypf, vblufTypf);
        }

        publid NbvigbblfMbp<K, V> ifbdMbp(K toKfy, boolfbn indlusivf) {
            rfturn difdkfdNbvigbblfMbp(nm.ifbdMbp(toKfy, indlusivf), kfyTypf, vblufTypf);
        }

        publid NbvigbblfMbp<K, V> tbilMbp(K fromKfy, boolfbn indlusivf) {
            rfturn difdkfdNbvigbblfMbp(nm.tbilMbp(fromKfy, indlusivf), kfyTypf, vblufTypf);
        }
    }

    // Empty dollfdtions

    /**
     * Rfturns bn itfrbtor tibt ibs no flfmfnts.  Morf prfdisfly,
     *
     * <ul>
     * <li>{@link Itfrbtor#ibsNfxt ibsNfxt} blwbys rfturns {@dodf
     * fblsf}.</li>
     * <li>{@link Itfrbtor#nfxt nfxt} blwbys tirows {@link
     * NoSudiElfmfntExdfption}.</li>
     * <li>{@link Itfrbtor#rfmovf rfmovf} blwbys tirows {@link
     * IllfgblStbtfExdfption}.</li>
     * </ul>
     *
     * <p>Implfmfntbtions of tiis mftiod brf pfrmittfd, but not
     * rfquirfd, to rfturn tif sbmf objfdt from multiplf invodbtions.
     *
     * @pbrbm <T> typf of flfmfnts, if tifrf wfrf bny, in tif itfrbtor
     * @rfturn bn fmpty itfrbtor
     * @sindf 1.7
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> Itfrbtor<T> fmptyItfrbtor() {
        rfturn (Itfrbtor<T>) EmptyItfrbtor.EMPTY_ITERATOR;
    }

    privbtf stbtid dlbss EmptyItfrbtor<E> implfmfnts Itfrbtor<E> {
        stbtid finbl EmptyItfrbtor<Objfdt> EMPTY_ITERATOR
            = nfw EmptyItfrbtor<>();

        publid boolfbn ibsNfxt() { rfturn fblsf; }
        publid E nfxt() { tirow nfw NoSudiElfmfntExdfption(); }
        publid void rfmovf() { tirow nfw IllfgblStbtfExdfption(); }
        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
        }
    }

    /**
     * Rfturns b list itfrbtor tibt ibs no flfmfnts.  Morf prfdisfly,
     *
     * <ul>
     * <li>{@link Itfrbtor#ibsNfxt ibsNfxt} bnd {@link
     * ListItfrbtor#ibsPrfvious ibsPrfvious} blwbys rfturn {@dodf
     * fblsf}.</li>
     * <li>{@link Itfrbtor#nfxt nfxt} bnd {@link ListItfrbtor#prfvious
     * prfvious} blwbys tirow {@link NoSudiElfmfntExdfption}.</li>
     * <li>{@link Itfrbtor#rfmovf rfmovf} bnd {@link ListItfrbtor#sft
     * sft} blwbys tirow {@link IllfgblStbtfExdfption}.</li>
     * <li>{@link ListItfrbtor#bdd bdd} blwbys tirows {@link
     * UnsupportfdOpfrbtionExdfption}.</li>
     * <li>{@link ListItfrbtor#nfxtIndfx nfxtIndfx} blwbys rfturns
     * {@dodf 0}.</li>
     * <li>{@link ListItfrbtor#prfviousIndfx prfviousIndfx} blwbys
     * rfturns {@dodf -1}.</li>
     * </ul>
     *
     * <p>Implfmfntbtions of tiis mftiod brf pfrmittfd, but not
     * rfquirfd, to rfturn tif sbmf objfdt from multiplf invodbtions.
     *
     * @pbrbm <T> typf of flfmfnts, if tifrf wfrf bny, in tif itfrbtor
     * @rfturn bn fmpty list itfrbtor
     * @sindf 1.7
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> ListItfrbtor<T> fmptyListItfrbtor() {
        rfturn (ListItfrbtor<T>) EmptyListItfrbtor.EMPTY_ITERATOR;
    }

    privbtf stbtid dlbss EmptyListItfrbtor<E>
        fxtfnds EmptyItfrbtor<E>
        implfmfnts ListItfrbtor<E>
    {
        stbtid finbl EmptyListItfrbtor<Objfdt> EMPTY_ITERATOR
            = nfw EmptyListItfrbtor<>();

        publid boolfbn ibsPrfvious() { rfturn fblsf; }
        publid E prfvious() { tirow nfw NoSudiElfmfntExdfption(); }
        publid int nfxtIndfx()     { rfturn 0; }
        publid int prfviousIndfx() { rfturn -1; }
        publid void sft(E f) { tirow nfw IllfgblStbtfExdfption(); }
        publid void bdd(E f) { tirow nfw UnsupportfdOpfrbtionExdfption(); }
    }

    /**
     * Rfturns bn fnumfrbtion tibt ibs no flfmfnts.  Morf prfdisfly,
     *
     * <ul>
     * <li>{@link Enumfrbtion#ibsMorfElfmfnts ibsMorfElfmfnts} blwbys
     * rfturns {@dodf fblsf}.</li>
     * <li> {@link Enumfrbtion#nfxtElfmfnt nfxtElfmfnt} blwbys tirows
     * {@link NoSudiElfmfntExdfption}.</li>
     * </ul>
     *
     * <p>Implfmfntbtions of tiis mftiod brf pfrmittfd, but not
     * rfquirfd, to rfturn tif sbmf objfdt from multiplf invodbtions.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif fnumfrbtion
     * @rfturn bn fmpty fnumfrbtion
     * @sindf 1.7
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> Enumfrbtion<T> fmptyEnumfrbtion() {
        rfturn (Enumfrbtion<T>) EmptyEnumfrbtion.EMPTY_ENUMERATION;
    }

    privbtf stbtid dlbss EmptyEnumfrbtion<E> implfmfnts Enumfrbtion<E> {
        stbtid finbl EmptyEnumfrbtion<Objfdt> EMPTY_ENUMERATION
            = nfw EmptyEnumfrbtion<>();

        publid boolfbn ibsMorfElfmfnts() { rfturn fblsf; }
        publid E nfxtElfmfnt() { tirow nfw NoSudiElfmfntExdfption(); }
    }

    /**
     * Tif fmpty sft (immutbblf).  Tiis sft is sfriblizbblf.
     *
     * @sff #fmptySft()
     */
    @SupprfssWbrnings("rbwtypfs")
    publid stbtid finbl Sft EMPTY_SET = nfw EmptySft<>();

    /**
     * Rfturns bn fmpty sft (immutbblf).  Tiis sft is sfriblizbblf.
     * Unlikf tif likf-nbmfd fifld, tiis mftiod is pbrbmftfrizfd.
     *
     * <p>Tiis fxbmplf illustrbtfs tif typf-sbff wby to obtbin bn fmpty sft:
     * <prf>
     *     Sft&lt;String&gt; s = Collfdtions.fmptySft();
     * </prf>
     * @implNotf Implfmfntbtions of tiis mftiod nffd not drfbtf b sfpbrbtf
     * {@dodf Sft} objfdt for fbdi dbll.  Using tiis mftiod is likfly to ibvf
     * dompbrbblf dost to using tif likf-nbmfd fifld.  (Unlikf tiis mftiod, tif
     * fifld dofs not providf typf sbffty.)
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif sft
     * @rfturn tif fmpty sft
     *
     * @sff #EMPTY_SET
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl <T> Sft<T> fmptySft() {
        rfturn (Sft<T>) EMPTY_SET;
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss EmptySft<E>
        fxtfnds AbstrbdtSft<E>
        implfmfnts Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 1582296315990362920L;

        publid Itfrbtor<E> itfrbtor() { rfturn fmptyItfrbtor(); }

        publid int sizf() {rfturn 0;}
        publid boolfbn isEmpty() {rfturn truf;}

        publid boolfbn dontbins(Objfdt obj) {rfturn fblsf;}
        publid boolfbn dontbinsAll(Collfdtion<?> d) { rfturn d.isEmpty(); }

        publid Objfdt[] toArrby() { rfturn nfw Objfdt[0]; }

        publid <T> T[] toArrby(T[] b) {
            if (b.lfngti > 0)
                b[0] = null;
            rfturn b;
        }

        // Ovfrridf dffbult mftiods in Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
        }
        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            Objfdts.rfquirfNonNull(filtfr);
            rfturn fblsf;
        }
        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() { rfturn Splitfrbtors.fmptySplitfrbtor(); }

        // Prfsfrvfs singlfton propfrty
        privbtf Objfdt rfbdRfsolvf() {
            rfturn EMPTY_SET;
        }
    }

    /**
     * Rfturns bn fmpty sortfd sft (immutbblf).  Tiis sft is sfriblizbblf.
     *
     * <p>Tiis fxbmplf illustrbtfs tif typf-sbff wby to obtbin bn fmpty
     * sortfd sft:
     * <prf> {@dodf
     *     SortfdSft<String> s = Collfdtions.fmptySortfdSft();
     * }</prf>
     *
     * @implNotf Implfmfntbtions of tiis mftiod nffd not drfbtf b sfpbrbtf
     * {@dodf SortfdSft} objfdt for fbdi dbll.
     *
     * @pbrbm <E> typf of flfmfnts, if tifrf wfrf bny, in tif sft
     * @rfturn tif fmpty sortfd sft
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <E> SortfdSft<E> fmptySortfdSft() {
        rfturn (SortfdSft<E>) UnmodifibblfNbvigbblfSft.EMPTY_NAVIGABLE_SET;
    }

    /**
     * Rfturns bn fmpty nbvigbblf sft (immutbblf).  Tiis sft is sfriblizbblf.
     *
     * <p>Tiis fxbmplf illustrbtfs tif typf-sbff wby to obtbin bn fmpty
     * nbvigbblf sft:
     * <prf> {@dodf
     *     NbvigbblfSft<String> s = Collfdtions.fmptyNbvigbblfSft();
     * }</prf>
     *
     * @implNotf Implfmfntbtions of tiis mftiod nffd not
     * drfbtf b sfpbrbtf {@dodf NbvigbblfSft} objfdt for fbdi dbll.
     *
     * @pbrbm <E> typf of flfmfnts, if tifrf wfrf bny, in tif sft
     * @rfturn tif fmpty nbvigbblf sft
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <E> NbvigbblfSft<E> fmptyNbvigbblfSft() {
        rfturn (NbvigbblfSft<E>) UnmodifibblfNbvigbblfSft.EMPTY_NAVIGABLE_SET;
    }

    /**
     * Tif fmpty list (immutbblf).  Tiis list is sfriblizbblf.
     *
     * @sff #fmptyList()
     */
    @SupprfssWbrnings("rbwtypfs")
    publid stbtid finbl List EMPTY_LIST = nfw EmptyList<>();

    /**
     * Rfturns bn fmpty list (immutbblf).  Tiis list is sfriblizbblf.
     *
     * <p>Tiis fxbmplf illustrbtfs tif typf-sbff wby to obtbin bn fmpty list:
     * <prf>
     *     List&lt;String&gt; s = Collfdtions.fmptyList();
     * </prf>
     *
     * @implNotf
     * Implfmfntbtions of tiis mftiod nffd not drfbtf b sfpbrbtf <tt>List</tt>
     * objfdt for fbdi dbll.   Using tiis mftiod is likfly to ibvf dompbrbblf
     * dost to using tif likf-nbmfd fifld.  (Unlikf tiis mftiod, tif fifld dofs
     * not providf typf sbffty.)
     *
     * @pbrbm <T> typf of flfmfnts, if tifrf wfrf bny, in tif list
     * @rfturn bn fmpty immutbblf list
     *
     * @sff #EMPTY_LIST
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl <T> List<T> fmptyList() {
        rfturn (List<T>) EMPTY_LIST;
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss EmptyList<E>
        fxtfnds AbstrbdtList<E>
        implfmfnts RbndomAddfss, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 8842843931221139166L;

        publid Itfrbtor<E> itfrbtor() {
            rfturn fmptyItfrbtor();
        }
        publid ListItfrbtor<E> listItfrbtor() {
            rfturn fmptyListItfrbtor();
        }

        publid int sizf() {rfturn 0;}
        publid boolfbn isEmpty() {rfturn truf;}

        publid boolfbn dontbins(Objfdt obj) {rfturn fblsf;}
        publid boolfbn dontbinsAll(Collfdtion<?> d) { rfturn d.isEmpty(); }

        publid Objfdt[] toArrby() { rfturn nfw Objfdt[0]; }

        publid <T> T[] toArrby(T[] b) {
            if (b.lfngti > 0)
                b[0] = null;
            rfturn b;
        }

        publid E gft(int indfx) {
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx);
        }

        publid boolfbn fqubls(Objfdt o) {
            rfturn (o instbndfof List) && ((List<?>)o).isEmpty();
        }

        publid int ibsiCodf() { rfturn 1; }

        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            Objfdts.rfquirfNonNull(filtfr);
            rfturn fblsf;
        }
        @Ovfrridf
        publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
            Objfdts.rfquirfNonNull(opfrbtor);
        }
        @Ovfrridf
        publid void sort(Compbrbtor<? supfr E> d) {
        }

        // Ovfrridf dffbult mftiods in Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
        }

        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() { rfturn Splitfrbtors.fmptySplitfrbtor(); }

        // Prfsfrvfs singlfton propfrty
        privbtf Objfdt rfbdRfsolvf() {
            rfturn EMPTY_LIST;
        }
    }

    /**
     * Tif fmpty mbp (immutbblf).  Tiis mbp is sfriblizbblf.
     *
     * @sff #fmptyMbp()
     * @sindf 1.3
     */
    @SupprfssWbrnings("rbwtypfs")
    publid stbtid finbl Mbp EMPTY_MAP = nfw EmptyMbp<>();

    /**
     * Rfturns bn fmpty mbp (immutbblf).  Tiis mbp is sfriblizbblf.
     *
     * <p>Tiis fxbmplf illustrbtfs tif typf-sbff wby to obtbin bn fmpty mbp:
     * <prf>
     *     Mbp&lt;String, Dbtf&gt; s = Collfdtions.fmptyMbp();
     * </prf>
     * @implNotf Implfmfntbtions of tiis mftiod nffd not drfbtf b sfpbrbtf
     * {@dodf Mbp} objfdt for fbdi dbll.  Using tiis mftiod is likfly to ibvf
     * dompbrbblf dost to using tif likf-nbmfd fifld.  (Unlikf tiis mftiod, tif
     * fifld dofs not providf typf sbffty.)
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @rfturn bn fmpty mbp
     * @sff #EMPTY_MAP
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl <K,V> Mbp<K,V> fmptyMbp() {
        rfturn (Mbp<K,V>) EMPTY_MAP;
    }

    /**
     * Rfturns bn fmpty sortfd mbp (immutbblf).  Tiis mbp is sfriblizbblf.
     *
     * <p>Tiis fxbmplf illustrbtfs tif typf-sbff wby to obtbin bn fmpty mbp:
     * <prf> {@dodf
     *     SortfdMbp<String, Dbtf> s = Collfdtions.fmptySortfdMbp();
     * }</prf>
     *
     * @implNotf Implfmfntbtions of tiis mftiod nffd not drfbtf b sfpbrbtf
     * {@dodf SortfdMbp} objfdt for fbdi dbll.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @rfturn bn fmpty sortfd mbp
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl <K,V> SortfdMbp<K,V> fmptySortfdMbp() {
        rfturn (SortfdMbp<K,V>) UnmodifibblfNbvigbblfMbp.EMPTY_NAVIGABLE_MAP;
    }

    /**
     * Rfturns bn fmpty nbvigbblf mbp (immutbblf).  Tiis mbp is sfriblizbblf.
     *
     * <p>Tiis fxbmplf illustrbtfs tif typf-sbff wby to obtbin bn fmpty mbp:
     * <prf> {@dodf
     *     NbvigbblfMbp<String, Dbtf> s = Collfdtions.fmptyNbvigbblfMbp();
     * }</prf>
     *
     * @implNotf Implfmfntbtions of tiis mftiod nffd not drfbtf b sfpbrbtf
     * {@dodf NbvigbblfMbp} objfdt for fbdi dbll.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @rfturn bn fmpty nbvigbblf mbp
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl <K,V> NbvigbblfMbp<K,V> fmptyNbvigbblfMbp() {
        rfturn (NbvigbblfMbp<K,V>) UnmodifibblfNbvigbblfMbp.EMPTY_NAVIGABLE_MAP;
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss EmptyMbp<K,V>
        fxtfnds AbstrbdtMbp<K,V>
        implfmfnts Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 6428348081105594320L;

        publid int sizf()                          {rfturn 0;}
        publid boolfbn isEmpty()                   {rfturn truf;}
        publid boolfbn dontbinsKfy(Objfdt kfy)     {rfturn fblsf;}
        publid boolfbn dontbinsVbluf(Objfdt vbluf) {rfturn fblsf;}
        publid V gft(Objfdt kfy)                   {rfturn null;}
        publid Sft<K> kfySft()                     {rfturn fmptySft();}
        publid Collfdtion<V> vblufs()              {rfturn fmptySft();}
        publid Sft<Mbp.Entry<K,V>> fntrySft()      {rfturn fmptySft();}

        publid boolfbn fqubls(Objfdt o) {
            rfturn (o instbndfof Mbp) && ((Mbp<?,?>)o).isEmpty();
        }

        publid int ibsiCodf()                      {rfturn 0;}

        // Ovfrridf dffbult mftiods in Mbp
        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid V gftOrDffbult(Objfdt k, V dffbultVbluf) {
            rfturn dffbultVbluf;
        }

        @Ovfrridf
        publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
        }

        @Ovfrridf
        publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
            Objfdts.rfquirfNonNull(fundtion);
        }

        @Ovfrridf
        publid V putIfAbsfnt(K kfy, V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V rfplbdf(K kfy, V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputfIfAbsfnt(K kfy,
                Fundtion<? supfr K, ? fxtfnds V> mbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputfIfPrfsfnt(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputf(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V mfrgf(K kfy, V vbluf,
                BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        // Prfsfrvfs singlfton propfrty
        privbtf Objfdt rfbdRfsolvf() {
            rfturn EMPTY_MAP;
        }
    }

    // Singlfton dollfdtions

    /**
     * Rfturns bn immutbblf sft dontbining only tif spfdififd objfdt.
     * Tif rfturnfd sft is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif sft
     * @pbrbm o tif solf objfdt to bf storfd in tif rfturnfd sft.
     * @rfturn bn immutbblf sft dontbining only tif spfdififd objfdt.
     */
    publid stbtid <T> Sft<T> singlfton(T o) {
        rfturn nfw SinglftonSft<>(o);
    }

    stbtid <E> Itfrbtor<E> singlftonItfrbtor(finbl E f) {
        rfturn nfw Itfrbtor<E>() {
            privbtf boolfbn ibsNfxt = truf;
            publid boolfbn ibsNfxt() {
                rfturn ibsNfxt;
            }
            publid E nfxt() {
                if (ibsNfxt) {
                    ibsNfxt = fblsf;
                    rfturn f;
                }
                tirow nfw NoSudiElfmfntExdfption();
            }
            publid void rfmovf() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            @Ovfrridf
            publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
                Objfdts.rfquirfNonNull(bdtion);
                if (ibsNfxt) {
                    bdtion.bddfpt(f);
                    ibsNfxt = fblsf;
                }
            }
        };
    }

    /**
     * Crfbtfs b {@dodf Splitfrbtor} witi only tif spfdififd flfmfnt
     *
     * @pbrbm <T> Typf of flfmfnts
     * @rfturn A singlfton {@dodf Splitfrbtor}
     */
    stbtid <T> Splitfrbtor<T> singlftonSplitfrbtor(finbl T flfmfnt) {
        rfturn nfw Splitfrbtor<T>() {
            long fst = 1;

            @Ovfrridf
            publid Splitfrbtor<T> trySplit() {
                rfturn null;
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(Consumfr<? supfr T> donsumfr) {
                Objfdts.rfquirfNonNull(donsumfr);
                if (fst > 0) {
                    fst--;
                    donsumfr.bddfpt(flfmfnt);
                    rfturn truf;
                }
                rfturn fblsf;
            }

            @Ovfrridf
            publid void forEbdiRfmbining(Consumfr<? supfr T> donsumfr) {
                tryAdvbndf(donsumfr);
            }

            @Ovfrridf
            publid long fstimbtfSizf() {
                rfturn fst;
            }

            @Ovfrridf
            publid int dibrbdtfristids() {
                int vbluf = (flfmfnt != null) ? Splitfrbtor.NONNULL : 0;

                rfturn vbluf | Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED | Splitfrbtor.IMMUTABLE |
                       Splitfrbtor.DISTINCT | Splitfrbtor.ORDERED;
            }
        };
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss SinglftonSft<E>
        fxtfnds AbstrbdtSft<E>
        implfmfnts Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 3193687207550431679L;

        privbtf finbl E flfmfnt;

        SinglftonSft(E f) {flfmfnt = f;}

        publid Itfrbtor<E> itfrbtor() {
            rfturn singlftonItfrbtor(flfmfnt);
        }

        publid int sizf() {rfturn 1;}

        publid boolfbn dontbins(Objfdt o) {rfturn fq(o, flfmfnt);}

        // Ovfrridf dffbult mftiods for Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {
            bdtion.bddfpt(flfmfnt);
        }
        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {
            rfturn singlftonSplitfrbtor(flfmfnt);
        }
        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    /**
     * Rfturns bn immutbblf list dontbining only tif spfdififd objfdt.
     * Tif rfturnfd list is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif list
     * @pbrbm o tif solf objfdt to bf storfd in tif rfturnfd list.
     * @rfturn bn immutbblf list dontbining only tif spfdififd objfdt.
     * @sindf 1.3
     */
    publid stbtid <T> List<T> singlftonList(T o) {
        rfturn nfw SinglftonList<>(o);
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss SinglftonList<E>
        fxtfnds AbstrbdtList<E>
        implfmfnts RbndomAddfss, Sfriblizbblf {

        privbtf stbtid finbl long sfriblVfrsionUID = 3093736618740652951L;

        privbtf finbl E flfmfnt;

        SinglftonList(E obj)                {flfmfnt = obj;}

        publid Itfrbtor<E> itfrbtor() {
            rfturn singlftonItfrbtor(flfmfnt);
        }

        publid int sizf()                   {rfturn 1;}

        publid boolfbn dontbins(Objfdt obj) {rfturn fq(obj, flfmfnt);}

        publid E gft(int indfx) {
            if (indfx != 0)
              tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+", Sizf: 1");
            rfturn flfmfnt;
        }

        // Ovfrridf dffbult mftiods for Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {
            bdtion.bddfpt(flfmfnt);
        }
        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        @Ovfrridf
        publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        @Ovfrridf
        publid void sort(Compbrbtor<? supfr E> d) {
        }
        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {
            rfturn singlftonSplitfrbtor(flfmfnt);
        }
    }

    /**
     * Rfturns bn immutbblf mbp, mbpping only tif spfdififd kfy to tif
     * spfdififd vbluf.  Tif rfturnfd mbp is sfriblizbblf.
     *
     * @pbrbm <K> tif dlbss of tif mbp kfys
     * @pbrbm <V> tif dlbss of tif mbp vblufs
     * @pbrbm kfy tif solf kfy to bf storfd in tif rfturnfd mbp.
     * @pbrbm vbluf tif vbluf to wiidi tif rfturnfd mbp mbps <tt>kfy</tt>.
     * @rfturn bn immutbblf mbp dontbining only tif spfdififd kfy-vbluf
     *         mbpping.
     * @sindf 1.3
     */
    publid stbtid <K,V> Mbp<K,V> singlftonMbp(K kfy, V vbluf) {
        rfturn nfw SinglftonMbp<>(kfy, vbluf);
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss SinglftonMbp<K,V>
          fxtfnds AbstrbdtMbp<K,V>
          implfmfnts Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -6979724477215052911L;

        privbtf finbl K k;
        privbtf finbl V v;

        SinglftonMbp(K kfy, V vbluf) {
            k = kfy;
            v = vbluf;
        }

        publid int sizf()                                           {rfturn 1;}
        publid boolfbn isEmpty()                                {rfturn fblsf;}
        publid boolfbn dontbinsKfy(Objfdt kfy)             {rfturn fq(kfy, k);}
        publid boolfbn dontbinsVbluf(Objfdt vbluf)       {rfturn fq(vbluf, v);}
        publid V gft(Objfdt kfy)              {rfturn (fq(kfy, k) ? v : null);}

        privbtf trbnsifnt Sft<K> kfySft;
        privbtf trbnsifnt Sft<Mbp.Entry<K,V>> fntrySft;
        privbtf trbnsifnt Collfdtion<V> vblufs;

        publid Sft<K> kfySft() {
            if (kfySft==null)
                kfySft = singlfton(k);
            rfturn kfySft;
        }

        publid Sft<Mbp.Entry<K,V>> fntrySft() {
            if (fntrySft==null)
                fntrySft = Collfdtions.<Mbp.Entry<K,V>>singlfton(
                    nfw SimplfImmutbblfEntry<>(k, v));
            rfturn fntrySft;
        }

        publid Collfdtion<V> vblufs() {
            if (vblufs==null)
                vblufs = singlfton(v);
            rfturn vblufs;
        }

        // Ovfrridf dffbult mftiods in Mbp
        @Ovfrridf
        publid V gftOrDffbult(Objfdt kfy, V dffbultVbluf) {
            rfturn fq(kfy, k) ? v : dffbultVbluf;
        }

        @Ovfrridf
        publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
            bdtion.bddfpt(k, v);
        }

        @Ovfrridf
        publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V putIfAbsfnt(K kfy, V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid boolfbn rfmovf(Objfdt kfy, Objfdt vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid boolfbn rfplbdf(K kfy, V oldVbluf, V nfwVbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V rfplbdf(K kfy, V vbluf) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputfIfAbsfnt(K kfy,
                Fundtion<? supfr K, ? fxtfnds V> mbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputfIfPrfsfnt(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V domputf(K kfy,
                BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        publid V mfrgf(K kfy, V vbluf,
                BiFundtion<? supfr V, ? supfr V, ? fxtfnds V> rfmbppingFundtion) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    // Misdfllbnfous

    /**
     * Rfturns bn immutbblf list donsisting of <tt>n</tt> dopifs of tif
     * spfdififd objfdt.  Tif nfwly bllodbtfd dbtb objfdt is tiny (it dontbins
     * b singlf rfffrfndf to tif dbtb objfdt).  Tiis mftiod is usfful in
     * dombinbtion witi tif <tt>List.bddAll</tt> mftiod to grow lists.
     * Tif rfturnfd list is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdt to dopy bnd of tif objfdts
     *         in tif rfturnfd list.
     * @pbrbm  n tif numbfr of flfmfnts in tif rfturnfd list.
     * @pbrbm  o tif flfmfnt to bppfbr rfpfbtfdly in tif rfturnfd list.
     * @rfturn bn immutbblf list donsisting of <tt>n</tt> dopifs of tif
     *         spfdififd objfdt.
     * @tirows IllfgblArgumfntExdfption if {@dodf n < 0}
     * @sff    List#bddAll(Collfdtion)
     * @sff    List#bddAll(int, Collfdtion)
     */
    publid stbtid <T> List<T> nCopifs(int n, T o) {
        if (n < 0)
            tirow nfw IllfgblArgumfntExdfption("List lfngti = " + n);
        rfturn nfw CopifsList<>(n, o);
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss CopifsList<E>
        fxtfnds AbstrbdtList<E>
        implfmfnts RbndomAddfss, Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 2739099268398711800L;

        finbl int n;
        finbl E flfmfnt;

        CopifsList(int n, E f) {
            bssfrt n >= 0;
            tiis.n = n;
            flfmfnt = f;
        }

        publid int sizf() {
            rfturn n;
        }

        publid boolfbn dontbins(Objfdt obj) {
            rfturn n != 0 && fq(obj, flfmfnt);
        }

        publid int indfxOf(Objfdt o) {
            rfturn dontbins(o) ? 0 : -1;
        }

        publid int lbstIndfxOf(Objfdt o) {
            rfturn dontbins(o) ? n - 1 : -1;
        }

        publid E gft(int indfx) {
            if (indfx < 0 || indfx >= n)
                tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+
                                                    ", Sizf: "+n);
            rfturn flfmfnt;
        }

        publid Objfdt[] toArrby() {
            finbl Objfdt[] b = nfw Objfdt[n];
            if (flfmfnt != null)
                Arrbys.fill(b, 0, n, flfmfnt);
            rfturn b;
        }

        @SupprfssWbrnings("undifdkfd")
        publid <T> T[] toArrby(T[] b) {
            finbl int n = tiis.n;
            if (b.lfngti < n) {
                b = (T[])jbvb.lbng.rfflfdt.Arrby
                    .nfwInstbndf(b.gftClbss().gftComponfntTypf(), n);
                if (flfmfnt != null)
                    Arrbys.fill(b, 0, n, flfmfnt);
            } flsf {
                Arrbys.fill(b, 0, n, flfmfnt);
                if (b.lfngti > n)
                    b[n] = null;
            }
            rfturn b;
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            if (fromIndfx < 0)
                tirow nfw IndfxOutOfBoundsExdfption("fromIndfx = " + fromIndfx);
            if (toIndfx > n)
                tirow nfw IndfxOutOfBoundsExdfption("toIndfx = " + toIndfx);
            if (fromIndfx > toIndfx)
                tirow nfw IllfgblArgumfntExdfption("fromIndfx(" + fromIndfx +
                                                   ") > toIndfx(" + toIndfx + ")");
            rfturn nfw CopifsList<>(toIndfx - fromIndfx, flfmfnt);
        }

        // Ovfrridf dffbult mftiods in Collfdtion
        @Ovfrridf
        publid Strfbm<E> strfbm() {
            rfturn IntStrfbm.rbngf(0, n).mbpToObj(i -> flfmfnt);
        }

        @Ovfrridf
        publid Strfbm<E> pbrbllflStrfbm() {
            rfturn IntStrfbm.rbngf(0, n).pbrbllfl().mbpToObj(i -> flfmfnt);
        }

        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {
            rfturn strfbm().splitfrbtor();
        }
    }

    /**
     * Rfturns b dompbrbtor tibt imposfs tif rfvfrsf of tif <fm>nbturbl
     * ordfring</fm> on b dollfdtion of objfdts tibt implfmfnt tif
     * {@dodf Compbrbblf} intfrfbdf.  (Tif nbturbl ordfring is tif ordfring
     * imposfd by tif objfdts' own {@dodf dompbrfTo} mftiod.)  Tiis fnbblfs b
     * simplf idiom for sorting (or mbintbining) dollfdtions (or brrbys) of
     * objfdts tibt implfmfnt tif {@dodf Compbrbblf} intfrfbdf in
     * rfvfrsf-nbturbl-ordfr.  For fxbmplf, supposf {@dodf b} is bn brrby of
     * strings. Tifn: <prf>
     *          Arrbys.sort(b, Collfdtions.rfvfrsfOrdfr());
     * </prf> sorts tif brrby in rfvfrsf-lfxidogrbpiid (blpibbftidbl) ordfr.<p>
     *
     * Tif rfturnfd dompbrbtor is sfriblizbblf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts dompbrfd by tif dompbrbtor
     * @rfturn A dompbrbtor tibt imposfs tif rfvfrsf of tif <i>nbturbl
     *         ordfring</i> on b dollfdtion of objfdts tibt implfmfnt
     *         tif <tt>Compbrbblf</tt> intfrfbdf.
     * @sff Compbrbblf
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> Compbrbtor<T> rfvfrsfOrdfr() {
        rfturn (Compbrbtor<T>) RfvfrsfCompbrbtor.REVERSE_ORDER;
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss RfvfrsfCompbrbtor
        implfmfnts Compbrbtor<Compbrbblf<Objfdt>>, Sfriblizbblf {

        privbtf stbtid finbl long sfriblVfrsionUID = 7207038068494060240L;

        stbtid finbl RfvfrsfCompbrbtor REVERSE_ORDER
            = nfw RfvfrsfCompbrbtor();

        publid int dompbrf(Compbrbblf<Objfdt> d1, Compbrbblf<Objfdt> d2) {
            rfturn d2.dompbrfTo(d1);
        }

        privbtf Objfdt rfbdRfsolvf() { rfturn Collfdtions.rfvfrsfOrdfr(); }

        @Ovfrridf
        publid Compbrbtor<Compbrbblf<Objfdt>> rfvfrsfd() {
            rfturn Compbrbtor.nbturblOrdfr();
        }
    }

    /**
     * Rfturns b dompbrbtor tibt imposfs tif rfvfrsf ordfring of tif spfdififd
     * dompbrbtor.  If tif spfdififd dompbrbtor is {@dodf null}, tiis mftiod is
     * fquivblfnt to {@link #rfvfrsfOrdfr()} (in otifr words, it rfturns b
     * dompbrbtor tibt imposfs tif rfvfrsf of tif <fm>nbturbl ordfring</fm> on
     * b dollfdtion of objfdts tibt implfmfnt tif Compbrbblf intfrfbdf).
     *
     * <p>Tif rfturnfd dompbrbtor is sfriblizbblf (bssuming tif spfdififd
     * dompbrbtor is blso sfriblizbblf or {@dodf null}).
     *
     * @pbrbm <T> tif dlbss of tif objfdts dompbrfd by tif dompbrbtor
     * @pbrbm dmp b dompbrbtor wio's ordfring is to bf rfvfrsfd by tif rfturnfd
     * dompbrbtor or {@dodf null}
     * @rfturn A dompbrbtor tibt imposfs tif rfvfrsf ordfring of tif
     *         spfdififd dompbrbtor.
     * @sindf 1.5
     */
    publid stbtid <T> Compbrbtor<T> rfvfrsfOrdfr(Compbrbtor<T> dmp) {
        if (dmp == null)
            rfturn rfvfrsfOrdfr();

        if (dmp instbndfof RfvfrsfCompbrbtor2)
            rfturn ((RfvfrsfCompbrbtor2<T>)dmp).dmp;

        rfturn nfw RfvfrsfCompbrbtor2<>(dmp);
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss RfvfrsfCompbrbtor2<T> implfmfnts Compbrbtor<T>,
        Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 4374092139857L;

        /**
         * Tif dompbrbtor spfdififd in tif stbtid fbdtory.  Tiis will nfvfr
         * bf null, bs tif stbtid fbdtory rfturns b RfvfrsfCompbrbtor
         * instbndf if its brgumfnt is null.
         *
         * @sfribl
         */
        finbl Compbrbtor<T> dmp;

        RfvfrsfCompbrbtor2(Compbrbtor<T> dmp) {
            bssfrt dmp != null;
            tiis.dmp = dmp;
        }

        publid int dompbrf(T t1, T t2) {
            rfturn dmp.dompbrf(t2, t1);
        }

        publid boolfbn fqubls(Objfdt o) {
            rfturn (o == tiis) ||
                (o instbndfof RfvfrsfCompbrbtor2 &&
                 dmp.fqubls(((RfvfrsfCompbrbtor2)o).dmp));
        }

        publid int ibsiCodf() {
            rfturn dmp.ibsiCodf() ^ Intfgfr.MIN_VALUE;
        }

        @Ovfrridf
        publid Compbrbtor<T> rfvfrsfd() {
            rfturn dmp;
        }
    }

    /**
     * Rfturns bn fnumfrbtion ovfr tif spfdififd dollfdtion.  Tiis providfs
     * intfropfrbbility witi lfgbdy APIs tibt rfquirf bn fnumfrbtion
     * bs input.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif dollfdtion
     * @pbrbm d tif dollfdtion for wiidi bn fnumfrbtion is to bf rfturnfd.
     * @rfturn bn fnumfrbtion ovfr tif spfdififd dollfdtion.
     * @sff Enumfrbtion
     */
    publid stbtid <T> Enumfrbtion<T> fnumfrbtion(finbl Collfdtion<T> d) {
        rfturn nfw Enumfrbtion<T>() {
            privbtf finbl Itfrbtor<T> i = d.itfrbtor();

            publid boolfbn ibsMorfElfmfnts() {
                rfturn i.ibsNfxt();
            }

            publid T nfxtElfmfnt() {
                rfturn i.nfxt();
            }
        };
    }

    /**
     * Rfturns bn brrby list dontbining tif flfmfnts rfturnfd by tif
     * spfdififd fnumfrbtion in tif ordfr tify brf rfturnfd by tif
     * fnumfrbtion.  Tiis mftiod providfs intfropfrbbility bftwffn
     * lfgbdy APIs tibt rfturn fnumfrbtions bnd nfw APIs tibt rfquirf
     * dollfdtions.
     *
     * @pbrbm <T> tif dlbss of tif objfdts rfturnfd by tif fnumfrbtion
     * @pbrbm f fnumfrbtion providing flfmfnts for tif rfturnfd
     *          brrby list
     * @rfturn bn brrby list dontbining tif flfmfnts rfturnfd
     *         by tif spfdififd fnumfrbtion.
     * @sindf 1.4
     * @sff Enumfrbtion
     * @sff ArrbyList
     */
    publid stbtid <T> ArrbyList<T> list(Enumfrbtion<T> f) {
        ArrbyList<T> l = nfw ArrbyList<>();
        wiilf (f.ibsMorfElfmfnts())
            l.bdd(f.nfxtElfmfnt());
        rfturn l;
    }

    /**
     * Rfturns truf if tif spfdififd brgumfnts brf fqubl, or boti null.
     *
     * NB: Do not rfplbdf witi Objfdt.fqubls until JDK-8015417 is rfsolvfd.
     */
    stbtid boolfbn fq(Objfdt o1, Objfdt o2) {
        rfturn o1==null ? o2==null : o1.fqubls(o2);
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tif spfdififd dollfdtion fqubl to tif
     * spfdififd objfdt.  Morf formblly, rfturns tif numbfr of flfmfnts
     * <tt>f</tt> in tif dollfdtion sudi tibt
     * <tt>(o == null ? f == null : o.fqubls(f))</tt>.
     *
     * @pbrbm d tif dollfdtion in wiidi to dftfrminf tif frfqufndy
     *     of <tt>o</tt>
     * @pbrbm o tif objfdt wiosf frfqufndy is to bf dftfrminfd
     * @rfturn tif numbfr of flfmfnts in {@dodf d} fqubl to {@dodf o}
     * @tirows NullPointfrExdfption if <tt>d</tt> is null
     * @sindf 1.5
     */
    publid stbtid int frfqufndy(Collfdtion<?> d, Objfdt o) {
        int rfsult = 0;
        if (o == null) {
            for (Objfdt f : d)
                if (f == null)
                    rfsult++;
        } flsf {
            for (Objfdt f : d)
                if (o.fqubls(f))
                    rfsult++;
        }
        rfturn rfsult;
    }

    /**
     * Rfturns {@dodf truf} if tif two spfdififd dollfdtions ibvf no
     * flfmfnts in dommon.
     *
     * <p>Cbrf must bf fxfrdisfd if tiis mftiod is usfd on dollfdtions tibt
     * do not domply witi tif gfnfrbl dontrbdt for {@dodf Collfdtion}.
     * Implfmfntbtions mby flfdt to itfrbtf ovfr fitifr dollfdtion bnd tfst
     * for dontbinmfnt in tif otifr dollfdtion (or to pfrform bny fquivblfnt
     * domputbtion).  If fitifr dollfdtion usfs b nonstbndbrd fqublity tfst
     * (bs dofs b {@link SortfdSft} wiosf ordfring is not <fm>dompbtiblf witi
     * fqubls</fm>, or tif kfy sft of bn {@link IdfntityHbsiMbp}), boti
     * dollfdtions must usf tif sbmf nonstbndbrd fqublity tfst, or tif
     * rfsult of tiis mftiod is undffinfd.
     *
     * <p>Cbrf must blso bf fxfrdisfd wifn using dollfdtions tibt ibvf
     * rfstridtions on tif flfmfnts tibt tify mby dontbin. Collfdtion
     * implfmfntbtions brf bllowfd to tirow fxdfptions for bny opfrbtion
     * involving flfmfnts tify dffm infligiblf. For bbsolutf sbffty tif
     * spfdififd dollfdtions siould dontbin only flfmfnts wiidi brf
     * fligiblf flfmfnts for boti dollfdtions.
     *
     * <p>Notf tibt it is pfrmissiblf to pbss tif sbmf dollfdtion in boti
     * pbrbmftfrs, in wiidi dbsf tif mftiod will rfturn {@dodf truf} if bnd
     * only if tif dollfdtion is fmpty.
     *
     * @pbrbm d1 b dollfdtion
     * @pbrbm d2 b dollfdtion
     * @rfturn {@dodf truf} if tif two spfdififd dollfdtions ibvf no
     * flfmfnts in dommon.
     * @tirows NullPointfrExdfption if fitifr dollfdtion is {@dodf null}.
     * @tirows NullPointfrExdfption if onf dollfdtion dontbins b {@dodf null}
     * flfmfnt bnd {@dodf null} is not bn fligiblf flfmfnt for tif otifr dollfdtion.
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows ClbssCbstExdfption if onf dollfdtion dontbins bn flfmfnt tibt is
     * of b typf wiidi is infligiblf for tif otifr dollfdtion.
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @sindf 1.5
     */
    publid stbtid boolfbn disjoint(Collfdtion<?> d1, Collfdtion<?> d2) {
        // Tif dollfdtion to bf usfd for dontbins(). Prfffrfndf is givfn to
        // tif dollfdtion wio's dontbins() ibs lowfr O() domplfxity.
        Collfdtion<?> dontbins = d2;
        // Tif dollfdtion to bf itfrbtfd. If tif dollfdtions' dontbins() impl
        // brf of difffrfnt O() domplfxity, tif dollfdtion witi slowfr
        // dontbins() will bf usfd for itfrbtion. For dollfdtions wio's
        // dontbins() brf of tif sbmf domplfxity tifn bfst pfrformbndf is
        // bdiifvfd by itfrbting tif smbllfr dollfdtion.
        Collfdtion<?> itfrbtf = d1;

        // Pfrformbndf optimizbtion dbsfs. Tif ifuristids:
        //   1. Gfnfrblly itfrbtf ovfr d1.
        //   2. If d1 is b Sft tifn itfrbtf ovfr d2.
        //   3. If fitifr dollfdtion is fmpty tifn rfsult is blwbys truf.
        //   4. Itfrbtf ovfr tif smbllfr Collfdtion.
        if (d1 instbndfof Sft) {
            // Usf d1 for dontbins bs b Sft's dontbins() is fxpfdtfd to pfrform
            // bfttfr tibn O(N/2)
            itfrbtf = d2;
            dontbins = d1;
        } flsf if (!(d2 instbndfof Sft)) {
            // Boti brf mfrf Collfdtions. Itfrbtf ovfr smbllfr dollfdtion.
            // Exbmplf: If d1 dontbins 3 flfmfnts bnd d2 dontbins 50 flfmfnts bnd
            // bssuming dontbins() rfquirfs dfiling(N/2) dompbrisons tifn
            // difdking for bll d1 flfmfnts in d2 would rfquirf 75 dompbrisons
            // (3 * dfiling(50/2)) vs. difdking bll d2 flfmfnts in d1 rfquiring
            // 100 dompbrisons (50 * dfiling(3/2)).
            int d1sizf = d1.sizf();
            int d2sizf = d2.sizf();
            if (d1sizf == 0 || d2sizf == 0) {
                // At lfbst onf dollfdtion is fmpty. Notiing will mbtdi.
                rfturn truf;
            }

            if (d1sizf > d2sizf) {
                itfrbtf = d2;
                dontbins = d1;
            }
        }

        for (Objfdt f : itfrbtf) {
            if (dontbins.dontbins(f)) {
               // Found b dommon flfmfnt. Collfdtions brf not disjoint.
                rfturn fblsf;
            }
        }

        // No dommon flfmfnts wfrf found.
        rfturn truf;
    }

    /**
     * Adds bll of tif spfdififd flfmfnts to tif spfdififd dollfdtion.
     * Elfmfnts to bf bddfd mby bf spfdififd individublly or bs bn brrby.
     * Tif bfibvior of tiis donvfnifndf mftiod is idfntidbl to tibt of
     * <tt>d.bddAll(Arrbys.bsList(flfmfnts))</tt>, but tiis mftiod is likfly
     * to run signifidbntly fbstfr undfr most implfmfntbtions.
     *
     * <p>Wifn flfmfnts brf spfdififd individublly, tiis mftiod providfs b
     * donvfnifnt wby to bdd b ffw flfmfnts to bn fxisting dollfdtion:
     * <prf>
     *     Collfdtions.bddAll(flbvors, "Pfbdifs 'n Plutonium", "Rodky Rbdoon");
     * </prf>
     *
     * @pbrbm  <T> tif dlbss of tif flfmfnts to bdd bnd of tif dollfdtion
     * @pbrbm d tif dollfdtion into wiidi <tt>flfmfnts</tt> brf to bf insfrtfd
     * @pbrbm flfmfnts tif flfmfnts to insfrt into <tt>d</tt>
     * @rfturn <tt>truf</tt> if tif dollfdtion dibngfd bs b rfsult of tif dbll
     * @tirows UnsupportfdOpfrbtionExdfption if <tt>d</tt> dofs not support
     *         tif <tt>bdd</tt> opfrbtion
     * @tirows NullPointfrExdfption if <tt>flfmfnts</tt> dontbins onf or morf
     *         null vblufs bnd <tt>d</tt> dofs not pfrmit null flfmfnts, or
     *         if <tt>d</tt> or <tt>flfmfnts</tt> brf <tt>null</tt>
     * @tirows IllfgblArgumfntExdfption if somf propfrty of b vbluf in
     *         <tt>flfmfnts</tt> prfvfnts it from bfing bddfd to <tt>d</tt>
     * @sff Collfdtion#bddAll(Collfdtion)
     * @sindf 1.5
     */
    @SbffVbrbrgs
    publid stbtid <T> boolfbn bddAll(Collfdtion<? supfr T> d, T... flfmfnts) {
        boolfbn rfsult = fblsf;
        for (T flfmfnt : flfmfnts)
            rfsult |= d.bdd(flfmfnt);
        rfturn rfsult;
    }

    /**
     * Rfturns b sft bbdkfd by tif spfdififd mbp.  Tif rfsulting sft displbys
     * tif sbmf ordfring, dondurrfndy, bnd pfrformbndf dibrbdtfristids bs tif
     * bbdking mbp.  In fssfndf, tiis fbdtory mftiod providfs b {@link Sft}
     * implfmfntbtion dorrfsponding to bny {@link Mbp} implfmfntbtion.  Tifrf
     * is no nffd to usf tiis mftiod on b {@link Mbp} implfmfntbtion tibt
     * blrfbdy ibs b dorrfsponding {@link Sft} implfmfntbtion (sudi bs {@link
     * HbsiMbp} or {@link TrffMbp}).
     *
     * <p>Ebdi mftiod invodbtion on tif sft rfturnfd by tiis mftiod rfsults in
     * fxbdtly onf mftiod invodbtion on tif bbdking mbp or its <tt>kfySft</tt>
     * vifw, witi onf fxdfption.  Tif <tt>bddAll</tt> mftiod is implfmfntfd
     * bs b sfqufndf of <tt>put</tt> invodbtions on tif bbdking mbp.
     *
     * <p>Tif spfdififd mbp must bf fmpty bt tif timf tiis mftiod is invokfd,
     * bnd siould not bf bddfssfd dirfdtly bftfr tiis mftiod rfturns.  Tifsf
     * donditions brf fnsurfd if tif mbp is drfbtfd fmpty, pbssfd dirfdtly
     * to tiis mftiod, bnd no rfffrfndf to tif mbp is rftbinfd, bs illustrbtfd
     * in tif following dodf frbgmfnt:
     * <prf>
     *    Sft&lt;Objfdt&gt; wfbkHbsiSft = Collfdtions.nfwSftFromMbp(
     *        nfw WfbkHbsiMbp&lt;Objfdt, Boolfbn&gt;());
     * </prf>
     *
     * @pbrbm <E> tif dlbss of tif mbp kfys bnd of tif objfdts in tif
     *        rfturnfd sft
     * @pbrbm mbp tif bbdking mbp
     * @rfturn tif sft bbdkfd by tif mbp
     * @tirows IllfgblArgumfntExdfption if <tt>mbp</tt> is not fmpty
     * @sindf 1.6
     */
    publid stbtid <E> Sft<E> nfwSftFromMbp(Mbp<E, Boolfbn> mbp) {
        rfturn nfw SftFromMbp<>(mbp);
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss SftFromMbp<E> fxtfnds AbstrbdtSft<E>
        implfmfnts Sft<E>, Sfriblizbblf
    {
        privbtf finbl Mbp<E, Boolfbn> m;  // Tif bbdking mbp
        privbtf trbnsifnt Sft<E> s;       // Its kfySft

        SftFromMbp(Mbp<E, Boolfbn> mbp) {
            if (!mbp.isEmpty())
                tirow nfw IllfgblArgumfntExdfption("Mbp is non-fmpty");
            m = mbp;
            s = mbp.kfySft();
        }

        publid void dlfbr()               {        m.dlfbr(); }
        publid int sizf()                 { rfturn m.sizf(); }
        publid boolfbn isEmpty()          { rfturn m.isEmpty(); }
        publid boolfbn dontbins(Objfdt o) { rfturn m.dontbinsKfy(o); }
        publid boolfbn rfmovf(Objfdt o)   { rfturn m.rfmovf(o) != null; }
        publid boolfbn bdd(E f) { rfturn m.put(f, Boolfbn.TRUE) == null; }
        publid Itfrbtor<E> itfrbtor()     { rfturn s.itfrbtor(); }
        publid Objfdt[] toArrby()         { rfturn s.toArrby(); }
        publid <T> T[] toArrby(T[] b)     { rfturn s.toArrby(b); }
        publid String toString()          { rfturn s.toString(); }
        publid int ibsiCodf()             { rfturn s.ibsiCodf(); }
        publid boolfbn fqubls(Objfdt o)   { rfturn o == tiis || s.fqubls(o); }
        publid boolfbn dontbinsAll(Collfdtion<?> d) {rfturn s.dontbinsAll(d);}
        publid boolfbn rfmovfAll(Collfdtion<?> d)   {rfturn s.rfmovfAll(d);}
        publid boolfbn rftbinAll(Collfdtion<?> d)   {rfturn s.rftbinAll(d);}
        // bddAll is tif only inifritfd implfmfntbtion

        // Ovfrridf dffbult mftiods in Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {
            s.forEbdi(bdtion);
        }
        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            rfturn s.rfmovfIf(filtfr);
        }

        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {rfturn s.splitfrbtor();}
        @Ovfrridf
        publid Strfbm<E> strfbm()           {rfturn s.strfbm();}
        @Ovfrridf
        publid Strfbm<E> pbrbllflStrfbm()   {rfturn s.pbrbllflStrfbm();}

        privbtf stbtid finbl long sfriblVfrsionUID = 2454657854757543876L;

        privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm strfbm)
            tirows IOExdfption, ClbssNotFoundExdfption
        {
            strfbm.dffbultRfbdObjfdt();
            s = m.kfySft();
        }
    }

    /**
     * Rfturns b vifw of b {@link Dfquf} bs b Lbst-in-first-out (Lifo)
     * {@link Qufuf}. Mftiod <tt>bdd</tt> is mbppfd to <tt>pusi</tt>,
     * <tt>rfmovf</tt> is mbppfd to <tt>pop</tt> bnd so on. Tiis
     * vifw dbn bf usfful wifn you would likf to usf b mftiod
     * rfquiring b <tt>Qufuf</tt> but you nffd Lifo ordfring.
     *
     * <p>Ebdi mftiod invodbtion on tif qufuf rfturnfd by tiis mftiod
     * rfsults in fxbdtly onf mftiod invodbtion on tif bbdking dfquf, witi
     * onf fxdfption.  Tif {@link Qufuf#bddAll bddAll} mftiod is
     * implfmfntfd bs b sfqufndf of {@link Dfquf#bddFirst bddFirst}
     * invodbtions on tif bbdking dfquf.
     *
     * @pbrbm  <T> tif dlbss of tif objfdts in tif dfquf
     * @pbrbm dfquf tif dfquf
     * @rfturn tif qufuf
     * @sindf  1.6
     */
    publid stbtid <T> Qufuf<T> bsLifoQufuf(Dfquf<T> dfquf) {
        rfturn nfw AsLIFOQufuf<>(dfquf);
    }

    /**
     * @sfribl indludf
     */
    stbtid dlbss AsLIFOQufuf<E> fxtfnds AbstrbdtQufuf<E>
        implfmfnts Qufuf<E>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 1802017725587941708L;
        privbtf finbl Dfquf<E> q;
        AsLIFOQufuf(Dfquf<E> q)           { tiis.q = q; }
        publid boolfbn bdd(E f)           { q.bddFirst(f); rfturn truf; }
        publid boolfbn offfr(E f)         { rfturn q.offfrFirst(f); }
        publid E poll()                   { rfturn q.pollFirst(); }
        publid E rfmovf()                 { rfturn q.rfmovfFirst(); }
        publid E pffk()                   { rfturn q.pffkFirst(); }
        publid E flfmfnt()                { rfturn q.gftFirst(); }
        publid void dlfbr()               {        q.dlfbr(); }
        publid int sizf()                 { rfturn q.sizf(); }
        publid boolfbn isEmpty()          { rfturn q.isEmpty(); }
        publid boolfbn dontbins(Objfdt o) { rfturn q.dontbins(o); }
        publid boolfbn rfmovf(Objfdt o)   { rfturn q.rfmovf(o); }
        publid Itfrbtor<E> itfrbtor()     { rfturn q.itfrbtor(); }
        publid Objfdt[] toArrby()         { rfturn q.toArrby(); }
        publid <T> T[] toArrby(T[] b)     { rfturn q.toArrby(b); }
        publid String toString()          { rfturn q.toString(); }
        publid boolfbn dontbinsAll(Collfdtion<?> d) {rfturn q.dontbinsAll(d);}
        publid boolfbn rfmovfAll(Collfdtion<?> d)   {rfturn q.rfmovfAll(d);}
        publid boolfbn rftbinAll(Collfdtion<?> d)   {rfturn q.rftbinAll(d);}
        // Wf usf inifritfd bddAll; forwbrding bddAll would bf wrong

        // Ovfrridf dffbult mftiods in Collfdtion
        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {q.forEbdi(bdtion);}
        @Ovfrridf
        publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
            rfturn q.rfmovfIf(filtfr);
        }
        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {rfturn q.splitfrbtor();}
        @Ovfrridf
        publid Strfbm<E> strfbm()           {rfturn q.strfbm();}
        @Ovfrridf
        publid Strfbm<E> pbrbllflStrfbm()   {rfturn q.pbrbllflStrfbm();}
    }
}
