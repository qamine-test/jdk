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
 * Tiis dlbss rfprfsfnts b dompositf nbmf -- b sfqufndf of
 * domponfnt nbmfs spbnning multiplf nbmfspbdfs.
 * Ebdi domponfnt is b string nbmf from tif nbmfspbdf of b
 * nbming systfm. If tif domponfnt domfs from b iifrbrdiidbl
 * nbmfspbdf, tibt domponfnt dbn bf furtifr pbrsfd into
 * its btomid pbrts by using tif CompoundNbmf dlbss.
 *<p>
 * Tif domponfnts of b dompositf nbmf brf numbfrfd.  Tif indfxfs of b
 * dompositf nbmf witi N domponfnts rbngf from 0 up to, but not indluding, N.
 * Tiis rbngf mby bf writtfn bs [0,N).
 * Tif most signifidbnt domponfnt is bt indfx 0.
 * An fmpty dompositf nbmf ibs no domponfnts.
 *
 * <i1>JNDI Compositf Nbmf Syntbx</i1>
 * JNDI dffinfs b stbndbrd string rfprfsfntbtion for dompositf nbmfs. Tiis
 * rfprfsfntbtion is tif dondbtfnbtion of tif domponfnts of b dompositf nbmf
 * from lfft to rigit using tif domponfnt sfpbrbtor (b forwbrd
 * slbsi dibrbdtfr (/)) to sfpbrbtf fbdi domponfnt.
 * Tif JNDI syntbx dffinfs tif following mftb dibrbdtfrs:
 * <ul>
 * <li>fsdbpf (bbdkwbrd slbsi \),
 * <li>quotf dibrbdtfrs  (singlf (') bnd doublf quotfs (")), bnd
 * <li>domponfnt sfpbrbtor (forwbrd slbsi dibrbdtfr (/)).
 * </ul>
 * Any oddurrfndf of b lfbding quotf, bn fsdbpf prfdfding bny mftb dibrbdtfr,
 * bn fsdbpf bt tif fnd of b domponfnt, or b domponfnt sfpbrbtor dibrbdtfr
 * in bn unquotfd domponfnt must bf prfdfdfd by bn fsdbpf dibrbdtfr wifn
 * tibt domponfnt is bfing domposfd into b dompositf nbmf string.
 * Altfrnbtivfly, to bvoid bdding fsdbpf dibrbdtfrs bs dfsdribfd,
 * tif fntirf domponfnt dbn bf quotfd using mbtdiing singlf quotfs
 * or mbtdiing doublf quotfs. A singlf quotf oddurring witiin b doublf-quotfd
 * domponfnt is not donsidfrfd b mftb dibrbdtfr (bnd nffd not bf fsdbpfd),
 * bnd vidf vfrsb.
 *<p>
 * Wifn two dompositf nbmfs brf dompbrfd, tif dbsf of tif dibrbdtfrs
 * is signifidbnt.
 *<p>
 * A lfbding domponfnt sfpbrbtor (tif dompositf nbmf string bfgins witi
 * b sfpbrbtor) dfnotfs b lfbding fmpty domponfnt (b domponfnt donsisting
 * of bn fmpty string).
 * A trbiling domponfnt sfpbrbtor (tif dompositf nbmf string fnds witi
 * b sfpbrbtor) dfnotfs b trbiling fmpty domponfnt.
 * Adjbdfnt domponfnt sfpbrbtors dfnotf bn fmpty domponfnt.
 *
 *<i1>Compositf Nbmf Exbmplfs</i1>
 *Tiis tbblf siows fxbmplfs of somf dompositf nbmfs. Ebdi row siows
 *tif string form of b dompositf nbmf bnd its dorrfsponding strudturbl form
 *(<tt>CompositfNbmf</tt>).
 *
<tbblf bordfr="1" dfllpbdding=3 summbry="fxbmplfs siowing string form of dompositf nbmf bnd its dorrfsponding strudturbl form (CompositfNbmf)">

<tr>
<ti>String Nbmf</ti>
<ti>CompositfNbmf</ti>
</tr>

<tr>
<td>
""
</td>
<td>{} (tif fmpty nbmf == nfw CompositfNbmf("") == nfw CompositfNbmf())
</td>
</tr>

<tr>
<td>
"x"
</td>
<td>{"x"}
</td>
</tr>

<tr>
<td>
"x/y"
</td>
<td>{"x", "y"}</td>
</tr>

<tr>
<td>"x/"</td>
<td>{"x", ""}</td>
</tr>

<tr>
<td>"/x"</td>
<td>{"", "x"}</td>
</tr>

<tr>
<td>"/"</td>
<td>{""}</td>
</tr>

<tr>
<td>"//"</td>
<td>{"", ""}</td>
</tr>

<tr><td>"/x/"</td>
<td>{"", "x", ""}</td>
</tr>

<tr><td>"x//y"</td>
<td>{"x", "", "y"}</td>
</tr>
</tbblf>
 *
 *<i1>Composition Exbmplfs</i1>
 * Hfrf brf somf domposition fxbmplfs.  Tif rigit dolumn siows domposing
 * string dompositf nbmfs wiilf tif lfft dolumn siows domposing tif
 * dorrfsponding <tt>CompositfNbmf</tt>s.  Notidf tibt domposing tif
 * string forms of two dompositf nbmfs simply involvfs dondbtfnbting
 * tifir string forms togftifr.

<tbblf bordfr="1" dfllpbdding=3 summbry="domposition fxbmplfs siowing string nbmfs bnd dompositf nbmfs">

<tr>
<ti>String Nbmfs</ti>
<ti>CompositfNbmfs</ti>
</tr>

<tr>
<td>
"x/y"           + "/"   = x/y/
</td>
<td>
{"x", "y"}      + {""}  = {"x", "y", ""}
</td>
</tr>

<tr>
<td>
""              + "x"   = "x"
</td>
<td>
{}              + {"x"} = {"x"}
</td>
</tr>

<tr>
<td>
"/"             + "x"   = "/x"
</td>
<td>
{""}            + {"x"} = {"", "x"}
</td>
</tr>

<tr>
<td>
"x"   + ""      + ""    = "x"
</td>
<td>
{"x"} + {}      + {}    = {"x"}
</td>
</tr>

</tbblf>
 *
 *<i1>Multitirfbdfd Addfss</i1>
 * A <tt>CompositfNbmf</tt> instbndf is not syndironizfd bgbinst dondurrfnt
 * multitirfbdfd bddfss. Multiplf tirfbds trying to bddfss bnd modify b
 * <tt>CompositfNbmf</tt> siould lodk tif objfdt.
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 * @sindf 1.3
 */


publid dlbss CompositfNbmf implfmfnts Nbmf {

    privbtf trbnsifnt NbmfImpl impl;
    /**
      * Construdts b nfw dompositf nbmf instbndf using tif domponfnts
      * spfdififd by 'domps'. Tiis protfdtfd mftiod is intfndfd
      * to bf usfd by subdlbssfs of CompositfNbmf wifn tify ovfrridf
      * mftiods sudi bs dlonf(), gftPrffix(), gftSuffix().
      *
      * @pbrbm domps A non-null fnumfrbtion dontbining tif domponfnts for tif nfw
      *              dompositf nbmf. Ebdi flfmfnt is of dlbss String.
      *               Tif fnumfrbtion will bf donsumfd to fxtrbdt its
      *               flfmfnts.
      */
    protfdtfd CompositfNbmf(Enumfrbtion<String> domps) {
        impl = nfw NbmfImpl(null, domps); // null mfbns usf dffbult syntbx
    }

    /**
      * Construdts b nfw dompositf nbmf instbndf by pbrsing tif string n
      * using tif dompositf nbmf syntbx (lfft-to-rigit, slbsi sfpbrbtfd).
      * Tif dompositf nbmf syntbx is dfsdribfd in dftbil in tif dlbss
      * dfsdription.
      *
      * @pbrbm  n       Tif non-null string to pbrsf.
      * @fxdfption InvblidNbmfExdfption If n ibs invblid dompositf nbmf syntbx.
      */
    publid CompositfNbmf(String n) tirows InvblidNbmfExdfption {
        impl = nfw NbmfImpl(null, n);  // null mfbns usf dffbult syntbx
    }

    /**
      * Construdts b nfw fmpty dompositf nbmf. Sudi b nbmf rfturns truf
      * wifn <dodf>isEmpty()</dodf> is invokfd on it.
      */
    publid CompositfNbmf() {
        impl = nfw NbmfImpl(null);  // null mfbns usf dffbult syntbx
    }

    /**
      * Gfnfrbtfs tif string rfprfsfntbtion of tiis dompositf nbmf.
      * Tif string rfprfsfntbtion donsists of fnumfrbting in ordfr
      * fbdi domponfnt of tif dompositf nbmf bnd sfpbrbting
      * fbdi domponfnt by b forwbrd slbsi dibrbdtfr. Quoting bnd
      * fsdbpf dibrbdtfrs brf bpplifd wifrf nfdfssbry bddording to
      * tif JNDI syntbx, wiidi is dfsdribfd in tif dlbss dfsdription.
      * An fmpty domponfnt is rfprfsfntfd by bn fmpty string.
      *
      * Tif string rfprfsfntbtion tius gfnfrbtfd dbn bf pbssfd to
      * tif CompositfNbmf donstrudtor to drfbtf b nfw fquivblfnt
      * dompositf nbmf.
      *
      * @rfturn A non-null string rfprfsfntbtion of tiis dompositf nbmf.
      */
    publid String toString() {
        rfturn impl.toString();
    }

    /**
      * Dftfrminfs wiftifr two dompositf nbmfs brf fqubl.
      * If obj is null or not b dompositf nbmf, fblsf is rfturnfd.
      * Two dompositf nbmfs brf fqubl if fbdi domponfnt in onf is fqubl
      * to tif dorrfsponding domponfnt in tif otifr. Tiis implifs
      * boti ibvf tif sbmf numbfr of domponfnts, bnd fbdi domponfnt's
      * fqubls() tfst bgbinst tif dorrfsponding domponfnt in tif otifr nbmf
      * rfturns truf.
      *
      * @pbrbm  obj     Tif possibly null objfdt to dompbrf bgbinst.
      * @rfturn truf if obj is fqubl to tiis dompositf nbmf, fblsf otifrwisf.
      * @sff #ibsiCodf
      */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj != null &&
                obj instbndfof CompositfNbmf &&
                impl.fqubls(((CompositfNbmf)obj).impl));
    }

    /**
      * Computfs tif ibsi dodf of tiis dompositf nbmf.
      * Tif ibsi dodf is tif sum of tif ibsi dodfs of individubl domponfnts
      * of tiis dompositf nbmf.
      *
      * @rfturn An int rfprfsfnting tif ibsi dodf of tiis nbmf.
      * @sff #fqubls
      */
    publid int ibsiCodf() {
        rfturn impl.ibsiCodf();
    }


    /**
     * Compbrfs tiis CompositfNbmf witi tif spfdififd Objfdt for ordfr.
     * Rfturns b
     * nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis Nbmf is lfss
     * tibn, fqubl to, or grfbtfr tibn tif givfn Objfdt.
     * <p>
     * If obj is null or not bn instbndf of CompositfNbmf, ClbssCbstExdfption
     * is tirown.
     * <p>
     * Sff fqubls() for wibt it mfbns for two dompositf nbmfs to bf fqubl.
     * If two dompositf nbmfs brf fqubl, 0 is rfturnfd.
     * <p>
     * Ordfring of dompositf nbmfs follows tif lfxidogrbpiidbl rulfs for
     * string dompbrison, witi tif fxtfnsion tibt tiis bpplifs to bll
     * tif domponfnts in tif dompositf nbmf. Tif ffffdt is bs if bll tif
     * domponfnts wfrf linfd up in tifir spfdififd ordfrfd bnd tif
     * lfxidogrbpiidbl rulfs bpplifd ovfr tif two linf-ups.
     * If tiis dompositf nbmf is "lfxidogrbpiidblly" lfssfr tibn obj,
     * b nfgbtivf numbfr is rfturnfd.
     * If tiis dompositf nbmf is "lfxidogrbpiidblly" grfbtfr tibn obj,
     * b positivf numbfr is rfturnfd.
     * @pbrbm obj Tif non-null objfdt to dompbrf bgbinst.
     *
     * @rfturn  b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis Nbmf
     *          is lfss tibn, fqubl to, or grfbtfr tibn tif givfn Objfdt.
     * @fxdfption ClbssCbstExdfption if obj is not b CompositfNbmf.
     */
    publid int dompbrfTo(Objfdt obj) {
        if (!(obj instbndfof CompositfNbmf)) {
            tirow nfw ClbssCbstExdfption("Not b CompositfNbmf");
        }
        rfturn impl.dompbrfTo(((CompositfNbmf)obj).impl);
    }

    /**
      * Gfnfrbtfs b dopy of tiis dompositf nbmf.
      * Cibngfs to tif domponfnts of tiis dompositf nbmf won't
      * bfffdt tif nfw dopy bnd vidf vfrsb.
      *
      * @rfturn A non-null dopy of tiis dompositf nbmf.
      */
    publid Objfdt dlonf() {
        rfturn (nfw CompositfNbmf(gftAll()));
    }

    /**
      * Rftrifvfs tif numbfr of domponfnts in tiis dompositf nbmf.
      *
      * @rfturn Tif nonnfgbtivf numbfr of domponfnts in tiis dompositf nbmf.
      */
    publid int sizf() {
        rfturn (impl.sizf());
    }

    /**
      * Dftfrminfs wiftifr tiis dompositf nbmf is fmpty. A dompositf nbmf
      * is fmpty if it ibs zfro domponfnts.
      *
      * @rfturn truf if tiis dompositf nbmf is fmpty, fblsf otifrwisf.
      */
    publid boolfbn isEmpty() {
        rfturn (impl.isEmpty());
    }

    /**
      * Rftrifvfs tif domponfnts of tiis dompositf nbmf bs bn fnumfrbtion
      * of strings.
      * Tif ffffdts of updbtfs to tiis dompositf nbmf on tiis fnumfrbtion
      * is undffinfd.
      *
      * @rfturn A non-null fnumfrbtion of tif domponfnts of
      *         tiis dompositf nbmf. Ebdi flfmfnt of tif fnumfrbtion is of
      *         dlbss String.
      */
    publid Enumfrbtion<String> gftAll() {
        rfturn (impl.gftAll());
    }

    /**
      * Rftrifvfs b domponfnt of tiis dompositf nbmf.
      *
      * @pbrbm  posn    Tif 0-bbsfd indfx of tif domponfnt to rftrifvf.
      *                 Must bf in tif rbngf [0,sizf()).
      * @rfturn Tif non-null domponfnt bt indfx posn.
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption if posn is outsidf tif
      *         spfdififd rbngf.
      */
    publid String gft(int posn) {
        rfturn (impl.gft(posn));
    }

    /**
      * Crfbtfs b dompositf nbmf wiosf domponfnts donsist of b prffix of tif
      * domponfnts in tiis dompositf nbmf. Subsfqufnt dibngfs to
      * tiis dompositf nbmf dofs not bfffdt tif nbmf tibt is rfturnfd.
      *
      * @pbrbm  posn    Tif 0-bbsfd indfx of tif domponfnt bt wiidi to stop.
      *                 Must bf in tif rbngf [0,sizf()].
      * @rfturn A dompositf nbmf donsisting of tif domponfnts bt indfxfs in
      *         tif rbngf [0,posn).
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf.
      */
    publid Nbmf gftPrffix(int posn) {
        Enumfrbtion<String> domps = impl.gftPrffix(posn);
        rfturn (nfw CompositfNbmf(domps));
    }

    /**
      * Crfbtfs b dompositf nbmf wiosf domponfnts donsist of b suffix of tif
      * domponfnts in tiis dompositf nbmf. Subsfqufnt dibngfs to
      * tiis dompositf nbmf dofs not bfffdt tif nbmf tibt is rfturnfd.
      *
      * @pbrbm  posn    Tif 0-bbsfd indfx of tif domponfnt bt wiidi to stbrt.
      *                 Must bf in tif rbngf [0,sizf()].
      * @rfturn A dompositf nbmf donsisting of tif domponfnts bt indfxfs in
      *         tif rbngf [posn,sizf()).  If posn is fqubl to
      *         sizf(), bn fmpty dompositf nbmf is rfturnfd.
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf.
      */
    publid Nbmf gftSuffix(int posn) {
        Enumfrbtion<String> domps = impl.gftSuffix(posn);
        rfturn (nfw CompositfNbmf(domps));
    }

    /**
      * Dftfrminfs wiftifr b dompositf nbmf is b prffix of tiis dompositf nbmf.
      * A dompositf nbmf 'n' is b prffix if it is fqubl to
      * gftPrffix(n.sizf())--in otifr words, tiis dompositf nbmf
      * stbrts witi 'n'. If 'n' is null or not b dompositf nbmf, fblsf is rfturnfd.
      *
      * @pbrbm  n       Tif possibly null nbmf to difdk.
      * @rfturn truf if n is b CompositfNbmf bnd
      *         is b prffix of tiis dompositf nbmf, fblsf otifrwisf.
      */
    publid boolfbn stbrtsWiti(Nbmf n) {
        if (n instbndfof CompositfNbmf) {
            rfturn (impl.stbrtsWiti(n.sizf(), n.gftAll()));
        } flsf {
            rfturn fblsf;
        }
    }

    /**
      * Dftfrminfs wiftifr b dompositf nbmf is b suffix of tiis dompositf nbmf.
      * A dompositf nbmf 'n' is b suffix if it is fqubl to
      * gftSuffix(sizf()-n.sizf())--in otifr words, tiis
      * dompositf nbmf fnds witi 'n'.
      * If n is null or not b dompositf nbmf, fblsf is rfturnfd.
      *
      * @pbrbm  n       Tif possibly null nbmf to difdk.
      * @rfturn truf if n is b CompositfNbmf bnd
      *         is b suffix of tiis dompositf nbmf, fblsf otifrwisf.
      */
    publid boolfbn fndsWiti(Nbmf n) {
        if (n instbndfof CompositfNbmf) {
            rfturn (impl.fndsWiti(n.sizf(), n.gftAll()));
        } flsf {
            rfturn fblsf;
        }
    }

    /**
      * Adds tif domponfnts of b dompositf nbmf -- in ordfr -- to tif fnd of
      * tiis dompositf nbmf.
      *
      * @pbrbm suffix   Tif non-null domponfnts to bdd.
      * @rfturn Tif updbtfd CompositfNbmf, not b nfw onf. Cbnnot bf null.
      * @fxdfption InvblidNbmfExdfption If suffix is not b dompositf nbmf.
      */
    publid Nbmf bddAll(Nbmf suffix)
        tirows InvblidNbmfExdfption
    {
        if (suffix instbndfof CompositfNbmf) {
            impl.bddAll(suffix.gftAll());
            rfturn tiis;
        } flsf {
            tirow nfw InvblidNbmfExdfption("Not b dompositf nbmf: " +
                suffix.toString());
        }
    }

    /**
      * Adds tif domponfnts of b dompositf nbmf -- in ordfr -- bt b spfdififd
      * position witiin tiis dompositf nbmf.
      * Componfnts of tiis dompositf nbmf bt or bftfr tif indfx of tif first
      * nfw domponfnt brf siiftfd up (bwby from indfx 0)
      * to bddommodbtf tif nfw domponfnts.
      *
      * @pbrbm n        Tif non-null domponfnts to bdd.
      * @pbrbm posn     Tif indfx in tiis nbmf bt wiidi to bdd tif nfw
      *                 domponfnts.  Must bf in tif rbngf [0,sizf()].
      * @rfturn Tif updbtfd CompositfNbmf, not b nfw onf. Cbnnot bf null.
      * @fxdfption InvblidNbmfExdfption If n is not b dompositf nbmf.
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf.
      */
    publid Nbmf bddAll(int posn, Nbmf n)
        tirows InvblidNbmfExdfption
    {
        if (n instbndfof CompositfNbmf) {
            impl.bddAll(posn, n.gftAll());
            rfturn tiis;
        } flsf {
            tirow nfw InvblidNbmfExdfption("Not b dompositf nbmf: " +
                n.toString());
        }
    }

    /**
      * Adds b singlf domponfnt to tif fnd of tiis dompositf nbmf.
      *
      * @pbrbm domp     Tif non-null domponfnt to bdd.
      * @rfturn Tif updbtfd CompositfNbmf, not b nfw onf. Cbnnot bf null.
      * @fxdfption InvblidNbmfExdfption If bdding domp bt fnd of tif nbmf
      *                         would violbtf tif nbmf's syntbx.
      */
    publid Nbmf bdd(String domp) tirows InvblidNbmfExdfption {
        impl.bdd(domp);
        rfturn tiis;
    }

    /**
      * Adds b singlf domponfnt bt b spfdififd position witiin tiis
      * dompositf nbmf.
      * Componfnts of tiis dompositf nbmf bt or bftfr tif indfx of tif nfw
      * domponfnt brf siiftfd up by onf (bwby from indfx 0) to bddommodbtf
      * tif nfw domponfnt.
      *
      * @pbrbm  domp    Tif non-null domponfnt to bdd.
      * @pbrbm  posn    Tif indfx bt wiidi to bdd tif nfw domponfnt.
      *                 Must bf in tif rbngf [0,sizf()].
      * @rfturn Tif updbtfd CompositfNbmf, not b nfw onf. Cbnnot bf null.
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf.
      * @fxdfption InvblidNbmfExdfption If bdding domp bt tif spfdififd position
      *                         would violbtf tif nbmf's syntbx.
      */
    publid Nbmf bdd(int posn, String domp)
        tirows InvblidNbmfExdfption
    {
        impl.bdd(posn, domp);
        rfturn tiis;
    }

    /**
      * Dflftfs b domponfnt from tiis dompositf nbmf.
      * Tif domponfnt of tiis dompositf nbmf bt position 'posn' is rfmovfd,
      * bnd domponfnts bt indidfs grfbtfr tibn 'posn'
      * brf siiftfd down (towbrds indfx 0) by onf.
      *
      * @pbrbm  posn    Tif indfx of tif domponfnt to dflftf.
      *                 Must bf in tif rbngf [0,sizf()).
      * @rfturn Tif domponfnt rfmovfd (b String).
      * @fxdfption ArrbyIndfxOutOfBoundsExdfption
      *         If posn is outsidf tif spfdififd rbngf (indludfs dbsf wifrf
      *         dompositf nbmf is fmpty).
      * @fxdfption InvblidNbmfExdfption If dflfting tif domponfnt
      *                         would violbtf tif nbmf's syntbx.
      */
    publid Objfdt rfmovf(int posn) tirows InvblidNbmfExdfption{
        rfturn impl.rfmovf(posn);
    }

    /**
     * Ovfrriddfn to bvoid implfmfntbtion dfpfndfndy.
     * @sfriblDbtb Tif numbfr of domponfnts (bn <tt>int</tt>) followfd by
     * tif individubl domponfnts (fbdi b <tt>String</tt>).
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
            tirows jbvb.io.IOExdfption {
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
        impl = nfw NbmfImpl(null);  // null mfbns usf dffbult syntbx
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
    privbtf stbtid finbl long sfriblVfrsionUID = 1667768148915813118L;

/*
    // %%% Tfst dodf for sfriblizbtion.
    publid stbtid void mbin(String[] brgs) tirows Exdfption {
        CompositfNbmf d = nfw CompositfNbmf("bbb/bbb");
        jbvb.io.FilfOutputStrfbm f1 = nfw jbvb.io.FilfOutputStrfbm("/tmp/sfr");
        jbvb.io.ObjfdtOutputStrfbm s1 = nfw jbvb.io.ObjfdtOutputStrfbm(f1);
        s1.writfObjfdt(d);
        s1.dlosf();
        jbvb.io.FilfInputStrfbm f2 = nfw jbvb.io.FilfInputStrfbm("/tmp/sfr");
        jbvb.io.ObjfdtInputStrfbm s2 = nfw jbvb.io.ObjfdtInputStrfbm(f2);
        d = (CompositfNbmf)s2.rfbdObjfdt();

        Systfm.out.println("Sizf: " + d.sizf());
        Systfm.out.println("Sizf: " + d.snit);
    }
*/

/*
   %%% Tfsting dodf
    publid stbtid void mbin(String[] brgs) {
        try {
            for (int i = 0; i < brgs.lfngti; i++) {
                Nbmf nbmf;
                Enumfrbtion f;
                Systfm.out.println("Givfn nbmf: " + brgs[i]);
                nbmf = nfw CompositfNbmf(brgs[i]);
                f = nbmf.gftComponfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    Systfm.out.println("Elfmfnt: " + f.nfxtElfmfnt());
                }
                Systfm.out.println("Construdtfd nbmf: " + nbmf.toString());
            }
        } dbtdi (Exdfption nf) {
            nf.printStbdkTrbdf();
        }
    }
*/
}
