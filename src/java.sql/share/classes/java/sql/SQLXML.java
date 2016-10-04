/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sql;

import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.Rfbdfr;
import jbvb.io.Writfr;

import jbvbx.xml.trbnsform.Rfsult;
import jbvbx.xml.trbnsform.Sourdf;

/**
 * Tif mbpping in tif JbvbTM progrbmming lbngubgf for tif SQL XML typf.
 * XML is b built-in typf tibt storfs bn XML vbluf
 * bs b dolumn vbluf in b row of b dbtbbbsf tbblf.
 * By dffbult drivfrs implfmfnt bn SQLXML objfdt bs
 * b logidbl pointfr to tif XML dbtb
 * rbtifr tibn tif dbtb itsflf.
 * An SQLXML objfdt is vblid for tif durbtion of tif trbnsbdtion in wiidi it wbs drfbtfd.
 * <p>
 * Tif SQLXML intfrfbdf providfs mftiods for bddfssing tif XML vbluf
 * bs b String, b Rfbdfr or Writfr, or bs b Strfbm.  Tif XML vbluf
 * mby blso bf bddfssfd tirougi b Sourdf or sft bs b Rfsult, wiidi
 * brf usfd witi XML Pbrsfr APIs sudi bs DOM, SAX, bnd StAX, bs
 * wfll bs witi XSLT trbnsforms bnd XPbti fvblubtions.
 * <p>
 * Mftiods in tif intfrfbdfs RfsultSft, CbllbblfStbtfmfnt, bnd PrfpbrfdStbtfmfnt,
 * sudi bs gftSQLXML bllow b progrbmmfr to bddfss bn XML vbluf.
 * In bddition, tiis intfrfbdf ibs mftiods for updbting bn XML vbluf.
 * <p>
 * Tif XML vbluf of tif SQLXML instbndf mby bf obtbinfd bs b BinbryStrfbm using
 * <prf>
 *   SQLXML sqlxml = rfsultSft.gftSQLXML(dolumn);
 *   InputStrfbm binbryStrfbm = sqlxml.gftBinbryStrfbm();
 * </prf>
 * For fxbmplf, to pbrsf bn XML vbluf witi b DOM pbrsfr:
 * <prf>
 *   DodumfntBuildfr pbrsfr = DodumfntBuildfrFbdtory.nfwInstbndf().nfwDodumfntBuildfr();
 *   Dodumfnt rfsult = pbrsfr.pbrsf(binbryStrfbm);
 * </prf>
 * or to pbrsf bn XML vbluf witi b SAX pbrsfr to your ibndlfr:
 * <prf>
 *   SAXPbrsfr pbrsfr = SAXPbrsfrFbdtory.nfwInstbndf().nfwSAXPbrsfr();
 *   pbrsfr.pbrsf(binbryStrfbm, myHbndlfr);
 * </prf>
 * or to pbrsf bn XML vbluf witi b StAX pbrsfr:
 * <prf>
 *   XMLInputFbdtory fbdtory = XMLInputFbdtory.nfwInstbndf();
 *   XMLStrfbmRfbdfr strfbmRfbdfr = fbdtory.drfbtfXMLStrfbmRfbdfr(binbryStrfbm);
 * </prf>
 * <p>
 * Bfdbusf dbtbbbsfs mby usf bn optimizfd rfprfsfntbtion for tif XML,
 * bddfssing tif vbluf tirougi gftSourdf() bnd
 * sftRfsult() dbn lfbd to improvfd prodfssing pfrformbndf
 * witiout sfriblizing to b strfbm rfprfsfntbtion bnd pbrsing tif XML.
 * <p>
 * For fxbmplf, to obtbin b DOM Dodumfnt Nodf:
 * <prf>
 *   DOMSourdf domSourdf = sqlxml.gftSourdf(DOMSourdf.dlbss);
 *   Dodumfnt dodumfnt = (Dodumfnt) domSourdf.gftNodf();
 * </prf>
 * or to sft tif vbluf to b DOM Dodumfnt Nodf to myNodf:
 * <prf>
 *   DOMRfsult domRfsult = sqlxml.sftRfsult(DOMRfsult.dlbss);
 *   domRfsult.sftNodf(myNodf);
 * </prf>
 * or, to sfnd SAX fvfnts to your ibndlfr:
 * <prf>
 *   SAXSourdf sbxSourdf = sqlxml.gftSourdf(SAXSourdf.dlbss);
 *   XMLRfbdfr xmlRfbdfr = sbxSourdf.gftXMLRfbdfr();
 *   xmlRfbdfr.sftContfntHbndlfr(myHbndlfr);
 *   xmlRfbdfr.pbrsf(sbxSourdf.gftInputSourdf());
 * </prf>
 * or, to sft tif rfsult vbluf from SAX fvfnts:
 * <prf>
 *   SAXRfsult sbxRfsult = sqlxml.sftRfsult(SAXRfsult.dlbss);
 *   ContfntHbndlfr dontfntHbndlfr = sbxRfsult.gftHbndlfr();
 *   dontfntHbndlfr.stbrtDodumfnt();
 *   // sft tif XML flfmfnts bnd bttributfs into tif rfsult
 *   dontfntHbndlfr.fndDodumfnt();
 * </prf>
 * or, to obtbin StAX fvfnts:
 * <prf>
 *   StAXSourdf stbxSourdf = sqlxml.gftSourdf(StAXSourdf.dlbss);
 *   XMLStrfbmRfbdfr strfbmRfbdfr = stbxSourdf.gftXMLStrfbmRfbdfr();
 * </prf>
 * or, to sft tif rfsult vbluf from StAX fvfnts:
 * <prf>
 *   StAXRfsult stbxRfsult = sqlxml.sftRfsult(StAXRfsult.dlbss);
 *   XMLStrfbmWritfr strfbmWritfr = stbxRfsult.gftXMLStrfbmWritfr();
 * </prf>
 * or, to pfrform XSLT trbnsformbtions on tif XML vbluf using tif XSLT in xsltFilf
 * output to filf rfsultFilf:
 * <prf>
 *   Filf xsltFilf = nfw Filf("b.xslt");
 *   Filf myFilf = nfw Filf("rfsult.xml");
 *   Trbnsformfr xslt = TrbnsformfrFbdtory.nfwInstbndf().nfwTrbnsformfr(nfw StrfbmSourdf(xsltFilf));
 *   Sourdf sourdf = sqlxml.gftSourdf(null);
 *   Rfsult rfsult = nfw StrfbmRfsult(myFilf);
 *   xslt.trbnsform(sourdf, rfsult);
 * </prf>
 * or, to fvblubtf bn XPbti fxprfssion on tif XML vbluf:
 * <prf>
 *   XPbti xpbti = XPbtiFbdtory.nfwInstbndf().nfwXPbti();
 *   DOMSourdf domSourdf = sqlxml.gftSourdf(DOMSourdf.dlbss);
 *   Dodumfnt dodumfnt = (Dodumfnt) domSourdf.gftNodf();
 *   String fxprfssion = "/foo/@bbr";
 *   String bbrVbluf = xpbti.fvblubtf(fxprfssion, dodumfnt);
 * </prf>
 * To sft tif XML vbluf to bf tif rfsult of bn XSLT trbnsform:
 * <prf>
 *   Filf sourdfFilf = nfw Filf("sourdf.xml");
 *   Trbnsformfr xslt = TrbnsformfrFbdtory.nfwInstbndf().nfwTrbnsformfr(nfw StrfbmSourdf(xsltFilf));
 *   Sourdf strfbmSourdf = nfw StrfbmSourdf(sourdfFilf);
 *   Rfsult rfsult = sqlxml.sftRfsult(null);
 *   xslt.trbnsform(strfbmSourdf, rfsult);
 * </prf>
 * Any Sourdf dbn bf trbnsformfd to b Rfsult using tif idfntity trbnsform
 * spfdififd by dblling nfwTrbnsformfr():
 * <prf>
 *   Trbnsformfr idfntity = TrbnsformfrFbdtory.nfwInstbndf().nfwTrbnsformfr();
 *   Sourdf sourdf = sqlxml.gftSourdf(null);
 *   Filf myFilf = nfw Filf("rfsult.xml");
 *   Rfsult rfsult = nfw StrfbmRfsult(myFilf);
 *   idfntity.trbnsform(sourdf, rfsult);
 * </prf>
 * To writf tif dontfnts of b Sourdf to stbndbrd output:
 * <prf>
 *   Trbnsformfr idfntity = TrbnsformfrFbdtory.nfwInstbndf().nfwTrbnsformfr();
 *   Sourdf sourdf = sqlxml.gftSourdf(null);
 *   Rfsult rfsult = nfw StrfbmRfsult(Systfm.out);
 *   idfntity.trbnsform(sourdf, rfsult);
 * </prf>
 * To drfbtf b DOMSourdf from b DOMRfsult:
 * <prf>
 *    DOMSourdf domSourdf = nfw DOMSourdf(domRfsult.gftNodf());
 * </prf>
 * <p>
 * Indomplftf or invblid XML vblufs mby dbusf bn SQLExdfption wifn
 * sft or tif fxdfption mby oddur wifn fxfdutf() oddurs.  All strfbms
 * must bf dlosfd bfforf fxfdutf() oddurs or bn SQLExdfption will bf tirown.
 * <p>
 * Rfbding bnd writing XML vblufs to or from bn SQLXML objfdt dbn ibppfn bt most ondf.
 * Tif dondfptubl stbtfs of rfbdbblf bnd not rfbdbblf dftfrminf if onf
 * of tif rfbding APIs will rfturn b vbluf or tirow bn fxdfption.
 * Tif dondfptubl stbtfs of writbblf bnd not writbblf dftfrminf if onf
 * of tif writing APIs will sft b vbluf or tirow bn fxdfption.
 * <p>
 * Tif stbtf movfs from rfbdbblf to not rfbdbblf ondf frff() or bny of tif
 * rfbding APIs brf dbllfd: gftBinbryStrfbm(), gftCibrbdtfrStrfbm(), gftSourdf(), bnd gftString().
 * Implfmfntbtions mby blso dibngf tif stbtf to not writbblf wifn tiis oddurs.
 * <p>
 * Tif stbtf movfs from writbblf to not writfbblf ondf frff() or bny of tif
 * writing APIs brf dbllfd: sftBinbryStrfbm(), sftCibrbdtfrStrfbm(), sftRfsult(), bnd sftString().
 * Implfmfntbtions mby blso dibngf tif stbtf to not rfbdbblf wifn tiis oddurs.
 *
 * <p>
 * All mftiods on tif <dodf>SQLXML</dodf> intfrfbdf must bf fully implfmfntfd if tif
 * JDBC drivfr supports tif dbtb typf.
 *
 * @sff jbvbx.xml.pbrsfrs
 * @sff jbvbx.xml.strfbm
 * @sff jbvbx.xml.trbnsform
 * @sff jbvbx.xml.xpbti
 * @sindf 1.6
 */
publid intfrfbdf SQLXML
{
  /**
   * Tiis mftiod dlosfs tiis objfdt bnd rflfbsfs tif rfsourdfs tibt it ifld.
   * Tif SQL XML objfdt bfdomfs invblid bnd nfitifr rfbdbblf or writfbblf
   * wifn tiis mftiod is dbllfd.
   *
   * Aftfr <dodf>frff</dodf> ibs bffn dbllfd, bny bttfmpt to invokf b
   * mftiod otifr tibn <dodf>frff</dodf> will rfsult in b <dodf>SQLExdfption</dodf>
   * bfing tirown.  If <dodf>frff</dodf> is dbllfd multiplf timfs, tif subsfqufnt
   * dblls to <dodf>frff</dodf> brf trfbtfd bs b no-op.
   * @tirows SQLExdfption if tifrf is bn frror frffing tif XML vbluf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  void frff() tirows SQLExdfption;

  /**
   * Rftrifvfs tif XML vbluf dfsignbtfd by tiis SQLXML instbndf bs b strfbm.
   * Tif bytfs of tif input strfbm brf intfrprftfd bddording to bppfndix F of tif XML 1.0 spfdifidbtion.
   * Tif bfibvior of tiis mftiod is tif sbmf bs RfsultSft.gftBinbryStrfbm()
   * wifn tif dfsignbtfd dolumn of tif RfsultSft ibs b typf jbvb.sql.Typfs of SQLXML.
   * <p>
   * Tif SQL XML objfdt bfdomfs not rfbdbblf wifn tiis mftiod is dbllfd bnd
   * mby blso bfdomf not writbblf dfpfnding on implfmfntbtion.
   *
   * @rfturn b strfbm dontbining tif XML dbtb.
   * @tirows SQLExdfption if tifrf is bn frror prodfssing tif XML vbluf.
   *   An fxdfption is tirown if tif stbtf is not rfbdbblf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  InputStrfbm gftBinbryStrfbm() tirows SQLExdfption;

  /**
   * Rftrifvfs b strfbm tibt dbn bf usfd to writf tif XML vbluf tibt tiis SQLXML instbndf rfprfsfnts.
   * Tif strfbm bfgins bt position 0.
   * Tif bytfs of tif strfbm brf intfrprftfd bddording to bppfndix F of tif XML 1.0 spfdifidbtion
   * Tif bfibvior of tiis mftiod is tif sbmf bs RfsultSft.updbtfBinbryStrfbm()
   * wifn tif dfsignbtfd dolumn of tif RfsultSft ibs b typf jbvb.sql.Typfs of SQLXML.
   * <p>
   * Tif SQL XML objfdt bfdomfs not writfbblf wifn tiis mftiod is dbllfd bnd
   * mby blso bfdomf not rfbdbblf dfpfnding on implfmfntbtion.
   *
   * @rfturn b strfbm to wiidi dbtb dbn bf writtfn.
   * @tirows SQLExdfption if tifrf is bn frror prodfssing tif XML vbluf.
   *   An fxdfption is tirown if tif stbtf is not writbblf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  OutputStrfbm sftBinbryStrfbm() tirows SQLExdfption;

  /**
   * Rftrifvfs tif XML vbluf dfsignbtfd by tiis SQLXML instbndf bs b jbvb.io.Rfbdfr objfdt.
   * Tif formbt of tiis strfbm is dffinfd by org.xml.sbx.InputSourdf,
   * wifrf tif dibrbdtfrs in tif strfbm rfprfsfnt tif unidodf dodf points for
   * XML bddording to sfdtion 2 bnd bppfndix B of tif XML 1.0 spfdifidbtion.
   * Altiougi bn fndoding dfdlbrbtion otifr tibn unidodf mby bf prfsfnt,
   * tif fndoding of tif strfbm is unidodf.
   * Tif bfibvior of tiis mftiod is tif sbmf bs RfsultSft.gftCibrbdtfrStrfbm()
   * wifn tif dfsignbtfd dolumn of tif RfsultSft ibs b typf jbvb.sql.Typfs of SQLXML.
   * <p>
   * Tif SQL XML objfdt bfdomfs not rfbdbblf wifn tiis mftiod is dbllfd bnd
   * mby blso bfdomf not writbblf dfpfnding on implfmfntbtion.
   *
   * @rfturn b strfbm dontbining tif XML dbtb.
   * @tirows SQLExdfption if tifrf is bn frror prodfssing tif XML vbluf.
   *   Tif gftCbusf() mftiod of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf,
   *   if tif strfbm dofs not dontbin vblid dibrbdtfrs.
   *   An fxdfption is tirown if tif stbtf is not rfbdbblf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  Rfbdfr gftCibrbdtfrStrfbm() tirows SQLExdfption;

  /**
   * Rftrifvfs b strfbm to bf usfd to writf tif XML vbluf tibt tiis SQLXML instbndf rfprfsfnts.
   * Tif formbt of tiis strfbm is dffinfd by org.xml.sbx.InputSourdf,
   * wifrf tif dibrbdtfrs in tif strfbm rfprfsfnt tif unidodf dodf points for
   * XML bddording to sfdtion 2 bnd bppfndix B of tif XML 1.0 spfdifidbtion.
   * Altiougi bn fndoding dfdlbrbtion otifr tibn unidodf mby bf prfsfnt,
   * tif fndoding of tif strfbm is unidodf.
   * Tif bfibvior of tiis mftiod is tif sbmf bs RfsultSft.updbtfCibrbdtfrStrfbm()
   * wifn tif dfsignbtfd dolumn of tif RfsultSft ibs b typf jbvb.sql.Typfs of SQLXML.
   * <p>
   * Tif SQL XML objfdt bfdomfs not writfbblf wifn tiis mftiod is dbllfd bnd
   * mby blso bfdomf not rfbdbblf dfpfnding on implfmfntbtion.
   *
   * @rfturn b strfbm to wiidi dbtb dbn bf writtfn.
   * @tirows SQLExdfption if tifrf is bn frror prodfssing tif XML vbluf.
   *   Tif gftCbusf() mftiod of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf,
   *   if tif strfbm dofs not dontbin vblid dibrbdtfrs.
   *   An fxdfption is tirown if tif stbtf is not writbblf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  Writfr sftCibrbdtfrStrfbm() tirows SQLExdfption;

  /**
   * Rfturns b string rfprfsfntbtion of tif XML vbluf dfsignbtfd by tiis SQLXML instbndf.
   * Tif formbt of tiis String is dffinfd by org.xml.sbx.InputSourdf,
   * wifrf tif dibrbdtfrs in tif strfbm rfprfsfnt tif unidodf dodf points for
   * XML bddording to sfdtion 2 bnd bppfndix B of tif XML 1.0 spfdifidbtion.
   * Altiougi bn fndoding dfdlbrbtion otifr tibn unidodf mby bf prfsfnt,
   * tif fndoding of tif String is unidodf.
   * Tif bfibvior of tiis mftiod is tif sbmf bs RfsultSft.gftString()
   * wifn tif dfsignbtfd dolumn of tif RfsultSft ibs b typf jbvb.sql.Typfs of SQLXML.
   * <p>
   * Tif SQL XML objfdt bfdomfs not rfbdbblf wifn tiis mftiod is dbllfd bnd
   * mby blso bfdomf not writbblf dfpfnding on implfmfntbtion.
   *
   * @rfturn b string rfprfsfntbtion of tif XML vbluf dfsignbtfd by tiis SQLXML instbndf.
   * @tirows SQLExdfption if tifrf is bn frror prodfssing tif XML vbluf.
   *   Tif gftCbusf() mftiod of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf,
   *   if tif strfbm dofs not dontbin vblid dibrbdtfrs.
   *   An fxdfption is tirown if tif stbtf is not rfbdbblf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  String gftString() tirows SQLExdfption;

  /**
   * Sfts tif XML vbluf dfsignbtfd by tiis SQLXML instbndf to tif givfn String rfprfsfntbtion.
   * Tif formbt of tiis String is dffinfd by org.xml.sbx.InputSourdf,
   * wifrf tif dibrbdtfrs in tif strfbm rfprfsfnt tif unidodf dodf points for
   * XML bddording to sfdtion 2 bnd bppfndix B of tif XML 1.0 spfdifidbtion.
   * Altiougi bn fndoding dfdlbrbtion otifr tibn unidodf mby bf prfsfnt,
   * tif fndoding of tif String is unidodf.
   * Tif bfibvior of tiis mftiod is tif sbmf bs RfsultSft.updbtfString()
   * wifn tif dfsignbtfd dolumn of tif RfsultSft ibs b typf jbvb.sql.Typfs of SQLXML.
   * <p>
   * Tif SQL XML objfdt bfdomfs not writfbblf wifn tiis mftiod is dbllfd bnd
   * mby blso bfdomf not rfbdbblf dfpfnding on implfmfntbtion.
   *
   * @pbrbm vbluf tif XML vbluf
   * @tirows SQLExdfption if tifrf is bn frror prodfssing tif XML vbluf.
   *   Tif gftCbusf() mftiod of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf,
   *   if tif strfbm dofs not dontbin vblid dibrbdtfrs.
   *   An fxdfption is tirown if tif stbtf is not writbblf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  void sftString(String vbluf) tirows SQLExdfption;

  /**
   * Rfturns b Sourdf for rfbding tif XML vbluf dfsignbtfd by tiis SQLXML instbndf.
   * Sourdfs brf usfd bs inputs to XML pbrsfrs bnd XSLT trbnsformfrs.
   * <p>
   * Sourdfs for XML pbrsfrs will ibvf nbmfspbdf prodfssing on by dffbult.
   * Tif systfmID of tif Sourdf is implfmfntbtion dfpfndfnt.
   * <p>
   * Tif SQL XML objfdt bfdomfs not rfbdbblf wifn tiis mftiod is dbllfd bnd
   * mby blso bfdomf not writbblf dfpfnding on implfmfntbtion.
   * <p>
   * Notf tibt SAX is b dbllbbdk brdiitfdturf, so b rfturnfd
   * SAXSourdf siould tifn bf sft witi b dontfnt ibndlfr tibt will
   * rfdfivf tif SAX fvfnts from pbrsing.  Tif dontfnt ibndlfr
   * will rfdfivf dbllbbdks bbsfd on tif dontfnts of tif XML.
   * <prf>
   *   SAXSourdf sbxSourdf = sqlxml.gftSourdf(SAXSourdf.dlbss);
   *   XMLRfbdfr xmlRfbdfr = sbxSourdf.gftXMLRfbdfr();
   *   xmlRfbdfr.sftContfntHbndlfr(myHbndlfr);
   *   xmlRfbdfr.pbrsf(sbxSourdf.gftInputSourdf());
   * </prf>
   *
   * @pbrbm <T> tif typf of tif dlbss modflfd by tiis Clbss objfdt
   * @pbrbm sourdfClbss Tif dlbss of tif sourdf, or null.
   * If tif dlbss is null, b vfndor spfdifid Sourdf implfmfntbtion will bf rfturnfd.
   * Tif following dlbssfs brf supportfd bt b minimum:
   * <prf>
   *   jbvbx.xml.trbnsform.dom.DOMSourdf - rfturns b DOMSourdf
   *   jbvbx.xml.trbnsform.sbx.SAXSourdf - rfturns b SAXSourdf
   *   jbvbx.xml.trbnsform.stbx.StAXSourdf - rfturns b StAXSourdf
   *   jbvbx.xml.trbnsform.strfbm.StrfbmSourdf - rfturns b StrfbmSourdf
   * </prf>
   * @rfturn b Sourdf for rfbding tif XML vbluf.
   * @tirows SQLExdfption if tifrf is bn frror prodfssing tif XML vbluf
   *   or if tiis ffbturf is not supportfd.
   *   Tif gftCbusf() mftiod of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf,
   *   if bn XML pbrsfr fxdfption oddurs.
   *   An fxdfption is tirown if tif stbtf is not rfbdbblf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  <T fxtfnds Sourdf> T gftSourdf(Clbss<T> sourdfClbss) tirows SQLExdfption;

  /**
   * Rfturns b Rfsult for sftting tif XML vbluf dfsignbtfd by tiis SQLXML instbndf.
   * <p>
   * Tif systfmID of tif Rfsult is implfmfntbtion dfpfndfnt.
   * <p>
   * Tif SQL XML objfdt bfdomfs not writfbblf wifn tiis mftiod is dbllfd bnd
   * mby blso bfdomf not rfbdbblf dfpfnding on implfmfntbtion.
   * <p>
   * Notf tibt SAX is b dbllbbdk brdiitfdturf bnd tif rfturnfd
   * SAXRfsult ibs b dontfnt ibndlfr bssignfd tibt will rfdfivf tif
   * SAX fvfnts bbsfd on tif dontfnts of tif XML.  Cbll tif dontfnt
   * ibndlfr witi tif dontfnts of tif XML dodumfnt to bssign tif vblufs.
   * <prf>
   *   SAXRfsult sbxRfsult = sqlxml.sftRfsult(SAXRfsult.dlbss);
   *   ContfntHbndlfr dontfntHbndlfr = sbxRfsult.gftXMLRfbdfr().gftContfntHbndlfr();
   *   dontfntHbndlfr.stbrtDodumfnt();
   *   // sft tif XML flfmfnts bnd bttributfs into tif rfsult
   *   dontfntHbndlfr.fndDodumfnt();
   * </prf>
   *
   * @pbrbm <T> tif typf of tif dlbss modflfd by tiis Clbss objfdt
   * @pbrbm rfsultClbss Tif dlbss of tif rfsult, or null.
   * If rfsultClbss is null, b vfndor spfdifid Rfsult implfmfntbtion will bf rfturnfd.
   * Tif following dlbssfs brf supportfd bt b minimum:
   * <prf>
   *   jbvbx.xml.trbnsform.dom.DOMRfsult - rfturns b DOMRfsult
   *   jbvbx.xml.trbnsform.sbx.SAXRfsult - rfturns b SAXRfsult
   *   jbvbx.xml.trbnsform.stbx.StAXRfsult - rfturns b StAXRfsult
   *   jbvbx.xml.trbnsform.strfbm.StrfbmRfsult - rfturns b StrfbmRfsult
   * </prf>
   * @rfturn Rfturns b Rfsult for sftting tif XML vbluf.
   * @tirows SQLExdfption if tifrf is bn frror prodfssing tif XML vbluf
   *   or if tiis ffbturf is not supportfd.
   *   Tif gftCbusf() mftiod of tif fxdfption mby providf b morf dftbilfd fxdfption, for fxbmplf,
   *   if bn XML pbrsfr fxdfption oddurs.
   *   An fxdfption is tirown if tif stbtf is not writbblf.
   * @fxdfption SQLFfbturfNotSupportfdExdfption if tif JDBC drivfr dofs not support
   * tiis mftiod
   * @sindf 1.6
   */
  <T fxtfnds Rfsult> T sftRfsult(Clbss<T> rfsultClbss) tirows SQLExdfption;

}
