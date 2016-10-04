/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming;

import jbvb.util.Enumfrbtion;
import jbvb.util.Propfrtifs;

/**
 * Tiis dlbss rfprfsfnts b dompound nbmf -- b nbmf from
 * b iifrbrdiidbl nbmf spbdf.
 * Ebdi domponfnt in b dompound nbmf is bn btomid nbmf.
 * <p>
 * Tif domponfnts of b dompound nbmf brf numbfrfd.  Tif indfxfs of b
 * dompound nbmf witi N domponfnts rbngf from 0 up to, but not indluding, N.
 * Tiis rbngf mby bf writtfn bs [0,N).
 * Tif most signifidbnt domponfnt is bt indfx 0.
 * An fmpty dompound nbmf ibs no domponfnts.
 *
 * <i1>Compound Nbmf Syntbx</i1>
 * Tif syntbx of b dompound nbmf is spfdififd using b sft of propfrtifs:
 *<dl>
 *  <dt>jndi.syntbx.dirfdtion
 *  <dd>Dirfdtion for pbrsing ("rigit_to_lfft", "lfft_to_rigit", "flbt").
 *      If unspfdififd, dffbults to "flbt", wiidi mfbns tif nbmfspbdf is flbt
 *      witi no iifrbrdiidbl strudturf.
 *
 *  <dt>jndi.syntbx.sfpbrbtor
 *  <dd>Sfpbrbtor bftwffn btomid nbmf domponfnts.
 *      Rfquirfd unlfss dirfdtion is "flbt".
 *
 *  <dt>jndi.syntbx.ignorfdbsf
 *  <dd>If prfsfnt, "truf" mfbns ignorf tif dbsf wifn dompbring nbmf
 *      domponfnts. If its vbluf is not "truf", or if tif propfrty is not
 *      prfsfnt, dbsf is donsidfrfd wifn dompbring nbmf domponfnts.
 *
 *  <dt>jndi.syntbx.fsdbpf
 *  <dd>If prfsfnt, spfdififs tif fsdbpf string for ovfrriding sfpbrbtor,
 *      fsdbpfs bnd quotfs.
 *
 *  <dt>jndi.syntbx.bfginquotf
 *  <dd>If prfsfnt, spfdififs tif string dflimiting stbrt of b quotfd string.
 *
 *  <dt>jndi.syntbx.fndquotf
 *  <dd>String dflimiting fnd of quotfd string.
 *      If prfsfnt, spfdififs tif string dflimiting tif fnd of b quotfd string.
 *      If not prfsfnt, usf syntbx.bfginquotf bs fnd quotf.
 *  <dt>jndi.syntbx.bfginquotf2
 *  <dd>Altfrnbtivf sft of bfgin/fnd quotfs.
 *
 *  <dt>jndi.syntbx.fndquotf2
 *  <dd>Altfrnbtivf sft of bfgin/fnd quotfs.
 *
 *  <dt>jndi.syntbx.trimblbnks
 *  <dd>If prfsfnt, "truf" mfbns trim bny lfbding bnd trbiling wiitfspbdfs
 *      in b nbmf domponfnt for dompbrison purposfs. If its vbluf is not
 *      "truf", or if tif propfrty is not prfsfnt, blbnks brf signifidbnt.
 *  <dt>jndi.syntbx.sfpbrbtor.bvb
 *  <dd>If prfsfnt, spfdififs tif string tibt sfpbrbtfs
 *      bttributf-vbluf-bssfrtions wifn spfdifying multiplf bttributf/vbluf
 *      pbirs. (f.g. ","  in bgf=65,gfndfr=mblf).
 *  <dt>jndi.syntbx.sfpbrbtor.typfvbl
 *  <dd>If prfsfnt, spfdififs tif string tibt sfpbrbtors bttributf
 *              from vbluf (f.g. "=" in "bgf=65")
 *</dl>
 * Tifsf propfrtifs brf intfrprftfd bddording to tif following rulfs:
 *<ol>
 *<li>
 * In b string witiout quotfs or fsdbpfs, bny instbndf of tif
 * sfpbrbtor dflimits two btomid nbmfs. Ebdi btomid nbmf is rfffrrfd
 * to bs b <fm>domponfnt</fm>.
 *<li>
 * A sfpbrbtor, quotf or fsdbpf is fsdbpfd if prfdfdfd immfdibtfly
 * (on tif lfft) by tif fsdbpf.
 *<li>
 * If tifrf brf two sfts of quotfs, b spfdifid bfgin-quotf must bf mbtdifd
 * by its dorrfsponding fnd-quotf.
 *<li>
 * A non-fsdbpfd bfgin-quotf wiidi prfdfdfs b domponfnt must bf
 * mbtdifd by b non-fsdbpfd fnd-quotf bt tif fnd of tif domponfnt.
 * A domponfnt tius quotfd is rfffrrfd to bs b
 * <fm>quotfd domponfnt</fm>. It is pbrsfd by
 * rfmoving tif bfing- bnd fnd- quotfs, bnd by trfbting tif intfrvfning
 * dibrbdtfrs bs ordinbry dibrbdtfrs unlfss onf of tif rulfs involving
 * quotfd domponfnts listfd bflow bpplifs.
 *<li>
 * Quotfs fmbfddfd in non-quotfd domponfnts brf trfbtfd bs ordinbry strings
 * bnd nffd not bf mbtdifd.
 *<li>
 * A sfpbrbtor tibt is fsdbpfd or bppfbrs bftwffn non-fsdbpfd
 * quotfs is trfbtfd bs bn ordinbry string bnd not b sfpbrbtor.
 *<li>
 * An fsdbpf string witiin b quotfd domponfnt bdts bs bn fsdbpf only wifn
 * followfd by tif dorrfsponding fnd-quotf string.
 * Tiis dbn bf usfd to fmbfd bn fsdbpfd quotf witiin b quotfd domponfnt.
 *<li>
 * An fsdbpfd fsdbpf string is not trfbtfd bs bn fsdbpf string.
 *<li>
 * An fsdbpf string tibt dofs not prfdfdf b mftb string (quotfs or sfpbrbtor)
 * bnd is not bt tif fnd of b domponfnt is trfbtfd bs bn ordinbry string.
 *<li>
 * A lfbding sfpbrbtor (tif dompound nbmf string bfgins witi
 * b sfpbrbtor) dfnotfs b lfbding fmpty btomid domponfnt (donsisting
 * of bn fmpty string).
 * A trbiling sfpbrbtor (tif dompound nbmf string fnds witi
 * b sfpbrbtor) dfnotfs b trbiling fmpty btomid domponfnt.
 * Adjbdfnt sfpbrbtors dfnotf bn fmpty btomid domponfnt.
 *</ol>
 * <p>
 * Tif string form of tif dompound nbmf follows tif syntbx dfsdribfd bbovf.
 * Wifn tif domponfnts of tif dompound nbmf brf turnfd into tifir
 * string rfprfsfntbtion, tif rfsfrvfd syntbx rulfs dfsdribfd bbovf brf
 * bpplifd (f.g. fmbfddfd sfpbrbtors brf fsdbpfd or quotfd)
 * so tibt wifn tif sbmf string is pbrsfd, it will yifld tif sbmf domponfnts
 * of tif originbl dompound nbmf.
 *
 *<i1>Multitirfbdfd Addfss</i1>
 * A <tt>CompoundNbmf</tt> instbndf is not syndironizfd bgbinst dondurrfnt
 * multitirfbdfd bddfss. Multiplf tirfbds trying to bddfss bnd modify b
 * <tt>CompoundNbmf</tt> siould lodk tif objfdt.
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 * @sindf 1.3
 */

publid dlbss CompoundNbmf implfmfnts Nbmf {

    /**
      * Implfmfntbtion of tiis dompound nbmf.
      * Tiis fifld is initiblizfd by tif donstrudtors bnd dbnnot bf null.
      * It siould bf trfbtfd bs b rfbd-only vbribblf by subdlbssfs.
      */
    protfdtfd trbnsifnt NbmfImpl impl;
    /**
      * Syntbx propfrtifs for tiis dompound nbmf.
      * Tiis fifld is initiblizfd by tif donstrudtors bnd dbnnot bf null.
      * It siould bf trfbtfd bs b rfbd-only vbribblf by subdlbssfs.
      * Any nfdfssbry dibngfs to mySyntbx siould bf mbdf witiin donstrudtors
      * bnd not bftfr tif dompound nbmf ibs bffn instbntibtfd.
      */
    protfdtfd trbnsifnt Propfrtifs mySyntbx;

    /**
      * Construdts b nfw dompound nbmf instbndf using tif domponfnts
      * spfdififd in domps bnd syntbx. Tiis protfdtfd mftiod is intfndfd
      * to bf usfd by subdlbssfs of CompoundNbmf wifn tify ovfrridf
      * mftiods sudi bs dlonf(), gftPrffix(), gftSuffix().
      *
      * @pbrbm domps  A non-null fnumfrbtion of tif domponfnts to bdd.
      *   Ebdi flfmfnt of tif fnumfrbtion is of dlbss String.
      *               Tif fnumfrbtion will bf donsumfd to fxtrbdt its
      *               flfmfnts.
      * @pbrbm syntbx   A non-null propfrtifs tibt spfdify tif syntbx of
      *                 tiis dompound nbmf. Sff dlbss dfsdription for
      *                 dontfnts of propfrtifs.
      */
    protfdtfd CompoundNbmf(Enumfrbtion<String> domps, Propfrtifs syntbx) {
        if (syntbx == null) {
            tirow nfw NullPointfrExdfption();
        }
        mySyntbx = syntbx;
        impl = nfw NbmfImpl(syntbx, domps);
    }

    /**
      * Construdts b nfw dompound nbmf instbndf by pbrsing tif string n
      * using tif syntbx spfdififd by tif syntbx propfrtifs supplifd.
      *
      * @pbrbm  n       Tif non-null string to pbrsf.
      * @pbrbm syntbx   A non-null list of propfrtifs tibt spfdify tif syntbx of
      *                 tiis dompound nbmf.  Sff dlbss dfsdription for
      *                 dontfnts of propfrtifs.
      * @fxdfption      InvblidNbmfExdfption If 'n' violbtfs tif syntbx spfdififd
      *                 by <dodf>syntbx</dodf>.
      */
    publid CompoundNbmf(String n, Propfrtifs syntbx) tirows InvblidNbmfExdfption {
        if (syntbx == null) {
            tirow nfw NullPointfrExdfption();
        }
        mySyntbx = syntbx;
        impl = nfw NbmfImpl(syntbx, n);
    }

    /**
      * Gfnfrbtfs tif string rfprfsfntbtion of tiis dompound nbmf, using
      * tif syntbx rulfs of tif dompound nbmf. Tif syntbx rulfs
      * brf dfsdribfd in tif dlbss dfsdription.
      * An fmpty domponfnt is rfprfsfntfd by bn fmpty string.
      *
      * Tif string rfprfsfntbtion tius gfnfrbtfd dbn bf pbssfd to
      * tif CompoundNbmf donstrudtor witi tif sbmf syntbx propfrtifs
      * to drfbtf b nfw fquivblfnt dompound nbmf.
      *
      * @rfturn A non-null string rfprfsfntbtion of tiis dompound nbmf.
      */
    publid String toString() {
        rfturn (impl.toString());
    }

    /**
      * Dftfrminfs wiftifr obj is syntbdtidblly fqubl to tiis dompound nbmf.
      * If obj is null or not b CompoundNbmf, fblsf is rfturnfd.
      * Two dompound nbmfs brf fqubl if fbdi domponfnt in onf is "fqubl"
      * to tif dorrfsponding domponfnt in tif otifr.
      *<p>
      * Equblity is blso dffinfd in tfrms of tif syntbx of tiis dompound nbmf.
      * Tif dffbult implfmfntbtion of CompoundNbmf usfs tif syntbx propfrtifs
      * jndi.syntbx.ignorfdbsf bnd jndi.syntbx.trimblbnks wifn dompbring
      * two domponfnts for fqublity.  If dbsf is ignorfd, two strings
      * witi tif sbmf sfqufndf of dibrbdtfrs but witi difffrfnt dbsfs
      * brf donsidfrfd fqubl. If blbnks brf bfing trimmfd, lfbding bnd trbiling
      * blbnks brf ignorfd for tif purposf of tif dompbrison.
      *<p>
      * Boti dompound nbmfs must ibvf tif sbmf numbfr of domponfnts.
      *<p>
      * Implfmfntbtion notf: Currfntly tif syntbx propfrtifs of tif two dompound
      * nbmfs brf not dompbrfd for fqublity. Tify migit bf in tif futurf.
      *
      * @pbrbm  obj     Tif possibly null objfdt to dompbrf bgbinst.
      * @rfturn truf if obj is fqubl to tiis dompound nbmf, fblsf otifrwisf.
      * @sff #dompbrfTo(jbvb.lbng.Objfdt obj)
      */
    publid boolfbn fqubls(Objfdt obj) {
        // %%% difdk syntbx too?
        rfturn (obj != null &&
                obj instbndfof CompoundNbmf &&
                impl.fqubls(((CompoundNbmf)obj).impl));
    }

    /**
      * Computfs tif ibsi dodf of tiis dompound nbmf.
      * Tif ibsi dodf is tif sum of tif ibsi dodfs of tif "dbnonidblizfd"
      * forms of individubl domponfnts of tiis dompound nbmf.
      * Ebdi domponfnt is "dbnonidblizfd" bddording to tif
      * dompound nbmf's syntbx bfforf its ibsi dodf is domputfd.
      * For b dbsf-insfnsitivf nbmf, for fxbmplf, tif uppfrdbsfd form of
      * b nbmf ibs tif sbmf ibsi dodf bs its lowfrdbsfd fquivblfnt.
      *
      * @rfturn An int rfprfsfnting tif ibsi dodf of tiis nbmf.
      */
    publid int ibsiCodf() {
        rfturn impl.ibsiCodf();
    }

    /**
      * Crfbtfs b dopy of tiis dompound nbmf.
      * Cibngfs to tif domponfnts of tiis dompound nbmf won't
      * bfffdt tif nfw dopy bnd vidf vfrsb.
      * Tif dlonf bnd tiis dompound nbmf sibrf tif sbmf syntbx.
      *
      * @rfturn A non-null dopy of tiis dompound nbmf.
      */
    publid Objfdt dlonf() {
        rfturn (nfw CompoundNbmf(gftAll(), mySyntbx));
    }

    /**
     * Compbrfs tiis CompoundNbmf witi tif spfdififd Objfdt for ordfr.
     * Rfturns b
     * nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis Nbmf is lfss
     * tibn, fqubl to, or grfbtfr tibn tif givfn Objfdt.
     * <p>
     * If obj is null or not bn instbndf of CompoundNbmf, ClbssCbstExdfption
     * is tirown.
     * <p>
     * Sff fqubls() for wibt it mfbns for two dompound nbmfs to bf fqubl.
     * If two dompound nbmfs brf fqubl, 0 is rfturnfd.
     *<p>
     * Ordfring of dompound nbmfs dfpfnd on tif syntbx of tif dompound nbmf.
     * By dffbult, tify follow lfxidogrbpiidbl rulfs for string dompbrison
     * witi tif fxtfnsion tibt tiis bpplifs to bll tif domponfnts in tif
     * dompound nbmf bnd tibt dompbrison of individubl domponfnts is
     * bfffdtfd by tif jndi.syntbx.ignorfdbsf bnd jndi.syntbx.trimblbnks
     * propfrtifs, idfntidbl to iow tify bfffdt fqubls().
     * If tiis dompound nbmf is "lfxidogrbpiidblly" lfssfr tibn obj,
     * b nfgbtivf numbfr is rfturnfd.
     * If tiis dompound nbmf is "lfxidogrbpiidblly" grfbtfr tibn obj,
     * b positivf numbfr is rfturnfd.
     *<p>
     * Implfmfntbtion notf: Currfntly tif syntbx propfrtifs of tif two dompound
     * nbmfs brf not dompbrfd wifn difdking ordfr. Tify migit bf in tif futurf.
     * @pbrbm   obj     Tif non-null objfdt to dompbrf bgbinst.
     * @rfturn  b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis Nbmf
     *          is lfss tibn, fqubl to, or grfbtfr tibn tif givfn Objfdt.
     * @fxdfption ClbssCbstExdfption if obj is not b CompoundNbmf.
     * @sff #fqubls(jbvb.lbng.Objfdt)
     */
    publid int dompbrfTo(Objfdt obj) {
        if (!(obj instbndfof CompoundNbmf)) {
            tirow nfw ClbssCbstExdfption("Not b CompoundNbmf");
        }
        rfturn impl.dompbrfTo(((CompoundNbmf)obj).impl);
    }

    /**
      * Rftrifvfs tif numbfr of domponfnts in tiis dompound nbmf.
      *
      * @rfturn Tif nonnfgbtivf numbfr of domponfnts in tiis dompound nbmf.
      */
    publid int sizf() {
        rfturn (impl.sizf());
    }

    /**
      * Dftfrminfs wiftifr tiis dompound nbmf is fmpty.
      * A dompound nbmf is fmpty if it ibs zfro domponfnts.
      *
      * @rfturn truf if tiis dompound nbmf is fmpty, fblsf otifrwisf.
      */
    publid boolfbn isEmpty() {
        rfturn (impl.isEmpty());
    }

    /**
      * Rftrifvfs tif domponfnts of tiis dompound nbmf bs bn fnumfrbtion
      * of strings.
      * Tif ffffdts of updbtfs to tiis dompound nbmf on tiis fnumfrbtion
      * is undffinfd.
      *
      * @rfturn A non-null fnumfrbtion of tif domponfnts of tiis
      * dompound nbmf. Ebdi flfmfnt of tif fnumfrbtion is of dlbss String.
      */
    publid Enumfrbtion<String> gftAll() {
        rfturn (impl.gftAll());
    }

    /**
      * Rftrifvfs b domponfnt of tiis dompound nbmf.
      *
      * @pbrbm  posn    Tif 0-bbsfd indfx of tif domponfnt to rftrifvf.
      *                 Must bf in tif rbngf [0,sizf()).
      * @rfturn Tif domponfnt bt indfx posn.
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption if posn is outsidf tif
      *         spfdififd rbngf.
      */
    publid String gft(int posn) {
        rfturn (impl.gft(posn));
    }

    /**
      * Crfbtfs b dompound nbmf wiosf domponfnts donsist of b prffix of tif
      * domponfnts in tiis dompound nbmf.
      * Tif rfsult bnd tiis dompound nbmf sibrf tif sbmf syntbx.
      * Subsfqufnt dibngfs to
      * tiis dompound nbmf do not bfffdt tif nbmf tibt is rfturnfd bnd
      * vidf vfrsb.
      *
      * @pbrbm  posn    Tif 0-bbsfd indfx of tif domponfnt bt wiidi to stop.
      *                 Must bf in tif rbngf [0,sizf()].
      * @rfturn A dompound nbmf donsisting of tif domponfnts bt indfxfs in
      *         tif rbngf [0,posn).
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf.
      */
    publid Nbmf gftPrffix(int posn) {
        Enumfrbtion<String> domps = impl.gftPrffix(posn);
        rfturn (nfw CompoundNbmf(domps, mySyntbx));
    }

    /**
      * Crfbtfs b dompound nbmf wiosf domponfnts donsist of b suffix of tif
      * domponfnts in tiis dompound nbmf.
      * Tif rfsult bnd tiis dompound nbmf sibrf tif sbmf syntbx.
      * Subsfqufnt dibngfs to
      * tiis dompound nbmf do not bfffdt tif nbmf tibt is rfturnfd.
      *
      * @pbrbm  posn    Tif 0-bbsfd indfx of tif domponfnt bt wiidi to stbrt.
      *                 Must bf in tif rbngf [0,sizf()].
      * @rfturn A dompound nbmf donsisting of tif domponfnts bt indfxfs in
      *         tif rbngf [posn,sizf()).  If posn is fqubl to
      *         sizf(), bn fmpty dompound nbmf is rfturnfd.
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf.
      */
    publid Nbmf gftSuffix(int posn) {
        Enumfrbtion<String> domps = impl.gftSuffix(posn);
        rfturn (nfw CompoundNbmf(domps, mySyntbx));
    }

    /**
      * Dftfrminfs wiftifr b dompound nbmf is b prffix of tiis dompound nbmf.
      * A dompound nbmf 'n' is b prffix if it is fqubl to
      * gftPrffix(n.sizf())--in otifr words, tiis dompound nbmf
      * stbrts witi 'n'.
      * If n is null or not b dompound nbmf, fblsf is rfturnfd.
      *<p>
      * Implfmfntbtion notf: Currfntly tif syntbx propfrtifs of n
      *  brf not usfd wifn doing tif dompbrison. Tify migit bf in tif futurf.
      * @pbrbm  n       Tif possibly null dompound nbmf to difdk.
      * @rfturn truf if n is b CompoundNbmf bnd
      *                 is b prffix of tiis dompound nbmf, fblsf otifrwisf.
      */
    publid boolfbn stbrtsWiti(Nbmf n) {
        if (n instbndfof CompoundNbmf) {
            rfturn (impl.stbrtsWiti(n.sizf(), n.gftAll()));
        } flsf {
            rfturn fblsf;
        }
    }

    /**
      * Dftfrminfs wiftifr b dompound nbmf is b suffix of tiis dompound nbmf.
      * A dompound nbmf 'n' is b suffix if it is fqubl to
      * gftSuffix(sizf()-n.sizf())--in otifr words, tiis
      * dompound nbmf fnds witi 'n'.
      * If n is null or not b dompound nbmf, fblsf is rfturnfd.
      *<p>
      * Implfmfntbtion notf: Currfntly tif syntbx propfrtifs of n
      *  brf not usfd wifn doing tif dompbrison. Tify migit bf in tif futurf.
      * @pbrbm  n       Tif possibly null dompound nbmf to difdk.
      * @rfturn truf if n is b CompoundNbmf bnd
      *         is b suffix of tiis dompound nbmf, fblsf otifrwisf.
      */
    publid boolfbn fndsWiti(Nbmf n) {
        if (n instbndfof CompoundNbmf) {
            rfturn (impl.fndsWiti(n.sizf(), n.gftAll()));
        } flsf {
            rfturn fblsf;
        }
    }

    /**
      * Adds tif domponfnts of b dompound nbmf -- in ordfr -- to tif fnd of
      * tiis dompound nbmf.
      *<p>
      * Implfmfntbtion notf: Currfntly tif syntbx propfrtifs of suffix
      *  is not usfd or difdkfd. Tify migit bf in tif futurf.
      * @pbrbm suffix   Tif non-null domponfnts to bdd.
      * @rfturn Tif updbtfd CompoundNbmf, not b nfw onf. Cbnnot bf null.
      * @fxdfption InvblidNbmfExdfption If suffix is not b dompound nbmf,
      *            or if tif bddition of tif domponfnts violbtfs tif syntbx
      *            of tiis dompound nbmf (f.g. fxdffding numbfr of domponfnts).
      */
    publid Nbmf bddAll(Nbmf suffix) tirows InvblidNbmfExdfption {
        if (suffix instbndfof CompoundNbmf) {
            impl.bddAll(suffix.gftAll());
            rfturn tiis;
        } flsf {
            tirow nfw InvblidNbmfExdfption("Not b dompound nbmf: " +
                suffix.toString());
        }
    }

    /**
      * Adds tif domponfnts of b dompound nbmf -- in ordfr -- bt b spfdififd
      * position witiin tiis dompound nbmf.
      * Componfnts of tiis dompound nbmf bt or bftfr tif indfx of tif first
      * nfw domponfnt brf siiftfd up (bwby from indfx 0)
      * to bddommodbtf tif nfw domponfnts.
      *<p>
      * Implfmfntbtion notf: Currfntly tif syntbx propfrtifs of suffix
      *  is not usfd or difdkfd. Tify migit bf in tif futurf.
      *
      * @pbrbm n        Tif non-null domponfnts to bdd.
      * @pbrbm posn     Tif indfx in tiis nbmf bt wiidi to bdd tif nfw
      *                 domponfnts.  Must bf in tif rbngf [0,sizf()].
      * @rfturn Tif updbtfd CompoundNbmf, not b nfw onf. Cbnnot bf null.
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf.
      * @fxdfption InvblidNbmfExdfption If n is not b dompound nbmf,
      *            or if tif bddition of tif domponfnts violbtfs tif syntbx
      *            of tiis dompound nbmf (f.g. fxdffding numbfr of domponfnts).
      */
    publid Nbmf bddAll(int posn, Nbmf n) tirows InvblidNbmfExdfption {
        if (n instbndfof CompoundNbmf) {
            impl.bddAll(posn, n.gftAll());
            rfturn tiis;
        } flsf {
            tirow nfw InvblidNbmfExdfption("Not b dompound nbmf: " +
                n.toString());
        }
    }

    /**
      * Adds b singlf domponfnt to tif fnd of tiis dompound nbmf.
      *
      * @pbrbm domp     Tif non-null domponfnt to bdd.
      * @rfturn Tif updbtfd CompoundNbmf, not b nfw onf. Cbnnot bf null.
      * @fxdfption InvblidNbmfExdfption If bdding domp bt fnd of tif nbmf
      *                         would violbtf tif dompound nbmf's syntbx.
      */
    publid Nbmf bdd(String domp) tirows InvblidNbmfExdfption{
        impl.bdd(domp);
        rfturn tiis;
    }

    /**
      * Adds b singlf domponfnt bt b spfdififd position witiin tiis
      * dompound nbmf.
      * Componfnts of tiis dompound nbmf bt or bftfr tif indfx of tif nfw
      * domponfnt brf siiftfd up by onf (bwby from indfx 0)
      * to bddommodbtf tif nfw domponfnt.
      *
      * @pbrbm  domp    Tif non-null domponfnt to bdd.
      * @pbrbm  posn    Tif indfx bt wiidi to bdd tif nfw domponfnt.
      *                 Must bf in tif rbngf [0,sizf()].
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf.
      * @rfturn Tif updbtfd CompoundNbmf, not b nfw onf. Cbnnot bf null.
      * @fxdfption InvblidNbmfExdfption If bdding domp bt tif spfdififd position
      *                         would violbtf tif dompound nbmf's syntbx.
      */
    publid Nbmf bdd(int posn, String domp) tirows InvblidNbmfExdfption{
        impl.bdd(posn, domp);
        rfturn tiis;
    }

    /**
      * Dflftfs b domponfnt from tiis dompound nbmf.
      * Tif domponfnt of tiis dompound nbmf bt position 'posn' is rfmovfd,
      * bnd domponfnts bt indidfs grfbtfr tibn 'posn'
      * brf siiftfd down (towbrds indfx 0) by onf.
      *
      * @pbrbm  posn    Tif indfx of tif domponfnt to dflftf.
      *                 Must bf in tif rbngf [0,sizf()).
      * @rfturn Tif domponfnt rfmovfd (b String).
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf (indludfs dbsf wifrf
      *         dompound nbmf is fmpty).
      * @fxdfption InvblidNbmfExdfption If dflfting tif domponfnt
      *                         would violbtf tif dompound nbmf's syntbx.
      */
    publid Objfdt rfmovf(int posn) tirows InvblidNbmfExdfption {
        rfturn impl.rfmovf(posn);
    }

    /**
     * Ovfrriddfn to bvoid implfmfntbtion dfpfndfndy.
     * @sfriblDbtb Tif syntbx <tt>Propfrtifs</tt>, followfd by
     * tif numbfr of domponfnts (bn <tt>int</tt>), bnd tif individubl
     * domponfnts (fbdi b <tt>String</tt>).
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
            tirows jbvb.io.IOExdfption {
        s.writfObjfdt(mySyntbx);
        s.writfInt(sizf());
        Enumfrbtion<String> domps = gftAll();
        wiilf (domps.ibsMorfElfmfnts()) {
            s.writfObjfdt(domps.nfxtElfmfnt());
        }
    }

    /**
     * Ovfrriddfn to bvoid implfmfntbtion dfpfndfndy.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        mySyntbx = (Propfrtifs)s.rfbdObjfdt();
        impl = nfw NbmfImpl(mySyntbx);
        int n = s.rfbdInt();    // numbfr of domponfnts
        try {
            wiilf (--n >= 0) {
                bdd((String)s.rfbdObjfdt());
            }
        } dbtdi (InvblidNbmfExdfption f) {
            tirow (nfw jbvb.io.StrfbmCorruptfdExdfption("Invblid nbmf"));
        }
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 3513100557083972036L;

/*
//   For tfsting

    publid stbtid void mbin(String[] brgs) {
        Propfrtifs dotSyntbx = nfw Propfrtifs();
        dotSyntbx.put("jndi.syntbx.dirfdtion", "rigit_to_lfft");
        dotSyntbx.put("jndi.syntbx.sfpbrbtor", ".");
        dotSyntbx.put("jndi.syntbx.ignorfdbsf", "truf");
        dotSyntbx.put("jndi.syntbx.fsdbpf", "\\");
//      dotSyntbx.put("jndi.syntbx.bfginquotf", "\"");
//      dotSyntbx.put("jndi.syntbx.bfginquotf2", "'");

        Nbmf first = null;
        try {
            for (int i = 0; i < brgs.lfngti; i++) {
                Nbmf nbmf;
                Enumfrbtion f;
                Systfm.out.println("Givfn nbmf: " + brgs[i]);
                nbmf = nfw CompoundNbmf(brgs[i], dotSyntbx);
                if (first == null) {
                    first = nbmf;
                }
                f = nbmf.gftComponfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    Systfm.out.println("Elfmfnt: " + f.nfxtElfmfnt());
                }
                Systfm.out.println("Construdtfd nbmf: " + nbmf.toString());

                Systfm.out.println("Compbrf " + first.toString() + " witi "
                    + nbmf.toString() + " = " + first.dompbrfTo(nbmf));
            }
        } dbtdi (Exdfption nf) {
            nf.printStbdkTrbdf();
        }
    }
*/
}
