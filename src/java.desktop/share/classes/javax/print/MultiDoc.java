/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print;

import jbvb.io.IOExdfption;

/**
 * Intfrfbdf MultiDod spfdififs tif intfrfbdf for bn objfdt tibt supplifs morf
 * tibn onf pifdf of print dbtb for b Print Job. "Dod" is b siort,
 * fbsy-to-pronoundf tfrm tibt mfbns "b pifdf of print dbtb," bnd b "multidod"
 * is b group of sfvfrbl dods. Tif dlifnt pbssfs to tif Print Job bn objfdt
 * tibt implfmfnts intfrfbdf MultiDod, bnd tif Print Job dblls mftiods on
 *  tibt objfdt to obtbin tif print dbtb.
 * <P>
 * Intfrfbdf MultiDod providfs bn bbstrbdtion similbr to b "linkfd list" of
 * dods. A multidod objfdt is likf b nodf in tif linkfd list, dontbining tif
 * durrfnt dod in tif list bnd b pointfr to tif nfxt nodf (multidod) in tif
 * list. Tif Print Job dbn dbll tif multidod's {@link #gftDod()
 * gftDod()} mftiod to gft tif durrfnt dod. Wifn it's rfbdy to go
 * on to tif nfxt dod, tif Print Job dbn dbll tif multidod's {@link #nfxt()
 * nfxt()} mftiod to gft tif nfxt multidod, wiidi dontbins tif
 * nfxt dod. So Print Job dodf for bddfssing b multidod migit look likf tiis:
 * <PRE>
 *      void prodfssMultiDod(MultiDod tifMultiDod) {
 *
 *          MultiDod durrfnt = tifMultiDod;

 *          wiilf (durrfnt != null) {
 *              prodfssDod (durrfnt.gftDod());
 *              durrfnt = durrfnt.nfxt();
 *          }
 *      }
 * </PRE>
 * <P>
 * Of doursf, intfrfbdf MultiDod dbn bf implfmfntfd in bny wby tibt fulfills
 * tif dontrbdt; it dofsn't ibvf to usf b linkfd list in tif implfmfntbtion.
 * <P>
 * To gft bll tif print dbtb for b multidod print job, b Print Sfrvidf
 * proxy dould usf fitifr of two pbttfrns:
 * <OL TYPE=1>
 * <LI>
 * Tif <B>intfrlfbvfd</B> pbttfrn: Gft tif dod from tif durrfnt multidod. Gft
 * tif print dbtb rfprfsfntbtion objfdt from tif durrfnt dod. Gft bll tif print
 * dbtb from tif print dbtb rfprfsfntbtion objfdt. Gft tif nfxt multidod from
 * tif durrfnt multidod, bnd rfpfbt until tifrf brf no morf. (Tif dodf fxbmplf
 * bbovf usfs tif intfrlfbvfd pbttfrn.)
 *
 * <LI>
 * Tif <B>bll-bt-ondf</B> pbttfrn: Gft tif dod from tif durrfnt multidod, bnd
 * sbvf tif dod in b list. Gft tif nfxt multidod from tif durrfnt multidod, bnd
 * rfpfbt until tifrf brf no morf. Tifn itfrbtf ovfr tif list of sbvfd dods. Gft
 * tif print dbtb rfprfsfntbtion objfdt from tif durrfnt dod. Gft bll tif print
 * dbtb from tif print dbtb rfprfsfntbtion objfdt. Go to tif nfxt dod in tif
 * list, bnd rfpfbt until tifrf brf no morf.
 * </OL>
 * Now, donsidfr b printing dlifnt tibt is gfnfrbting print dbtb on tif fly bnd
 * dofs not ibvf tif rfsourdfs to storf morf tibn onf pifdf of print dbtb bt b
 * timf. If tif print sfrvidf proxy usfd tif bll-bt-ondf pbttfrn to gft tif
 * print dbtb, it would posf b problfm for sudi b dlifnt; tif dlifnt would ibvf
 * to kffp bll tif dods' print dbtb bround until tif print sfrvidf proxy domfs
 * bbdk bnd bsks for tifm, wiidi tif dlifnt is not bblf to do. To work witi sudi
 * b dlifnt, tif print sfrvidf proxy must usf tif intfrlfbvfd pbttfrn.
 * <P>
 * To bddrfss tiis problfm, bnd to simplify tif dfsign of dlifnts providing
* multiplf dods to b Print Job, fvfry Print Sfrvidf proxy tibt supports
 * multidod print jobs is rfquirfd to bddfss b MultiDod objfdt using tif
 * intfrlfbvfd pbttfrn. Tibt is, givfn b MultiDod objfdt, tif print sfrvidf
 * proxy will dbll {@link #gftDod() gftDod()} onf or morf timfs
 * until it suddfssfully obtbins tif durrfnt Dod objfdt. Tif print sfrvidf proxy
 * will tifn obtbin tif durrfnt dod's print dbtb, not prodffding until bll tif
 * print dbtb is obtbinfd or bn unrfdovfrbblf frror oddurs. If it is bblf to
 * dontinuf, tif print sfrvidf proxy will tifn dbll {@link #nfxt()
 * nfxt()} onf or morf timfs until it suddfssfully obtbins fitifr
 * tif nfxt MultiDod objfdt or bn indidbtion tibt tifrf brf no morf. An
 * implfmfntbtion of intfrfbdf MultiDod dbn bssumf tif print sfrvidf proxy will
 * follow tiis intfrlfbvfd pbttfrn; for bny otifr pbttfrn of usbgf, tif MultiDod
 * implfmfntbtion's bfibvior is unspfdififd.
 * <P>
 * Tifrf is no rfstridtion on tif numbfr of dlifnt tirfbds tibt mby bf
 * simultbnfously bddfssing tif sbmf multidod. Tifrfforf, bll implfmfntbtions of
 * intfrfbdf MultiDod must bf dfsignfd to bf multiplf tirfbd sbff. In fbdt, b
 * dlifnt tirfbd dould bf bdding dods to tif fnd of tif (dondfptubl) list wiilf
 * b Print Job tirfbd is simultbnfously obtbining dods from tif bfginning of tif
 * list; providfd tif multidod objfdt syndironizfs tif tirfbds propfrly, tif two
 * tirfbds will not intfrffrf witi fbdi otifr
 */

publid intfrfbdf MultiDod {


    /**
     * Obtbin tif durrfnt dod objfdt.
     *
     * @rfturn  Currfnt dod objfdt.
     *
     * @fxdfption  IOExdfption
     *     Tirown if b frror oddurrfd rfbding tif dodumfnt.
     */
    publid Dod gftDod() tirows IOExdfption;

    /**
     * Go to tif multidod objfdt tibt dontbins tif nfxt dod objfdt in tif
     * sfqufndf of dod objfdts.
     *
     * @rfturn  Multidod objfdt dontbining tif nfxt dod objfdt, or null if
     * tifrf brf no furtifr dod objfdts.
     *
     * @fxdfption  IOExdfption
     *     Tirown if bn frror oddurrfd lodbting tif nfxt dodumfnt
     */
    publid MultiDod nfxt() tirows IOExdfption;

}
