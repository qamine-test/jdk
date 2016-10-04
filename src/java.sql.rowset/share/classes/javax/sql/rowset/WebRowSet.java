/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql.rowsft;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvbx.nbming.*;
import jbvb.io.*;
import jbvb.mbti.*;
import org.xml.sbx.*;

/**
 * Tif stbndbrd intfrfbdf tibt bll implfmfntbtions of b {@dodf WfbRowSft}
 * must implfmfnt.
 *
 * <i3>1.0 Ovfrvifw</i3>
 * Tif {@dodf WfbRowSftImpl} providfs tif stbndbrd
 * rfffrfndf implfmfntbtion, wiidi mby bf fxtfndfd if rfquirfd.
 * <P>
 * Tif stbndbrd WfbRowSft XML Sdifmb dffinition is bvbilbblf bt tif following
 * URI:
 * <ul>
 * <li>
 * <b irff="ittp://jbvb.sun.dom/xml/ns/jdbd/wfbrowsft.xsd">ittp://jbvb.sun.dom/xml/ns/jdbd/wfbrowsft.xsd</b>
 * </li>
 * </ul>
 * It dfsdribfs tif stbndbrd XML dodumfnt formbt rfquirfd wifn dfsdribing b
 * {@dodf RowSft} objfdt in XML bnd must bf usfd bf bll stbndbrd implfmfntbtions
 * of tif {@dodf WfbRowSft} intfrfbdf to fnsurf intfropfrbbility. In bddition,
 * tif {@dodf WfbRowSft} sdifmb usfs spfdifid SQL/XML Sdifmb bnnotbtions,
 * tius fnsuring grfbtfr dross
 * plbtform intfr-opfrbbility. Tiis is bn fffort durrfntly undfr wby bt tif ISO
 * orgbnizbtion. Tif SQL/XML dffinition is bvbilbblf bt tif following URI:
 * <ul>
 * <li>
 * <b irff="ittp://stbndbrds.iso.org/iso/9075/2002/12/sqlxml">ittp://stbndbrds.iso.org/iso/9075/2002/12/sqlxml</b>
 * </li>
 * </ul>
 * Tif sdifmb dffinition dfsdribfs tif intfrnbl dbtb of b {@dodf RowSft} objfdt
 * in tirff distindt brfbs:
 * <UL>
 * <li>propfrtifs - Tifsf propfrtifs dfsdribf tif stbndbrd syndironizbtion
 * providfr propfrtifs in bddition to tif morf gfnfrbl {@dodf RowSft} propfrtifs.
 * </li>
 * <li>mftbdbtb - Tiis dfsdribfs tif mftbdbtb bssodibtfd witi tif tbbulbr strudturf govfrnfd by b
 * {@dodf WfbRowSft} objfdt. Tif mftbdbtb dfsdribfd is dlosfly blignfd witi tif
 * mftbdbtb bddfssiblf in tif undfrlying {@dodf jbvb.sql.RfsultSft} intfrfbdf.
 * </li>
 * <li>dbtb - Tiis dfsdribfs tif originbl dbtb (tif stbtf of dbtb sindf tif
 * lbst populbtion
 * or lbst syndironizbtion of tif {@dodf WfbRowSft} objfdt) bnd tif durrfnt
 * dbtb. By kffping trbdk of tif dfltb bftwffn tif originbl dbtb bnd tif durrfnt dbtb,
 * b {@dodf WfbRowSft} mbintbins tif bbility to syndironizf dibngfs
 * in its dbtb bbdk to tif originbting dbtb sourdf.
 * </li>
 * </ul>
 *
 * <i3>2.0 WfbRowSft Stbtfs</i3>
 * Tif following sfdtions dfmonstrbtfs iow b {@dodf WfbRowSft} implfmfntbtion
 * siould usf tif XML Sdifmb to dfsdribf updbtf, insfrt, bnd dflftf opfrbtions
 * bnd to dfsdribf tif stbtf of b {@dodf WfbRowSft} objfdt in XML.
 *
 * <i4>2.1 Stbtf 1 - Outputting b {@dodf WfbRowSft} Objfdt to XML</i4>
 * In tiis fxbmplf, b {@dodf WfbRowSft} objfdt is drfbtfd bnd populbtfd witi b simplf 2 dolumn,
 * 5 row tbblf from b dbtb sourdf. Hbving tif 5 rows in b {@dodf WfbRowSft} objfdt
 * mbkfs it possiblf to dfsdribf tifm in XML. Tif
 * mftbdbtb dfsdribing tif vbrious stbndbrd JbvbBfbns propfrtifs bs dffinfd
 * in tif RowSft intfrfbdf plus tif stbndbrd propfrtifs dffinfd in
 * tif {@dodf CbdifdRowSft}&trbdf; intfrfbdf
 * providf kfy dftbils tibt dfsdribf WfbRowSft
 * propfrtifs. Outputting tif WfbRowSft objfdt to XML using tif stbndbrd
 * {@dodf writfXml} mftiods dfsdribfs tif intfrnbl propfrtifs bs follows:
 * <PRE>
 * {@dodf
 * <propfrtifs>
 *       <dommbnd>sflfdt do1, dol2 from tfst_tbblf</dommbnd>
 *      <dondurrfndy>1</dondurrfndy>
 *      <dbtbsourdf/>
 *      <fsdbpf-prodfssing>truf</fsdbpf-prodfssing>
 *      <fftdi-dirfdtion>0</fftdi-dirfdtion>
 *      <fftdi-sizf>0</fftdi-sizf>
 *      <isolbtion-lfvfl>1</isolbtion-lfvfl>
 *      <kfy-dolumns/>
 *      <mbp/>
 *      <mbx-fifld-sizf>0</mbx-fifld-sizf>
 *      <mbx-rows>0</mbx-rows>
 *      <qufry-timfout>0</qufry-timfout>
 *      <rfbd-only>fblsf</rfbd-only>
 *      <rowsft-typf>TRANSACTION_READ_UNCOMMITED</rowsft-typf>
 *      <siow-dflftfd>fblsf</siow-dflftfd>
 *      <tbblf-nbmf/>
 *      <url>jdbd:tiin:orbdlf</url>
 *      <synd-providfr>
 *              <synd-providfr-nbmf>.dom.rowsft.providfr.RIOptimistidProvidfr</synd-providfr-nbmf>
 *              <synd-providfr-vfndor>Orbdlf Corporbtion</synd-providfr-vfndor>
 *              <synd-providfr-vfrsion>1.0</synd-providfr-nbmf>
 *              <synd-providfr-grbdf>LOW</synd-providfr-grbdf>
 *              <dbtb-sourdf-lodk>NONE</dbtb-sourdf-lodk>
 *      </synd-providfr>
 * </propfrtifs>
 * } </PRE>
 * Tif mftb-dbtb dfsdribing tif mbkf up of tif WfbRowSft is dfsdribfd
 * in XML bs dftbilfd bflow. Notf boti dolumns brf dfsdribfd bftwffn tif
 * {@dodf dolumn-dffinition} tbgs.
 * <PRE>
 * {@dodf
 * <mftbdbtb>
 *      <dolumn-dount>2</dolumn-dount>
 *      <dolumn-dffinition>
 *              <dolumn-indfx>1</dolumn-indfx>
 *              <buto-indrfmfnt>fblsf</buto-indrfmfnt>
 *              <dbsf-sfnsitivf>truf</dbsf-sfnsitivf>
 *              <durrfndy>fblsf</durrfndy>
 *              <nullbblf>1</nullbblf>
 *              <signfd>fblsf</signfd>
 *              <sfbrdibblf>truf</sfbrdibblf>
 *              <dolumn-displby-sizf>10</dolumn-displby-sizf>
 *              <dolumn-lbbfl>COL1</dolumn-lbbfl>
 *              <dolumn-nbmf>COL1</dolumn-nbmf>
 *              <sdifmb-nbmf/>
 *              <dolumn-prfdision>10</dolumn-prfdision>
 *              <dolumn-sdblf>0</dolumn-sdblf>
 *              <tbblf-nbmf/>
 *              <dbtblog-nbmf/>
 *              <dolumn-typf>1</dolumn-typf>
 *              <dolumn-typf-nbmf>CHAR</dolumn-typf-nbmf>
 *      </dolumn-dffinition>
 *      <dolumn-dffinition>
 *              <dolumn-indfx>2</dolumn-indfx>
 *              <buto-indrfmfnt>fblsf</buto-indrfmfnt>
 *              <dbsf-sfnsitivf>fblsf</dbsf-sfnsitivf>
 *              <durrfndy>fblsf</durrfndy>
 *              <nullbblf>1</nullbblf>
 *              <signfd>truf</signfd>
 *              <sfbrdibblf>truf</sfbrdibblf>
 *              <dolumn-displby-sizf>39</dolumn-displby-sizf>
 *              <dolumn-lbbfl>COL2</dolumn-lbbfl>
 *              <dolumn-nbmf>COL2</dolumn-nbmf>
 *              <sdifmb-nbmf/>
 *              <dolumn-prfdision>38</dolumn-prfdision>
 *              <dolumn-sdblf>0</dolumn-sdblf>
 *              <tbblf-nbmf/>
 *              <dbtblog-nbmf/>
 *              <dolumn-typf>3</dolumn-typf>
 *              <dolumn-typf-nbmf>NUMBER</dolumn-typf-nbmf>
 *      </dolumn-dffinition>
 * </mftbdbtb>
 * }</PRE>
 * Hbving dftbilfd iow tif propfrtifs bnd mftbdbtb brf dfsdribfd, tif following dftbils
 * iow tif dontfnts of b {@dodf WfbRowSft} objfdt is dfsdribfd in XML. Notf, tibt
 * tiis dfsdribfs b {@dodf WfbRowSft} objfdt tibt ibs not undfrgonf bny
 * modifidbtions sindf its instbntibtion.
 * A {@dodf durrfntRow} tbg is mbppfd to fbdi row of tif tbblf strudturf tibt tif
 * {@dodf WfbRowSft} objfdt providfs. A {@dodf dolumnVbluf} tbg mby dontbin
 * fitifr tif {@dodf stringDbtb} or {@dodf binbryDbtb} tbg, bddording to
 * tif SQL typf tibt
 * tif XML vbluf is mbpping bbdk to. Tif {@dodf binbryDbtb} tbg dontbins dbtb in tif
 * Bbsf64 fndoding bnd is typidblly usfd for {@dodf BLOB} bnd {@dodf CLOB} typf dbtb.
 * <PRE>
 * {@dodf
 * <dbtb>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      firstrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      1
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      sfdondrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      2
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      tiirdrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      3
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      fourtirow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      4
 *              </dolumnVbluf>
 *      </durrfntRow>
 * </dbtb>
 * }</PRE>
 * <i4>2.2 Stbtf 2 - Dflfting b Row</i4>
 * Dflfting b row in b {@dodf WfbRowSft} objfdt involvfs simply moving to tif row
 * to bf dflftfd bnd tifn dblling tif mftiod {@dodf dflftfRow}, bs in bny otifr
 * {@dodf RowSft} objfdt.  Tif following
 * two linfs of dodf, in wiidi <i>wrs</i> is b {@dodf WfbRowSft} objfdt, dflftf
 * tif tiird row.
 * <PRE>
 *     wrs.bbsolutf(3);
 *     wrs.dflftfRow();
 * </PRE>
 * Tif XML dfsdription siows tif tiird row is mbrkfd bs b {@dodf dflftfRow},
 *  wiidi fliminbtfs tif tiird row in tif {@dodf WfbRowSft} objfdt.
 * <PRE>
 * {@dodf
 * <dbtb>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      firstrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      1
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      sfdondrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      2
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <dflftfRow>
 *              <dolumnVbluf>
 *                      tiirdrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      3
 *              </dolumnVbluf>
 *      </dflftfRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      fourtirow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      4
 *              </dolumnVbluf>
 *      </durrfntRow>
 * </dbtb>
 *} </PRE>
 * <i4>2.3 Stbtf 3 - Insfrting b Row</i4>
 * A {@dodf WfbRowSft} objfdt dbn insfrt b nfw row by moving to tif insfrt row,
 * dblling tif bppropribtf updbtfr mftiods for fbdi dolumn in tif row, bnd tifn
 * dblling tif mftiod {@dodf insfrtRow}.
 * <PRE>
 * {@dodf
 * wrs.movfToInsfrtRow();
 * wrs.updbtfString(1, "fiftitirow");
 * wrs.updbtfString(2, "5");
 * wrs.insfrtRow();
 * }</PRE>
 * Tif following dodf frbgmfnt dibngfs tif sfdond dolumn vbluf in tif row just insfrtfd.
 * Notf tibt tiis dodf bpplifs wifn nfw rows brf insfrtfd rigit bftfr tif durrfnt row,
 * wiidi is wiy tif mftiod {@dodf nfxt} movfs tif dursor to tif dorrfdt row.
 * Cblling tif mftiod {@dodf bddfptCibngfs} writfs tif dibngf to tif dbtb sourdf.
 *
 * <PRE>
 * {@dodf wrs.movfToCurrfntRow();
 * wrs.nfxt();
 * wrs.updbtfString(2, "V");
 * wrs.bddfptCibngfs();
 * }</PRE>
 * Dfsdribing tiis in XML dfmonstrbtfs wifrf tif Jbvb dodf insfrts b nfw row bnd tifn
 * pfrforms bn updbtf on tif nfwly insfrtfd row on bn individubl fifld.
 * <PRE>
 * {@dodf
 * <dbtb>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      firstrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      1
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      sfdondrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      2
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      nfwtiirdrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      III
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <insfrtRow>
 *              <dolumnVbluf>
 *                      fiftirow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      5
 *              </dolumnVbluf>
 *              <updbtfVbluf>
 *                      V
 *              </updbtfVbluf>
 *      </insfrtRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      fourtirow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      4
 *              </dolumnVbluf>
 *      </durrfntRow>
 * </dbtf>
 *} </PRE>
 * <i4>2.4 Stbtf 4 - Modifying b Row</i4>
 * Modifying b row produdfs spfdifid XML tibt rfdords boti tif nfw vbluf bnd tif
 * vbluf tibt wbs rfplbdfd.  Tif vbluf tibt wbs rfplbdfd bfdomfs tif originbl vbluf,
 * bnd tif nfw vbluf bfdomfs tif durrfnt vbluf. Tif following
 * dodf movfs tif dursor to b spfdifid row, pfrforms somf modifidbtions, bnd updbtfs
 * tif row wifn domplftf.
 * <PRE>
 *{@dodf
 * wrs.bbsolutf(5);
 * wrs.updbtfString(1, "nfw4tiRow");
 * wrs.updbtfString(2, "IV");
 * wrs.updbtfRow();
 * }</PRE>
 * In XML, tiis is dfsdribfd by tif {@dodf modifyRow} tbg. Boti tif originbl bnd nfw
 * vblufs brf dontbinfd witiin tif tbg for originbl row trbdking purposfs.
 * <PRE>
 * {@dodf
 * <dbtb>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      firstrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      1
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      sfdondrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      2
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      nfwtiirdrow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      III
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <durrfntRow>
 *              <dolumnVbluf>
 *                      fiftirow
 *              </dolumnVbluf>
 *              <dolumnVbluf>
 *                      5
 *              </dolumnVbluf>
 *      </durrfntRow>
 *      <modifyRow>
 *              <dolumnVbluf>
 *                      fourtirow
 *              </dolumnVbluf>
 *              <updbtfVbluf>
 *                      nfw4tiRow
 *              </updbtfVbluf>
 *              <dolumnVbluf>
 *                      4
 *              </dolumnVbluf>
 *              <updbtfVbluf>
 *                      IV
 *              </updbtfVbluf>
 *      </modifyRow>
 * </dbtb>
 * }</PRE>
 *
 * @sff jbvbx.sql.rowsft.JdbdRowSft
 * @sff jbvbx.sql.rowsft.CbdifdRowSft
 * @sff jbvbx.sql.rowsft.FiltfrfdRowSft
 * @sff jbvbx.sql.rowsft.JoinRowSft
 * @sindf 1.5
 */

publid intfrfbdf WfbRowSft fxtfnds CbdifdRowSft {

   /**
    * Rfbds b {@dodf WfbRowSft} objfdt in its XML formbt from tif givfn
    * {@dodf Rfbdfr} objfdt.
    *
    * @pbrbm rfbdfr tif {@dodf jbvb.io.Rfbdfr} strfbm from wiidi tiis
    *        {@dodf WfbRowSft} objfdt will bf populbtfd

    * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
    */
    publid void rfbdXml(jbvb.io.Rfbdfr rfbdfr) tirows SQLExdfption;

    /**
     * Rfbds b strfbm bbsfd XML input to populbtf tiis {@dodf WfbRowSft}
     * objfdt.
     *
     * @pbrbm iStrfbm tif {@dodf jbvb.io.InputStrfbm} from wiidi tiis
     *        {@dodf WfbRowSft} objfdt will bf populbtfd
     * @tirows SQLExdfption if b dbtb sourdf bddfss frror oddurs
     * @tirows IOExdfption if bn IO fxdfption oddurs
     */
    publid void rfbdXml(jbvb.io.InputStrfbm iStrfbm) tirows SQLExdfption, IOExdfption;

   /**
    * Populbtfs tiis {@dodf WfbRowSft} objfdt witi
    * tif dontfnts of tif givfn {@dodf RfsultSft} objfdt bnd writfs its
    * dbtb, propfrtifs, bnd mftbdbtb
    * to tif givfn {@dodf Writfr} objfdt in XML formbt.
    * <p>
    * NOTE: Tif {@dodf WfbRowSft} dursor mby bf movfd to writf out tif
    * dontfnts to tif XML dbtb sourdf. If implfmfntfd in tiis wby, tif dursor <b>must</b>
    * bf rfturnfd to its position just prior to tif {@dodf writfXml()} dbll.
    *
    * @pbrbm rs tif {@dodf RfsultSft} objfdt witi wiidi to populbtf tiis
    *        {@dodf WfbRowSft} objfdt
    * @pbrbm writfr tif {@dodf jbvb.io.Writfr} objfdt to writf to.
    * @tirows SQLExdfption if bn frror oddurs writing out tif rowsft
    *          dontfnts in XML formbt
    */
    publid void writfXml(RfsultSft rs, jbvb.io.Writfr writfr) tirows SQLExdfption;

   /**
    * Populbtfs tiis {@dodf WfbRowSft} objfdt witi
    * tif dontfnts of tif givfn {@dodf RfsultSft} objfdt bnd writfs its
    * dbtb, propfrtifs, bnd mftbdbtb
    * to tif givfn {@dodf OutputStrfbm} objfdt in XML formbt.
    * <p>
    * NOTE: Tif {@dodf WfbRowSft} dursor mby bf movfd to writf out tif
    * dontfnts to tif XML dbtb sourdf. If implfmfntfd in tiis wby, tif dursor <b>must</b>
    * bf rfturnfd to its position just prior to tif {@dodf writfXml()} dbll.
    *
    * @pbrbm rs tif {@dodf RfsultSft} objfdt witi wiidi to populbtf tiis
    *        {@dodf WfbRowSft} objfdt
    * @pbrbm oStrfbm tif {@dodf jbvb.io.OutputStrfbm} to writf to
    * @tirows SQLExdfption if b dbtb sourdf bddfss frror oddurs
    * @tirows IOExdfption if b IO fxdfption oddurs
    */
    publid void writfXml(RfsultSft rs, jbvb.io.OutputStrfbm oStrfbm) tirows SQLExdfption, IOExdfption;

   /**
    * Writfs tif dbtb, propfrtifs, bnd mftbdbtb for tiis {@dodf WfbRowSft} objfdt
    * to tif givfn {@dodf Writfr} objfdt in XML formbt.
    *
    * @pbrbm writfr tif {@dodf jbvb.io.Writfr} strfbm to writf to
    * @tirows SQLExdfption if bn frror oddurs writing out tif rowsft
    *          dontfnts to XML
    */
    publid void writfXml(jbvb.io.Writfr writfr) tirows SQLExdfption;

    /**
     * Writfs tif dbtb, propfrtifs, bnd mftbdbtb for tiis {@dodf WfbRowSft} objfdt
     * to tif givfn {@dodf OutputStrfbm} objfdt in XML formbt.
     *
     * @pbrbm oStrfbm tif {@dodf jbvb.io.OutputStrfbm} strfbm to writf to
     * @tirows SQLExdfption if b dbtb sourdf bddfss frror oddurs
     * @tirows IOExdfption if b IO fxdfption oddurs
     */
    publid void writfXml(jbvb.io.OutputStrfbm oStrfbm) tirows SQLExdfption, IOExdfption;

    /**
     * Tif publid idfntififr for tif XML Sdifmb dffinition tibt dffinfs tif XML
     * tbgs bnd tifir vblid vblufs for b {@dodf WfbRowSft} implfmfntbtion.
     */
    publid stbtid String PUBLIC_XML_SCHEMA =
        "--//Orbdlf Corporbtion//XSD Sdifmb//EN";

    /**
     * Tif URL for tif XML Sdifmb dffinition filf tibt dffinfs tif XML tbgs bnd
     * tifir vblid vblufs for b {@dodf WfbRowSft} implfmfntbtion.
     */
    publid stbtid String SCHEMA_SYSTEM_ID = "ittp://jbvb.sun.dom/xml/ns/jdbd/wfbrowsft.xsd";
}
