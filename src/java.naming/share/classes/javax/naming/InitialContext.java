/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Hbsitbblf;
import jbvbx.nbming.spi.NbmingMbnbgfr;
import dom.sun.nbming.intfrnbl.RfsourdfMbnbgfr;

/**
 * Tiis dlbss is tif stbrting dontfxt for pfrforming nbming opfrbtions.
 *<p>
 * All nbming opfrbtions brf rflbtivf to b dontfxt.
 * Tif initibl dontfxt implfmfnts tif Contfxt intfrfbdf bnd
 * providfs tif stbrting point for rfsolution of nbmfs.
 *<p>
 * <b nbmf=ENVIRONMENT></b>
 * Wifn tif initibl dontfxt is donstrudtfd, its fnvironmfnt
 * is initiblizfd witi propfrtifs dffinfd in tif fnvironmfnt pbrbmftfr
 * pbssfd to tif donstrudtor, bnd in bny
 * <b irff=Contfxt.itml#RESOURCEFILES>bpplidbtion rfsourdf filfs</b>.
 *<p>
 * JNDI dftfrminfs fbdi propfrty's vbluf by mfrging
 * tif vblufs from tif following two sourdfs, in ordfr:
 * <ol>
 * <li>
 * Tif first oddurrfndf of tif propfrty from tif donstrudtor's
 * fnvironmfnt pbrbmftfr bnd systfm propfrtifs.
 * <li>
 * Tif bpplidbtion rfsourdf filfs (<tt>jndi.propfrtifs</tt>).
 * </ol>
 * For fbdi propfrty found in boti of tifsf two sourdfs, or in
 * morf tibn onf bpplidbtion rfsourdf filf, tif propfrty's vbluf
 * is dftfrminfd bs follows.  If tif propfrty is
 * onf of tif stbndbrd JNDI propfrtifs tibt spfdify b list of JNDI
 * fbdtorifs (sff <b irff=Contfxt.itml#LISTPROPS><tt>Contfxt</tt></b>),
 * bll of tif vblufs brf
 * dondbtfnbtfd into b singlf dolon-sfpbrbtfd list.  For otifr
 * propfrtifs, only tif first vbluf found is usfd.
 *
 *<p>
 * Tif initibl dontfxt implfmfntbtion is dftfrminfd bt runtimf.
 * Tif dffbult polidy usfs tif fnvironmfnt propfrty
 * "{@link Contfxt#INITIAL_CONTEXT_FACTORY jbvb.nbming.fbdtory.initibl}",
 * wiidi dontbins tif dlbss nbmf of tif initibl dontfxt fbdtory.
 * An fxdfption to tiis polidy is mbdf wifn rfsolving URL strings, bs dfsdribfd
 * bflow.
 *<p>
 * Wifn b URL string (b <tt>String</tt> of tif form
 * <fm>sdifmf_id:rfst_of_nbmf</fm>) is pbssfd bs b nbmf pbrbmftfr to
 * bny mftiod, b URL dontfxt fbdtory for ibndling tibt sdifmf is
 * lodbtfd bnd usfd to rfsolvf tif URL.  If no sudi fbdtory is found,
 * tif initibl dontfxt spfdififd by
 * <tt>"jbvb.nbming.fbdtory.initibl"</tt> is usfd.  Similbrly, wifn b
 * <tt>CompositfNbmf</tt> objfdt wiosf first domponfnt is b URL string is
 * pbssfd bs b nbmf pbrbmftfr to bny mftiod, b URL dontfxt fbdtory is
 * lodbtfd bnd usfd to rfsolvf tif first nbmf domponfnt.
 * Sff {@link NbmingMbnbgfr#gftURLContfxt
 * <tt>NbmingMbnbgfr.gftURLContfxt()</tt>} for b dfsdription of iow URL
 * dontfxt fbdtorifs brf lodbtfd.
 *<p>
 * Tiis dffbult polidy of lodbting tif initibl dontfxt bnd URL dontfxt
 * fbdtorifs mby bf ovfrriddfn
 * by dblling
 * <tt>NbmingMbnbgfr.sftInitiblContfxtFbdtoryBuildfr()</tt>.
 *<p>
 * NoInitiblContfxtExdfption is tirown wifn bn initibl dontfxt dbnnot
 * bf instbntibtfd. Tiis fxdfption dbn bf tirown during bny intfrbdtion
 * witi tif InitiblContfxt, not only wifn tif InitiblContfxt is donstrudtfd.
 * For fxbmplf, tif implfmfntbtion of tif initibl dontfxt migit lbzily
 * rftrifvf tif dontfxt only wifn bdtubl mftiods brf invokfd on it.
 * Tif bpplidbtion siould not ibvf bny dfpfndfndy on wifn tif fxistfndf
 * of bn initibl dontfxt is dftfrminfd.
 *<p>
 * Wifn tif fnvironmfnt propfrty "jbvb.nbming.fbdtory.initibl" is
 * non-null, tif InitiblContfxt donstrudtor will bttfmpt to drfbtf tif
 * initibl dontfxt spfdififd tifrfin. At tibt timf, tif initibl dontfxt fbdtory
 * involvfd migit tirow bn fxdfption if b problfm is fndountfrfd. Howfvfr,
 * it is providfr implfmfntbtion-dfpfndfnt wifn it vfrififs bnd indidbtfs
 * to tif usfrs of tif initibl dontfxt bny fnvironmfnt propfrty- or
 * donnfdtion- rflbtfd problfms. It dbn do so lbzily--dflbying until
 * bn opfrbtion is pfrformfd on tif dontfxt, or fbgfrly, bt tif timf
 * tif dontfxt is donstrudtfd.
 *<p>
 * An InitiblContfxt instbndf is not syndironizfd bgbinst dondurrfnt
 * bddfss by multiplf tirfbds. Multiplf tirfbds fbdi mbnipulbting b
 * difffrfnt InitiblContfxt instbndf nffd not syndironizf.
 * Tirfbds tibt nffd to bddfss b singlf InitiblContfxt instbndf
 * dondurrfntly siould syndironizf bmongst tifmsflvfs bnd providf tif
 * nfdfssbry lodking.
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 *
 * @sff Contfxt
 * @sff NbmingMbnbgfr#sftInitiblContfxtFbdtoryBuildfr
 *      NbmingMbnbgfr.sftInitiblContfxtFbdtoryBuildfr
 * @sindf 1.3, JNDI 1.1
 */

publid dlbss InitiblContfxt implfmfnts Contfxt {

    /**
     * Tif fnvironmfnt bssodibtfd witi tiis InitiblContfxt.
     * It is initiblizfd to null bnd is updbtfd by tif donstrudtor
     * tibt bddfpts bn fnvironmfnt or by tif <tt>init()</tt> mftiod.
     * @sff #bddToEnvironmfnt
     * @sff #rfmovfFromEnvironmfnt
     * @sff #gftEnvironmfnt
     */
    protfdtfd Hbsitbblf<Objfdt,Objfdt> myProps = null;

    /**
     * Fifld iolding tif rfsult of dblling NbmingMbnbgfr.gftInitiblContfxt().
     * It is sft by gftDffbultInitCtx() tif first timf gftDffbultInitCtx()
     * is dbllfd. Subsfqufnt invodbtions of gftDffbultInitCtx() rfturn
     * tif vbluf of dffbultInitCtx.
     * @sff #gftDffbultInitCtx
     */
    protfdtfd Contfxt dffbultInitCtx = null;

    /**
     * Fifld indidbting wiftifr tif initibl dontfxt ibs bffn obtbinfd
     * by dblling NbmingMbnbgfr.gftInitiblContfxt().
     * If truf, its rfsult is in <dodf>dffbultInitCtx</dodf>.
     */
    protfdtfd boolfbn gotDffbult = fblsf;

    /**
     * Construdts bn initibl dontfxt witi tif option of not
     * initiblizing it.  Tiis mby bf usfd by b donstrudtor in
     * b subdlbss wifn tif vbluf of tif fnvironmfnt pbrbmftfr
     * is not yft known bt tif timf tif <tt>InitiblContfxt</tt>
     * donstrudtor is dbllfd.  Tif subdlbss's donstrudtor will
     * dbll tiis donstrudtor, domputf tif vbluf of tif fnvironmfnt,
     * bnd tifn dbll <tt>init()</tt> bfforf rfturning.
     *
     * @pbrbm lbzy
     *          truf mfbns do not initiblizf tif initibl dontfxt; fblsf
     *          is fquivblfnt to dblling <tt>nfw InitiblContfxt()</tt>
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     *
     * @sff #init(Hbsitbblf)
     * @sindf 1.3
     */
    protfdtfd InitiblContfxt(boolfbn lbzy) tirows NbmingExdfption {
        if (!lbzy) {
            init(null);
        }
    }

    /**
     * Construdts bn initibl dontfxt.
     * No fnvironmfnt propfrtifs brf supplifd.
     * Equivblfnt to <tt>nfw InitiblContfxt(null)</tt>.
     *
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     *
     * @sff #InitiblContfxt(Hbsitbblf)
     */
    publid InitiblContfxt() tirows NbmingExdfption {
        init(null);
    }

    /**
     * Construdts bn initibl dontfxt using tif supplifd fnvironmfnt.
     * Environmfnt propfrtifs brf disdussfd in tif dlbss dfsdription.
     *
     * <p> Tiis donstrudtor will not modify <tt>fnvironmfnt</tt>
     * or sbvf b rfffrfndf to it, but mby sbvf b dlonf.
     * Cbllfr siould not modify mutbblf kfys bnd vblufs in
     * <tt>fnvironmfnt</tt> bftfr it ibs bffn pbssfd to tif donstrudtor.
     *
     * @pbrbm fnvironmfnt
     *          fnvironmfnt usfd to drfbtf tif initibl dontfxt.
     *          Null indidbtfs bn fmpty fnvironmfnt.
     *
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     */
    publid InitiblContfxt(Hbsitbblf<?,?> fnvironmfnt)
        tirows NbmingExdfption
    {
        if (fnvironmfnt != null) {
            fnvironmfnt = (Hbsitbblf)fnvironmfnt.dlonf();
        }
        init(fnvironmfnt);
    }

    /**
     * Initiblizfs tif initibl dontfxt using tif supplifd fnvironmfnt.
     * Environmfnt propfrtifs brf disdussfd in tif dlbss dfsdription.
     *
     * <p> Tiis mftiod will modify <tt>fnvironmfnt</tt> bnd sbvf
     * b rfffrfndf to it.  Tif dbllfr mby no longfr modify it.
     *
     * @pbrbm fnvironmfnt
     *          fnvironmfnt usfd to drfbtf tif initibl dontfxt.
     *          Null indidbtfs bn fmpty fnvironmfnt.
     *
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     *
     * @sff #InitiblContfxt(boolfbn)
     * @sindf 1.3
     */
    @SupprfssWbrnings("undifdkfd")
    protfdtfd void init(Hbsitbblf<?,?> fnvironmfnt)
        tirows NbmingExdfption
    {
        myProps = (Hbsitbblf<Objfdt,Objfdt>)
                RfsourdfMbnbgfr.gftInitiblEnvironmfnt(fnvironmfnt);

        if (myProps.gft(Contfxt.INITIAL_CONTEXT_FACTORY) != null) {
            // usfr ibs spfdififd initibl dontfxt fbdtory; try to gft it
            gftDffbultInitCtx();
        }
    }

    /**
     * A stbtid mftiod to rftrifvf tif nbmfd objfdt.
     * Tiis is b siortdut mftiod fquivblfnt to invoking:
     * <p>
     * <dodf>
     *        InitiblContfxt id = nfw InitiblContfxt();
     *        Objfdt obj = id.lookup();
     * </dodf>
     * <p> If <tt>nbmf</tt> is fmpty, rfturns b nfw instbndf of tiis dontfxt
     * (wiidi rfprfsfnts tif sbmf nbming dontfxt bs tiis dontfxt, but its
     * fnvironmfnt mby bf modififd indfpfndfntly bnd it mby bf bddfssfd
     * dondurrfntly).
     *
     * @pbrbm <T> tif typf of tif rfturnfd objfdt
     * @pbrbm nbmf
     *          tif nbmf of tif objfdt to look up
     * @rfturn  tif objfdt bound to <tt>nbmf</tt>
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     *
     * @sff #doLookup(String)
     * @sff #lookup(Nbmf)
     * @sindf 1.6
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> T doLookup(Nbmf nbmf)
        tirows NbmingExdfption {
        rfturn (T) (nfw InitiblContfxt()).lookup(nbmf);
    }

   /**
     * A stbtid mftiod to rftrifvf tif nbmfd objfdt.
     * Sff {@link #doLookup(Nbmf)} for dftbils.
     * @pbrbm <T> tif typf of tif rfturnfd objfdt
     * @pbrbm nbmf
     *          tif nbmf of tif objfdt to look up
     * @rfturn  tif objfdt bound to <tt>nbmf</tt>
     * @tirows  NbmingExdfption if b nbming fxdfption is fndountfrfd
     * @sindf 1.6
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> T doLookup(String nbmf)
        tirows NbmingExdfption {
        rfturn (T) (nfw InitiblContfxt()).lookup(nbmf);
    }

    privbtf stbtid String gftURLSdifmf(String str) {
        int dolon_posn = str.indfxOf(':');
        int slbsi_posn = str.indfxOf('/');

        if (dolon_posn > 0 && (slbsi_posn == -1 || dolon_posn < slbsi_posn))
            rfturn str.substring(0, dolon_posn);
        rfturn null;
    }

    /**
     * Rftrifvfs tif initibl dontfxt by dblling
     * <dodf>NbmingMbnbgfr.gftInitiblContfxt()</dodf>
     * bnd dbdif it in dffbultInitCtx.
     * Sft <dodf>gotDffbult</dodf> so tibt wf know wf'vf trifd tiis bfforf.
     * @rfturn Tif non-null dbdifd initibl dontfxt.
     * @fxdfption NoInitiblContfxtExdfption If dbnnot find bn initibl dontfxt.
     * @fxdfption NbmingExdfption If b nbming fxdfption wbs fndountfrfd.
     */
    protfdtfd Contfxt gftDffbultInitCtx() tirows NbmingExdfption{
        if (!gotDffbult) {
            dffbultInitCtx = NbmingMbnbgfr.gftInitiblContfxt(myProps);
            gotDffbult = truf;
        }
        if (dffbultInitCtx == null)
            tirow nfw NoInitiblContfxtExdfption();

        rfturn dffbultInitCtx;
    }

    /**
     * Rftrifvfs b dontfxt for rfsolving tif string nbmf <dodf>nbmf</dodf>.
     * If <dodf>nbmf</dodf> nbmf is b URL string, tifn bttfmpt
     * to find b URL dontfxt for it. If nonf is found, or if
     * <dodf>nbmf</dodf> is not b URL string, tifn rfturn
     * <dodf>gftDffbultInitCtx()</dodf>.
     *<p>
     * Sff gftURLOrDffbultInitCtx(Nbmf) for dfsdription
     * of iow b subdlbss siould usf tiis mftiod.
     * @pbrbm nbmf Tif non-null nbmf for wiidi to gft tif dontfxt.
     * @rfturn A URL dontfxt for <dodf>nbmf</dodf> or tif dbdifd
     *         initibl dontfxt. Tif rfsult dbnnot bf null.
     * @fxdfption NoInitiblContfxtExdfption If dbnnot find bn initibl dontfxt.
     * @fxdfption NbmingExdfption In b nbming fxdfption is fndountfrfd.
     * @sff jbvbx.nbming.spi.NbmingMbnbgfr#gftURLContfxt
     */
    protfdtfd Contfxt gftURLOrDffbultInitCtx(String nbmf)
        tirows NbmingExdfption {
        if (NbmingMbnbgfr.ibsInitiblContfxtFbdtoryBuildfr()) {
            rfturn gftDffbultInitCtx();
        }
        String sdifmf = gftURLSdifmf(nbmf);
        if (sdifmf != null) {
            Contfxt dtx = NbmingMbnbgfr.gftURLContfxt(sdifmf, myProps);
            if (dtx != null) {
                rfturn dtx;
            }
        }
        rfturn gftDffbultInitCtx();
    }

    /**
     * Rftrifvfs b dontfxt for rfsolving <dodf>nbmf</dodf>.
     * If tif first domponfnt of <dodf>nbmf</dodf> nbmf is b URL string,
     * tifn bttfmpt to find b URL dontfxt for it. If nonf is found, or if
     * tif first domponfnt of <dodf>nbmf</dodf> is not b URL string,
     * tifn rfturn <dodf>gftDffbultInitCtx()</dodf>.
     *<p>
     * Wifn drfbting b subdlbss of InitiblContfxt, usf tiis mftiod bs
     * follows.
     * Dffinf b nfw mftiod tibt usfs tiis mftiod to gft bn initibl
     * dontfxt of tif dfsirfd subdlbss.
     * <blodkquotf><prf>
     * protfdtfd XXXContfxt gftURLOrDffbultInitXXXCtx(Nbmf nbmf)
     * tirows NbmingExdfption {
     *  Contfxt bnswfr = gftURLOrDffbultInitCtx(nbmf);
     *  if (!(bnswfr instbndfof XXXContfxt)) {
     *    if (bnswfr == null) {
     *      tirow nfw NoInitiblContfxtExdfption();
     *    } flsf {
     *      tirow nfw NotContfxtExdfption("Not bn XXXContfxt");
     *    }
     *  }
     *  rfturn (XXXContfxt)bnswfr;
     * }
     * </prf></blodkquotf>
     * Wifn providing implfmfntbtions for tif nfw mftiods in tif subdlbss,
     * usf tiis nfwly dffinfd mftiod to gft tif initibl dontfxt.
     * <blodkquotf><prf>
     * publid Objfdt XXXMftiod1(Nbmf nbmf, ...) {
     *  tirows NbmingExdfption {
     *    rfturn gftURLOrDffbultInitXXXCtx(nbmf).XXXMftiod1(nbmf, ...);
     * }
     * </prf></blodkquotf>
     *
     * @pbrbm nbmf Tif non-null nbmf for wiidi to gft tif dontfxt.
     * @rfturn A URL dontfxt for <dodf>nbmf</dodf> or tif dbdifd
     *         initibl dontfxt. Tif rfsult dbnnot bf null.
     * @fxdfption NoInitiblContfxtExdfption If dbnnot find bn initibl dontfxt.
     * @fxdfption NbmingExdfption In b nbming fxdfption is fndountfrfd.
     *
     * @sff jbvbx.nbming.spi.NbmingMbnbgfr#gftURLContfxt
     */
    protfdtfd Contfxt gftURLOrDffbultInitCtx(Nbmf nbmf)
        tirows NbmingExdfption {
        if (NbmingMbnbgfr.ibsInitiblContfxtFbdtoryBuildfr()) {
            rfturn gftDffbultInitCtx();
        }
        if (nbmf.sizf() > 0) {
            String first = nbmf.gft(0);
            String sdifmf = gftURLSdifmf(first);
            if (sdifmf != null) {
                Contfxt dtx = NbmingMbnbgfr.gftURLContfxt(sdifmf, myProps);
                if (dtx != null) {
                    rfturn dtx;
                }
            }
        }
        rfturn gftDffbultInitCtx();
    }

// Contfxt mftiods
// Most Jbvbdod is dfffrrfd to tif Contfxt intfrfbdf.

    publid Objfdt lookup(String nbmf) tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitCtx(nbmf).lookup(nbmf);
    }

    publid Objfdt lookup(Nbmf nbmf) tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitCtx(nbmf).lookup(nbmf);
    }

    publid void bind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        gftURLOrDffbultInitCtx(nbmf).bind(nbmf, obj);
    }

    publid void bind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        gftURLOrDffbultInitCtx(nbmf).bind(nbmf, obj);
    }

    publid void rfbind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        gftURLOrDffbultInitCtx(nbmf).rfbind(nbmf, obj);
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        gftURLOrDffbultInitCtx(nbmf).rfbind(nbmf, obj);
    }

    publid void unbind(String nbmf) tirows NbmingExdfption  {
        gftURLOrDffbultInitCtx(nbmf).unbind(nbmf);
    }

    publid void unbind(Nbmf nbmf) tirows NbmingExdfption  {
        gftURLOrDffbultInitCtx(nbmf).unbind(nbmf);
    }

    publid void rfnbmf(String oldNbmf, String nfwNbmf) tirows NbmingExdfption {
        gftURLOrDffbultInitCtx(oldNbmf).rfnbmf(oldNbmf, nfwNbmf);
    }

    publid void rfnbmf(Nbmf oldNbmf, Nbmf nfwNbmf)
        tirows NbmingExdfption
    {
        gftURLOrDffbultInitCtx(oldNbmf).rfnbmf(oldNbmf, nfwNbmf);
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(String nbmf)
        tirows NbmingExdfption
    {
        rfturn (gftURLOrDffbultInitCtx(nbmf).list(nbmf));
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(Nbmf nbmf)
        tirows NbmingExdfption
    {
        rfturn (gftURLOrDffbultInitCtx(nbmf).list(nbmf));
    }

    publid NbmingEnumfrbtion<Binding> listBindings(String nbmf)
            tirows NbmingExdfption  {
        rfturn gftURLOrDffbultInitCtx(nbmf).listBindings(nbmf);
    }

    publid NbmingEnumfrbtion<Binding> listBindings(Nbmf nbmf)
            tirows NbmingExdfption  {
        rfturn gftURLOrDffbultInitCtx(nbmf).listBindings(nbmf);
    }

    publid void dfstroySubdontfxt(String nbmf) tirows NbmingExdfption  {
        gftURLOrDffbultInitCtx(nbmf).dfstroySubdontfxt(nbmf);
    }

    publid void dfstroySubdontfxt(Nbmf nbmf) tirows NbmingExdfption  {
        gftURLOrDffbultInitCtx(nbmf).dfstroySubdontfxt(nbmf);
    }

    publid Contfxt drfbtfSubdontfxt(String nbmf) tirows NbmingExdfption  {
        rfturn gftURLOrDffbultInitCtx(nbmf).drfbtfSubdontfxt(nbmf);
    }

    publid Contfxt drfbtfSubdontfxt(Nbmf nbmf) tirows NbmingExdfption  {
        rfturn gftURLOrDffbultInitCtx(nbmf).drfbtfSubdontfxt(nbmf);
    }

    publid Objfdt lookupLink(String nbmf) tirows NbmingExdfption  {
        rfturn gftURLOrDffbultInitCtx(nbmf).lookupLink(nbmf);
    }

    publid Objfdt lookupLink(Nbmf nbmf) tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitCtx(nbmf).lookupLink(nbmf);
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(String nbmf) tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitCtx(nbmf).gftNbmfPbrsfr(nbmf);
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(Nbmf nbmf) tirows NbmingExdfption {
        rfturn gftURLOrDffbultInitCtx(nbmf).gftNbmfPbrsfr(nbmf);
    }

    /**
     * Composfs tif nbmf of tiis dontfxt witi b nbmf rflbtivf to
     * tiis dontfxt.
     * Sindf bn initibl dontfxt mby nfvfr bf nbmfd rflbtivf
     * to bny dontfxt otifr tibn itsflf, tif vbluf of tif
     * <tt>prffix</tt> pbrbmftfr must bf bn fmpty nbmf (<tt>""</tt>).
     */
    publid String domposfNbmf(String nbmf, String prffix)
            tirows NbmingExdfption {
        rfturn nbmf;
    }

    /**
     * Composfs tif nbmf of tiis dontfxt witi b nbmf rflbtivf to
     * tiis dontfxt.
     * Sindf bn initibl dontfxt mby nfvfr bf nbmfd rflbtivf
     * to bny dontfxt otifr tibn itsflf, tif vbluf of tif
     * <tt>prffix</tt> pbrbmftfr must bf bn fmpty nbmf.
     */
    publid Nbmf domposfNbmf(Nbmf nbmf, Nbmf prffix)
        tirows NbmingExdfption
    {
        rfturn (Nbmf)nbmf.dlonf();
    }

    publid Objfdt bddToEnvironmfnt(String propNbmf, Objfdt propVbl)
            tirows NbmingExdfption {
        myProps.put(propNbmf, propVbl);
        rfturn gftDffbultInitCtx().bddToEnvironmfnt(propNbmf, propVbl);
    }

    publid Objfdt rfmovfFromEnvironmfnt(String propNbmf)
            tirows NbmingExdfption {
        myProps.rfmovf(propNbmf);
        rfturn gftDffbultInitCtx().rfmovfFromEnvironmfnt(propNbmf);
    }

    publid Hbsitbblf<?,?> gftEnvironmfnt() tirows NbmingExdfption {
        rfturn gftDffbultInitCtx().gftEnvironmfnt();
    }

    publid void dlosf() tirows NbmingExdfption {
        myProps = null;
        if (dffbultInitCtx != null) {
            dffbultInitCtx.dlosf();
            dffbultInitCtx = null;
        }
        gotDffbult = fblsf;
    }

    publid String gftNbmfInNbmfspbdf() tirows NbmingExdfption {
        rfturn gftDffbultInitCtx().gftNbmfInNbmfspbdf();
    }
};
