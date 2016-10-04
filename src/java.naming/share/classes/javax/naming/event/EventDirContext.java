/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.fvfnt;
import jbvbx.nbming.Nbmf;
import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.dirfdtory.DirContfxt;
import jbvbx.nbming.dirfdtory.SfbrdiControls;

/**
 * Contbins mftiods for rfgistfring listfnfrs to bf notififd
 * of fvfnts firfd wifn objfdts nbmfd in b dirfdtory dontfxt dibngfs.
 *<p>
 * Tif mftiods in tiis intfrfbdf support idfntifidbtion of objfdts by
 * <A HREF="ittp://www.iftf.org/rfd/rfd2254.txt">RFC 2254</b>
 * sfbrdi filtfrs.
 *
 *<P>Using tif sfbrdi filtfr, it is possiblf to rfgistfr intfrfst in objfdts
 * tibt do not fxist bt tif timf of rfgistrbtion but lbtfr domf into fxistfndf bnd
 * sbtisfy tif filtfr.  Howfvfr, tifrf migit bf limitbtions in tif fxtfnt
 * to wiidi tiis dbn bf supportfd by tif sfrvidf providfr bnd undfrlying
 * protodol/sfrvidf.  If tif dbllfr submits b filtfr tibt dbnnot bf
 * supportfd in tiis wby, <tt>bddNbmingListfnfr()</tt> tirows bn
 * <tt>InvblidSfbrdiFiltfrExdfption</tt>.
 *<p>
 * Sff <tt>EvfntContfxt</tt> for b dfsdription of fvfnt sourdf
 * bnd tbrgft, bnd informbtion bbout listfnfr rfgistrbtion/dfrfgistrbtion
 * tibt brf blso bpplidbblf to mftiods in tiis intfrfbdf.
 * Sff tif
 * <b irff=pbdkbgf-summbry.itml#THREADING>pbdkbgf dfsdription</b>
 * for informbtion on tirfbding issufs.
 *<p>
 * A <tt>SfbrdiControls</tt> or brrby objfdt
 * pbssfd bs b pbrbmftfr to bny mftiod is ownfd by tif dbllfr.
 * Tif sfrvidf providfr will not modify tif objfdt or kffp b rfffrfndf to it.
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 * @sindf 1.3
 */

publid intfrfbdf EvfntDirContfxt fxtfnds EvfntContfxt, DirContfxt {
    /**
     * Adds b listfnfr for rfdfiving nbming fvfnts firfd
     * wifn objfdts idfntififd by tif sfbrdi filtfr <tt>filtfr</tt> bt
     * tif objfdt nbmfd by tbrgft brf modififd.
     * <p>
     * Tif sdopf, rfturningObj flbg, bnd rfturningAttributfs flbg from
     * tif sfbrdi dontrols <tt>dtls</tt> brf usfd to dontrol tif sflfdtion
     * of objfdts tibt tif listfnfr is intfrfstfd in,
     * bnd dftfrminfs wibt informbtion is rfturnfd in tif fvfntubl
     * <tt>NbmingEvfnt</tt> objfdt. Notf tibt tif rfqufstfd
     * informbtion to bf rfturnfd migit not bf prfsfnt in tif <tt>NbmingEvfnt</tt>
     * objfdt if tify brf unbvbilbblf or dould not bf obtbinfd by tif
     * sfrvidf providfr or sfrvidf.
     *
     * @pbrbm tbrgft Tif nonnull nbmf of tif objfdt rfsolvfd rflbtivf to tiis dontfxt.
     * @pbrbm filtfr Tif nonnull string filtfr (sff RFC2254).
     * @pbrbm dtls   Tif possibly null sfbrdi dontrols. If null, tif dffbult
     *        sfbrdi dontrols brf usfd.
     * @pbrbm l  Tif nonnull listfnfr.
     * @fxdfption NbmingExdfption If b problfm wbs fndountfrfd wiilf
     * bdding tif listfnfr.
     * @sff EvfntContfxt#rfmovfNbmingListfnfr
     * @sff jbvbx.nbming.dirfdtory.DirContfxt#sfbrdi(jbvbx.nbming.Nbmf, jbvb.lbng.String, jbvbx.nbming.dirfdtory.SfbrdiControls)
     */
    void bddNbmingListfnfr(Nbmf tbrgft, String filtfr, SfbrdiControls dtls,
        NbmingListfnfr l) tirows NbmingExdfption;

    /**
     * Adds b listfnfr for rfdfiving nbming fvfnts firfd wifn
     * objfdts idfntififd by tif sfbrdi filtfr <tt>filtfr</tt> bt tif
     * objfdt nbmfd by tif string tbrgft nbmf brf modififd.
     * Sff tif ovfrlobd tibt bddfpts b <tt>Nbmf</tt> for dftbils of
     * iow tiis mftiod bfibvfs.
     *
     * @pbrbm tbrgft Tif nonnull string nbmf of tif objfdt rfsolvfd rflbtivf to tiis dontfxt.
     * @pbrbm filtfr Tif nonnull string filtfr (sff RFC2254).
     * @pbrbm dtls   Tif possibly null sfbrdi dontrols. If null, tif dffbult
     *        sfbrdi dontrols is usfd.
     * @pbrbm l  Tif nonnull listfnfr.
     * @fxdfption NbmingExdfption If b problfm wbs fndountfrfd wiilf
     * bdding tif listfnfr.
     * @sff EvfntContfxt#rfmovfNbmingListfnfr
     * @sff jbvbx.nbming.dirfdtory.DirContfxt#sfbrdi(jbvb.lbng.String, jbvb.lbng.String, jbvbx.nbming.dirfdtory.SfbrdiControls)
     */
    void bddNbmingListfnfr(String tbrgft, String filtfr, SfbrdiControls dtls,
        NbmingListfnfr l) tirows NbmingExdfption;

    /**
     * Adds b listfnfr for rfdfiving nbming fvfnts firfd
     * wifn objfdts idfntififd by tif sfbrdi filtfr <tt>filtfr</tt> bnd
     * filtfr brgumfnts bt tif objfdt nbmfd by tif tbrgft brf modififd.
     * Tif sdopf, rfturningObj flbg, bnd rfturningAttributfs flbg from
     * tif sfbrdi dontrols <tt>dtls</tt> brf usfd to dontrol tif sflfdtion
     * of objfdts tibt tif listfnfr is intfrfstfd in,
     * bnd dftfrminfs wibt informbtion is rfturnfd in tif fvfntubl
     * <tt>NbmingEvfnt</tt> objfdt.  Notf tibt tif rfqufstfd
     * informbtion to bf rfturnfd migit not bf prfsfnt in tif <tt>NbmingEvfnt</tt>
     * objfdt if tify brf unbvbilbblf or dould not bf obtbinfd by tif
     * sfrvidf providfr or sfrvidf.
     *
     * @pbrbm tbrgft Tif nonnull nbmf of tif objfdt rfsolvfd rflbtivf to tiis dontfxt.
     * @pbrbm filtfr Tif nonnull string filtfr (sff RFC2254).
     * @pbrbm filtfrArgs Tif possibly null brrby of brgumfnts for tif filtfr.
     * @pbrbm dtls   Tif possibly null sfbrdi dontrols. If null, tif dffbult
     *        sfbrdi dontrols brf usfd.
     * @pbrbm l  Tif nonnull listfnfr.
     * @fxdfption NbmingExdfption If b problfm wbs fndountfrfd wiilf
     * bdding tif listfnfr.
     * @sff EvfntContfxt#rfmovfNbmingListfnfr
     * @sff jbvbx.nbming.dirfdtory.DirContfxt#sfbrdi(jbvbx.nbming.Nbmf, jbvb.lbng.String, jbvb.lbng.Objfdt[], jbvbx.nbming.dirfdtory.SfbrdiControls)
     */
    void bddNbmingListfnfr(Nbmf tbrgft, String filtfr, Objfdt[] filtfrArgs,
        SfbrdiControls dtls, NbmingListfnfr l) tirows NbmingExdfption;

    /**
     * Adds b listfnfr for rfdfiving nbming fvfnts firfd wifn
     * objfdts idfntififd by tif sfbrdi filtfr <tt>filtfr</tt>
     * bnd filtfr brgumfnts bt tif
     * objfdt nbmfd by tif string tbrgft nbmf brf modififd.
     * Sff tif ovfrlobd tibt bddfpts b <tt>Nbmf</tt> for dftbils of
     * iow tiis mftiod bfibvfs.
     *
     * @pbrbm tbrgft Tif nonnull string nbmf of tif objfdt rfsolvfd rflbtivf to tiis dontfxt.
     * @pbrbm filtfr Tif nonnull string filtfr (sff RFC2254).
     * @pbrbm filtfrArgs Tif possibly null brrby of brgumfnts for tif filtfr.
     * @pbrbm dtls   Tif possibly null sfbrdi dontrols. If null, tif dffbult
     *        sfbrdi dontrols is usfd.
     * @pbrbm l  Tif nonnull listfnfr.
     * @fxdfption NbmingExdfption If b problfm wbs fndountfrfd wiilf
     * bdding tif listfnfr.
     * @sff EvfntContfxt#rfmovfNbmingListfnfr
     * @sff jbvbx.nbming.dirfdtory.DirContfxt#sfbrdi(jbvb.lbng.String, jbvb.lbng.String, jbvb.lbng.Objfdt[], jbvbx.nbming.dirfdtory.SfbrdiControls)      */
    void bddNbmingListfnfr(String tbrgft, String filtfr, Objfdt[] filtfrArgs,
        SfbrdiControls dtls, NbmingListfnfr l) tirows NbmingExdfption;
}
