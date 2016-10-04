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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.tfxt.Bidi;

import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.undo.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.trff.TrffNodf;

import sun.font.BidiUtils;
import sun.swing.SwingUtilitifs2;

/**
 * An implfmfntbtion of tif dodumfnt intfrfbdf to sfrvf bs b
 * bbsis for implfmfnting vbrious kinds of dodumfnts.  At tiis
 * lfvfl tifrf is vfry littlf polidy, so tifrf is b dorrfsponding
 * indrfbsf in diffidulty of usf.
 * <p>
 * Tiis dlbss implfmfnts b lodking mfdibnism for tif dodumfnt.  It
 * bllows multiplf rfbdfrs or onf writfr, bnd writfrs must wbit until
 * bll obsfrvfrs of tif dodumfnt ibvf bffn notififd of b prfvious
 * dibngf bfforf bfginning bnotifr mutbtion to tif dodumfnt.  Tif
 * rfbd lodk is bdquirfd bnd rflfbsfd using tif <dodf>rfndfr</dodf>
 * mftiod.  A writf lodk is bdquirfd by tif mftiods tibt mutbtf tif
 * dodumfnt, bnd brf ifld for tif durbtion of tif mftiod dbll.
 * Notifidbtion is donf on tif tirfbd tibt produdfd tif mutbtion,
 * bnd tif tirfbd ibs full rfbd bddfss to tif dodumfnt for tif
 * durbtion of tif notifidbtion, but otifr rfbdfrs brf kfpt out
 * until tif notifidbtion ibs finisifd.  Tif notifidbtion is b
 * bfbns fvfnt notifidbtion wiidi dofs not bllow bny furtifr
 * mutbtions until bll listfnfrs ibvf bffn notififd.
 * <p>
 * Any modfls subdlbssfd from tiis dlbss bnd usfd in donjundtion
 * witi b tfxt domponfnt tibt ibs b look bnd fffl implfmfntbtion
 * tibt is dfrivfd from BbsidTfxtUI mby bf sbffly updbtfd
 * bsyndironously, bfdbusf bll bddfss to tif Vifw iifrbrdiy
 * is sfriblizfd by BbsidTfxtUI if tif dodumfnt is of typf
 * <dodf>AbstrbdtDodumfnt</dodf>.  Tif lodking bssumfs tibt bn
 * indfpfndfnt tirfbd will bddfss tif Vifw iifrbrdiy only from
 * tif DodumfntListfnfr mftiods, bnd tibt tifrf will bf only
 * onf fvfnt tirfbd bdtivf bt b timf.
 * <p>
 * If dondurrfndy support is dfsirfd, tifrf brf tif following
 * bdditionbl implidbtions.  Tif dodf pbti for bny DodumfntListfnfr
 * implfmfntbtion bnd bny UndoListfnfr implfmfntbtion must bf tirfbdsbff,
 * bnd not bddfss tif domponfnt lodk if trying to bf sbff from dfbdlodks.
 * Tif <dodf>rfpbint</dodf> bnd <dodf>rfvblidbtf</dodf> mftiods
 * on JComponfnt brf sbff.
 * <p>
 * AbstrbdtDodumfnt modfls bn implifd brfbk bt tif fnd of tif dodumfnt.
 * Among otifr tiings tiis bllows you to position tif dbrft bftfr tif lbst
 * dibrbdtfr. As b rfsult of tiis, <dodf>gftLfngti</dodf> rfturns onf lfss
 * tibn tif lfngti of tif Contfnt. If you drfbtf your own Contfnt, bf
 * surf bnd initiblizf it to ibvf bn bdditionbl dibrbdtfr. Rfffr to
 * StringContfnt bnd GbpContfnt for fxbmplfs of tiis. Anotifr implidbtion
 * of tiis is tibt Elfmfnts tibt modfl tif implifd fnd dibrbdtfr will ibvf
 * bn fndOffsft == (gftLfngti() + 1). For fxbmplf, in DffbultStylfdDodumfnt
 * <dodf>gftPbrbgrbpiElfmfnt(gftLfngti()).gftEndOffsft() == gftLfngti() + 1
 * </dodf>.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss AbstrbdtDodumfnt implfmfnts Dodumfnt, Sfriblizbblf {

    /**
     * Construdts b nfw <dodf>AbstrbdtDodumfnt</dodf>, wrbppfd bround somf
     * spfdififd dontfnt storbgf mfdibnism.
     *
     * @pbrbm dbtb tif dontfnt
     */
    protfdtfd AbstrbdtDodumfnt(Contfnt dbtb) {
        tiis(dbtb, StylfContfxt.gftDffbultStylfContfxt());
    }

    /**
     * Construdts b nfw <dodf>AbstrbdtDodumfnt</dodf>, wrbppfd bround somf
     * spfdififd dontfnt storbgf mfdibnism.
     *
     * @pbrbm dbtb tif dontfnt
     * @pbrbm dontfxt tif bttributf dontfxt
     */
    protfdtfd AbstrbdtDodumfnt(Contfnt dbtb, AttributfContfxt dontfxt) {
        tiis.dbtb = dbtb;
        tiis.dontfxt = dontfxt;
        bidiRoot = nfw BidiRootElfmfnt();

        if (dffbultI18NPropfrty == null) {
            // dftfrminf dffbult sftting for i18n support
            String o = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<String>() {
                    publid String run() {
                        rfturn Systfm.gftPropfrty(I18NPropfrty);
                    }
                }
            );
            if (o != null) {
                dffbultI18NPropfrty = Boolfbn.vblufOf(o);
            } flsf {
                dffbultI18NPropfrty = Boolfbn.FALSE;
            }
        }
        putPropfrty( I18NPropfrty, dffbultI18NPropfrty);

        //REMIND(bdb) Tiis drfbtfs bn initibl bidi flfmfnt to bddount for
        //tif \n tibt fxists by dffbult in tif dontfnt.  Doing it tiis wby
        //sffms to fxposf b littlf too mudi knowlfdgf of tif dontfnt givfn
        //to us by tif sub-dlbss.  Considfr ibving tif sub-dlbss' donstrudtor
        //mbkf bn initibl dbll to insfrtUpdbtf.
        writfLodk();
        try {
            Elfmfnt[] p = nfw Elfmfnt[1];
            p[0] = nfw BidiElfmfnt( bidiRoot, 0, 1, 0 );
            bidiRoot.rfplbdf(0,0,p);
        } finblly {
            writfUnlodk();
        }
    }

    /**
     * Supports mbnbging b sft of propfrtifs. Cbllfrs
     * dbn usf tif <dodf>dodumfntPropfrtifs</dodf> didtionbry
     * to bnnotbtf tif dodumfnt witi dodumfnt-widf propfrtifs.
     *
     * @rfturn b non-<dodf>null</dodf> <dodf>Didtionbry</dodf>
     * @sff #sftDodumfntPropfrtifs
     */
    publid Didtionbry<Objfdt,Objfdt> gftDodumfntPropfrtifs() {
        if (dodumfntPropfrtifs == null) {
            dodumfntPropfrtifs = nfw Hbsitbblf<Objfdt, Objfdt>(2);
        }
        rfturn dodumfntPropfrtifs;
    }

    /**
     * Rfplbdfs tif dodumfnt propfrtifs didtionbry for tiis dodumfnt.
     *
     * @pbrbm x tif nfw didtionbry
     * @sff #gftDodumfntPropfrtifs
     */
    publid void sftDodumfntPropfrtifs(Didtionbry<Objfdt,Objfdt> x) {
        dodumfntPropfrtifs = x;
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     *
     * @pbrbm f tif fvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfInsfrtUpdbtf(DodumfntEvfnt f) {
        notifyingListfnfrs = truf;
        try {
            // Gubrbntffd to rfturn b non-null brrby
            Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
            // Prodfss tif listfnfrs lbst to first, notifying
            // tiosf tibt brf intfrfstfd in tiis fvfnt
            for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
                if (listfnfrs[i]==DodumfntListfnfr.dlbss) {
                    // Lbzily drfbtf tif fvfnt:
                    // if (f == null)
                    // f = nfw ListSflfdtionEvfnt(tiis, firstIndfx, lbstIndfx);
                    ((DodumfntListfnfr)listfnfrs[i+1]).insfrtUpdbtf(f);
                }
            }
        } finblly {
            notifyingListfnfrs = fblsf;
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     *
     * @pbrbm f tif fvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfCibngfdUpdbtf(DodumfntEvfnt f) {
        notifyingListfnfrs = truf;
        try {
            // Gubrbntffd to rfturn b non-null brrby
            Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
            // Prodfss tif listfnfrs lbst to first, notifying
            // tiosf tibt brf intfrfstfd in tiis fvfnt
            for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
                if (listfnfrs[i]==DodumfntListfnfr.dlbss) {
                    // Lbzily drfbtf tif fvfnt:
                    // if (f == null)
                    // f = nfw ListSflfdtionEvfnt(tiis, firstIndfx, lbstIndfx);
                    ((DodumfntListfnfr)listfnfrs[i+1]).dibngfdUpdbtf(f);
                }
            }
        } finblly {
            notifyingListfnfrs = fblsf;
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     *
     * @pbrbm f tif fvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfRfmovfUpdbtf(DodumfntEvfnt f) {
        notifyingListfnfrs = truf;
        try {
            // Gubrbntffd to rfturn b non-null brrby
            Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
            // Prodfss tif listfnfrs lbst to first, notifying
            // tiosf tibt brf intfrfstfd in tiis fvfnt
            for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
                if (listfnfrs[i]==DodumfntListfnfr.dlbss) {
                    // Lbzily drfbtf tif fvfnt:
                    // if (f == null)
                    // f = nfw ListSflfdtionEvfnt(tiis, firstIndfx, lbstIndfx);
                    ((DodumfntListfnfr)listfnfrs[i+1]).rfmovfUpdbtf(f);
                }
            }
        } finblly {
            notifyingListfnfrs = fblsf;
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     *
     * @pbrbm f tif fvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfUndobblfEditUpdbtf(UndobblfEditEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==UndobblfEditListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                // if (f == null)
                // f = nfw ListSflfdtionEvfnt(tiis, firstIndfx, lbstIndfx);
                ((UndobblfEditListfnfr)listfnfrs[i+1]).undobblfEditHbppfnfd(f);
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis dodumfnt.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * dodumfnt <dodf>d</dodf>
     * for its dodumfnt listfnfrs witi tif following dodf:
     *
     * <prf>DodumfntListfnfr[] mls = (DodumfntListfnfr[])(d.gftListfnfrs(DodumfntListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis domponfnt,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftDodumfntListfnfrs
     * @sff #gftUndobblfEditListfnfrs
     *
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        rfturn listfnfrList.gftListfnfrs(listfnfrTypf);
    }

    /**
     * Gfts tif bsyndironous lobding priority.  If lfss tibn zfro,
     * tif dodumfnt siould not bf lobdfd bsyndironously.
     *
     * @rfturn tif bsyndironous lobding priority, or <dodf>-1</dodf>
     *   if tif dodumfnt siould not bf lobdfd bsyndironously
     */
    publid int gftAsyndironousLobdPriority() {
        Intfgfr lobdPriority = (Intfgfr)
            gftPropfrty(AbstrbdtDodumfnt.AsyndLobdPriority);
        if (lobdPriority != null) {
            rfturn lobdPriority.intVbluf();
        }
        rfturn -1;
    }

    /**
     * Sfts tif bsyndironous lobding priority.
     * @pbrbm p tif nfw bsyndironous lobding priority; b vbluf
     *   lfss tibn zfro indidbtfs tibt tif dodumfnt siould not bf
     *   lobdfd bsyndironously
     */
    publid void sftAsyndironousLobdPriority(int p) {
        Intfgfr lobdPriority = (p >= 0) ? Intfgfr.vblufOf(p) : null;
        putPropfrty(AbstrbdtDodumfnt.AsyndLobdPriority, lobdPriority);
    }

    /**
     * Sfts tif <dodf>DodumfntFiltfr</dodf>. Tif <dodf>DodumfntFiltfr</dodf>
     * is pbssfd <dodf>insfrt</dodf> bnd <dodf>rfmovf</dodf> to donditionblly
     * bllow insfrting/dflfting of tif tfxt.  A <dodf>null</dodf> vbluf
     * indidbtfs tibt no filtfring will oddur.
     *
     * @pbrbm filtfr tif <dodf>DodumfntFiltfr</dodf> usfd to donstrbin tfxt
     * @sff #gftDodumfntFiltfr
     * @sindf 1.4
     */
    publid void sftDodumfntFiltfr(DodumfntFiltfr filtfr) {
        dodumfntFiltfr = filtfr;
    }

    /**
     * Rfturns tif <dodf>DodumfntFiltfr</dodf> tibt is rfsponsiblf for
     * filtfring of insfrtion/rfmovbl. A <dodf>null</dodf> rfturn vbluf
     * implifs no filtfring is to oddur.
     *
     * @sindf 1.4
     * @sff #sftDodumfntFiltfr
     * @rfturn tif DodumfntFiltfr
     */
    publid DodumfntFiltfr gftDodumfntFiltfr() {
        rfturn dodumfntFiltfr;
    }

    // --- Dodumfnt mftiods -----------------------------------------

    /**
     * Tiis bllows tif modfl to bf sbffly rfndfrfd in tif prfsfndf
     * of durrfndy, if tif modfl supports bfing updbtfd bsyndironously.
     * Tif givfn runnbblf will bf fxfdutfd in b wby tibt bllows it
     * to sbffly rfbd tif modfl witi no dibngfs wiilf tif runnbblf
     * is bfing fxfdutfd.  Tif runnbblf itsflf mby <fm>not</fm>
     * mbkf bny mutbtions.
     * <p>
     * Tiis is implfmfntfd to bdquirf b rfbd lodk for tif durbtion
     * of tif runnbblfs fxfdution.  Tifrf mby bf multiplf runnbblfs
     * fxfduting bt tif sbmf timf, bnd bll writfrs will bf blodkfd
     * wiilf tifrf brf bdtivf rfndfring runnbblfs.  If tif runnbblf
     * tirows bn fxdfption, its lodk will bf sbffly rflfbsfd.
     * Tifrf is no protfdtion bgbinst b runnbblf tibt nfvfr fxits,
     * wiidi will ffffdtivfly lfbvf tif dodumfnt lodkfd for it's
     * lifftimf.
     * <p>
     * If tif givfn runnbblf bttfmpts to mbkf bny mutbtions in
     * tiis implfmfntbtion, b dfbdlodk will oddur.  Tifrf is
     * no trbdking of individubl rfndfring tirfbds to fnbblf
     * dftfdting tiis situbtion, but b subdlbss dould indur
     * tif ovfrifbd of trbdking tifm bnd tirowing bn frror.
     * <p>
     * Tiis mftiod is tirfbd sbff, bltiougi most Swing mftiods
     * brf not. Plfbsf sff
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy
     * in Swing</A> for morf informbtion.
     *
     * @pbrbm r tif rfndfrfr to fxfdutf
     */
    publid void rfndfr(Runnbblf r) {
        rfbdLodk();
        try {
            r.run();
        } finblly {
            rfbdUnlodk();
        }
    }

    /**
     * Rfturns tif lfngti of tif dbtb.  Tiis is tif numbfr of
     * dibrbdtfrs of dontfnt tibt rfprfsfnts tif usfrs dbtb.
     *
     * @rfturn tif lfngti &gt;= 0
     * @sff Dodumfnt#gftLfngti
     */
    publid int gftLfngti() {
        rfturn dbtb.lfngti() - 1;
    }

    /**
     * Adds b dodumfnt listfnfr for notifidbtion of bny dibngfs.
     *
     * @pbrbm listfnfr tif <dodf>DodumfntListfnfr</dodf> to bdd
     * @sff Dodumfnt#bddDodumfntListfnfr
     */
    publid void bddDodumfntListfnfr(DodumfntListfnfr listfnfr) {
        listfnfrList.bdd(DodumfntListfnfr.dlbss, listfnfr);
    }

    /**
     * Rfmovfs b dodumfnt listfnfr.
     *
     * @pbrbm listfnfr tif <dodf>DodumfntListfnfr</dodf> to rfmovf
     * @sff Dodumfnt#rfmovfDodumfntListfnfr
     */
    publid void rfmovfDodumfntListfnfr(DodumfntListfnfr listfnfr) {
        listfnfrList.rfmovf(DodumfntListfnfr.dlbss, listfnfr);
    }

    /**
     * Rfturns bn brrby of bll tif dodumfnt listfnfrs
     * rfgistfrfd on tiis dodumfnt.
     *
     * @rfturn bll of tiis dodumfnt's <dodf>DodumfntListfnfr</dodf>s
     *         or bn fmpty brrby if no dodumfnt listfnfrs brf
     *         durrfntly rfgistfrfd
     *
     * @sff #bddDodumfntListfnfr
     * @sff #rfmovfDodumfntListfnfr
     * @sindf 1.4
     */
    publid DodumfntListfnfr[] gftDodumfntListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(DodumfntListfnfr.dlbss);
    }

    /**
     * Adds bn undo listfnfr for notifidbtion of bny dibngfs.
     * Undo/Rfdo opfrbtions pfrformfd on tif <dodf>UndobblfEdit</dodf>
     * will dbusf tif bppropribtf DodumfntEvfnt to bf firfd to kffp
     * tif vifw(s) in synd witi tif modfl.
     *
     * @pbrbm listfnfr tif <dodf>UndobblfEditListfnfr</dodf> to bdd
     * @sff Dodumfnt#bddUndobblfEditListfnfr
     */
    publid void bddUndobblfEditListfnfr(UndobblfEditListfnfr listfnfr) {
        listfnfrList.bdd(UndobblfEditListfnfr.dlbss, listfnfr);
    }

    /**
     * Rfmovfs bn undo listfnfr.
     *
     * @pbrbm listfnfr tif <dodf>UndobblfEditListfnfr</dodf> to rfmovf
     * @sff Dodumfnt#rfmovfDodumfntListfnfr
     */
    publid void rfmovfUndobblfEditListfnfr(UndobblfEditListfnfr listfnfr) {
        listfnfrList.rfmovf(UndobblfEditListfnfr.dlbss, listfnfr);
    }

    /**
     * Rfturns bn brrby of bll tif undobblf fdit listfnfrs
     * rfgistfrfd on tiis dodumfnt.
     *
     * @rfturn bll of tiis dodumfnt's <dodf>UndobblfEditListfnfr</dodf>s
     *         or bn fmpty brrby if no undobblf fdit listfnfrs brf
     *         durrfntly rfgistfrfd
     *
     * @sff #bddUndobblfEditListfnfr
     * @sff #rfmovfUndobblfEditListfnfr
     *
     * @sindf 1.4
     */
    publid UndobblfEditListfnfr[] gftUndobblfEditListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(UndobblfEditListfnfr.dlbss);
    }

    /**
     * A donvfnifndf mftiod for looking up b propfrty vbluf. It is
     * fquivblfnt to:
     * <prf>
     * gftDodumfntPropfrtifs().gft(kfy);
     * </prf>
     *
     * @pbrbm kfy tif non-<dodf>null</dodf> propfrty kfy
     * @rfturn tif vbluf of tiis propfrty or <dodf>null</dodf>
     * @sff #gftDodumfntPropfrtifs
     */
    publid finbl Objfdt gftPropfrty(Objfdt kfy) {
        rfturn gftDodumfntPropfrtifs().gft(kfy);
    }


    /**
     * A donvfnifndf mftiod for storing up b propfrty vbluf.  It is
     * fquivblfnt to:
     * <prf>
     * gftDodumfntPropfrtifs().put(kfy, vbluf);
     * </prf>
     * If <dodf>vbluf</dodf> is <dodf>null</dodf> tiis mftiod will
     * rfmovf tif propfrty.
     *
     * @pbrbm kfy tif non-<dodf>null</dodf> kfy
     * @pbrbm vbluf tif propfrty vbluf
     * @sff #gftDodumfntPropfrtifs
     */
    publid finbl void putPropfrty(Objfdt kfy, Objfdt vbluf) {
        if (vbluf != null) {
            gftDodumfntPropfrtifs().put(kfy, vbluf);
        } flsf {
            gftDodumfntPropfrtifs().rfmovf(kfy);
        }
        if( kfy == TfxtAttributf.RUN_DIRECTION
            && Boolfbn.TRUE.fqubls(gftPropfrty(I18NPropfrty)) )
        {
            //REMIND - tiis nffds to flip on tif i18n propfrty if run dir
            //is rtl bnd tif i18n propfrty is not blrfbdy on.
            writfLodk();
            try {
                DffbultDodumfntEvfnt f
                    = nfw DffbultDodumfntEvfnt(0, gftLfngti(),
                                               DodumfntEvfnt.EvfntTypf.INSERT);
                updbtfBidi( f );
            } finblly {
                writfUnlodk();
            }
        }
    }

    /**
     * Rfmovfs somf dontfnt from tif dodumfnt.
     * Rfmoving dontfnt dbusfs b writf lodk to bf ifld wiilf tif
     * bdtubl dibngfs brf tbking plbdf.  Obsfrvfrs brf notififd
     * of tif dibngf on tif tirfbd tibt dbllfd tiis mftiod.
     * <p>
     * Tiis mftiod is tirfbd sbff, bltiougi most Swing mftiods
     * brf not. Plfbsf sff
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy
     * in Swing</A> for morf informbtion.
     *
     * @pbrbm offs tif stbrting offsft &gt;= 0
     * @pbrbm lfn tif numbfr of dibrbdtfrs to rfmovf &gt;= 0
     * @fxdfption BbdLodbtionExdfption  tif givfn rfmovf position is not b vblid
     *   position witiin tif dodumfnt
     * @sff Dodumfnt#rfmovf
     */
    publid void rfmovf(int offs, int lfn) tirows BbdLodbtionExdfption {
        DodumfntFiltfr filtfr = gftDodumfntFiltfr();

        writfLodk();
        try {
            if (filtfr != null) {
                filtfr.rfmovf(gftFiltfrBypbss(), offs, lfn);
            }
            flsf {
                ibndlfRfmovf(offs, lfn);
            }
        } finblly {
            writfUnlodk();
        }
    }

    /**
     * Pfrforms tif bdtubl work of tif rfmovf. It is bssumfd tif dbllfr
     * will ibvf obtbinfd b <dodf>writfLodk</dodf> bfforf invoking tiis.
     */
    void ibndlfRfmovf(int offs, int lfn) tirows BbdLodbtionExdfption {
        if (lfn > 0) {
            if (offs < 0 || (offs + lfn) > gftLfngti()) {
                tirow nfw BbdLodbtionExdfption("Invblid rfmovf",
                                               gftLfngti() + 1);
            }
            DffbultDodumfntEvfnt ding =
                    nfw DffbultDodumfntEvfnt(offs, lfn, DodumfntEvfnt.EvfntTypf.REMOVE);

            boolfbn isComposfdTfxtElfmfnt;
            // Cifdk wiftifr tif position of intfrfst is tif domposfd tfxt
            isComposfdTfxtElfmfnt = Utilitifs.isComposfdTfxtElfmfnt(tiis, offs);

            rfmovfUpdbtf(ding);
            UndobblfEdit u = dbtb.rfmovf(offs, lfn);
            if (u != null) {
                ding.bddEdit(u);
            }
            postRfmovfUpdbtf(ding);
            // Mbrk tif fdit bs donf.
            ding.fnd();
            firfRfmovfUpdbtf(ding);
            // only firf undo if Contfnt implfmfntbtion supports it
            // undo for tif domposfd tfxt is not supportfd for now
            if ((u != null) && !isComposfdTfxtElfmfnt) {
                firfUndobblfEditUpdbtf(nfw UndobblfEditEvfnt(tiis, ding));
            }
        }
    }

    /**
     * Dflftfs tif rfgion of tfxt from <dodf>offsft</dodf> to
     * <dodf>offsft + lfngti</dodf>, bnd rfplbdfs it witi <dodf>tfxt</dodf>.
     * It is up to tif implfmfntbtion bs to iow tiis is implfmfntfd, somf
     * implfmfntbtions mby trfbt tiis bs two distindt opfrbtions: b rfmovf
     * followfd by bn insfrt, otifrs mby trfbt tif rfplbdf bs onf btomid
     * opfrbtion.
     *
     * @pbrbm offsft indfx of diild flfmfnt
     * @pbrbm lfngti lfngti of tfxt to dflftf, mby bf 0 indidbting don't
     *               dflftf bnytiing
     * @pbrbm tfxt tfxt to insfrt, <dodf>null</dodf> indidbtfs no tfxt to insfrt
     * @pbrbm bttrs AttributfSft indidbting bttributfs of insfrtfd tfxt,
     *              <dodf>null</dodf>
     *              is lfgbl, bnd typidblly trfbtfd bs bn fmpty bttributfsft,
     *              but fxbdt intfrprftbtion is lfft to tif subdlbss
     * @fxdfption BbdLodbtionExdfption tif givfn position is not b vblid
     *            position witiin tif dodumfnt
     * @sindf 1.4
     */
    publid void rfplbdf(int offsft, int lfngti, String tfxt,
                        AttributfSft bttrs) tirows BbdLodbtionExdfption {
        if (lfngti == 0 && (tfxt == null || tfxt.lfngti() == 0)) {
            rfturn;
        }
        DodumfntFiltfr filtfr = gftDodumfntFiltfr();

        writfLodk();
        try {
            if (filtfr != null) {
                filtfr.rfplbdf(gftFiltfrBypbss(), offsft, lfngti, tfxt,
                               bttrs);
            }
            flsf {
                if (lfngti > 0) {
                    rfmovf(offsft, lfngti);
                }
                if (tfxt != null && tfxt.lfngti() > 0) {
                    insfrtString(offsft, tfxt, bttrs);
                }
            }
        } finblly {
            writfUnlodk();
        }
    }

    /**
     * Insfrts somf dontfnt into tif dodumfnt.
     * Insfrting dontfnt dbusfs b writf lodk to bf ifld wiilf tif
     * bdtubl dibngfs brf tbking plbdf, followfd by notifidbtion
     * to tif obsfrvfrs on tif tirfbd tibt grbbbfd tif writf lodk.
     * <p>
     * Tiis mftiod is tirfbd sbff, bltiougi most Swing mftiods
     * brf not. Plfbsf sff
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy
     * in Swing</A> for morf informbtion.
     *
     * @pbrbm offs tif stbrting offsft &gt;= 0
     * @pbrbm str tif string to insfrt; dofs notiing witi null/fmpty strings
     * @pbrbm b tif bttributfs for tif insfrtfd dontfnt
     * @fxdfption BbdLodbtionExdfption  tif givfn insfrt position is not b vblid
     *   position witiin tif dodumfnt
     * @sff Dodumfnt#insfrtString
     */
    publid void insfrtString(int offs, String str, AttributfSft b) tirows BbdLodbtionExdfption {
        if ((str == null) || (str.lfngti() == 0)) {
            rfturn;
        }
        DodumfntFiltfr filtfr = gftDodumfntFiltfr();

        writfLodk();

        try {
            if (filtfr != null) {
                filtfr.insfrtString(gftFiltfrBypbss(), offs, str, b);
            } flsf {
                ibndlfInsfrtString(offs, str, b);
            }
        } finblly {
            writfUnlodk();
        }
    }

    /**
     * Pfrforms tif bdtubl work of insfrting tif tfxt; it is bssumfd tif
     * dbllfr ibs obtbinfd b writf lodk bfforf invoking tiis.
     */
    privbtf void ibndlfInsfrtString(int offs, String str, AttributfSft b)
            tirows BbdLodbtionExdfption {
        if ((str == null) || (str.lfngti() == 0)) {
            rfturn;
        }
        UndobblfEdit u = dbtb.insfrtString(offs, str);
        DffbultDodumfntEvfnt f =
            nfw DffbultDodumfntEvfnt(offs, str.lfngti(), DodumfntEvfnt.EvfntTypf.INSERT);
        if (u != null) {
            f.bddEdit(u);
        }

        // sff if domplfx glypi lbyout support is nffdfd
        if( gftPropfrty(I18NPropfrty).fqubls( Boolfbn.FALSE ) ) {
            // if b dffbult dirfdtion of rigit-to-lfft ibs bffn spfdififd,
            // wf wbnt domplfx lbyout fvfn if tif tfxt is bll lfft to rigit.
            Objfdt d = gftPropfrty(TfxtAttributf.RUN_DIRECTION);
            if ((d != null) && (d.fqubls(TfxtAttributf.RUN_DIRECTION_RTL))) {
                putPropfrty( I18NPropfrty, Boolfbn.TRUE);
            } flsf {
                dibr[] dibrs = str.toCibrArrby();
                if (SwingUtilitifs2.isComplfxLbyout(dibrs, 0, dibrs.lfngti)) {
                    putPropfrty( I18NPropfrty, Boolfbn.TRUE);
                }
            }
        }

        insfrtUpdbtf(f, b);
        // Mbrk tif fdit bs donf.
        f.fnd();
        firfInsfrtUpdbtf(f);
        // only firf undo if Contfnt implfmfntbtion supports it
        // undo for tif domposfd tfxt is not supportfd for now
        if (u != null && (b == null || !b.isDffinfd(StylfConstbnts.ComposfdTfxtAttributf))) {
            firfUndobblfEditUpdbtf(nfw UndobblfEditEvfnt(tiis, f));
        }
    }

    /**
     * Gfts b sfqufndf of tfxt from tif dodumfnt.
     *
     * @pbrbm offsft tif stbrting offsft &gt;= 0
     * @pbrbm lfngti tif numbfr of dibrbdtfrs to rftrifvf &gt;= 0
     * @rfturn tif tfxt
     * @fxdfption BbdLodbtionExdfption  tif rbngf givfn indludfs b position
     *   tibt is not b vblid position witiin tif dodumfnt
     * @sff Dodumfnt#gftTfxt
     */
    publid String gftTfxt(int offsft, int lfngti) tirows BbdLodbtionExdfption {
        if (lfngti < 0) {
            tirow nfw BbdLodbtionExdfption("Lfngti must bf positivf", lfngti);
        }
        String str = dbtb.gftString(offsft, lfngti);
        rfturn str;
    }

    /**
     * Fftdifs tif tfxt dontbinfd witiin tif givfn portion
     * of tif dodumfnt.
     * <p>
     * If tif pbrtiblRfturn propfrty on tif txt pbrbmftfr is fblsf, tif
     * dbtb rfturnfd in tif Sfgmfnt will bf tif fntirf lfngti rfqufstfd bnd
     * mby or mby not bf b dopy dfpfnding upon iow tif dbtb wbs storfd.
     * If tif pbrtiblRfturn propfrty is truf, only tif bmount of tfxt tibt
     * dbn bf rfturnfd witiout drfbting b dopy is rfturnfd.  Using pbrtibl
     * rfturns will givf bfttfr pfrformbndf for situbtions wifrf lbrgf
     * pbrts of tif dodumfnt brf bfing sdbnnfd.  Tif following is bn fxbmplf
     * of using tif pbrtibl rfturn to bddfss tif fntirf dodumfnt:
     *
     * <prf>
     * &nbsp; int nlfft = dod.gftDodumfntLfngti();
     * &nbsp; Sfgmfnt tfxt = nfw Sfgmfnt();
     * &nbsp; int offs = 0;
     * &nbsp; tfxt.sftPbrtiblRfturn(truf);
     * &nbsp; wiilf (nlfft &gt; 0) {
     * &nbsp;     dod.gftTfxt(offs, nlfft, tfxt);
     * &nbsp;     // do somftiing witi tfxt
     * &nbsp;     nlfft -= tfxt.dount;
     * &nbsp;     offs += tfxt.dount;
     * &nbsp; }
     * </prf>
     *
     * @pbrbm offsft tif stbrting offsft &gt;= 0
     * @pbrbm lfngti tif numbfr of dibrbdtfrs to rftrifvf &gt;= 0
     * @pbrbm txt tif Sfgmfnt objfdt to rftrifvf tif tfxt into
     * @fxdfption BbdLodbtionExdfption  tif rbngf givfn indludfs b position
     *   tibt is not b vblid position witiin tif dodumfnt
     */
    publid void gftTfxt(int offsft, int lfngti, Sfgmfnt txt) tirows BbdLodbtionExdfption {
        if (lfngti < 0) {
            tirow nfw BbdLodbtionExdfption("Lfngti must bf positivf", lfngti);
        }
        dbtb.gftCibrs(offsft, lfngti, txt);
    }

    /**
     * Rfturns b position tibt will trbdk dibngf bs tif dodumfnt
     * is bltfrfd.
     * <p>
     * Tiis mftiod is tirfbd sbff, bltiougi most Swing mftiods
     * brf not. Plfbsf sff
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy
     * in Swing</A> for morf informbtion.
     *
     * @pbrbm offs tif position in tif modfl &gt;= 0
     * @rfturn tif position
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Dodumfnt#drfbtfPosition
     */
    publid syndironizfd Position drfbtfPosition(int offs) tirows BbdLodbtionExdfption {
        rfturn dbtb.drfbtfPosition(offs);
    }

    /**
     * Rfturns b position tibt rfprfsfnts tif stbrt of tif dodumfnt.  Tif
     * position rfturnfd dbn bf dountfd on to trbdk dibngf bnd stby
     * lodbtfd bt tif bfginning of tif dodumfnt.
     *
     * @rfturn tif position
     */
    publid finbl Position gftStbrtPosition() {
        Position p;
        try {
            p = drfbtfPosition(0);
        } dbtdi (BbdLodbtionExdfption bl) {
            p = null;
        }
        rfturn p;
    }

    /**
     * Rfturns b position tibt rfprfsfnts tif fnd of tif dodumfnt.  Tif
     * position rfturnfd dbn bf dountfd on to trbdk dibngf bnd stby
     * lodbtfd bt tif fnd of tif dodumfnt.
     *
     * @rfturn tif position
     */
    publid finbl Position gftEndPosition() {
        Position p;
        try {
            p = drfbtfPosition(dbtb.lfngti());
        } dbtdi (BbdLodbtionExdfption bl) {
            p = null;
        }
        rfturn p;
    }

    /**
     * Gfts bll root flfmfnts dffinfd.  Typidblly, tifrf
     * will only bf onf so tif dffbult implfmfntbtion
     * is to rfturn tif dffbult root flfmfnt.
     *
     * @rfturn tif root flfmfnt
     */
    publid Elfmfnt[] gftRootElfmfnts() {
        Elfmfnt[] flfms = nfw Elfmfnt[2];
        flfms[0] = gftDffbultRootElfmfnt();
        flfms[1] = gftBidiRootElfmfnt();
        rfturn flfms;
    }

    /**
     * Rfturns tif root flfmfnt tibt vifws siould bf bbsfd upon
     * unlfss somf otifr mfdibnism for bssigning vifws to flfmfnt
     * strudturfs is providfd.
     *
     * @rfturn tif root flfmfnt
     * @sff Dodumfnt#gftDffbultRootElfmfnt
     */
    publid bbstrbdt Elfmfnt gftDffbultRootElfmfnt();

    // ---- lodbl mftiods -----------------------------------------

    /**
     * Rfturns tif <dodf>FiltfrBypbss</dodf>. Tiis will drfbtf onf if onf
     * dofs not yft fxist.
     */
    privbtf DodumfntFiltfr.FiltfrBypbss gftFiltfrBypbss() {
        if (filtfrBypbss == null) {
            filtfrBypbss = nfw DffbultFiltfrBypbss();
        }
        rfturn filtfrBypbss;
    }

    /**
     * Rfturns tif root flfmfnt of tif bidirfdtionbl strudturf for tiis
     * dodumfnt.  Its diildrfn rfprfsfnt dibrbdtfr runs witi b givfn
     * Unidodf bidi lfvfl.
     */
    publid Elfmfnt gftBidiRootElfmfnt() {
        rfturn bidiRoot;
    }

    /**
     * Rfturns truf if tif tfxt in tif rbngf <dodf>p0</dodf> to
     * <dodf>p1</dodf> is lfft to rigit.
     */
    stbtid boolfbn isLfftToRigit(Dodumfnt dod, int p0, int p1) {
        if (Boolfbn.TRUE.fqubls(dod.gftPropfrty(I18NPropfrty))) {
            if (dod instbndfof AbstrbdtDodumfnt) {
                AbstrbdtDodumfnt bdod = (AbstrbdtDodumfnt) dod;
                Elfmfnt bidiRoot = bdod.gftBidiRootElfmfnt();
                int indfx = bidiRoot.gftElfmfntIndfx(p0);
                Elfmfnt bidiElfm = bidiRoot.gftElfmfnt(indfx);
                if (bidiElfm.gftEndOffsft() >= p1) {
                    AttributfSft bidiAttrs = bidiElfm.gftAttributfs();
                    rfturn ((StylfConstbnts.gftBidiLfvfl(bidiAttrs) % 2) == 0);
                }
            }
        }
        rfturn truf;
    }

    /**
     * Gft tif pbrbgrbpi flfmfnt dontbining tif givfn position.  Sub-dlbssfs
     * must dffinf for tifmsflvfs wibt fxbdtly donstitutfs b pbrbgrbpi.  Tify
     * siould kffp in mind iowfvfr tibt b pbrbgrbpi siould bt lfbst bf tif
     * unit of tfxt ovfr wiidi to run tif Unidodf bidirfdtionbl blgoritim.
     *
     * @pbrbm pos tif stbrting offsft &gt;= 0
     * @rfturn tif flfmfnt */
    publid bbstrbdt Elfmfnt gftPbrbgrbpiElfmfnt(int pos);


    /**
     * Fftdifs tif dontfxt for mbnbging bttributfs.  Tiis
     * mftiod ffffdtivfly fstbblisifs tif strbtfgy usfd
     * for domprfssing AttributfSft informbtion.
     *
     * @rfturn tif dontfxt
     */
    protfdtfd finbl AttributfContfxt gftAttributfContfxt() {
        rfturn dontfxt;
    }

    /**
     * Updbtfs dodumfnt strudturf bs b rfsult of tfxt insfrtion.  Tiis
     * will ibppfn witiin b writf lodk.  If b subdlbss of
     * tiis dlbss rfimplfmfnts tiis mftiod, it siould dflfgbtf to tif
     * supfrdlbss bs wfll.
     *
     * @pbrbm ding b dfsdription of tif dibngf
     * @pbrbm bttr tif bttributfs for tif dibngf
     */
    protfdtfd void insfrtUpdbtf(DffbultDodumfntEvfnt ding, AttributfSft bttr) {
        if( gftPropfrty(I18NPropfrty).fqubls( Boolfbn.TRUE ) )
            updbtfBidi( ding );

        // Cifdk if b multi bytf is fndountfrfd in tif insfrtfd tfxt.
        if (ding.typf == DodumfntEvfnt.EvfntTypf.INSERT &&
                        ding.gftLfngti() > 0 &&
                        !Boolfbn.TRUE.fqubls(gftPropfrty(MultiBytfPropfrty))) {
            Sfgmfnt sfgmfnt = SfgmfntCbdif.gftSibrfdSfgmfnt();
            try {
                gftTfxt(ding.gftOffsft(), ding.gftLfngti(), sfgmfnt);
                sfgmfnt.first();
                do {
                    if ((int)sfgmfnt.durrfnt() > 255) {
                        putPropfrty(MultiBytfPropfrty, Boolfbn.TRUE);
                        brfbk;
                    }
                } wiilf (sfgmfnt.nfxt() != Sfgmfnt.DONE);
            } dbtdi (BbdLodbtionExdfption blf) {
                // Siould nfvfr ibppfn
            }
            SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(sfgmfnt);
        }
    }

    /**
     * Updbtfs bny dodumfnt strudturf bs b rfsult of tfxt rfmovbl.  Tiis
     * mftiod is dbllfd bfforf tif tfxt is bdtublly rfmovfd from tif Contfnt.
     * Tiis will ibppfn witiin b writf lodk. If b subdlbss
     * of tiis dlbss rfimplfmfnts tiis mftiod, it siould dflfgbtf to tif
     * supfrdlbss bs wfll.
     *
     * @pbrbm ding b dfsdription of tif dibngf
     */
    protfdtfd void rfmovfUpdbtf(DffbultDodumfntEvfnt ding) {
    }

    /**
     * Updbtfs bny dodumfnt strudturf bs b rfsult of tfxt rfmovbl.  Tiis
     * mftiod is dbllfd bftfr tif tfxt ibs bffn rfmovfd from tif Contfnt.
     * Tiis will ibppfn witiin b writf lodk. If b subdlbss
     * of tiis dlbss rfimplfmfnts tiis mftiod, it siould dflfgbtf to tif
     * supfrdlbss bs wfll.
     *
     * @pbrbm ding b dfsdription of tif dibngf
     */
    protfdtfd void postRfmovfUpdbtf(DffbultDodumfntEvfnt ding) {
        if( gftPropfrty(I18NPropfrty).fqubls( Boolfbn.TRUE ) )
            updbtfBidi( ding );
    }


    /**
     * Updbtf tif bidi flfmfnt strudturf bs b rfsult of tif givfn dibngf
     * to tif dodumfnt.  Tif givfn dibngf will bf updbtfd to rfflfdt tif
     * dibngfs mbdf to tif bidi strudturf.
     *
     * Tiis mftiod bssumfs tibt fvfry offsft in tif modfl is dontbinfd in
     * fxbdtly onf pbrbgrbpi.  Tiis mftiod blso bssumfs tibt it is dbllfd
     * bftfr tif dibngf is mbdf to tif dffbult flfmfnt strudturf.
     */
    void updbtfBidi( DffbultDodumfntEvfnt ding ) {

        // Cbldulbtf tif rbngf of pbrbgrbpis bfffdtfd by tif dibngf.
        int firstPStbrt;
        int lbstPEnd;
        if( ding.typf == DodumfntEvfnt.EvfntTypf.INSERT
            || ding.typf == DodumfntEvfnt.EvfntTypf.CHANGE )
        {
            int dingStbrt = ding.gftOffsft();
            int dingEnd =  dingStbrt + ding.gftLfngti();
            firstPStbrt = gftPbrbgrbpiElfmfnt(dingStbrt).gftStbrtOffsft();
            lbstPEnd = gftPbrbgrbpiElfmfnt(dingEnd).gftEndOffsft();
        } flsf if( ding.typf == DodumfntEvfnt.EvfntTypf.REMOVE ) {
            Elfmfnt pbrbgrbpi = gftPbrbgrbpiElfmfnt( ding.gftOffsft() );
            firstPStbrt = pbrbgrbpi.gftStbrtOffsft();
            lbstPEnd = pbrbgrbpi.gftEndOffsft();
        } flsf {
            tirow nfw Error("Intfrnbl frror: unknown fvfnt typf.");
        }
        //Systfm.out.println("updbtfBidi: firstPStbrt = " + firstPStbrt + " lbstPEnd = " + lbstPEnd );


        // Cbldulbtf tif bidi lfvfls for tif bfffdtfd rbngf of pbrbgrbpis.  Tif
        // lfvfls brrby will dontbin b bidi lfvfl for fbdi dibrbdtfr in tif
        // bfffdtfd tfxt.
        bytf lfvfls[] = dbldulbtfBidiLfvfls( firstPStbrt, lbstPEnd );


        Vfdtor<Elfmfnt> nfwElfmfnts = nfw Vfdtor<Elfmfnt>();

        // Cbldulbtf tif first spbn of dibrbdtfrs in tif bfffdtfd rbngf witi
        // tif sbmf bidi lfvfl.  If tiis lfvfl is tif sbmf bs tif lfvfl of tif
        // prfvious bidi flfmfnt (tif fxisting bidi flfmfnt dontbining
        // firstPStbrt-1), tifn mfrgf in tif prfvious flfmfnt.  If not, but
        // tif prfvious flfmfnt ovfrlbps tif bfffdtfd rbngf, trundbtf tif
        // prfvious flfmfnt bt firstPStbrt.
        int firstSpbnStbrt = firstPStbrt;
        int rfmovfFromIndfx = 0;
        if( firstSpbnStbrt > 0 ) {
            int prfvElfmIndfx = bidiRoot.gftElfmfntIndfx(firstPStbrt-1);
            rfmovfFromIndfx = prfvElfmIndfx;
            Elfmfnt prfvElfm = bidiRoot.gftElfmfnt(prfvElfmIndfx);
            int prfvLfvfl=StylfConstbnts.gftBidiLfvfl(prfvElfm.gftAttributfs());
            //Systfm.out.println("drfbtfbidiElfmfnts: prfvElfm= " + prfvElfm  + " prfvLfvfl= " + prfvLfvfl + "lfvfl[0] = " + lfvfls[0]);
            if( prfvLfvfl==lfvfls[0] ) {
                firstSpbnStbrt = prfvElfm.gftStbrtOffsft();
            } flsf if( prfvElfm.gftEndOffsft() > firstPStbrt ) {
                nfwElfmfnts.bddElfmfnt(nfw BidiElfmfnt(bidiRoot,
                                                       prfvElfm.gftStbrtOffsft(),
                                                       firstPStbrt, prfvLfvfl));
            } flsf {
                rfmovfFromIndfx++;
            }
        }

        int firstSpbnEnd = 0;
        wiilf((firstSpbnEnd<lfvfls.lfngti) && (lfvfls[firstSpbnEnd]==lfvfls[0]))
            firstSpbnEnd++;


        // Cbldulbtf tif lbst spbn of dibrbdtfrs in tif bfffdtfd rbngf witi
        // tif sbmf bidi lfvfl.  If tiis lfvfl is tif sbmf bs tif lfvfl of tif
        // nfxt bidi flfmfnt (tif fxisting bidi flfmfnt dontbining lbstPEnd),
        // tifn mfrgf in tif nfxt flfmfnt.  If not, but tif nfxt flfmfnt
        // ovfrlbps tif bfffdtfd rbngf, bdjust tif nfxt flfmfnt to stbrt bt
        // lbstPEnd.
        int lbstSpbnEnd = lbstPEnd;
        Elfmfnt nfwNfxtElfm = null;
        int rfmovfToIndfx = bidiRoot.gftElfmfntCount() - 1;
        if( lbstSpbnEnd <= gftLfngti() ) {
            int nfxtElfmIndfx = bidiRoot.gftElfmfntIndfx( lbstPEnd );
            rfmovfToIndfx = nfxtElfmIndfx;
            Elfmfnt nfxtElfm = bidiRoot.gftElfmfnt( nfxtElfmIndfx );
            int nfxtLfvfl = StylfConstbnts.gftBidiLfvfl(nfxtElfm.gftAttributfs());
            if( nfxtLfvfl == lfvfls[lfvfls.lfngti-1] ) {
                lbstSpbnEnd = nfxtElfm.gftEndOffsft();
            } flsf if( nfxtElfm.gftStbrtOffsft() < lbstPEnd ) {
                nfwNfxtElfm = nfw BidiElfmfnt(bidiRoot, lbstPEnd,
                                              nfxtElfm.gftEndOffsft(),
                                              nfxtLfvfl);
            } flsf {
                rfmovfToIndfx--;
            }
        }

        int lbstSpbnStbrt = lfvfls.lfngti;
        wiilf( (lbstSpbnStbrt>firstSpbnEnd)
               && (lfvfls[lbstSpbnStbrt-1]==lfvfls[lfvfls.lfngti-1]) )
            lbstSpbnStbrt--;


        // If tif first bnd lbst spbns brf dontiguous bnd ibvf tif sbmf lfvfl,
        // mfrgf tifm bnd drfbtf b singlf nfw flfmfnt for tif fntirf spbn.
        // Otifrwisf, drfbtf flfmfnts for tif first bnd lbst spbns bs wfll bs
        // bny spbns in bftwffn.
        if((firstSpbnEnd==lbstSpbnStbrt)&&(lfvfls[0]==lfvfls[lfvfls.lfngti-1])){
            nfwElfmfnts.bddElfmfnt(nfw BidiElfmfnt(bidiRoot, firstSpbnStbrt,
                                                   lbstSpbnEnd, lfvfls[0]));
        } flsf {
            // Crfbtf bn flfmfnt for tif first spbn.
            nfwElfmfnts.bddElfmfnt(nfw BidiElfmfnt(bidiRoot, firstSpbnStbrt,
                                                   firstSpbnEnd+firstPStbrt,
                                                   lfvfls[0]));
            // Crfbtf flfmfnts for tif spbns in bftwffn tif first bnd lbst
            for( int i=firstSpbnEnd; i<lbstSpbnStbrt; ) {
                //Systfm.out.println("fxfdutfd linf 872");
                int j;
                for( j=i;  (j<lfvfls.lfngti) && (lfvfls[j] == lfvfls[i]); j++ );
                nfwElfmfnts.bddElfmfnt(nfw BidiElfmfnt(bidiRoot, firstPStbrt+i,
                                                       firstPStbrt+j,
                                                       (int)lfvfls[i]));
                i=j;
            }
            // Crfbtf bn flfmfnt for tif lbst spbn.
            nfwElfmfnts.bddElfmfnt(nfw BidiElfmfnt(bidiRoot,
                                                   lbstSpbnStbrt+firstPStbrt,
                                                   lbstSpbnEnd,
                                                   lfvfls[lfvfls.lfngti-1]));
        }

        if( nfwNfxtElfm != null )
            nfwElfmfnts.bddElfmfnt( nfwNfxtElfm );


        // Cbldulbtf tif sft of fxisting bidi flfmfnts wiidi must bf
        // rfmovfd.
        int rfmovfdElfmCount = 0;
        if( bidiRoot.gftElfmfntCount() > 0 ) {
            rfmovfdElfmCount = rfmovfToIndfx - rfmovfFromIndfx + 1;
        }
        Elfmfnt[] rfmovfdElfms = nfw Elfmfnt[rfmovfdElfmCount];
        for( int i=0; i<rfmovfdElfmCount; i++ ) {
            rfmovfdElfms[i] = bidiRoot.gftElfmfnt(rfmovfFromIndfx+i);
        }

        Elfmfnt[] bddfdElfms = nfw Elfmfnt[ nfwElfmfnts.sizf() ];
        nfwElfmfnts.dopyInto( bddfdElfms );

        // Updbtf tif dibngf rfdord.
        ElfmfntEdit ff = nfw ElfmfntEdit( bidiRoot, rfmovfFromIndfx,
                                          rfmovfdElfms, bddfdElfms );
        ding.bddEdit( ff );

        // Updbtf tif bidi flfmfnt strudturf.
        bidiRoot.rfplbdf( rfmovfFromIndfx, rfmovfdElfms.lfngti, bddfdElfms );
    }


    /**
     * Cbldulbtf tif lfvfls brrby for b rbngf of pbrbgrbpis.
     */
    privbtf bytf[] dbldulbtfBidiLfvfls( int firstPStbrt, int lbstPEnd ) {

        bytf lfvfls[] = nfw bytf[ lbstPEnd - firstPStbrt ];
        int  lfvflsEnd = 0;
        Boolfbn dffbultDirfdtion = null;
        Objfdt d = gftPropfrty(TfxtAttributf.RUN_DIRECTION);
        if (d instbndfof Boolfbn) {
            dffbultDirfdtion = (Boolfbn) d;
        }

        // For fbdi pbrbgrbpi in tif givfn rbngf of pbrbgrbpis, gft its
        // lfvfls brrby bnd bdd it to tif lfvfls brrby for tif fntirf spbn.
        for(int o=firstPStbrt; o<lbstPEnd; ) {
            Elfmfnt p = gftPbrbgrbpiElfmfnt( o );
            int pStbrt = p.gftStbrtOffsft();
            int pEnd = p.gftEndOffsft();

            // dffbult run dirfdtion for tif pbrbgrbpi.  Tiis will bf
            // null if tifrf is no dirfdtion ovfrridf spfdififd (i.f.
            // tif dirfdtion will bf dftfrminfd from tif dontfnt).
            Boolfbn dirfdtion = dffbultDirfdtion;
            d = p.gftAttributfs().gftAttributf(TfxtAttributf.RUN_DIRECTION);
            if (d instbndfof Boolfbn) {
                dirfdtion = (Boolfbn) d;
            }

            //Systfm.out.println("updbtfBidi: pbrbgrbpi stbrt = " + pStbrt + " pbrbgrbpi fnd = " + pEnd);

            // Crfbtf b Bidi ovfr tiis pbrbgrbpi tifn gft tif lfvfl
            // brrby.
            Sfgmfnt sfg = SfgmfntCbdif.gftSibrfdSfgmfnt();
            try {
                gftTfxt(pStbrt, pEnd-pStbrt, sfg);
            } dbtdi (BbdLodbtionExdfption f ) {
                tirow nfw Error("Intfrnbl frror: " + f.toString());
            }
            // REMIND(bdb) wf siould rfblly bf using b Sfgmfnt ifrf.
            Bidi bidiAnblyzfr;
            int bidiflbg = Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT;
            if (dirfdtion != null) {
                if (TfxtAttributf.RUN_DIRECTION_LTR.fqubls(dirfdtion)) {
                    bidiflbg = Bidi.DIRECTION_LEFT_TO_RIGHT;
                } flsf {
                    bidiflbg = Bidi.DIRECTION_RIGHT_TO_LEFT;
                }
            }
            bidiAnblyzfr = nfw Bidi(sfg.brrby, sfg.offsft, null, 0, sfg.dount,
                    bidiflbg);
            BidiUtils.gftLfvfls(bidiAnblyzfr, lfvfls, lfvflsEnd);
            lfvflsEnd += bidiAnblyzfr.gftLfngti();

            o =  p.gftEndOffsft();
            SfgmfntCbdif.rflfbsfSibrfdSfgmfnt(sfg);
        }

        // REMIND(bdb) rfmovf tiis dodf wifn dfbugging is donf.
        if( lfvflsEnd != lfvfls.lfngti )
            tirow nfw Error("lfvflsEnd bssfrtion fbilfd.");

        rfturn lfvfls;
    }

    /**
     * Givfs b dibgnostid dump.
     *
     * @pbrbm out tif output strfbm
     */
    publid void dump(PrintStrfbm out) {
        Elfmfnt root = gftDffbultRootElfmfnt();
        if (root instbndfof AbstrbdtElfmfnt) {
            ((AbstrbdtElfmfnt)root).dump(out, 0);
        }
        bidiRoot.dump(out,0);
    }

    /**
     * Gfts tif dontfnt for tif dodumfnt.
     *
     * @rfturn tif dontfnt
     */
    protfdtfd finbl Contfnt gftContfnt() {
        rfturn dbtb;
    }

    /**
     * Crfbtfs b dodumfnt lfbf flfmfnt.
     * Hook tirougi wiidi flfmfnts brf drfbtfd to rfprfsfnt tif
     * dodumfnt strudturf.  Bfdbusf tiis implfmfntbtion kffps
     * strudturf bnd dontfnt sfpbrbtf, flfmfnts grow butombtidblly
     * wifn dontfnt is fxtfndfd so splits of fxisting flfmfnts
     * follow.  Tif dodumfnt itsflf gfts to dfdidf iow to gfnfrbtf
     * flfmfnts to givf flfxibility in tif typf of flfmfnts usfd.
     *
     * @pbrbm pbrfnt tif pbrfnt flfmfnt
     * @pbrbm b tif bttributfs for tif flfmfnt
     * @pbrbm p0 tif bfginning of tif rbngf &gt;= 0
     * @pbrbm p1 tif fnd of tif rbngf &gt;= p0
     * @rfturn tif nfw flfmfnt
     */
    protfdtfd Elfmfnt drfbtfLfbfElfmfnt(Elfmfnt pbrfnt, AttributfSft b, int p0, int p1) {
        rfturn nfw LfbfElfmfnt(pbrfnt, b, p0, p1);
    }

    /**
     * Crfbtfs b dodumfnt brbndi flfmfnt, tibt dbn dontbin otifr flfmfnts.
     *
     * @pbrbm pbrfnt tif pbrfnt flfmfnt
     * @pbrbm b tif bttributfs
     * @rfturn tif flfmfnt
     */
    protfdtfd Elfmfnt drfbtfBrbndiElfmfnt(Elfmfnt pbrfnt, AttributfSft b) {
        rfturn nfw BrbndiElfmfnt(pbrfnt, b);
    }

    // --- Dodumfnt lodking ----------------------------------

    /**
     * Fftdifs tif durrfnt writing tirfbd if tifrf is onf.
     * Tiis dbn bf usfd to distinguisi wiftifr b mftiod is
     * bfing dbllfd bs pbrt of bn fxisting modifidbtion or
     * if b lodk nffds to bf bdquirfd bnd b nfw trbnsbdtion
     * stbrtfd.
     *
     * @rfturn tif tirfbd bdtivfly modifying tif dodumfnt
     *  or <dodf>null</dodf> if tifrf brf no modifidbtions in progrfss
     */
    protfdtfd syndironizfd finbl Tirfbd gftCurrfntWritfr() {
        rfturn durrWritfr;
    }

    /**
     * Adquirfs b lodk to bfgin mutbting tif dodumfnt tiis lodk
     * protfdts.  Tifrf dbn bf no writing, notifidbtion of dibngfs, or
     * rfbding going on in ordfr to gbin tif lodk.  Additionblly b tirfbd is
     * bllowfd to gbin morf tibn onf <dodf>writfLodk</dodf>,
     * bs long bs it dofsn't bttfmpt to gbin bdditionbl <dodf>writfLodk</dodf>s
     * from witiin dodumfnt notifidbtion.  Attfmpting to gbin b
     * <dodf>writfLodk</dodf> from witiin b DodumfntListfnfr notifidbtion will
     * rfsult in bn <dodf>IllfgblStbtfExdfption</dodf>.  Tif bbility
     * to obtbin morf tibn onf <dodf>writfLodk</dodf> pfr tirfbd bllows
     * subdlbssfs to gbin b writfLodk, pfrform b numbfr of opfrbtions, tifn
     * rflfbsf tif lodk.
     * <p>
     * Cblls to <dodf>writfLodk</dodf>
     * must bf bblbndfd witi dblls to <dodf>writfUnlodk</dodf>, flsf tif
     * <dodf>Dodumfnt</dodf> will bf lfft in b lodkfd stbtf so tibt no
     * rfbding or writing dbn bf donf.
     *
     * @fxdfption IllfgblStbtfExdfption tirown on illfgbl lodk
     *  bttfmpt.  If tif dodumfnt is implfmfntfd propfrly, tiis dbn
     *  only ibppfn if b dodumfnt listfnfr bttfmpts to mutbtf tif
     *  dodumfnt.  Tiis situbtion violbtfs tif bfbn fvfnt modfl
     *  wifrf ordfr of dflivfry is not gubrbntffd bnd bll listfnfrs
     *  siould bf notififd bfforf furtifr mutbtions brf bllowfd.
     */
    protfdtfd syndironizfd finbl void writfLodk() {
        try {
            wiilf ((numRfbdfrs > 0) || (durrWritfr != null)) {
                if (Tirfbd.durrfntTirfbd() == durrWritfr) {
                    if (notifyingListfnfrs) {
                        // Assuming onf dofsn't do somftiing wrong in b
                        // subdlbss tiis siould only ibppfn if b
                        // DodumfntListfnfr trifs to mutbtf tif dodumfnt.
                        tirow nfw IllfgblStbtfExdfption(
                                      "Attfmpt to mutbtf in notifidbtion");
                    }
                    numWritfrs++;
                    rfturn;
                }
                wbit();
            }
            durrWritfr = Tirfbd.durrfntTirfbd();
            numWritfrs = 1;
        } dbtdi (IntfrruptfdExdfption f) {
            tirow nfw Error("Intfrruptfd bttfmpt to bdquirf writf lodk");
        }
    }

    /**
     * Rflfbsfs b writf lodk prfviously obtbinfd vib <dodf>writfLodk</dodf>.
     * Aftfr dfdrfmfnting tif lodk dount if tifrf brf no outstbnding lodks
     * tiis will bllow b nfw writfr, or rfbdfrs.
     *
     * @sff #writfLodk
     */
    protfdtfd syndironizfd finbl void writfUnlodk() {
        if (--numWritfrs <= 0) {
            numWritfrs = 0;
            durrWritfr = null;
            notifyAll();
        }
    }

    /**
     * Adquirfs b lodk to bfgin rfbding somf stbtf from tif
     * dodumfnt.  Tifrf dbn bf multiplf rfbdfrs bt tif sbmf timf.
     * Writing blodks tif rfbdfrs until notifidbtion of tif dibngf
     * to tif listfnfrs ibs bffn domplftfd.  Tiis mftiod siould
     * bf usfd vfry dbrffully to bvoid unintfndfd dompromisf
     * of tif dodumfnt.  It siould blwbys bf bblbndfd witi b
     * <dodf>rfbdUnlodk</dodf>.
     *
     * @sff #rfbdUnlodk
     */
    publid syndironizfd finbl void rfbdLodk() {
        try {
            wiilf (durrWritfr != null) {
                if (durrWritfr == Tirfbd.durrfntTirfbd()) {
                    // writfr ibs full rfbd bddfss.... mby try to bdquirf
                    // lodk in notifidbtion
                    rfturn;
                }
                wbit();
            }
            numRfbdfrs += 1;
        } dbtdi (IntfrruptfdExdfption f) {
            tirow nfw Error("Intfrruptfd bttfmpt to bdquirf rfbd lodk");
        }
    }

    /**
     * Dofs b rfbd unlodk.  Tiis signbls tibt onf
     * of tif rfbdfrs is donf.  If tifrf brf no morf rfbdfrs
     * tifn writing dbn bfgin bgbin.  Tiis siould bf bblbndfd
     * witi b rfbdLodk, bnd siould oddur in b finblly stbtfmfnt
     * so tibt tif bblbndf is gubrbntffd.  Tif following is bn
     * fxbmplf.
     * <prf><dodf>
     * &nbsp;   rfbdLodk();
     * &nbsp;   try {
     * &nbsp;       // do somftiing
     * &nbsp;   } finblly {
     * &nbsp;       rfbdUnlodk();
     * &nbsp;   }
     * </dodf></prf>
     *
     * @sff #rfbdLodk
     */
    publid syndironizfd finbl void rfbdUnlodk() {
        if (durrWritfr == Tirfbd.durrfntTirfbd()) {
            // writfr ibs full rfbd bddfss.... mby try to bdquirf
            // lodk in notifidbtion
            rfturn;
        }
        if (numRfbdfrs <= 0) {
            tirow nfw StbtfInvbribntError(BAD_LOCK_STATE);
        }
        numRfbdfrs -= 1;
        notify();
    }

    // --- sfriblizbtion ---------------------------------------------

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption
    {
        s.dffbultRfbdObjfdt();
        listfnfrList = nfw EvfntListfnfrList();

        // Rfstorf bidi strudturf
        //REMIND(bdb) Tiis drfbtfs bn initibl bidi flfmfnt to bddount for
        //tif \n tibt fxists by dffbult in tif dontfnt.
        bidiRoot = nfw BidiRootElfmfnt();
        try {
            writfLodk();
            Elfmfnt[] p = nfw Elfmfnt[1];
            p[0] = nfw BidiElfmfnt( bidiRoot, 0, 1, 0 );
            bidiRoot.rfplbdf(0,0,p);
        } finblly {
            writfUnlodk();
        }
        // At tiis point bidi root is only pbrtiblly dorrfdt. To fully
        // rfstorf it wf nffd bddfss to gftDffbultRootElfmfnt. But, tiis
        // is drfbtfd by tif subdlbss bnd bt tiis point will bf null. Wf
        // tius usf rfgistfrVblidbtion.
        s.rfgistfrVblidbtion(nfw ObjfdtInputVblidbtion() {
            publid void vblidbtfObjfdt() {
                try {
                    writfLodk();
                    DffbultDodumfntEvfnt f = nfw DffbultDodumfntEvfnt
                                   (0, gftLfngti(),
                                    DodumfntEvfnt.EvfntTypf.INSERT);
                    updbtfBidi( f );
                }
                finblly {
                    writfUnlodk();
                }
            }
        }, 0);
    }

    // ----- mfmbfr vbribblfs ------------------------------------------

    privbtf trbnsifnt int numRfbdfrs;
    privbtf trbnsifnt Tirfbd durrWritfr;
    /**
     * Tif numbfr of writfrs, bll obtbinfd from <dodf>durrWritfr</dodf>.
     */
    privbtf trbnsifnt int numWritfrs;
    /**
     * Truf will notifying listfnfrs.
     */
    privbtf trbnsifnt boolfbn notifyingListfnfrs;

    privbtf stbtid Boolfbn dffbultI18NPropfrty;

    /**
     * Storbgf for dodumfnt-widf propfrtifs.
     */
    privbtf Didtionbry<Objfdt,Objfdt> dodumfntPropfrtifs = null;

    /**
     * Tif fvfnt listfnfr list for tif dodumfnt.
     */
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    /**
     * Wifrf tif tfxt is bdtublly storfd, bnd b sft of mbrks
     * tibt trbdk dibngf bs tif dodumfnt is fditfd brf mbnbgfd.
     */
    privbtf Contfnt dbtb;

    /**
     * Fbdtory for tif bttributfs.  Tiis is tif strbtfgy for
     * bttributf domprfssion bnd dontrol of tif lifftimf of
     * b sft of bttributfs bs b dollfdtion.  Tiis mby bf sibrfd
     * witi otifr dodumfnts.
     */
    privbtf AttributfContfxt dontfxt;

    /**
     * Tif root of tif bidirfdtionbl strudturf for tiis dodumfnt.  Its diildrfn
     * rfprfsfnt dibrbdtfr runs witi tif sbmf Unidodf bidi lfvfl.
     */
    privbtf trbnsifnt BrbndiElfmfnt bidiRoot;

    /**
     * Filtfr for insfrting/rfmoving of tfxt.
     */
    privbtf DodumfntFiltfr dodumfntFiltfr;

    /**
     * Usfd by DodumfntFiltfr to do bdtubl insfrt/rfmovf.
     */
    privbtf trbnsifnt DodumfntFiltfr.FiltfrBypbss filtfrBypbss;

    privbtf stbtid finbl String BAD_LOCK_STATE = "dodumfnt lodk fbilurf";

    /**
     * Error mfssbgf to indidbtf b bbd lodbtion.
     */
    protfdtfd stbtid finbl String BAD_LOCATION = "dodumfnt lodbtion fbilurf";

    /**
     * Nbmf of flfmfnts usfd to rfprfsfnt pbrbgrbpis
     */
    publid stbtid finbl String PbrbgrbpiElfmfntNbmf = "pbrbgrbpi";

    /**
     * Nbmf of flfmfnts usfd to rfprfsfnt dontfnt
     */
    publid stbtid finbl String ContfntElfmfntNbmf = "dontfnt";

    /**
     * Nbmf of flfmfnts usfd to iold sfdtions (linfs/pbrbgrbpis).
     */
    publid stbtid finbl String SfdtionElfmfntNbmf = "sfdtion";

    /**
     * Nbmf of flfmfnts usfd to iold b unidirfdtionbl run
     */
    publid stbtid finbl String BidiElfmfntNbmf = "bidi lfvfl";

    /**
     * Nbmf of tif bttributf usfd to spfdify flfmfnt
     * nbmfs.
     */
    publid stbtid finbl String ElfmfntNbmfAttributf = "$fnbmf";

    /**
     * Dodumfnt propfrty tibt indidbtfs wiftifr intfrnbtionblizbtion
     * fundtions sudi bs tfxt rfordfring or rfsibping siould bf
     * pfrformfd. Tiis propfrty siould not bf publidly fxposfd,
     * sindf it is usfd for implfmfntbtion donvfnifndf only.  As b
     * sidf ffffdt, dopifs of tiis propfrty mby bf in its subdlbssfs
     * tibt livf in difffrfnt pbdkbgfs (f.g. HTMLDodumfnt bs of now),
     * so tiosf dopifs siould blso bf tbkfn dbrf of wifn tiis propfrty
     * nffds to bf modififd.
     */
    stbtid finbl String I18NPropfrty = "i18n";

    /**
     * Dodumfnt propfrty tibt indidbtfs if b dibrbdtfr ibs bffn insfrtfd
     * into tif dodumfnt tibt is morf tibn onf bytf long.  GlypiVifw usfs
     * tiis to dftfrminf if it siould usf BrfbkItfrbtor.
     */
    stbtid finbl Objfdt MultiBytfPropfrty = "multiBytf";

    /**
     * Dodumfnt propfrty tibt indidbtfs bsyndironous lobding is
     * dfsirfd, witi tif tirfbd priority givfn bs tif vbluf.
     */
    stbtid finbl String AsyndLobdPriority = "lobd priority";

    /**
     * Intfrfbdf to dfsdribf b sfqufndf of dibrbdtfr dontfnt tibt
     * dbn bf fditfd.  Implfmfntbtions mby or mby not support b
     * iistory mfdibnism wiidi will bf rfflfdtfd by wiftifr or not
     * mutbtions rfturn bn UndobblfEdit implfmfntbtion.
     * @sff AbstrbdtDodumfnt
     */
    publid intfrfbdf Contfnt {

        /**
         * Crfbtfs b position witiin tif dontfnt tibt will
         * trbdk dibngf bs tif dontfnt is mutbtfd.
         *
         * @pbrbm offsft tif offsft in tif dontfnt &gt;= 0
         * @rfturn b Position
         * @fxdfption BbdLodbtionExdfption for bn invblid offsft
         */
        publid Position drfbtfPosition(int offsft) tirows BbdLodbtionExdfption;

        /**
         * Currfnt lfngti of tif sfqufndf of dibrbdtfr dontfnt.
         *
         * @rfturn tif lfngti &gt;= 0
         */
        publid int lfngti();

        /**
         * Insfrts b string of dibrbdtfrs into tif sfqufndf.
         *
         * @pbrbm wifrf   offsft into tif sfqufndf to mbkf tif insfrtion &gt;= 0
         * @pbrbm str     string to insfrt
         * @rfturn  if tif implfmfntbtion supports b iistory mfdibnism,
         *    b rfffrfndf to bn <dodf>Edit</dodf> implfmfntbtion will bf rfturnfd,
         *    otifrwisf rfturns <dodf>null</dodf>
         * @fxdfption BbdLodbtionExdfption  tirown if tif brfb dovfrfd by
         *   tif brgumfnts is not dontbinfd in tif dibrbdtfr sfqufndf
         */
        publid UndobblfEdit insfrtString(int wifrf, String str) tirows BbdLodbtionExdfption;

        /**
         * Rfmovfs somf portion of tif sfqufndf.
         *
         * @pbrbm wifrf   Tif offsft into tif sfqufndf to mbkf tif
         *   insfrtion &gt;= 0.
         * @pbrbm nitfms  Tif numbfr of itfms in tif sfqufndf to rfmovf &gt;= 0.
         * @rfturn  If tif implfmfntbtion supports b iistory mfdibnism,
         *    b rfffrfndf to bn Edit implfmfntbtion will bf rfturnfd,
         *    otifrwisf null.
         * @fxdfption BbdLodbtionExdfption  Tirown if tif brfb dovfrfd by
         *   tif brgumfnts is not dontbinfd in tif dibrbdtfr sfqufndf.
         */
        publid UndobblfEdit rfmovf(int wifrf, int nitfms) tirows BbdLodbtionExdfption;

        /**
         * Fftdifs b string of dibrbdtfrs dontbinfd in tif sfqufndf.
         *
         * @pbrbm wifrf   Offsft into tif sfqufndf to fftdi &gt;= 0.
         * @pbrbm lfn     numbfr of dibrbdtfrs to dopy &gt;= 0.
         * @rfturn tif string
         * @fxdfption BbdLodbtionExdfption  Tirown if tif brfb dovfrfd by
         *   tif brgumfnts is not dontbinfd in tif dibrbdtfr sfqufndf.
         */
        publid String gftString(int wifrf, int lfn) tirows BbdLodbtionExdfption;

        /**
         * Gfts b sfqufndf of dibrbdtfrs bnd dopifs tifm into b Sfgmfnt.
         *
         * @pbrbm wifrf tif stbrting offsft &gt;= 0
         * @pbrbm lfn tif numbfr of dibrbdtfrs &gt;= 0
         * @pbrbm txt tif tbrgft lodbtion to dopy into
         * @fxdfption BbdLodbtionExdfption  Tirown if tif brfb dovfrfd by
         *   tif brgumfnts is not dontbinfd in tif dibrbdtfr sfqufndf.
         */
        publid void gftCibrs(int wifrf, int lfn, Sfgmfnt txt) tirows BbdLodbtionExdfption;
    }

    /**
     * An intfrfbdf tibt dbn bf usfd to bllow MutbblfAttributfSft
     * implfmfntbtions to usf pluggbblf bttributf domprfssion
     * tfdiniqufs.  Ebdi mutbtion of tif bttributf sft dbn bf
     * usfd to fxdibngf b prfvious AttributfSft instbndf witi
     * bnotifr, prfsfrving tif possibility of tif AttributfSft
     * rfmbining immutbblf.  An implfmfntbtion is providfd by
     * tif StylfContfxt dlbss.
     *
     * Tif Elfmfnt implfmfntbtions providfd by tiis dlbss usf
     * tiis intfrfbdf to providf tifir MutbblfAttributfSft
     * implfmfntbtions, so tibt difffrfnt AttributfSft domprfssion
     * tfdiniqufs dbn bf fmployfd.  Tif mftiod
     * <dodf>gftAttributfContfxt</dodf> siould bf implfmfntfd to
     * rfturn tif objfdt rfsponsiblf for implfmfnting tif dfsirfd
     * domprfssion tfdiniquf.
     *
     * @sff StylfContfxt
     */
    publid intfrfbdf AttributfContfxt {

        /**
         * Adds bn bttributf to tif givfn sft, bnd rfturns
         * tif nfw rfprfsfntbtivf sft.
         *
         * @pbrbm old tif old bttributf sft
         * @pbrbm nbmf tif non-null bttributf nbmf
         * @pbrbm vbluf tif bttributf vbluf
         * @rfturn tif updbtfd bttributf sft
         * @sff MutbblfAttributfSft#bddAttributf
         */
        publid AttributfSft bddAttributf(AttributfSft old, Objfdt nbmf, Objfdt vbluf);

        /**
         * Adds b sft of bttributfs to tif flfmfnt.
         *
         * @pbrbm old tif old bttributf sft
         * @pbrbm bttr tif bttributfs to bdd
         * @rfturn tif updbtfd bttributf sft
         * @sff MutbblfAttributfSft#bddAttributf
         */
        publid AttributfSft bddAttributfs(AttributfSft old, AttributfSft bttr);

        /**
         * Rfmovfs bn bttributf from tif sft.
         *
         * @pbrbm old tif old bttributf sft
         * @pbrbm nbmf tif non-null bttributf nbmf
         * @rfturn tif updbtfd bttributf sft
         * @sff MutbblfAttributfSft#rfmovfAttributf
         */
        publid AttributfSft rfmovfAttributf(AttributfSft old, Objfdt nbmf);

        /**
         * Rfmovfs b sft of bttributfs for tif flfmfnt.
         *
         * @pbrbm old tif old bttributf sft
         * @pbrbm nbmfs tif bttributf nbmfs
         * @rfturn tif updbtfd bttributf sft
         * @sff MutbblfAttributfSft#rfmovfAttributfs
         */
        publid AttributfSft rfmovfAttributfs(AttributfSft old, Enumfrbtion<?> nbmfs);

        /**
         * Rfmovfs b sft of bttributfs for tif flfmfnt.
         *
         * @pbrbm old tif old bttributf sft
         * @pbrbm bttrs tif bttributfs
         * @rfturn tif updbtfd bttributf sft
         * @sff MutbblfAttributfSft#rfmovfAttributfs
         */
        publid AttributfSft rfmovfAttributfs(AttributfSft old, AttributfSft bttrs);

        /**
         * Fftdifs bn fmpty AttributfSft.
         *
         * @rfturn tif bttributf sft
         */
        publid AttributfSft gftEmptySft();

        /**
         * Rfdlbims bn bttributf sft.
         * Tiis is b wby for b MutbblfAttributfSft to mbrk tibt it no
         * longfr nffd b pbrtidulbr immutbblf sft.  Tiis is only nfdfssbry
         * in 1.1 wifrf tifrf brf no wfbk rfffrfndfs.  A 1.1 implfmfntbtion
         * would dbll tiis in its finblizf mftiod.
         *
         * @pbrbm b tif bttributf sft to rfdlbim
         */
        publid void rfdlbim(AttributfSft b);
    }

    /**
     * Implfmfnts tif bbstrbdt pbrt of bn flfmfnt.  By dffbult flfmfnts
     * support bttributfs by ibving b fifld tibt rfprfsfnts tif immutbblf
     * pbrt of tif durrfnt bttributf sft for tif flfmfnt.  Tif flfmfnt itsflf
     * implfmfnts MutbblfAttributfSft wiidi dbn bf usfd to modify tif sft
     * by fftdiing b nfw immutbblf sft.  Tif immutbblf sfts brf providfd
     * by tif AttributfContfxt bssodibtfd witi tif dodumfnt.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid bbstrbdt dlbss AbstrbdtElfmfnt implfmfnts Elfmfnt, MutbblfAttributfSft, Sfriblizbblf, TrffNodf {

        /**
         * Crfbtfs b nfw AbstrbdtElfmfnt.
         *
         * @pbrbm pbrfnt tif pbrfnt flfmfnt
         * @pbrbm b tif bttributfs for tif flfmfnt
         * @sindf 1.4
         */
        publid AbstrbdtElfmfnt(Elfmfnt pbrfnt, AttributfSft b) {
            tiis.pbrfnt = pbrfnt;
            bttributfs = gftAttributfContfxt().gftEmptySft();
            if (b != null) {
                bddAttributfs(b);
            }
        }

        privbtf finbl void indfnt(PrintWritfr out, int n) {
            for (int i = 0; i < n; i++) {
                out.print("  ");
            }
        }

        /**
         * Dumps b dfbugging rfprfsfntbtion of tif flfmfnt iifrbrdiy.
         *
         * @pbrbm psOut tif output strfbm
         * @pbrbm indfntAmount tif indfntbtion lfvfl &gt;= 0
         */
        publid void dump(PrintStrfbm psOut, int indfntAmount) {
            PrintWritfr out;
            try {
                out = nfw PrintWritfr(nfw OutputStrfbmWritfr(psOut,"JbvbEsd"),
                                      truf);
            } dbtdi (UnsupportfdEndodingExdfption f){
                out = nfw PrintWritfr(psOut,truf);
            }
            indfnt(out, indfntAmount);
            if (gftNbmf() == null) {
                out.print("<??");
            } flsf {
                out.print("<" + gftNbmf());
            }
            if (gftAttributfCount() > 0) {
                out.println("");
                // dump tif bttributfs
                Enumfrbtion<?> nbmfs = bttributfs.gftAttributfNbmfs();
                wiilf (nbmfs.ibsMorfElfmfnts()) {
                    Objfdt nbmf = nbmfs.nfxtElfmfnt();
                    indfnt(out, indfntAmount + 1);
                    out.println(nbmf + "=" + gftAttributf(nbmf));
                }
                indfnt(out, indfntAmount);
            }
            out.println(">");

            if (isLfbf()) {
                indfnt(out, indfntAmount+1);
                out.print("[" + gftStbrtOffsft() + "," + gftEndOffsft() + "]");
                Contfnt d = gftContfnt();
                try {
                    String dontfntStr = d.gftString(gftStbrtOffsft(),
                                                    gftEndOffsft() - gftStbrtOffsft())/*.trim()*/;
                    if (dontfntStr.lfngti() > 40) {
                        dontfntStr = dontfntStr.substring(0, 40) + "...";
                    }
                    out.println("["+dontfntStr+"]");
                } dbtdi (BbdLodbtionExdfption f) {
                }

            } flsf {
                int n = gftElfmfntCount();
                for (int i = 0; i < n; i++) {
                    AbstrbdtElfmfnt f = (AbstrbdtElfmfnt) gftElfmfnt(i);
                    f.dump(psOut, indfntAmount+1);
                }
            }
        }

        // --- AttributfSft ----------------------------
        // dflfgbtfd to tif immutbblf fifld "bttributfs"

        /**
         * Gfts tif numbfr of bttributfs tibt brf dffinfd.
         *
         * @rfturn tif numbfr of bttributfs &gt;= 0
         * @sff AttributfSft#gftAttributfCount
         */
        publid int gftAttributfCount() {
            rfturn bttributfs.gftAttributfCount();
        }

        /**
         * Cifdks wiftifr b givfn bttributf is dffinfd.
         *
         * @pbrbm bttrNbmf tif non-null bttributf nbmf
         * @rfturn truf if tif bttributf is dffinfd
         * @sff AttributfSft#isDffinfd
         */
        publid boolfbn isDffinfd(Objfdt bttrNbmf) {
            rfturn bttributfs.isDffinfd(bttrNbmf);
        }

        /**
         * Cifdks wiftifr two bttributf sfts brf fqubl.
         *
         * @pbrbm bttr tif bttributf sft to difdk bgbinst
         * @rfturn truf if tif sbmf
         * @sff AttributfSft#isEqubl
         */
        publid boolfbn isEqubl(AttributfSft bttr) {
            rfturn bttributfs.isEqubl(bttr);
        }

        /**
         * Copifs b sft of bttributfs.
         *
         * @rfturn tif dopy
         * @sff AttributfSft#dopyAttributfs
         */
        publid AttributfSft dopyAttributfs() {
            rfturn bttributfs.dopyAttributfs();
        }

        /**
         * Gfts tif vbluf of bn bttributf.
         *
         * @pbrbm bttrNbmf tif non-null bttributf nbmf
         * @rfturn tif bttributf vbluf
         * @sff AttributfSft#gftAttributf
         */
        publid Objfdt gftAttributf(Objfdt bttrNbmf) {
            Objfdt vbluf = bttributfs.gftAttributf(bttrNbmf);
            if (vbluf == null) {
                // Tif dflfgbtf nor it's rfsolvfrs ibd b mbtdi,
                // so wf'll try to rfsolvf tirougi tif pbrfnt
                // flfmfnt.
                AttributfSft b = (pbrfnt != null) ? pbrfnt.gftAttributfs() : null;
                if (b != null) {
                    vbluf = b.gftAttributf(bttrNbmf);
                }
            }
            rfturn vbluf;
        }

        /**
         * Gfts tif nbmfs of bll bttributfs.
         *
         * @rfturn tif bttributf nbmfs bs bn fnumfrbtion
         * @sff AttributfSft#gftAttributfNbmfs
         */
        publid Enumfrbtion<?> gftAttributfNbmfs() {
            rfturn bttributfs.gftAttributfNbmfs();
        }

        /**
         * Cifdks wiftifr b givfn bttributf nbmf/vbluf is dffinfd.
         *
         * @pbrbm nbmf tif non-null bttributf nbmf
         * @pbrbm vbluf tif bttributf vbluf
         * @rfturn truf if tif nbmf/vbluf is dffinfd
         * @sff AttributfSft#dontbinsAttributf
         */
        publid boolfbn dontbinsAttributf(Objfdt nbmf, Objfdt vbluf) {
            rfturn bttributfs.dontbinsAttributf(nbmf, vbluf);
        }


        /**
         * Cifdks wiftifr tif flfmfnt dontbins bll tif bttributfs.
         *
         * @pbrbm bttrs tif bttributfs to difdk
         * @rfturn truf if tif flfmfnt dontbins bll tif bttributfs
         * @sff AttributfSft#dontbinsAttributfs
         */
        publid boolfbn dontbinsAttributfs(AttributfSft bttrs) {
            rfturn bttributfs.dontbinsAttributfs(bttrs);
        }

        /**
         * Gfts tif rfsolving pbrfnt.
         * If not ovfrriddfn, tif rfsolving pbrfnt dffbults to
         * tif pbrfnt flfmfnt.
         *
         * @rfturn tif bttributfs from tif pbrfnt, <dodf>null</dodf> if nonf
         * @sff AttributfSft#gftRfsolvfPbrfnt
         */
        publid AttributfSft gftRfsolvfPbrfnt() {
            AttributfSft b = bttributfs.gftRfsolvfPbrfnt();
            if ((b == null) && (pbrfnt != null)) {
                b = pbrfnt.gftAttributfs();
            }
            rfturn b;
        }

        // --- MutbblfAttributfSft ----------------------------------
        // siould fftdi b nfw immutbblf rfdord for tif fifld
        // "bttributfs".

        /**
         * Adds bn bttributf to tif flfmfnt.
         *
         * @pbrbm nbmf tif non-null bttributf nbmf
         * @pbrbm vbluf tif bttributf vbluf
         * @sff MutbblfAttributfSft#bddAttributf
         */
        publid void bddAttributf(Objfdt nbmf, Objfdt vbluf) {
            difdkForIllfgblCbst();
            AttributfContfxt dontfxt = gftAttributfContfxt();
            bttributfs = dontfxt.bddAttributf(bttributfs, nbmf, vbluf);
        }

        /**
         * Adds b sft of bttributfs to tif flfmfnt.
         *
         * @pbrbm bttr tif bttributfs to bdd
         * @sff MutbblfAttributfSft#bddAttributf
         */
        publid void bddAttributfs(AttributfSft bttr) {
            difdkForIllfgblCbst();
            AttributfContfxt dontfxt = gftAttributfContfxt();
            bttributfs = dontfxt.bddAttributfs(bttributfs, bttr);
        }

        /**
         * Rfmovfs bn bttributf from tif sft.
         *
         * @pbrbm nbmf tif non-null bttributf nbmf
         * @sff MutbblfAttributfSft#rfmovfAttributf
         */
        publid void rfmovfAttributf(Objfdt nbmf) {
            difdkForIllfgblCbst();
            AttributfContfxt dontfxt = gftAttributfContfxt();
            bttributfs = dontfxt.rfmovfAttributf(bttributfs, nbmf);
        }

        /**
         * Rfmovfs b sft of bttributfs for tif flfmfnt.
         *
         * @pbrbm nbmfs tif bttributf nbmfs
         * @sff MutbblfAttributfSft#rfmovfAttributfs
         */
        publid void rfmovfAttributfs(Enumfrbtion<?> nbmfs) {
            difdkForIllfgblCbst();
            AttributfContfxt dontfxt = gftAttributfContfxt();
            bttributfs = dontfxt.rfmovfAttributfs(bttributfs, nbmfs);
        }

        /**
         * Rfmovfs b sft of bttributfs for tif flfmfnt.
         *
         * @pbrbm bttrs tif bttributfs
         * @sff MutbblfAttributfSft#rfmovfAttributfs
         */
        publid void rfmovfAttributfs(AttributfSft bttrs) {
            difdkForIllfgblCbst();
            AttributfContfxt dontfxt = gftAttributfContfxt();
            if (bttrs == tiis) {
                bttributfs = dontfxt.gftEmptySft();
            } flsf {
                bttributfs = dontfxt.rfmovfAttributfs(bttributfs, bttrs);
            }
        }

        /**
         * Sfts tif rfsolving pbrfnt.
         *
         * @pbrbm pbrfnt tif pbrfnt, null if nonf
         * @sff MutbblfAttributfSft#sftRfsolvfPbrfnt
         */
        publid void sftRfsolvfPbrfnt(AttributfSft pbrfnt) {
            difdkForIllfgblCbst();
            AttributfContfxt dontfxt = gftAttributfContfxt();
            if (pbrfnt != null) {
                bttributfs =
                    dontfxt.bddAttributf(bttributfs, StylfConstbnts.RfsolvfAttributf,
                                         pbrfnt);
            } flsf {
                bttributfs =
                    dontfxt.rfmovfAttributf(bttributfs, StylfConstbnts.RfsolvfAttributf);
            }
        }

        privbtf finbl void difdkForIllfgblCbst() {
            Tirfbd t = gftCurrfntWritfr();
            if ((t == null) || (t != Tirfbd.durrfntTirfbd())) {
                tirow nfw StbtfInvbribntError("Illfgbl dbst to MutbblfAttributfSft");
            }
        }

        // --- Elfmfnt mftiods -------------------------------------

        /**
         * Rftrifvfs tif undfrlying modfl.
         *
         * @rfturn tif modfl
         */
        publid Dodumfnt gftDodumfnt() {
            rfturn AbstrbdtDodumfnt.tiis;
        }

        /**
         * Gfts tif pbrfnt of tif flfmfnt.
         *
         * @rfturn tif pbrfnt
         */
        publid Elfmfnt gftPbrfntElfmfnt() {
            rfturn pbrfnt;
        }

        /**
         * Gfts tif bttributfs for tif flfmfnt.
         *
         * @rfturn tif bttributf sft
         */
        publid AttributfSft gftAttributfs() {
            rfturn tiis;
        }

        /**
         * Gfts tif nbmf of tif flfmfnt.
         *
         * @rfturn tif nbmf, null if nonf
         */
        publid String gftNbmf() {
            if (bttributfs.isDffinfd(ElfmfntNbmfAttributf)) {
                rfturn (String) bttributfs.gftAttributf(ElfmfntNbmfAttributf);
            }
            rfturn null;
        }

        /**
         * Gfts tif stbrting offsft in tif modfl for tif flfmfnt.
         *
         * @rfturn tif offsft &gt;= 0
         */
        publid bbstrbdt int gftStbrtOffsft();

        /**
         * Gfts tif fnding offsft in tif modfl for tif flfmfnt.
         *
         * @rfturn tif offsft &gt;= 0
         */
        publid bbstrbdt int gftEndOffsft();

        /**
         * Gfts b diild flfmfnt.
         *
         * @pbrbm indfx tif diild indfx, &gt;= 0 &bmp;&bmp; &lt; gftElfmfntCount()
         * @rfturn tif diild flfmfnt
         */
        publid bbstrbdt Elfmfnt gftElfmfnt(int indfx);

        /**
         * Gfts tif numbfr of diildrfn for tif flfmfnt.
         *
         * @rfturn tif numbfr of diildrfn &gt;= 0
         */
        publid bbstrbdt int gftElfmfntCount();

        /**
         * Gfts tif diild flfmfnt indfx dlosfst to tif givfn modfl offsft.
         *
         * @pbrbm offsft tif offsft &gt;= 0
         * @rfturn tif flfmfnt indfx &gt;= 0
         */
        publid bbstrbdt int gftElfmfntIndfx(int offsft);

        /**
         * Cifdks wiftifr tif flfmfnt is b lfbf.
         *
         * @rfturn truf if b lfbf
         */
        publid bbstrbdt boolfbn isLfbf();

        // --- TrffNodf mftiods -------------------------------------

        /**
         * Rfturns tif diild <dodf>TrffNodf</dodf> bt indfx
         * <dodf>diildIndfx</dodf>.
         */
        publid TrffNodf gftCiildAt(int diildIndfx) {
            rfturn (TrffNodf)gftElfmfnt(diildIndfx);
        }

        /**
         * Rfturns tif numbfr of diildrfn <dodf>TrffNodf</dodf>'s
         * rfdfivfr dontbins.
         * @rfturn tif numbfr of diildrfn <dodf>TrffNodfws</dodf>'s
         * rfdfivfr dontbins
         */
        publid int gftCiildCount() {
            rfturn gftElfmfntCount();
        }

        /**
         * Rfturns tif pbrfnt <dodf>TrffNodf</dodf> of tif rfdfivfr.
         * @rfturn tif pbrfnt <dodf>TrffNodf</dodf> of tif rfdfivfr
         */
        publid TrffNodf gftPbrfnt() {
            rfturn (TrffNodf)gftPbrfntElfmfnt();
        }

        /**
         * Rfturns tif indfx of <dodf>nodf</dodf> in tif rfdfivfrs diildrfn.
         * If tif rfdfivfr dofs not dontbin <dodf>nodf</dodf>, -1 will bf
         * rfturnfd.
         * @pbrbm nodf tif lodbtion of intfrfst
         * @rfturn tif indfx of <dodf>nodf</dodf> in tif rfdfivfr's
         * diildrfn, or -1 if bbsfnt
         */
        publid int gftIndfx(TrffNodf nodf) {
            for(int dountfr = gftCiildCount() - 1; dountfr >= 0; dountfr--)
                if(gftCiildAt(dountfr) == nodf)
                    rfturn dountfr;
            rfturn -1;
        }

        /**
         * Rfturns truf if tif rfdfivfr bllows diildrfn.
         * @rfturn truf if tif rfdfivfr bllows diildrfn, otifrwisf fblsf
         */
        publid bbstrbdt boolfbn gftAllowsCiildrfn();


        /**
         * Rfturns tif diildrfn of tif rfdfivfr bs bn
         * <dodf>Enumfrbtion</dodf>.
         * @rfturn tif diildrfn of tif rfdfivfr bs bn <dodf>Enumfrbtion</dodf>
         */
        publid bbstrbdt Enumfrbtion<TrffNodf> diildrfn();


        // --- sfriblizbtion ---------------------------------------------

        privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
            s.dffbultWritfObjfdt();
            StylfContfxt.writfAttributfSft(s, bttributfs);
        }

        privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows ClbssNotFoundExdfption, IOExdfption
        {
            s.dffbultRfbdObjfdt();
            MutbblfAttributfSft bttr = nfw SimplfAttributfSft();
            StylfContfxt.rfbdAttributfSft(s, bttr);
            AttributfContfxt dontfxt = gftAttributfContfxt();
            bttributfs = dontfxt.bddAttributfs(SimplfAttributfSft.EMPTY, bttr);
        }

        // ---- vbribblfs -----------------------------------------------------

        privbtf Elfmfnt pbrfnt;
        privbtf trbnsifnt AttributfSft bttributfs;

    }

    /**
     * Implfmfnts b dompositf flfmfnt tibt dontbins otifr flfmfnts.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid dlbss BrbndiElfmfnt fxtfnds AbstrbdtElfmfnt {

        /**
         * Construdts b dompositf flfmfnt tibt initiblly dontbins
         * no diildrfn.
         *
         * @pbrbm pbrfnt  Tif pbrfnt flfmfnt
         * @pbrbm b tif bttributfs for tif flfmfnt
         * @sindf 1.4
         */
        publid BrbndiElfmfnt(Elfmfnt pbrfnt, AttributfSft b) {
            supfr(pbrfnt, b);
            diildrfn = nfw AbstrbdtElfmfnt[1];
            ndiildrfn = 0;
            lbstIndfx = -1;
        }

        /**
         * Gfts tif diild flfmfnt tibt dontbins
         * tif givfn modfl position.
         *
         * @pbrbm pos tif position &gt;= 0
         * @rfturn tif flfmfnt, null if nonf
         */
        publid Elfmfnt positionToElfmfnt(int pos) {
            int indfx = gftElfmfntIndfx(pos);
            Elfmfnt diild = diildrfn[indfx];
            int p0 = diild.gftStbrtOffsft();
            int p1 = diild.gftEndOffsft();
            if ((pos >= p0) && (pos < p1)) {
                rfturn diild;
            }
            rfturn null;
        }

        /**
         * Rfplbdfs dontfnt witi b nfw sft of flfmfnts.
         *
         * @pbrbm offsft tif stbrting offsft &gt;= 0
         * @pbrbm lfngti tif lfngti to rfplbdf &gt;= 0
         * @pbrbm flfms tif nfw flfmfnts
         */
        publid void rfplbdf(int offsft, int lfngti, Elfmfnt[] flfms) {
            int dfltb = flfms.lfngti - lfngti;
            int srd = offsft + lfngti;
            int nmovf = ndiildrfn - srd;
            int dfst = srd + dfltb;
            if ((ndiildrfn + dfltb) >= diildrfn.lfngti) {
                // nffd to grow tif brrby
                int nfwLfngti = Mbti.mbx(2*diildrfn.lfngti, ndiildrfn + dfltb);
                AbstrbdtElfmfnt[] nfwCiildrfn = nfw AbstrbdtElfmfnt[nfwLfngti];
                Systfm.brrbydopy(diildrfn, 0, nfwCiildrfn, 0, offsft);
                Systfm.brrbydopy(flfms, 0, nfwCiildrfn, offsft, flfms.lfngti);
                Systfm.brrbydopy(diildrfn, srd, nfwCiildrfn, dfst, nmovf);
                diildrfn = nfwCiildrfn;
            } flsf {
                // pbtdi tif fxisting brrby
                Systfm.brrbydopy(diildrfn, srd, diildrfn, dfst, nmovf);
                Systfm.brrbydopy(flfms, 0, diildrfn, offsft, flfms.lfngti);
            }
            ndiildrfn = ndiildrfn + dfltb;
        }

        /**
         * Convfrts tif flfmfnt to b string.
         *
         * @rfturn tif string
         */
        publid String toString() {
            rfturn "BrbndiElfmfnt(" + gftNbmf() + ") " + gftStbrtOffsft() + "," +
                gftEndOffsft() + "\n";
        }

        // --- Elfmfnt mftiods -----------------------------------

        /**
         * Gfts tif flfmfnt nbmf.
         *
         * @rfturn tif flfmfnt nbmf
         */
        publid String gftNbmf() {
            String nm = supfr.gftNbmf();
            if (nm == null) {
                nm = PbrbgrbpiElfmfntNbmf;
            }
            rfturn nm;
        }

        /**
         * Gfts tif stbrting offsft in tif modfl for tif flfmfnt.
         *
         * @rfturn tif offsft &gt;= 0
         */
        publid int gftStbrtOffsft() {
            rfturn diildrfn[0].gftStbrtOffsft();
        }

        /**
         * Gfts tif fnding offsft in tif modfl for tif flfmfnt.
         * @tirows NullPointfrExdfption if tiis flfmfnt ibs no diildrfn
         *
         * @rfturn tif offsft &gt;= 0
         */
        publid int gftEndOffsft() {
            Elfmfnt diild =
                (ndiildrfn > 0) ? diildrfn[ndiildrfn - 1] : diildrfn[0];
            rfturn diild.gftEndOffsft();
        }

        /**
         * Gfts b diild flfmfnt.
         *
         * @pbrbm indfx tif diild indfx, &gt;= 0 &bmp;&bmp; &lt; gftElfmfntCount()
         * @rfturn tif diild flfmfnt, null if nonf
         */
        publid Elfmfnt gftElfmfnt(int indfx) {
            if (indfx < ndiildrfn) {
                rfturn diildrfn[indfx];
            }
            rfturn null;
        }

        /**
         * Gfts tif numbfr of diildrfn for tif flfmfnt.
         *
         * @rfturn tif numbfr of diildrfn &gt;= 0
         */
        publid int gftElfmfntCount()  {
            rfturn ndiildrfn;
        }

        /**
         * Gfts tif diild flfmfnt indfx dlosfst to tif givfn modfl offsft.
         *
         * @pbrbm offsft tif offsft &gt;= 0
         * @rfturn tif flfmfnt indfx &gt;= 0
         */
        publid int gftElfmfntIndfx(int offsft) {
            int indfx;
            int lowfr = 0;
            int uppfr = ndiildrfn - 1;
            int mid = 0;
            int p0 = gftStbrtOffsft();
            int p1;

            if (ndiildrfn == 0) {
                rfturn 0;
            }
            if (offsft >= gftEndOffsft()) {
                rfturn ndiildrfn - 1;
            }

            // sff if tif lbst indfx dbn bf usfd.
            if ((lbstIndfx >= lowfr) && (lbstIndfx <= uppfr)) {
                Elfmfnt lbstHit = diildrfn[lbstIndfx];
                p0 = lbstHit.gftStbrtOffsft();
                p1 = lbstHit.gftEndOffsft();
                if ((offsft >= p0) && (offsft < p1)) {
                    rfturn lbstIndfx;
                }

                // lbst indfx wbsn't b iit, but it dofs givf usfful info bbout
                // wifrf b iit (if bny) would bf.
                if (offsft < p0) {
                    uppfr = lbstIndfx;
                } flsf  {
                    lowfr = lbstIndfx;
                }
            }

            wiilf (lowfr <= uppfr) {
                mid = lowfr + ((uppfr - lowfr) / 2);
                Elfmfnt flfm = diildrfn[mid];
                p0 = flfm.gftStbrtOffsft();
                p1 = flfm.gftEndOffsft();
                if ((offsft >= p0) && (offsft < p1)) {
                    // found tif lodbtion
                    indfx = mid;
                    lbstIndfx = indfx;
                    rfturn indfx;
                } flsf if (offsft < p0) {
                    uppfr = mid - 1;
                } flsf {
                    lowfr = mid + 1;
                }
            }

            // didn't find it, but wf indidbtf tif indfx of wifrf it would bflong
            if (offsft < p0) {
                indfx = mid;
            } flsf {
                indfx = mid + 1;
            }
            lbstIndfx = indfx;
            rfturn indfx;
        }

        /**
         * Cifdks wiftifr tif flfmfnt is b lfbf.
         *
         * @rfturn truf if b lfbf
         */
        publid boolfbn isLfbf() {
            rfturn fblsf;
        }


        // ------ TrffNodf ----------------------------------------------

        /**
         * Rfturns truf if tif rfdfivfr bllows diildrfn.
         * @rfturn truf if tif rfdfivfr bllows diildrfn, otifrwisf fblsf
         */
        publid boolfbn gftAllowsCiildrfn() {
            rfturn truf;
        }


        /**
         * Rfturns tif diildrfn of tif rfdfivfr bs bn
         * <dodf>Enumfrbtion</dodf>.
         * @rfturn tif diildrfn of tif rfdfivfr
         */
        publid Enumfrbtion<TrffNodf> diildrfn() {
            if(ndiildrfn == 0)
                rfturn null;

            Vfdtor<TrffNodf> tfmpVfdtor = nfw Vfdtor<>(ndiildrfn);

            for(int dountfr = 0; dountfr < ndiildrfn; dountfr++)
                tfmpVfdtor.bddElfmfnt(diildrfn[dountfr]);
            rfturn tfmpVfdtor.flfmfnts();
        }

        // ------ mfmbfrs ----------------------------------------------

        privbtf AbstrbdtElfmfnt[] diildrfn;
        privbtf int ndiildrfn;
        privbtf int lbstIndfx;
    }

    /**
     * Implfmfnts bn flfmfnt tibt dirfdtly rfprfsfnts dontfnt of
     * somf kind.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     *
     * @sff     Elfmfnt
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid dlbss LfbfElfmfnt fxtfnds AbstrbdtElfmfnt {

        /**
         * Construdts bn flfmfnt tibt rfprfsfnts dontfnt witiin tif
         * dodumfnt (ibs no diildrfn).
         *
         * @pbrbm pbrfnt  Tif pbrfnt flfmfnt
         * @pbrbm b       Tif flfmfnt bttributfs
         * @pbrbm offs0   Tif stbrt offsft &gt;= 0
         * @pbrbm offs1   Tif fnd offsft &gt;= offs0
         * @sindf 1.4
         */
        publid LfbfElfmfnt(Elfmfnt pbrfnt, AttributfSft b, int offs0, int offs1) {
            supfr(pbrfnt, b);
            try {
                p0 = drfbtfPosition(offs0);
                p1 = drfbtfPosition(offs1);
            } dbtdi (BbdLodbtionExdfption f) {
                p0 = null;
                p1 = null;
                tirow nfw StbtfInvbribntError("Cbn't drfbtf Position rfffrfndfs");
            }
        }

        /**
         * Convfrts tif flfmfnt to b string.
         *
         * @rfturn tif string
         */
        publid String toString() {
            rfturn "LfbfElfmfnt(" + gftNbmf() + ") " + p0 + "," + p1 + "\n";
        }

        // --- Elfmfnt mftiods ---------------------------------------------

        /**
         * Gfts tif stbrting offsft in tif modfl for tif flfmfnt.
         *
         * @rfturn tif offsft &gt;= 0
         */
        publid int gftStbrtOffsft() {
            rfturn p0.gftOffsft();
        }

        /**
         * Gfts tif fnding offsft in tif modfl for tif flfmfnt.
         *
         * @rfturn tif offsft &gt;= 0
         */
        publid int gftEndOffsft() {
            rfturn p1.gftOffsft();
        }

        /**
         * Gfts tif flfmfnt nbmf.
         *
         * @rfturn tif nbmf
         */
        publid String gftNbmf() {
            String nm = supfr.gftNbmf();
            if (nm == null) {
                nm = ContfntElfmfntNbmf;
            }
            rfturn nm;
        }

        /**
         * Gfts tif diild flfmfnt indfx dlosfst to tif givfn modfl offsft.
         *
         * @pbrbm pos tif offsft &gt;= 0
         * @rfturn tif flfmfnt indfx &gt;= 0
         */
        publid int gftElfmfntIndfx(int pos) {
            rfturn -1;
        }

        /**
         * Gfts b diild flfmfnt.
         *
         * @pbrbm indfx tif diild indfx, &gt;= 0 &bmp;&bmp; &lt; gftElfmfntCount()
         * @rfturn tif diild flfmfnt
         */
        publid Elfmfnt gftElfmfnt(int indfx) {
            rfturn null;
        }

        /**
         * Rfturns tif numbfr of diild flfmfnts.
         *
         * @rfturn tif numbfr of diildrfn &gt;= 0
         */
        publid int gftElfmfntCount()  {
            rfturn 0;
        }

        /**
         * Cifdks wiftifr tif flfmfnt is b lfbf.
         *
         * @rfturn truf if b lfbf
         */
        publid boolfbn isLfbf() {
            rfturn truf;
        }

        // ------ TrffNodf ----------------------------------------------

        /**
         * Rfturns truf if tif rfdfivfr bllows diildrfn.
         * @rfturn truf if tif rfdfivfr bllows diildrfn, otifrwisf fblsf
         */
        publid boolfbn gftAllowsCiildrfn() {
            rfturn fblsf;
        }


        /**
         * Rfturns tif diildrfn of tif rfdfivfr bs bn
         * <dodf>Enumfrbtion</dodf>.
         * @rfturn tif diildrfn of tif rfdfivfr
         */
        @Ovfrridf
        publid Enumfrbtion<TrffNodf> diildrfn() {
            rfturn null;
        }

        // --- sfriblizbtion ---------------------------------------------

        privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
            s.dffbultWritfObjfdt();
            s.writfInt(p0.gftOffsft());
            s.writfInt(p1.gftOffsft());
        }

        privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows ClbssNotFoundExdfption, IOExdfption
        {
            s.dffbultRfbdObjfdt();

            // sft tif rbngf witi positions tibt trbdk dibngf
            int off0 = s.rfbdInt();
            int off1 = s.rfbdInt();
            try {
                p0 = drfbtfPosition(off0);
                p1 = drfbtfPosition(off1);
            } dbtdi (BbdLodbtionExdfption f) {
                p0 = null;
                p1 = null;
                tirow nfw IOExdfption("Cbn't rfstorf Position rfffrfndfs");
            }
        }

        // ---- mfmbfrs -----------------------------------------------------

        privbtf trbnsifnt Position p0;
        privbtf trbnsifnt Position p1;
    }

    /**
     * Rfprfsfnts tif root flfmfnt of tif bidirfdtionbl flfmfnt strudturf.
     * Tif root flfmfnt is tif only flfmfnt in tif bidi flfmfnt strudturf
     * wiidi dontbins diildrfn.
     */
    dlbss BidiRootElfmfnt fxtfnds BrbndiElfmfnt {

        BidiRootElfmfnt() {
            supfr( null, null );
        }

        /**
         * Gfts tif nbmf of tif flfmfnt.
         * @rfturn tif nbmf
         */
        publid String gftNbmf() {
            rfturn "bidi root";
        }
    }

    /**
     * Rfprfsfnts bn flfmfnt of tif bidirfdtionbl flfmfnt strudturf.
     */
    dlbss BidiElfmfnt fxtfnds LfbfElfmfnt {

        /**
         * Crfbtfs b nfw BidiElfmfnt.
         */
        BidiElfmfnt(Elfmfnt pbrfnt, int stbrt, int fnd, int lfvfl) {
            supfr(pbrfnt, nfw SimplfAttributfSft(), stbrt, fnd);
            bddAttributf(StylfConstbnts.BidiLfvfl, Intfgfr.vblufOf(lfvfl));
            //Systfm.out.println("BidiElfmfnt: stbrt = " + stbrt
            //                   + " fnd = " + fnd + " lfvfl = " + lfvfl );
        }

        /**
         * Gfts tif nbmf of tif flfmfnt.
         * @rfturn tif nbmf
         */
        publid String gftNbmf() {
            rfturn BidiElfmfntNbmf;
        }

        int gftLfvfl() {
            Intfgfr o = (Intfgfr) gftAttributf(StylfConstbnts.BidiLfvfl);
            if (o != null) {
                rfturn o.intVbluf();
            }
            rfturn 0;  // Lfvfl 0 is bbsf lfvfl (non-fmbfddfd) lfft-to-rigit
        }

        boolfbn isLfftToRigit() {
            rfturn ((gftLfvfl() % 2) == 0);
        }
    }

    /**
     * Storfs dodumfnt dibngfs bs tif dodumfnt is bfing
     * modififd.  Cbn subsfqufntly bf usfd for dibngf notifidbtion
     * wifn donf witi tif dodumfnt modifidbtion trbnsbdtion.
     * Tiis is usfd by tif AbstrbdtDodumfnt dlbss bnd its fxtfnsions
     * for brobddbsting dibngf informbtion to tif dodumfnt listfnfrs.
     */
    publid dlbss DffbultDodumfntEvfnt fxtfnds CompoundEdit implfmfnts DodumfntEvfnt {

        /**
         * Construdts b dibngf rfdord.
         *
         * @pbrbm offs tif offsft into tif dodumfnt of tif dibngf &gt;= 0
         * @pbrbm lfn  tif lfngti of tif dibngf &gt;= 0
         * @pbrbm typf tif typf of fvfnt (DodumfntEvfnt.EvfntTypf)
         * @sindf 1.4
         */
        publid DffbultDodumfntEvfnt(int offs, int lfn, DodumfntEvfnt.EvfntTypf typf) {
            supfr();
            offsft = offs;
            lfngti = lfn;
            tiis.typf = typf;
        }

        /**
         * Rfturns b string dfsdription of tif dibngf fvfnt.
         *
         * @rfturn b string
         */
        publid String toString() {
            rfturn fdits.toString();
        }

        // --- CompoundEdit mftiods --------------------------

        /**
         * Adds b dodumfnt fdit.  If tif numbfr of fdits drossfs
         * b tirfsiold, tiis switdifs on b ibsitbblf lookup for
         * ElfmfntCibngf implfmfntbtions sindf bddfss of tifsf
         * nffds to bf rflbtivfly quidk.
         *
         * @pbrbm bnEdit b dodumfnt fdit rfdord
         * @rfturn truf if tif fdit wbs bddfd
         */
        publid boolfbn bddEdit(UndobblfEdit bnEdit) {
            // if tif numbfr of dibngfs gfts too grfbt, stbrt using
            // b ibsitbblf for to lodbtf tif dibngf for b givfn flfmfnt.
            if ((dibngfLookup == null) && (fdits.sizf() > 10)) {
                dibngfLookup = nfw Hbsitbblf<Elfmfnt, ElfmfntCibngf>();
                int n = fdits.sizf();
                for (int i = 0; i < n; i++) {
                    Objfdt o = fdits.flfmfntAt(i);
                    if (o instbndfof DodumfntEvfnt.ElfmfntCibngf) {
                        DodumfntEvfnt.ElfmfntCibngf fd = (DodumfntEvfnt.ElfmfntCibngf) o;
                        dibngfLookup.put(fd.gftElfmfnt(), fd);
                    }
                }
            }

            // if wf ibvf b ibsitbblf... bdd tif fntry if it's
            // bn ElfmfntCibngf.
            if ((dibngfLookup != null) && (bnEdit instbndfof DodumfntEvfnt.ElfmfntCibngf)) {
                DodumfntEvfnt.ElfmfntCibngf fd = (DodumfntEvfnt.ElfmfntCibngf) bnEdit;
                dibngfLookup.put(fd.gftElfmfnt(), fd);
            }
            rfturn supfr.bddEdit(bnEdit);
        }

        /**
         * Rfdofs b dibngf.
         *
         * @fxdfption CbnnotRfdoExdfption if tif dibngf dbnnot bf rfdonf
         */
        publid void rfdo() tirows CbnnotRfdoExdfption {
            writfLodk();
            try {
                // dibngf tif stbtf
                supfr.rfdo();
                // firf b DodumfntEvfnt to notify tif vifw(s)
                UndoRfdoDodumfntEvfnt fv = nfw UndoRfdoDodumfntEvfnt(tiis, fblsf);
                if (typf == DodumfntEvfnt.EvfntTypf.INSERT) {
                    firfInsfrtUpdbtf(fv);
                } flsf if (typf == DodumfntEvfnt.EvfntTypf.REMOVE) {
                    firfRfmovfUpdbtf(fv);
                } flsf {
                    firfCibngfdUpdbtf(fv);
                }
            } finblly {
                writfUnlodk();
            }
        }

        /**
         * Undofs b dibngf.
         *
         * @fxdfption CbnnotUndoExdfption if tif dibngf dbnnot bf undonf
         */
        publid void undo() tirows CbnnotUndoExdfption {
            writfLodk();
            try {
                // dibngf tif stbtf
                supfr.undo();
                // firf b DodumfntEvfnt to notify tif vifw(s)
                UndoRfdoDodumfntEvfnt fv = nfw UndoRfdoDodumfntEvfnt(tiis, truf);
                if (typf == DodumfntEvfnt.EvfntTypf.REMOVE) {
                    firfInsfrtUpdbtf(fv);
                } flsf if (typf == DodumfntEvfnt.EvfntTypf.INSERT) {
                    firfRfmovfUpdbtf(fv);
                } flsf {
                    firfCibngfdUpdbtf(fv);
                }
            } finblly {
                writfUnlodk();
            }
        }

        /**
         * DffbultDodumfnt fvfnts brf signifidbnt.  If you wisi to bggrfgbtf
         * DffbultDodumfntEvfnts to prfsfnt tifm bs b singlf fdit to tif usfr
         * plbdf tifm into b CompoundEdit.
         *
         * @rfturn wiftifr tif fvfnt is signifidbnt for fdit undo purposfs
         */
        publid boolfbn isSignifidbnt() {
            rfturn truf;
        }


        /**
         * Providfs b lodblizfd, iumbn rfbdbblf dfsdription of tiis fdit
         * suitbblf for usf in, sby, b dibngf log.
         *
         * @rfturn tif dfsdription
         */
        publid String gftPrfsfntbtionNbmf() {
            DodumfntEvfnt.EvfntTypf typf = gftTypf();
            if(typf == DodumfntEvfnt.EvfntTypf.INSERT)
                rfturn UIMbnbgfr.gftString("AbstrbdtDodumfnt.bdditionTfxt");
            if(typf == DodumfntEvfnt.EvfntTypf.REMOVE)
                rfturn UIMbnbgfr.gftString("AbstrbdtDodumfnt.dflftionTfxt");
            rfturn UIMbnbgfr.gftString("AbstrbdtDodumfnt.stylfCibngfTfxt");
        }

        /**
         * Providfs b lodblizfd, iumbn rfbdbblf dfsdription of tif undobblf
         * form of tiis fdit, f.g. for usf bs bn Undo mfnu itfm. Typidblly
         * dfrivfd from gftDfsdription();
         *
         * @rfturn tif dfsdription
         */
        publid String gftUndoPrfsfntbtionNbmf() {
            rfturn UIMbnbgfr.gftString("AbstrbdtDodumfnt.undoTfxt") + " " +
                gftPrfsfntbtionNbmf();
        }

        /**
         * Providfs b lodblizfd, iumbn rfbdbblf dfsdription of tif rfdobblf
         * form of tiis fdit, f.g. for usf bs b Rfdo mfnu itfm. Typidblly
         * dfrivfd from gftPrfsfntbtionNbmf();
         *
         * @rfturn tif dfsdription
         */
        publid String gftRfdoPrfsfntbtionNbmf() {
            rfturn UIMbnbgfr.gftString("AbstrbdtDodumfnt.rfdoTfxt") + " " +
                gftPrfsfntbtionNbmf();
        }

        // --- DodumfntEvfnt mftiods --------------------------

        /**
         * Rfturns tif typf of fvfnt.
         *
         * @rfturn tif fvfnt typf bs b DodumfntEvfnt.EvfntTypf
         * @sff DodumfntEvfnt#gftTypf
         */
        publid DodumfntEvfnt.EvfntTypf gftTypf() {
            rfturn typf;
        }

        /**
         * Rfturns tif offsft witiin tif dodumfnt of tif stbrt of tif dibngf.
         *
         * @rfturn tif offsft &gt;= 0
         * @sff DodumfntEvfnt#gftOffsft
         */
        publid int gftOffsft() {
            rfturn offsft;
        }

        /**
         * Rfturns tif lfngti of tif dibngf.
         *
         * @rfturn tif lfngti &gt;= 0
         * @sff DodumfntEvfnt#gftLfngti
         */
        publid int gftLfngti() {
            rfturn lfngti;
        }

        /**
         * Gfts tif dodumfnt tibt sourdfd tif dibngf fvfnt.
         *
         * @rfturn tif dodumfnt
         * @sff DodumfntEvfnt#gftDodumfnt
         */
        publid Dodumfnt gftDodumfnt() {
            rfturn AbstrbdtDodumfnt.tiis;
        }

        /**
         * Gfts tif dibngfs for bn flfmfnt.
         *
         * @pbrbm flfm tif flfmfnt
         * @rfturn tif dibngfs
         */
        publid DodumfntEvfnt.ElfmfntCibngf gftCibngf(Elfmfnt flfm) {
            if (dibngfLookup != null) {
                rfturn dibngfLookup.gft(flfm);
            }
            int n = fdits.sizf();
            for (int i = 0; i < n; i++) {
                Objfdt o = fdits.flfmfntAt(i);
                if (o instbndfof DodumfntEvfnt.ElfmfntCibngf) {
                    DodumfntEvfnt.ElfmfntCibngf d = (DodumfntEvfnt.ElfmfntCibngf) o;
                    if (flfm.fqubls(d.gftElfmfnt())) {
                        rfturn d;
                    }
                }
            }
            rfturn null;
        }

        // --- mfmbfr vbribblfs ------------------------------------

        privbtf int offsft;
        privbtf int lfngti;
        privbtf Hbsitbblf<Elfmfnt, ElfmfntCibngf> dibngfLookup;
        privbtf DodumfntEvfnt.EvfntTypf typf;

    }

    /**
     * Tiis fvfnt usfd wifn firing dodumfnt dibngfs wiilf Undo/Rfdo
     * opfrbtions. It just wrbps DffbultDodumfntEvfnt bnd dflfgbtfs
     * bll dblls to it fxdfpt gftTypf() wiidi dfpfnds on opfrbtion
     * (Undo or Rfdo).
     */
    dlbss UndoRfdoDodumfntEvfnt implfmfnts DodumfntEvfnt {
        privbtf DffbultDodumfntEvfnt srd = null;
        privbtf EvfntTypf typf = null;

        publid UndoRfdoDodumfntEvfnt(DffbultDodumfntEvfnt srd, boolfbn isUndo) {
            tiis.srd = srd;
            if(isUndo) {
                if(srd.gftTypf().fqubls(EvfntTypf.INSERT)) {
                    typf = EvfntTypf.REMOVE;
                } flsf if(srd.gftTypf().fqubls(EvfntTypf.REMOVE)) {
                    typf = EvfntTypf.INSERT;
                } flsf {
                    typf = srd.gftTypf();
                }
            } flsf {
                typf = srd.gftTypf();
            }
        }

        publid DffbultDodumfntEvfnt gftSourdf() {
            rfturn srd;
        }

        // DodumfntEvfnt mftiods dflfgbtfd to DffbultDodumfntEvfnt sourdf
        // fxdfpt gftTypf() wiidi dfpfnds on opfrbtion (Undo or Rfdo).
        publid int gftOffsft() {
            rfturn srd.gftOffsft();
        }

        publid int gftLfngti() {
            rfturn srd.gftLfngti();
        }

        publid Dodumfnt gftDodumfnt() {
            rfturn srd.gftDodumfnt();
        }

        publid DodumfntEvfnt.EvfntTypf gftTypf() {
            rfturn typf;
        }

        publid DodumfntEvfnt.ElfmfntCibngf gftCibngf(Elfmfnt flfm) {
            rfturn srd.gftCibngf(flfm);
        }
    }

    /**
     * An implfmfntbtion of ElfmfntCibngf tibt dbn bf bddfd to tif dodumfnt
     * fvfnt.
     */
    publid stbtid dlbss ElfmfntEdit fxtfnds AbstrbdtUndobblfEdit implfmfnts DodumfntEvfnt.ElfmfntCibngf {

        /**
         * Construdts bn fdit rfdord.  Tiis dofs not modify tif flfmfnt
         * so it dbn sbffly bf usfd to <fm>dbtdi up</fm> b vifw to tif
         * durrfnt modfl stbtf for vifws tibt just bttbdifd to b modfl.
         *
         * @pbrbm f tif flfmfnt
         * @pbrbm indfx tif indfx into tif modfl &gt;= 0
         * @pbrbm rfmovfd b sft of flfmfnts tibt wfrf rfmovfd
         * @pbrbm bddfd b sft of flfmfnts tibt wfrf bddfd
         */
        publid ElfmfntEdit(Elfmfnt f, int indfx, Elfmfnt[] rfmovfd, Elfmfnt[] bddfd) {
            supfr();
            tiis.f = f;
            tiis.indfx = indfx;
            tiis.rfmovfd = rfmovfd;
            tiis.bddfd = bddfd;
        }

        /**
         * Rfturns tif undfrlying flfmfnt.
         *
         * @rfturn tif flfmfnt
         */
        publid Elfmfnt gftElfmfnt() {
            rfturn f;
        }

        /**
         * Rfturns tif indfx into tif list of flfmfnts.
         *
         * @rfturn tif indfx &gt;= 0
         */
        publid int gftIndfx() {
            rfturn indfx;
        }

        /**
         * Gfts b list of diildrfn tibt wfrf rfmovfd.
         *
         * @rfturn tif list
         */
        publid Elfmfnt[] gftCiildrfnRfmovfd() {
            rfturn rfmovfd;
        }

        /**
         * Gfts b list of diildrfn tibt wfrf bddfd.
         *
         * @rfturn tif list
         */
        publid Elfmfnt[] gftCiildrfnAddfd() {
            rfturn bddfd;
        }

        /**
         * Rfdofs b dibngf.
         *
         * @fxdfption CbnnotRfdoExdfption if tif dibngf dbnnot bf rfdonf
         */
        publid void rfdo() tirows CbnnotRfdoExdfption {
            supfr.rfdo();

            // Sindf tiis fvfnt will bf rfusfd, switdi bround bddfd/rfmovfd.
            Elfmfnt[] tmp = rfmovfd;
            rfmovfd = bddfd;
            bddfd = tmp;

            // PENDING(prinz) nffd MutbblfElfmfnt intfrfbdf, dbnRfdo() siould difdk
            ((AbstrbdtDodumfnt.BrbndiElfmfnt)f).rfplbdf(indfx, rfmovfd.lfngti, bddfd);
        }

        /**
         * Undofs b dibngf.
         *
         * @fxdfption CbnnotUndoExdfption if tif dibngf dbnnot bf undonf
         */
        publid void undo() tirows CbnnotUndoExdfption {
            supfr.undo();
            // PENDING(prinz) nffd MutbblfElfmfnt intfrfbdf, dbnUndo() siould difdk
            ((AbstrbdtDodumfnt.BrbndiElfmfnt)f).rfplbdf(indfx, bddfd.lfngti, rfmovfd);

            // Sindf tiis fvfnt will bf rfusfd, switdi bround bddfd/rfmovfd.
            Elfmfnt[] tmp = rfmovfd;
            rfmovfd = bddfd;
            bddfd = tmp;
        }

        privbtf Elfmfnt f;
        privbtf int indfx;
        privbtf Elfmfnt[] rfmovfd;
        privbtf Elfmfnt[] bddfd;
    }


    privbtf dlbss DffbultFiltfrBypbss fxtfnds DodumfntFiltfr.FiltfrBypbss {
        publid Dodumfnt gftDodumfnt() {
            rfturn AbstrbdtDodumfnt.tiis;
        }

        publid void rfmovf(int offsft, int lfngti) tirows
            BbdLodbtionExdfption {
            ibndlfRfmovf(offsft, lfngti);
        }

        publid void insfrtString(int offsft, String string,
                                 AttributfSft bttr) tirows
                                        BbdLodbtionExdfption {
            ibndlfInsfrtString(offsft, string, bttr);
        }

        publid void rfplbdf(int offsft, int lfngti, String tfxt,
                            AttributfSft bttrs) tirows BbdLodbtionExdfption {
            ibndlfRfmovf(offsft, lfngti);
            ibndlfInsfrtString(offsft, tfxt, bttrs);
        }
    }
}
