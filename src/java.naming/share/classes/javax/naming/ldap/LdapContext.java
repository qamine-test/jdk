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

pbdkbgf jbvbx.nbming.ldbp;

import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.dirfdtory.DirContfxt;
import jbvb.util.Hbsitbblf;

/**
 * Tiis intfrfbdf rfprfsfnts b dontfxt in wiidi you dbn pfrform
 * opfrbtions witi LDAPv3-stylf dontrols bnd pfrform LDAPv3-stylf
 * fxtfndfd opfrbtions.
 *
 * For bpplidbtions tibt do not rfquirf sudi dontrols or fxtfndfd
 * opfrbtions, tif morf gfnfrid <tt>jbvbx.nbming.dirfdtory.DirContfxt</tt>
 * siould bf usfd instfbd.
 *
 * <i3>Usbgf Dftbils About Controls</i3>
 *
 * Tiis intfrfbdf providfs support for LDAP v3 dontrols.
 * At b iigi lfvfl, tiis support bllows b usfr
 * progrbm to sft rfqufst dontrols for LDAP opfrbtions tibt brf fxfdutfd
 * in tif doursf of tif usfr progrbm's invodbtion of
 * <tt>Contfxt</tt>/<tt>DirContfxt</tt>
 * mftiods, bnd rfbd rfsponsf dontrols rfsulting from LDAP opfrbtions.
 * At tif implfmfntbtion lfvfl, tifrf brf somf dftbils tibt dfvflopfrs of
 * boti tif usfr progrbm bnd sfrvidf providfrs nffd to undfrstbnd in ordfr
 * to dorrfdtly usf rfqufst bnd rfsponsf dontrols.
 *
 * <i3>Rfqufst Controls</i3>
 * <p>
 * Tifrf brf two typfs of rfqufst dontrols:
 * <ul>
 * <li>Rfqufst dontrols tibt bfffdt iow b donnfdtion is drfbtfd
 * <li>Rfqufst dontrols tibt bfffdt dontfxt mftiods
 * </ul>
 *
 * Tif formfr is usfd wifnfvfr b donnfdtion nffds to bf fstbblisifd or
 * rf-fstbblisifd witi bn LDAP sfrvfr. Tif lbttfr is usfd wifn bll otifr
 * LDAP opfrbtions brf sfnt to tif LDAP sfrvfr.  Tif rfbson wiy b
 * distindtion bftwffn tifsf two typfs of rfqufst dontrols is nfdfssbry
 * is bfdbusf JNDI is b iigi-lfvfl API tibt dofs not dfbl dirfdtly witi
 * donnfdtions.  It is tif job of sfrvidf providfrs to do bny nfdfssbry
 * donnfdtion mbnbgfmfnt. Consfqufntly, b singlf
 * donnfdtion mby bf sibrfd by multiplf dontfxt instbndfs, bnd b sfrvidf providfr
 * is frff to usf its own blgoritims to donsfrvf donnfdtion bnd nftwork
 * usbgf. Tius, wifn b mftiod is invokfd on tif dontfxt instbndf, tif sfrvidf
 * providfr migit nffd to do somf donnfdtion mbnbgfmfnt in bddition to
 * pfrforming tif dorrfsponding LDAP opfrbtions. For donnfdtion mbnbgfmfnt,
 * it usfs tif <fm>donnfdtion rfqufst dontrols</fm>, wiilf for tif normbl
 * LDAP opfrbtions, it usfs tif <fm>dontfxt rfqufst dontrols</fm>.
 *<p>Unlfss fxpliditly qublififd, tif tfrm "rfqufst dontrols" rfffrs to
 * dontfxt rfqufst dontrols.
 *
 * <i4>Contfxt Rfqufst Controls</i4>
 * Tifrf brf two wbys in wiidi b dontfxt instbndf gfts its rfqufst dontrols:
 * <ol>
 * <li><tt>ldbpContfxt.nfwInstbndf(<strong>rfqCtls</strong>)</tt>
 * <li><tt>ldbpContfxt.sftRfqufstControls(<strong>rfqCtls</strong>)</tt>
 * </ol>
 * wifrf <tt>ldbpContfxt</tt> is bn instbndf of <tt>LdbpContfxt</tt>.
 * Spfdifying <tt>null</tt> or bn fmpty brrby for <tt>rfqCtls</tt>
 * mfbns no rfqufst dontrols.
 * <tt>nfwInstbndf()</tt> drfbtfs b nfw instbndf of b dontfxt using
 * <tt>rfqCtls</tt>, wiilf <tt>sftRfqufstControls()</tt>
 * updbtfs bn fxisting dontfxt instbndf's rfqufst dontrols to <tt>rfqCtls</tt>.
 * <p>
 * Unlikf fnvironmfnt propfrtifs, rfqufst dontrols of b dontfxt instbndf
 * <fm>brf not inifritfd</fm> by dontfxt instbndfs tibt brf dfrivfd from
 * it.  Dfrivfd dontfxt instbndfs ibvf <tt>null</tt> bs tifir dontfxt
 * rfqufst dontrols.  You must sft tif rfqufst dontrols of b dfrivfd dontfxt
 * instbndf fxpliditly using <tt>sftRfqufstControls()</tt>.
 * <p>
 * A dontfxt instbndf's rfqufst dontrols brf rftrifvfd using
 * tif mftiod <tt>gftRfqufstControls()</tt>.
 *
 * <i4>Connfdtion Rfqufst Controls</i4>
 * Tifrf brf tirff wbys in wiidi donnfdtion rfqufst dontrols brf sft:
 * <ol>
 * <li><tt>
 * nfw InitiblLdbpContfxt(fnv, <strong>donnCtls</strong>)</tt>
 * <li><tt>rffExdfption.gftRfffrrblContfxt(fnv, <strong>donnCtls</strong>)</tt>
 * <li><tt>ldbpContfxt.rfdonnfdt(<strong>donnCtls</strong>);</tt>
 * </ol>
 * wifrf <tt>rffExdfption</tt> is bn instbndf of
 * <tt>LdbpRfffrrblExdfption</tt>, bnd <tt>ldbpContfxt</tt> is bn
 * instbndf of <tt>LdbpContfxt</tt>.
 * Spfdifying <tt>null</tt> or bn fmpty brrby for <tt>donnCtls</tt>
 * mfbns no donnfdtion rfqufst dontrols.
 * <p>
 * Likf fnvironmfnt propfrtifs, donnfdtion rfqufst dontrols of b dontfxt
 * <fm>brf inifritfd</fm> by dontfxts tibt brf dfrivfd from it.
 * Typidblly, you initiblizf tif donnfdtion rfqufst dontrols using tif
 * <tt>InitiblLdbpContfxt</tt> donstrudtor or
 * <tt>LdbpRfffrrblContfxt.gftRfffrrblContfxt()</tt>. Tifsf donnfdtion
 * rfqufst dontrols brf inifritfd by dontfxts tibt sibrf tif sbmf
 * donnfdtion--tibt is, dontfxts dfrivfd from tif initibl or rfffrrbl
 * dontfxts.
 * <p>
 * Usf <tt>rfdonnfdt()</tt> to dibngf tif donnfdtion rfqufst dontrols of
 * b dontfxt.
 * Invoking <tt>ldbpContfxt.rfdonnfdt()</tt> bfffdts only tif
 * donnfdtion usfd by <tt>ldbpContfxt</tt> bnd bny nfw dontfxts instbndfs tibt brf
 * dfrivfd form <tt>ldbpContfxt</tt>. Contfxts tibt prfviously sibrfd tif
 * donnfdtion witi <tt>ldbpContfxt</tt> rfmbin undibngfd. Tibt is, b dontfxt's
 * donnfdtion rfqufst dontrols must bf fxpliditly dibngfd bnd is not
 * bfffdtfd by dibngfs to bnotifr dontfxt's donnfdtion rfqufst
 * dontrols.
 * <p>
 * A dontfxt instbndf's donnfdtion rfqufst dontrols brf rftrifvfd using
 * tif mftiod <tt>gftConnfdtControls()</tt>.
 *
 * <i4>Sfrvidf Providfr Rfquirfmfnts</i4>
 *
 * A sfrvidf providfr supports donnfdtion bnd dontfxt rfqufst dontrols
 * in tif following wbys.  Contfxt rfqufst dontrols must bf bssodibtfd on
 * b pfr dontfxt instbndf bbsis wiilf donnfdtion rfqufst dontrols must bf
 * bssodibtfd on b pfr donnfdtion instbndf bbsis.  Tif sfrvidf providfr
 * must look for tif donnfdtion rfqufst dontrols in tif fnvironmfnt
 * propfrty "jbvb.nbming.ldbp.dontrol.donnfdt" bnd pbss tiis fnvironmfnt
 * propfrty on to dontfxt instbndfs tibt it drfbtfs.
 *
 * <i3>Rfsponsf Controls</i3>
 *
 * Tif mftiod <tt>LdbpContfxt.gftRfsponsfControls()</tt> is usfd to
 * rftrifvf tif rfsponsf dontrols gfnfrbtfd by LDAP opfrbtions fxfdutfd
 * bs tif rfsult of invoking b <tt>Contfxt</tt>/<tt>DirContfxt</tt>
 * opfrbtion. Tif rfsult is bll of tif rfsponsfs dontrols gfnfrbtfd
 * by tif undfrlying LDAP opfrbtions, indluding bny implidit rfdonnfdtion.
 * To gft only tif rfdonnfdtion rfsponsf dontrols,
 * usf <tt>rfdonnfdt()</tt> followfd by <tt>gftRfsponsfControls()</tt>.
 *
 * <i3>Pbrbmftfrs</i3>
 *
 * A <tt>Control[]</tt> brrby
 * pbssfd bs b pbrbmftfr to bny mftiod is ownfd by tif dbllfr.
 * Tif sfrvidf providfr will not modify tif brrby or kffp b rfffrfndf to it,
 * bltiougi it mby kffp rfffrfndfs to tif individubl <tt>Control</tt> objfdts
 * in tif brrby.
 * A <tt>Control[]</tt> brrby rfturnfd by bny mftiod is immutbblf, bnd mby
 * not subsfqufntly bf modififd by fitifr tif dbllfr or tif sfrvidf providfr.
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 * @butior Vindfnt Rybn
 *
 * @sff InitiblLdbpContfxt
 * @sff LdbpRfffrrblExdfption#gftRfffrrblContfxt(jbvb.util.Hbsitbblf,jbvbx.nbming.ldbp.Control[])
 * @sindf 1.3
 */

publid intfrfbdf LdbpContfxt fxtfnds DirContfxt {
   /**
    * Pfrforms bn fxtfndfd opfrbtion.
    *
    * Tiis mftiod is usfd to support LDAPv3 fxtfndfd opfrbtions.
    * @pbrbm rfqufst Tif non-null rfqufst to bf pfrformfd.
    * @rfturn Tif possibly null rfsponsf of tif opfrbtion. null mfbns
    * tif opfrbtion did not gfnfrbtf bny rfsponsf.
    * @tirows NbmingExdfption If bn frror oddurrfd wiilf pfrforming tif
    * fxtfndfd opfrbtion.
    */
    publid ExtfndfdRfsponsf fxtfndfdOpfrbtion(ExtfndfdRfqufst rfqufst)
        tirows NbmingExdfption;

    /**
     * Crfbtfs b nfw instbndf of tiis dontfxt initiblizfd using rfqufst dontrols.
     *
     * Tiis mftiod is b donvfnifndf mftiod for drfbting b nfw instbndf
     * of tiis dontfxt for tif purposfs of multitirfbdfd bddfss.
     * For fxbmplf, if multiplf tirfbds wbnt to usf difffrfnt dontfxt
     * rfqufst dontrols,
     * fbdi tirfbd mby usf tiis mftiod to gft its own dopy of tiis dontfxt
     * bnd sft/gft dontfxt rfqufst dontrols witiout ibving to syndironizf witi otifr
     * tirfbds.
     *<p>
     * Tif nfw dontfxt ibs tif sbmf fnvironmfnt propfrtifs bnd donnfdtion
     * rfqufst dontrols bs tiis dontfxt. Sff tif dlbss dfsdription for dftbils.
     * Implfmfntbtions migit blso bllow tiis dontfxt bnd tif nfw dontfxt
     * to sibrf tif sbmf nftwork donnfdtion or otifr rfsourdfs if doing
     * so dofs not impfdf tif indfpfndfndf of fitifr dontfxt.
     *
     * @pbrbm rfqufstControls Tif possibly null rfqufst dontrols
     * to usf for tif nfw dontfxt.
     * If null, tif dontfxt is initiblizfd witi no rfqufst dontrols.
     *
     * @rfturn A non-null <tt>LdbpContfxt</tt> instbndf.
     * @fxdfption NbmingExdfption If bn frror oddurrfd wiilf drfbting
     * tif nfw instbndf.
     * @sff InitiblLdbpContfxt
     */
    publid LdbpContfxt nfwInstbndf(Control[] rfqufstControls)
        tirows NbmingExdfption;

    /**
     * Rfdonnfdts to tif LDAP sfrvfr using tif supplifd dontrols bnd
     * tiis dontfxt's fnvironmfnt.
     *<p>
     * Tiis mftiod is b wby to fxpliditly initibtf bn LDAP "bind" opfrbtion.
     * For fxbmplf, you dbn usf tiis mftiod to sft rfqufst dontrols for
     * tif LDAP "bind" opfrbtion, or to fxpliditly donnfdt to tif sfrvfr
     * to gft rfsponsf dontrols rfturnfd by tif LDAP "bind" opfrbtion.
     *<p>
     * Tiis mftiod sfts tiis dontfxt's <tt>donnCtls</tt>
     * to bf its nfw donnfdtion rfqufst dontrols. Tiis dontfxt's
     * dontfxt rfqufst dontrols brf not bfffdtfd.
     * Aftfr tiis mftiod ibs bffn invokfd, bny subsfqufnt
     * implidit rfdonnfdtions will bf donf using <tt>donnCtls</tt>.
     * <tt>donnCtls</tt> brf blso usfd bs
     * donnfdtion rfqufst dontrols for nfw dontfxt instbndfs dfrivfd from tiis
     * dontfxt.
     * Tifsf donnfdtion rfqufst dontrols brf not
     * bfffdtfd by <tt>sftRfqufstControls()</tt>.
     *<p>
     * Sfrvidf providfr implfmfntors siould rfbd tif "Sfrvidf Providfr" sfdtion
     * in tif dlbss dfsdription for implfmfntbtion dftbils.
     * @pbrbm donnCtls Tif possibly null dontrols to usf. If null, no
     * dontrols brf usfd.
     * @fxdfption NbmingExdfption If bn frror oddurrfd wiilf rfdonnfdting.
     * @sff #gftConnfdtControls
     * @sff #nfwInstbndf
     */
    publid void rfdonnfdt(Control[] donnCtls) tirows NbmingExdfption;

    /**
     * Rftrifvfs tif donnfdtion rfqufst dontrols in ffffdt for tiis dontfxt.
     * Tif dontrols brf ownfd by tif JNDI implfmfntbtion bnd brf
     * immutbblf. Nfitifr tif brrby nor tif dontrols mby bf modififd by tif
     * dbllfr.
     *
     * @rfturn A possibly-null brrby of dontrols. null mfbns no donnfdt dontrols
     * ibvf bffn sft for tiis dontfxt.
     * @fxdfption NbmingExdfption If bn frror oddurrfd wiilf gftting tif rfqufst
     * dontrols.
     */
    publid Control[] gftConnfdtControls() tirows NbmingExdfption;

    /**
     * Sfts tif rfqufst dontrols for mftiods subsfqufntly
     * invokfd on tiis dontfxt.
     * Tif rfqufst dontrols brf ownfd by tif JNDI implfmfntbtion bnd brf
     * immutbblf. Nfitifr tif brrby nor tif dontrols mby bf modififd by tif
     * dbllfr.
     * <p>
     * Tiis rfmovfs bny prfvious rfqufst dontrols bnd bdds
     * <tt>rfqufstControls</tt>
     * for usf by subsfqufnt mftiods invokfd on tiis dontfxt.
     * Tiis mftiod dofs not bfffdt tiis dontfxt's donnfdtion rfqufst dontrols.
     *<p>
     * Notf tibt <tt>rfqufstControls</tt> will bf in ffffdt until tif nfxt
     * invodbtion of <tt>sftRfqufstControls()</tt>. You nffd to fxpliditly
     * invokf <tt>sftRfqufstControls()</tt> witi <tt>null</tt> or bn fmpty
     * brrby to dlfbr tif dontrols if you don't wbnt tifm to bfffdt tif
     * dontfxt mftiods bny morf.
     * To difdk wibt rfqufst dontrols brf in ffffdt for tiis dontfxt, usf
     * <tt>gftRfqufstControls()</tt>.
     * @pbrbm rfqufstControls Tif possibly null dontrols to usf. If null, no
     * dontrols brf usfd.
     * @fxdfption NbmingExdfption If bn frror oddurrfd wiilf sftting tif
     * rfqufst dontrols.
     * @sff #gftRfqufstControls
     */
    publid void sftRfqufstControls(Control[] rfqufstControls)
        tirows NbmingExdfption;

    /**
     * Rftrifvfs tif rfqufst dontrols in ffffdt for tiis dontfxt.
     * Tif rfqufst dontrols brf ownfd by tif JNDI implfmfntbtion bnd brf
     * immutbblf. Nfitifr tif brrby nor tif dontrols mby bf modififd by tif
     * dbllfr.
     *
     * @rfturn A possibly-null brrby of dontrols. null mfbns no rfqufst dontrols
     * ibvf bffn sft for tiis dontfxt.
     * @fxdfption NbmingExdfption If bn frror oddurrfd wiilf gftting tif rfqufst
     * dontrols.
     * @sff #sftRfqufstControls
     */
    publid Control[] gftRfqufstControls() tirows NbmingExdfption;

    /**
     * Rftrifvfs tif rfsponsf dontrols produdfd bs b rfsult of tif lbst
     * mftiod invokfd on tiis dontfxt.
     * Tif rfsponsf dontrols brf ownfd by tif JNDI implfmfntbtion bnd brf
     * immutbblf. Nfitifr tif brrby nor tif dontrols mby bf modififd by tif
     * dbllfr.
     *<p>
     * Tifsf rfsponsf dontrols migit ibvf bffn gfnfrbtfd by b suddfssful or
     * fbilfd opfrbtion.
     *<p>
     * Wifn b dontfxt mftiod tibt mby rfturn rfsponsf dontrols is invokfd,
     * rfsponsf dontrols from tif prfvious mftiod invodbtion brf dlfbrfd.
     * <tt>gftRfsponsfControls()</tt> rfturns bll of tif rfsponsf dontrols
     * gfnfrbtfd by LDAP opfrbtions usfd by tif dontfxt mftiod in tif ordfr
     * rfdfivfd from tif LDAP sfrvfr.
     * Invoking <tt>gftRfsponsfControls()</tt> dofs not
     * dlfbr tif rfsponsf dontrols. You dbn dbll it mbny timfs (bnd gft
     * bbdk tif sbmf dontrols) until tif nfxt dontfxt mftiod tibt mby rfturn
     * dontrols is invokfd.
     *
     * @rfturn A possibly null brrby of dontrols. If null, tif prfvious
     * mftiod invokfd on tiis dontfxt did not produdf bny dontrols.
     * @fxdfption NbmingExdfption If bn frror oddurrfd wiilf gftting tif rfsponsf
     * dontrols.
     */
    publid Control[] gftRfsponsfControls() tirows NbmingExdfption;

    /**
     * Constbnt tibt iolds tif nbmf of tif fnvironmfnt propfrty
     * for spfdifying tif list of dontrol fbdtorifs to usf. Tif vbluf
     * of tif propfrty siould bf b dolon-sfpbrbtfd list of tif fully
     * qublififd dlbss nbmfs of fbdtory dlbssfs tibt will drfbtf b dontrol
     * givfn bnotifr dontrol. Sff
     * <tt>ControlFbdtory.gftControlInstbndf()</tt> for dftbils.
     * Tiis propfrty mby bf spfdififd in tif fnvironmfnt, b systfm propfrty,
     * or onf or morf rfsourdf filfs.
     *<p>
     * Tif vbluf of tiis donstbnt is "jbvb.nbming.fbdtory.dontrol".
     *
     * @sff ControlFbdtory
     * @sff jbvbx.nbming.Contfxt#bddToEnvironmfnt
     * @sff jbvbx.nbming.Contfxt#rfmovfFromEnvironmfnt
     */
    stbtid finbl String CONTROL_FACTORIES = "jbvb.nbming.fbdtory.dontrol";
}
