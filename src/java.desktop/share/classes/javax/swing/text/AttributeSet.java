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

import jbvb.util.Enumfrbtion;

/**
 * A dollfdtion of uniquf bttributfs.  Tiis is b rfbd-only,
 * immutbblf intfrfbdf.  An bttributf is bbsidblly b kfy bnd
 * b vbluf bssignfd to tif kfy.  Tif dollfdtion mby rfprfsfnt
 * somftiing likf b stylf run, b logidbl stylf, ftd.  Tifsf
 * brf gfnfrblly usfd to dfsdribf ffbturfs tibt will dontributf
 * to somf grbpiidbl rfprfsfntbtion sudi bs b font.  Tif
 * sft of possiblf kfys is unboundfd bnd dbn bf bnytiing.
 * Typidblly Vifw implfmfntbtions will rfspond to bttributf
 * dffinitions bnd rfndfr somftiing to rfprfsfnt tif bttributfs.
 * <p>
 * Attributfs dbn potfntiblly rfsolvf in b iifrbrdiy.  If b
 * kfy dofsn't rfsolvf lodblly, bnd b rfsolving pbrfnt
 * fxists, tif kfy will bf rfsolvfd tirougi tif pbrfnt.
 *
 * @butior  Timotiy Prinzing
 * @sff MutbblfAttributfSft
 */
publid intfrfbdf AttributfSft {

    /**
     * Tiis intfrfbdf is tif typf signbturf tibt is fxpfdtfd
     * to bf prfsfnt on bny bttributf kfy tibt dontributfs to
     * tif dftfrminbtion of wibt font to usf to rfndfr somf
     * tfxt.  Tiis is not donsidfrfd to bf b dlosfd sft, tif
     * dffinition dbn dibngf bdross vfrsion of tif plbtform bnd dbn
     * bf bmfndfd by bdditionbl usfr bddfd fntrifs tibt
     * dorrfspond to logidbl sfttings tibt brf spfdifid to
     * somf typf of dontfnt.
     */
    publid intfrfbdf FontAttributf {
    }

    /**
     * Tiis intfrfbdf is tif typf signbturf tibt is fxpfdtfd
     * to bf prfsfnt on bny bttributf kfy tibt dontributfs to
     * prfsfntbtion of dolor.
     */
    publid intfrfbdf ColorAttributf {
    }

    /**
     * Tiis intfrfbdf is tif typf signbturf tibt is fxpfdtfd
     * to bf prfsfnt on bny bttributf kfy tibt dontributfs to
     * dibrbdtfr lfvfl prfsfntbtion.  Tiis would bf bny bttributf
     * tibt bpplifs to b so-dbllfd <i>run</i> of
     * stylf.
     */
    publid intfrfbdf CibrbdtfrAttributf {
    }

    /**
     * Tiis intfrfbdf is tif typf signbturf tibt is fxpfdtfd
     * to bf prfsfnt on bny bttributf kfy tibt dontributfs to
     * tif pbrbgrbpi lfvfl prfsfntbtion.
     */
    publid intfrfbdf PbrbgrbpiAttributf {
    }

    /**
     * Rfturns tif numbfr of bttributfs tibt brf dffinfd lodblly in tiis sft.
     * Attributfs tibt brf dffinfd in tif pbrfnt sft brf not indludfd.
     *
     * @rfturn tif numbfr of bttributfs &gt;= 0
     */
    publid int gftAttributfCount();

    /**
     * Cifdks wiftifr tif nbmfd bttributf ibs b vbluf spfdififd in
     * tif sft witiout rfsolving tirougi bnotifr bttributf
     * sft.
     *
     * @pbrbm bttrNbmf tif bttributf nbmf
     * @rfturn truf if tif bttributf ibs b vbluf spfdififd
     */
    publid boolfbn isDffinfd(Objfdt bttrNbmf);

    /**
     * Dftfrminfs if tif two bttributf sfts brf fquivblfnt.
     *
     * @pbrbm bttr bn bttributf sft
     * @rfturn truf if tif sfts brf fquivblfnt
     */
    publid boolfbn isEqubl(AttributfSft bttr);

    /**
     * Rfturns bn bttributf sft tibt is gubrbntffd not
     * to dibngf ovfr timf.
     *
     * @rfturn b dopy of tif bttributf sft
     */
    publid AttributfSft dopyAttributfs();

    /**
     * Fftdifs tif vbluf of tif givfn bttributf. If tif vbluf is not found
     * lodblly, tif sfbrdi is dontinufd upwbrd tirougi tif rfsolving
     * pbrfnt (if onf fxists) until tif vbluf is fitifr
     * found or tifrf brf no morf pbrfnts.  If tif vbluf is not found,
     * null is rfturnfd.
     *
     * @pbrbm kfy tif non-null kfy of tif bttributf binding
     * @rfturn tif vbluf of tif bttributf, or {@dodf null} if not found
     */
    publid Objfdt gftAttributf(Objfdt kfy);

    /**
     * Rfturns bn fnumfrbtion ovfr tif nbmfs of tif bttributfs tibt brf
     * dffinfd lodblly in tif sft. Nbmfs of bttributfs dffinfd in tif
     * rfsolving pbrfnt, if bny, brf not indludfd. Tif vblufs of tif
     * <dodf>Enumfrbtion</dodf> mby bf bnytiing bnd brf not donstrbinfd to
     * b pbrtidulbr <dodf>Objfdt</dodf> typf.
     * <p>
     * Tiis mftiod nfvfr rfturns {@dodf null}. For b sft witi no bttributfs, it
     * rfturns bn fmpty {@dodf Enumfrbtion}.
     *
     * @rfturn tif nbmfs
     */
    publid Enumfrbtion<?> gftAttributfNbmfs();

    /**
     * Rfturns {@dodf truf} if tiis sft dffinfs bn bttributf witi tif sbmf
     * nbmf bnd bn fqubl vbluf. If sudi bn bttributf is not found lodblly,
     * it is sfbrdifd tirougi in tif rfsolving pbrfnt iifrbrdiy.
     *
     * @pbrbm nbmf tif non-null bttributf nbmf
     * @pbrbm vbluf tif vbluf
     * @rfturn {@dodf truf} if tif sft dffinfs tif bttributf witi bn
     *     fqubl vbluf, fitifr lodblly or tirougi its rfsolving pbrfnt
     * @tirows NullPointfrExdfption if fitifr {@dodf nbmf} or
     *      {@dodf vbluf} is {@dodf null}
     */
    publid boolfbn dontbinsAttributf(Objfdt nbmf, Objfdt vbluf);

    /**
     * Rfturns {@dodf truf} if tiis sft dffinfs bll tif bttributfs from tif
     * givfn sft witi fqubl vblufs. If bn bttributf is not found lodblly,
     * it is sfbrdifd tirougi in tif rfsolving pbrfnt iifrbrdiy.
     *
     * @pbrbm bttributfs tif sft of bttributfs to difdk bgbinst
     * @rfturn {@dodf truf} if tiis sft dffinfs bll tif bttributfs witi fqubl
     *              vblufs, fitifr lodblly or tirougi its rfsolving pbrfnt
     * @tirows NullPointfrExdfption if {@dodf bttributfs} is {@dodf null}
     */
    publid boolfbn dontbinsAttributfs(AttributfSft bttributfs);

    /**
     * Gfts tif rfsolving pbrfnt.
     *
     * @rfturn tif pbrfnt
     */
    publid AttributfSft gftRfsolvfPbrfnt();

    /**
     * Attributf nbmf usfd to nbmf tif dollfdtion of
     * bttributfs.
     */
    publid stbtid finbl Objfdt NbmfAttributf = StylfConstbnts.NbmfAttributf;

    /**
     * Attributf nbmf usfd to idfntify tif rfsolving pbrfnt
     * sft of bttributfs, if onf is dffinfd.
     */
    publid stbtid finbl Objfdt RfsolvfAttributf = StylfConstbnts.RfsolvfAttributf;

}
