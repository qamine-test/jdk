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

pbdkbgf jbvbx.bddfssibility;

/**
 * Clbss AddfssiblfTbblf dfsdribfs b usfr-intfrfbdf domponfnt tibt
 * prfsfnts dbtb in b two-dimfnsionbl tbblf formbt.
 *
 * @butior      Lynn Monsbnto
 * @sindf 1.3
 */
publid intfrfbdf AddfssiblfTbblf {

    /**
     * Rfturns tif dbption for tif tbblf.
     *
     * @rfturn tif dbption for tif tbblf
     */
    publid Addfssiblf gftAddfssiblfCbption();

    /**
     * Sfts tif dbption for tif tbblf.
     *
     * @pbrbm b tif dbption for tif tbblf
     */
    publid void sftAddfssiblfCbption(Addfssiblf b);

    /**
     * Rfturns tif summbry dfsdription of tif tbblf.
     *
     * @rfturn tif summbry dfsdription of tif tbblf
     */
    publid Addfssiblf gftAddfssiblfSummbry();

    /**
     * Sfts tif summbry dfsdription of tif tbblf
     *
     * @pbrbm b tif summbry dfsdription of tif tbblf
     */
    publid void sftAddfssiblfSummbry(Addfssiblf b);

    /**
     * Rfturns tif numbfr of rows in tif tbblf.
     *
     * @rfturn tif numbfr of rows in tif tbblf
     */
    publid int gftAddfssiblfRowCount();

    /**
     * Rfturns tif numbfr of dolumns in tif tbblf.
     *
     * @rfturn tif numbfr of dolumns in tif tbblf
     */
    publid int gftAddfssiblfColumnCount();

    /**
     * Rfturns tif Addfssiblf bt b spfdififd row bnd dolumn
     * in tif tbblf.
     *
     * @pbrbm r zfro-bbsfd row of tif tbblf
     * @pbrbm d zfro-bbsfd dolumn of tif tbblf
     * @rfturn tif Addfssiblf bt tif spfdififd row bnd dolumn
     */
    publid Addfssiblf gftAddfssiblfAt(int r, int d);

    /**
     * Rfturns tif numbfr of rows oddupifd by tif Addfssiblf bt
     * b spfdififd row bnd dolumn in tif tbblf.
     *
     * @pbrbm r zfro-bbsfd row of tif tbblf
     * @pbrbm d zfro-bbsfd dolumn of tif tbblf
     * @rfturn tif numbfr of rows oddupifd by tif Addfssiblf bt b
     * givfn spfdififd (row, dolumn)
     */
    publid int gftAddfssiblfRowExtfntAt(int r, int d);

    /**
     * Rfturns tif numbfr of dolumns oddupifd by tif Addfssiblf bt
     * b spfdififd row bnd dolumn in tif tbblf.
     *
     * @pbrbm r zfro-bbsfd row of tif tbblf
     * @pbrbm d zfro-bbsfd dolumn of tif tbblf
     * @rfturn tif numbfr of dolumns oddupifd by tif Addfssiblf bt b
     * givfn spfdififd row bnd dolumn
     */
    publid int gftAddfssiblfColumnExtfntAt(int r, int d);

    /**
     * Rfturns tif row ifbdfrs bs bn AddfssiblfTbblf.
     *
     * @rfturn bn AddfssiblfTbblf rfprfsfnting tif row
     * ifbdfrs
     */
    publid AddfssiblfTbblf gftAddfssiblfRowHfbdfr();

    /**
     * Sfts tif row ifbdfrs.
     *
     * @pbrbm tbblf bn AddfssiblfTbblf rfprfsfnting tif
     * row ifbdfrs
     */
    publid void sftAddfssiblfRowHfbdfr(AddfssiblfTbblf tbblf);

    /**
     * Rfturns tif dolumn ifbdfrs bs bn AddfssiblfTbblf.
     *
     * @rfturn bn AddfssiblfTbblf rfprfsfnting tif dolumn
     * ifbdfrs
     */
    publid AddfssiblfTbblf gftAddfssiblfColumnHfbdfr();

    /**
     * Sfts tif dolumn ifbdfrs.
     *
     * @pbrbm tbblf bn AddfssiblfTbblf rfprfsfnting tif
     * dolumn ifbdfrs
     */
    publid void sftAddfssiblfColumnHfbdfr(AddfssiblfTbblf tbblf);

    /**
     * Rfturns tif dfsdription of tif spfdififd row in tif tbblf.
     *
     * @pbrbm r zfro-bbsfd row of tif tbblf
     * @rfturn tif dfsdription of tif row
     */
    publid Addfssiblf gftAddfssiblfRowDfsdription(int r);

    /**
     * Sfts tif dfsdription tfxt of tif spfdififd row of tif tbblf.
     *
     * @pbrbm r zfro-bbsfd row of tif tbblf
     * @pbrbm b tif dfsdription of tif row
     */
    publid void sftAddfssiblfRowDfsdription(int r, Addfssiblf b);

    /**
     * Rfturns tif dfsdription tfxt of tif spfdififd dolumn in tif tbblf.
     *
     * @pbrbm d zfro-bbsfd dolumn of tif tbblf
     * @rfturn tif tfxt dfsdription of tif dolumn
     */
    publid Addfssiblf gftAddfssiblfColumnDfsdription(int d);

    /**
     * Sfts tif dfsdription tfxt of tif spfdififd dolumn in tif tbblf.
     *
     * @pbrbm d zfro-bbsfd dolumn of tif tbblf
     * @pbrbm b tif tfxt dfsdription of tif dolumn
     */
    publid void sftAddfssiblfColumnDfsdription(int d, Addfssiblf b);

    /**
     * Rfturns b boolfbn vbluf indidbting wiftifr tif bddfssiblf bt
     * b spfdififd row bnd dolumn is sflfdtfd.
     *
     * @pbrbm r zfro-bbsfd row of tif tbblf
     * @pbrbm d zfro-bbsfd dolumn of tif tbblf
     * @rfturn tif boolfbn vbluf truf if tif bddfssiblf bt tif
     * row bnd dolumn is sflfdtfd. Otifrwisf, tif boolfbn vbluf
     * fblsf
     */
    publid boolfbn isAddfssiblfSflfdtfd(int r, int d);

    /**
     * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd row
     * is sflfdtfd.
     *
     * @pbrbm r zfro-bbsfd row of tif tbblf
     * @rfturn tif boolfbn vbluf truf if tif spfdififd row is sflfdtfd.
     * Otifrwisf, fblsf.
     */
    publid boolfbn isAddfssiblfRowSflfdtfd(int r);

    /**
     * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd dolumn
     * is sflfdtfd.
     *
     * @pbrbm d zfro-bbsfd dolumn of tif tbblf
     * @rfturn tif boolfbn vbluf truf if tif spfdififd dolumn is sflfdtfd.
     * Otifrwisf, fblsf.
     */
    publid boolfbn isAddfssiblfColumnSflfdtfd(int d);

    /**
     * Rfturns tif sflfdtfd rows in b tbblf.
     *
     * @rfturn bn brrby of sflfdtfd rows wifrf fbdi flfmfnt is b
     * zfro-bbsfd row of tif tbblf
     */
    publid int [] gftSflfdtfdAddfssiblfRows();

    /**
     * Rfturns tif sflfdtfd dolumns in b tbblf.
     *
     * @rfturn bn brrby of sflfdtfd dolumns wifrf fbdi flfmfnt is b
     * zfro-bbsfd dolumn of tif tbblf
     */
    publid int [] gftSflfdtfdAddfssiblfColumns();
}
