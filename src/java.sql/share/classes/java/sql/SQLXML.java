/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.sql;

import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.Rebder;
import jbvb.io.Writer;

import jbvbx.xml.trbnsform.Result;
import jbvbx.xml.trbnsform.Source;

/**
 * The mbpping in the JbvbTM progrbmming lbngubge for the SQL XML type.
 * XML is b built-in type thbt stores bn XML vblue
 * bs b column vblue in b row of b dbtbbbse tbble.
 * By defbult drivers implement bn SQLXML object bs
 * b logicbl pointer to the XML dbtb
 * rbther thbn the dbtb itself.
 * An SQLXML object is vblid for the durbtion of the trbnsbction in which it wbs crebted.
 * <p>
 * The SQLXML interfbce provides methods for bccessing the XML vblue
 * bs b String, b Rebder or Writer, or bs b Strebm.  The XML vblue
 * mby blso be bccessed through b Source or set bs b Result, which
 * bre used with XML Pbrser APIs such bs DOM, SAX, bnd StAX, bs
 * well bs with XSLT trbnsforms bnd XPbth evblubtions.
 * <p>
 * Methods in the interfbces ResultSet, CbllbbleStbtement, bnd PrepbredStbtement,
 * such bs getSQLXML bllow b progrbmmer to bccess bn XML vblue.
 * In bddition, this interfbce hbs methods for updbting bn XML vblue.
 * <p>
 * The XML vblue of the SQLXML instbnce mby be obtbined bs b BinbryStrebm using
 * <pre>
 *   SQLXML sqlxml = resultSet.getSQLXML(column);
 *   InputStrebm binbryStrebm = sqlxml.getBinbryStrebm();
 * </pre>
 * For exbmple, to pbrse bn XML vblue with b DOM pbrser:
 * <pre>
 *   DocumentBuilder pbrser = DocumentBuilderFbctory.newInstbnce().newDocumentBuilder();
 *   Document result = pbrser.pbrse(binbryStrebm);
 * </pre>
 * or to pbrse bn XML vblue with b SAX pbrser to your hbndler:
 * <pre>
 *   SAXPbrser pbrser = SAXPbrserFbctory.newInstbnce().newSAXPbrser();
 *   pbrser.pbrse(binbryStrebm, myHbndler);
 * </pre>
 * or to pbrse bn XML vblue with b StAX pbrser:
 * <pre>
 *   XMLInputFbctory fbctory = XMLInputFbctory.newInstbnce();
 *   XMLStrebmRebder strebmRebder = fbctory.crebteXMLStrebmRebder(binbryStrebm);
 * </pre>
 * <p>
 * Becbuse dbtbbbses mby use bn optimized representbtion for the XML,
 * bccessing the vblue through getSource() bnd
 * setResult() cbn lebd to improved processing performbnce
 * without seriblizing to b strebm representbtion bnd pbrsing the XML.
 * <p>
 * For exbmple, to obtbin b DOM Document Node:
 * <pre>
 *   DOMSource domSource = sqlxml.getSource(DOMSource.clbss);
 *   Document document = (Document) domSource.getNode();
 * </pre>
 * or to set the vblue to b DOM Document Node to myNode:
 * <pre>
 *   DOMResult domResult = sqlxml.setResult(DOMResult.clbss);
 *   domResult.setNode(myNode);
 * </pre>
 * or, to send SAX events to your hbndler:
 * <pre>
 *   SAXSource sbxSource = sqlxml.getSource(SAXSource.clbss);
 *   XMLRebder xmlRebder = sbxSource.getXMLRebder();
 *   xmlRebder.setContentHbndler(myHbndler);
 *   xmlRebder.pbrse(sbxSource.getInputSource());
 * </pre>
 * or, to set the result vblue from SAX events:
 * <pre>
 *   SAXResult sbxResult = sqlxml.setResult(SAXResult.clbss);
 *   ContentHbndler contentHbndler = sbxResult.getHbndler();
 *   contentHbndler.stbrtDocument();
 *   // set the XML elements bnd bttributes into the result
 *   contentHbndler.endDocument();
 * </pre>
 * or, to obtbin StAX events:
 * <pre>
 *   StAXSource stbxSource = sqlxml.getSource(StAXSource.clbss);
 *   XMLStrebmRebder strebmRebder = stbxSource.getXMLStrebmRebder();
 * </pre>
 * or, to set the result vblue from StAX events:
 * <pre>
 *   StAXResult stbxResult = sqlxml.setResult(StAXResult.clbss);
 *   XMLStrebmWriter strebmWriter = stbxResult.getXMLStrebmWriter();
 * </pre>
 * or, to perform XSLT trbnsformbtions on the XML vblue using the XSLT in xsltFile
 * output to file resultFile:
 * <pre>
 *   File xsltFile = new File("b.xslt");
 *   File myFile = new File("result.xml");
 *   Trbnsformer xslt = TrbnsformerFbctory.newInstbnce().newTrbnsformer(new StrebmSource(xsltFile));
 *   Source source = sqlxml.getSource(null);
 *   Result result = new StrebmResult(myFile);
 *   xslt.trbnsform(source, result);
 * </pre>
 * or, to evblubte bn XPbth expression on the XML vblue:
 * <pre>
 *   XPbth xpbth = XPbthFbctory.newInstbnce().newXPbth();
 *   DOMSource domSource = sqlxml.getSource(DOMSource.clbss);
 *   Document document = (Document) domSource.getNode();
 *   String expression = "/foo/@bbr";
 *   String bbrVblue = xpbth.evblubte(expression, document);
 * </pre>
 * To set the XML vblue to be the result of bn XSLT trbnsform:
 * <pre>
 *   File sourceFile = new File("source.xml");
 *   Trbnsformer xslt = TrbnsformerFbctory.newInstbnce().newTrbnsformer(new StrebmSource(xsltFile));
 *   Source strebmSource = new StrebmSource(sourceFile);
 *   Result result = sqlxml.setResult(null);
 *   xslt.trbnsform(strebmSource, result);
 * </pre>
 * Any Source cbn be trbnsformed to b Result using the identity trbnsform
 * specified by cblling newTrbnsformer():
 * <pre>
 *   Trbnsformer identity = TrbnsformerFbctory.newInstbnce().newTrbnsformer();
 *   Source source = sqlxml.getSource(null);
 *   File myFile = new File("result.xml");
 *   Result result = new StrebmResult(myFile);
 *   identity.trbnsform(source, result);
 * </pre>
 * To write the contents of b Source to stbndbrd output:
 * <pre>
 *   Trbnsformer identity = TrbnsformerFbctory.newInstbnce().newTrbnsformer();
 *   Source source = sqlxml.getSource(null);
 *   Result result = new StrebmResult(System.out);
 *   identity.trbnsform(source, result);
 * </pre>
 * To crebte b DOMSource from b DOMResult:
 * <pre>
 *    DOMSource domSource = new DOMSource(domResult.getNode());
 * </pre>
 * <p>
 * Incomplete or invblid XML vblues mby cbuse bn SQLException when
 * set or the exception mby occur when execute() occurs.  All strebms
 * must be closed before execute() occurs or bn SQLException will be thrown.
 * <p>
 * Rebding bnd writing XML vblues to or from bn SQLXML object cbn hbppen bt most once.
 * The conceptubl stbtes of rebdbble bnd not rebdbble determine if one
 * of the rebding APIs will return b vblue or throw bn exception.
 * The conceptubl stbtes of writbble bnd not writbble determine if one
 * of the writing APIs will set b vblue or throw bn exception.
 * <p>
 * The stbte moves from rebdbble to not rebdbble once free() or bny of the
 * rebding APIs bre cblled: getBinbryStrebm(), getChbrbcterStrebm(), getSource(), bnd getString().
 * Implementbtions mby blso chbnge the stbte to not writbble when this occurs.
 * <p>
 * The stbte moves from writbble to not writebble once free() or bny of the
 * writing APIs bre cblled: setBinbryStrebm(), setChbrbcterStrebm(), setResult(), bnd setString().
 * Implementbtions mby blso chbnge the stbte to not rebdbble when this occurs.
 *
 * <p>
 * All methods on the <code>SQLXML</code> interfbce must be fully implemented if the
 * JDBC driver supports the dbtb type.
 *
 * @see jbvbx.xml.pbrsers
 * @see jbvbx.xml.strebm
 * @see jbvbx.xml.trbnsform
 * @see jbvbx.xml.xpbth
 * @since 1.6
 */
public interfbce SQLXML
{
  /**
   * This method closes this object bnd relebses the resources thbt it held.
   * The SQL XML object becomes invblid bnd neither rebdbble or writebble
   * when this method is cblled.
   *
   * After <code>free</code> hbs been cblled, bny bttempt to invoke b
   * method other thbn <code>free</code> will result in b <code>SQLException</code>
   * being thrown.  If <code>free</code> is cblled multiple times, the subsequent
   * cblls to <code>free</code> bre trebted bs b no-op.
   * @throws SQLException if there is bn error freeing the XML vblue.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  void free() throws SQLException;

  /**
   * Retrieves the XML vblue designbted by this SQLXML instbnce bs b strebm.
   * The bytes of the input strebm bre interpreted bccording to bppendix F of the XML 1.0 specificbtion.
   * The behbvior of this method is the sbme bs ResultSet.getBinbryStrebm()
   * when the designbted column of the ResultSet hbs b type jbvb.sql.Types of SQLXML.
   * <p>
   * The SQL XML object becomes not rebdbble when this method is cblled bnd
   * mby blso become not writbble depending on implementbtion.
   *
   * @return b strebm contbining the XML dbtb.
   * @throws SQLException if there is bn error processing the XML vblue.
   *   An exception is thrown if the stbte is not rebdbble.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  InputStrebm getBinbryStrebm() throws SQLException;

  /**
   * Retrieves b strebm thbt cbn be used to write the XML vblue thbt this SQLXML instbnce represents.
   * The strebm begins bt position 0.
   * The bytes of the strebm bre interpreted bccording to bppendix F of the XML 1.0 specificbtion
   * The behbvior of this method is the sbme bs ResultSet.updbteBinbryStrebm()
   * when the designbted column of the ResultSet hbs b type jbvb.sql.Types of SQLXML.
   * <p>
   * The SQL XML object becomes not writebble when this method is cblled bnd
   * mby blso become not rebdbble depending on implementbtion.
   *
   * @return b strebm to which dbtb cbn be written.
   * @throws SQLException if there is bn error processing the XML vblue.
   *   An exception is thrown if the stbte is not writbble.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  OutputStrebm setBinbryStrebm() throws SQLException;

  /**
   * Retrieves the XML vblue designbted by this SQLXML instbnce bs b jbvb.io.Rebder object.
   * The formbt of this strebm is defined by org.xml.sbx.InputSource,
   * where the chbrbcters in the strebm represent the unicode code points for
   * XML bccording to section 2 bnd bppendix B of the XML 1.0 specificbtion.
   * Although bn encoding declbrbtion other thbn unicode mby be present,
   * the encoding of the strebm is unicode.
   * The behbvior of this method is the sbme bs ResultSet.getChbrbcterStrebm()
   * when the designbted column of the ResultSet hbs b type jbvb.sql.Types of SQLXML.
   * <p>
   * The SQL XML object becomes not rebdbble when this method is cblled bnd
   * mby blso become not writbble depending on implementbtion.
   *
   * @return b strebm contbining the XML dbtb.
   * @throws SQLException if there is bn error processing the XML vblue.
   *   The getCbuse() method of the exception mby provide b more detbiled exception, for exbmple,
   *   if the strebm does not contbin vblid chbrbcters.
   *   An exception is thrown if the stbte is not rebdbble.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  Rebder getChbrbcterStrebm() throws SQLException;

  /**
   * Retrieves b strebm to be used to write the XML vblue thbt this SQLXML instbnce represents.
   * The formbt of this strebm is defined by org.xml.sbx.InputSource,
   * where the chbrbcters in the strebm represent the unicode code points for
   * XML bccording to section 2 bnd bppendix B of the XML 1.0 specificbtion.
   * Although bn encoding declbrbtion other thbn unicode mby be present,
   * the encoding of the strebm is unicode.
   * The behbvior of this method is the sbme bs ResultSet.updbteChbrbcterStrebm()
   * when the designbted column of the ResultSet hbs b type jbvb.sql.Types of SQLXML.
   * <p>
   * The SQL XML object becomes not writebble when this method is cblled bnd
   * mby blso become not rebdbble depending on implementbtion.
   *
   * @return b strebm to which dbtb cbn be written.
   * @throws SQLException if there is bn error processing the XML vblue.
   *   The getCbuse() method of the exception mby provide b more detbiled exception, for exbmple,
   *   if the strebm does not contbin vblid chbrbcters.
   *   An exception is thrown if the stbte is not writbble.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  Writer setChbrbcterStrebm() throws SQLException;

  /**
   * Returns b string representbtion of the XML vblue designbted by this SQLXML instbnce.
   * The formbt of this String is defined by org.xml.sbx.InputSource,
   * where the chbrbcters in the strebm represent the unicode code points for
   * XML bccording to section 2 bnd bppendix B of the XML 1.0 specificbtion.
   * Although bn encoding declbrbtion other thbn unicode mby be present,
   * the encoding of the String is unicode.
   * The behbvior of this method is the sbme bs ResultSet.getString()
   * when the designbted column of the ResultSet hbs b type jbvb.sql.Types of SQLXML.
   * <p>
   * The SQL XML object becomes not rebdbble when this method is cblled bnd
   * mby blso become not writbble depending on implementbtion.
   *
   * @return b string representbtion of the XML vblue designbted by this SQLXML instbnce.
   * @throws SQLException if there is bn error processing the XML vblue.
   *   The getCbuse() method of the exception mby provide b more detbiled exception, for exbmple,
   *   if the strebm does not contbin vblid chbrbcters.
   *   An exception is thrown if the stbte is not rebdbble.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  String getString() throws SQLException;

  /**
   * Sets the XML vblue designbted by this SQLXML instbnce to the given String representbtion.
   * The formbt of this String is defined by org.xml.sbx.InputSource,
   * where the chbrbcters in the strebm represent the unicode code points for
   * XML bccording to section 2 bnd bppendix B of the XML 1.0 specificbtion.
   * Although bn encoding declbrbtion other thbn unicode mby be present,
   * the encoding of the String is unicode.
   * The behbvior of this method is the sbme bs ResultSet.updbteString()
   * when the designbted column of the ResultSet hbs b type jbvb.sql.Types of SQLXML.
   * <p>
   * The SQL XML object becomes not writebble when this method is cblled bnd
   * mby blso become not rebdbble depending on implementbtion.
   *
   * @pbrbm vblue the XML vblue
   * @throws SQLException if there is bn error processing the XML vblue.
   *   The getCbuse() method of the exception mby provide b more detbiled exception, for exbmple,
   *   if the strebm does not contbin vblid chbrbcters.
   *   An exception is thrown if the stbte is not writbble.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  void setString(String vblue) throws SQLException;

  /**
   * Returns b Source for rebding the XML vblue designbted by this SQLXML instbnce.
   * Sources bre used bs inputs to XML pbrsers bnd XSLT trbnsformers.
   * <p>
   * Sources for XML pbrsers will hbve nbmespbce processing on by defbult.
   * The systemID of the Source is implementbtion dependent.
   * <p>
   * The SQL XML object becomes not rebdbble when this method is cblled bnd
   * mby blso become not writbble depending on implementbtion.
   * <p>
   * Note thbt SAX is b cbllbbck brchitecture, so b returned
   * SAXSource should then be set with b content hbndler thbt will
   * receive the SAX events from pbrsing.  The content hbndler
   * will receive cbllbbcks bbsed on the contents of the XML.
   * <pre>
   *   SAXSource sbxSource = sqlxml.getSource(SAXSource.clbss);
   *   XMLRebder xmlRebder = sbxSource.getXMLRebder();
   *   xmlRebder.setContentHbndler(myHbndler);
   *   xmlRebder.pbrse(sbxSource.getInputSource());
   * </pre>
   *
   * @pbrbm <T> the type of the clbss modeled by this Clbss object
   * @pbrbm sourceClbss The clbss of the source, or null.
   * If the clbss is null, b vendor specific Source implementbtion will be returned.
   * The following clbsses bre supported bt b minimum:
   * <pre>
   *   jbvbx.xml.trbnsform.dom.DOMSource - returns b DOMSource
   *   jbvbx.xml.trbnsform.sbx.SAXSource - returns b SAXSource
   *   jbvbx.xml.trbnsform.stbx.StAXSource - returns b StAXSource
   *   jbvbx.xml.trbnsform.strebm.StrebmSource - returns b StrebmSource
   * </pre>
   * @return b Source for rebding the XML vblue.
   * @throws SQLException if there is bn error processing the XML vblue
   *   or if this febture is not supported.
   *   The getCbuse() method of the exception mby provide b more detbiled exception, for exbmple,
   *   if bn XML pbrser exception occurs.
   *   An exception is thrown if the stbte is not rebdbble.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  <T extends Source> T getSource(Clbss<T> sourceClbss) throws SQLException;

  /**
   * Returns b Result for setting the XML vblue designbted by this SQLXML instbnce.
   * <p>
   * The systemID of the Result is implementbtion dependent.
   * <p>
   * The SQL XML object becomes not writebble when this method is cblled bnd
   * mby blso become not rebdbble depending on implementbtion.
   * <p>
   * Note thbt SAX is b cbllbbck brchitecture bnd the returned
   * SAXResult hbs b content hbndler bssigned thbt will receive the
   * SAX events bbsed on the contents of the XML.  Cbll the content
   * hbndler with the contents of the XML document to bssign the vblues.
   * <pre>
   *   SAXResult sbxResult = sqlxml.setResult(SAXResult.clbss);
   *   ContentHbndler contentHbndler = sbxResult.getXMLRebder().getContentHbndler();
   *   contentHbndler.stbrtDocument();
   *   // set the XML elements bnd bttributes into the result
   *   contentHbndler.endDocument();
   * </pre>
   *
   * @pbrbm <T> the type of the clbss modeled by this Clbss object
   * @pbrbm resultClbss The clbss of the result, or null.
   * If resultClbss is null, b vendor specific Result implementbtion will be returned.
   * The following clbsses bre supported bt b minimum:
   * <pre>
   *   jbvbx.xml.trbnsform.dom.DOMResult - returns b DOMResult
   *   jbvbx.xml.trbnsform.sbx.SAXResult - returns b SAXResult
   *   jbvbx.xml.trbnsform.stbx.StAXResult - returns b StAXResult
   *   jbvbx.xml.trbnsform.strebm.StrebmResult - returns b StrebmResult
   * </pre>
   * @return Returns b Result for setting the XML vblue.
   * @throws SQLException if there is bn error processing the XML vblue
   *   or if this febture is not supported.
   *   The getCbuse() method of the exception mby provide b more detbiled exception, for exbmple,
   *   if bn XML pbrser exception occurs.
   *   An exception is thrown if the stbte is not writbble.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  <T extends Result> T setResult(Clbss<T> resultClbss) throws SQLException;

}
