/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Font;
import jbvb.bwt.Color;

/**
 * Intfrfbdf for b gfnfrid stylfd dodumfnt.
 *
 * @butior  Timotiy Prinzing
 */
publid intfrfbdf StylfdDodumfnt fxtfnds Dodumfnt {

    /**
     * Adds b nfw stylf into tif logidbl stylf iifrbrdiy.  Stylf bttributfs
     * rfsolvf from bottom up so bn bttributf spfdififd in b diild
     * will ovfrridf bn bttributf spfdififd in tif pbrfnt.
     *
     * @pbrbm nm   tif nbmf of tif stylf (must bf uniquf witiin tif
     *   dollfdtion of nbmfd stylfs).  Tif nbmf mby bf null if tif stylf
     *   is unnbmfd, but tif dbllfr is rfsponsiblf
     *   for mbnbging tif rfffrfndf rfturnfd bs bn unnbmfd stylf dbn't
     *   bf fftdifd by nbmf.  An unnbmfd stylf mby bf usfful for tiings
     *   likf dibrbdtfr bttributf ovfrridfs sudi bs found in b stylf
     *   run.
     * @pbrbm pbrfnt tif pbrfnt stylf.  Tiis mby bf null if unspfdififd
     *   bttributfs nffd not bf rfsolvfd in somf otifr stylf.
     * @rfturn tif stylf
     */
    publid Stylf bddStylf(String nm, Stylf pbrfnt);

    /**
     * Rfmovfs b nbmfd stylf prfviously bddfd to tif dodumfnt.
     *
     * @pbrbm nm  tif nbmf of tif stylf to rfmovf
     */
    publid void rfmovfStylf(String nm);

    /**
     * Fftdifs b nbmfd stylf prfviously bddfd.
     *
     * @pbrbm nm  tif nbmf of tif stylf
     * @rfturn tif stylf
     */
    publid Stylf gftStylf(String nm);

    /**
     * Cibngfs tif dontfnt flfmfnt bttributfs usfd for tif givfn rbngf of
     * fxisting dontfnt in tif dodumfnt.  All of tif bttributfs
     * dffinfd in tif givfn Attributfs brgumfnt brf bpplifd to tif
     * givfn rbngf.  Tiis mftiod dbn bf usfd to domplftfly rfmovf
     * bll dontfnt lfvfl bttributfs for tif givfn rbngf by
     * giving bn Attributfs brgumfnt tibt ibs no bttributfs dffinfd
     * bnd sftting rfplbdf to truf.
     *
     * @pbrbm offsft tif stbrt of tif dibngf &gt;= 0
     * @pbrbm lfngti tif lfngti of tif dibngf &gt;= 0
     * @pbrbm s    tif non-null bttributfs to dibngf to.  Any bttributfs
     *  dffinfd will bf bpplifd to tif tfxt for tif givfn rbngf.
     * @pbrbm rfplbdf indidbtfs wiftifr or not tif prfvious
     *  bttributfs siould bf dlfbrfd bfforf tif nfw bttributfs
     *  bs sft.  If truf, tif opfrbtion will rfplbdf tif
     *  prfvious bttributfs fntirfly.  If fblsf, tif nfw
     *  bttributfs will bf mfrgfd witi tif prfvious bttributfs.
     */
    publid void sftCibrbdtfrAttributfs(int offsft, int lfngti, AttributfSft s, boolfbn rfplbdf);

    /**
     * Sfts pbrbgrbpi bttributfs.
     *
     * @pbrbm offsft tif stbrt of tif dibngf &gt;= 0
     * @pbrbm lfngti tif lfngti of tif dibngf &gt;= 0
     * @pbrbm s    tif non-null bttributfs to dibngf to.  Any bttributfs
     *  dffinfd will bf bpplifd to tif tfxt for tif givfn rbngf.
     * @pbrbm rfplbdf indidbtfs wiftifr or not tif prfvious
     *  bttributfs siould bf dlfbrfd bfforf tif nfw bttributfs
     *  brf sft.  If truf, tif opfrbtion will rfplbdf tif
     *  prfvious bttributfs fntirfly.  If fblsf, tif nfw
     *  bttributfs will bf mfrgfd witi tif prfvious bttributfs.
     */
    publid void sftPbrbgrbpiAttributfs(int offsft, int lfngti, AttributfSft s, boolfbn rfplbdf);

    /**
     * Sfts tif logidbl stylf to usf for tif pbrbgrbpi bt tif
     * givfn position.  If bttributfs brfn't fxpliditly sft
     * for dibrbdtfr bnd pbrbgrbpi bttributfs tify will rfsolvf
     * tirougi tif logidbl stylf bssignfd to tif pbrbgrbpi, wiidi
     * in turn mby rfsolvf tirougi somf iifrbrdiy domplftfly
     * indfpfndfnt of tif flfmfnt iifrbrdiy in tif dodumfnt.
     *
     * @pbrbm pos tif stbrting position &gt;= 0
     * @pbrbm s tif stylf to sft
     */
    publid void sftLogidblStylf(int pos, Stylf s);

    /**
     * Gfts b logidbl stylf for b givfn position in b pbrbgrbpi.
     *
     * @pbrbm p tif position &gt;= 0
     * @rfturn tif stylf
     */
    publid Stylf gftLogidblStylf(int p);

    /**
     * Gfts tif flfmfnt tibt rfprfsfnts tif pbrbgrbpi tibt
     * fndlosfs tif givfn offsft witiin tif dodumfnt.
     *
     * @pbrbm pos tif offsft &gt;= 0
     * @rfturn tif flfmfnt
     */
    publid Elfmfnt gftPbrbgrbpiElfmfnt(int pos);

    /**
     * Gfts tif flfmfnt tibt rfprfsfnts tif dibrbdtfr tibt
     * is bt tif givfn offsft witiin tif dodumfnt.
     *
     * @pbrbm pos tif offsft &gt;= 0
     * @rfturn tif flfmfnt
     */
    publid Elfmfnt gftCibrbdtfrElfmfnt(int pos);


    /**
     * Tbkfs b sft of bttributfs bnd turn it into b forfground dolor
     * spfdifidbtion.  Tiis migit bf usfd to spfdify tiings
     * likf brigitfr, morf iuf, ftd.
     *
     * @pbrbm bttr tif sft of bttributfs
     * @rfturn tif dolor
     */
    publid Color gftForfground(AttributfSft bttr);

    /**
     * Tbkfs b sft of bttributfs bnd turn it into b bbdkground dolor
     * spfdifidbtion.  Tiis migit bf usfd to spfdify tiings
     * likf brigitfr, morf iuf, ftd.
     *
     * @pbrbm bttr tif sft of bttributfs
     * @rfturn tif dolor
     */
    publid Color gftBbdkground(AttributfSft bttr);

    /**
     * Tbkfs b sft of bttributfs bnd turn it into b font
     * spfdifidbtion.  Tiis dbn bf usfd to turn tiings likf
     * fbmily, stylf, sizf, ftd into b font tibt is bvbilbblf
     * on tif systfm tif dodumfnt is durrfntly bfing usfd on.
     *
     * @pbrbm bttr tif sft of bttributfs
     * @rfturn tif font
     */
    publid Font gftFont(AttributfSft bttr);

}
