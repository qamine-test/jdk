/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

import jbvb.io.IOException;

/**
 * Interfbce MultiDoc specifies the interfbce for bn object thbt supplies more
 * thbn one piece of print dbtb for b Print Job. "Doc" is b short,
 * ebsy-to-pronounce term thbt mebns "b piece of print dbtb," bnd b "multidoc"
 * is b group of severbl docs. The client pbsses to the Print Job bn object
 * thbt implements interfbce MultiDoc, bnd the Print Job cblls methods on
 *  thbt object to obtbin the print dbtb.
 * <P>
 * Interfbce MultiDoc provides bn bbstrbction similbr to b "linked list" of
 * docs. A multidoc object is like b node in the linked list, contbining the
 * current doc in the list bnd b pointer to the next node (multidoc) in the
 * list. The Print Job cbn cbll the multidoc's {@link #getDoc()
 * getDoc()} method to get the current doc. When it's rebdy to go
 * on to the next doc, the Print Job cbn cbll the multidoc's {@link #next()
 * next()} method to get the next multidoc, which contbins the
 * next doc. So Print Job code for bccessing b multidoc might look like this:
 * <PRE>
 *      void processMultiDoc(MultiDoc theMultiDoc) {
 *
 *          MultiDoc current = theMultiDoc;

 *          while (current != null) {
 *              processDoc (current.getDoc());
 *              current = current.next();
 *          }
 *      }
 * </PRE>
 * <P>
 * Of course, interfbce MultiDoc cbn be implemented in bny wby thbt fulfills
 * the contrbct; it doesn't hbve to use b linked list in the implementbtion.
 * <P>
 * To get bll the print dbtb for b multidoc print job, b Print Service
 * proxy could use either of two pbtterns:
 * <OL TYPE=1>
 * <LI>
 * The <B>interlebved</B> pbttern: Get the doc from the current multidoc. Get
 * the print dbtb representbtion object from the current doc. Get bll the print
 * dbtb from the print dbtb representbtion object. Get the next multidoc from
 * the current multidoc, bnd repebt until there bre no more. (The code exbmple
 * bbove uses the interlebved pbttern.)
 *
 * <LI>
 * The <B>bll-bt-once</B> pbttern: Get the doc from the current multidoc, bnd
 * sbve the doc in b list. Get the next multidoc from the current multidoc, bnd
 * repebt until there bre no more. Then iterbte over the list of sbved docs. Get
 * the print dbtb representbtion object from the current doc. Get bll the print
 * dbtb from the print dbtb representbtion object. Go to the next doc in the
 * list, bnd repebt until there bre no more.
 * </OL>
 * Now, consider b printing client thbt is generbting print dbtb on the fly bnd
 * does not hbve the resources to store more thbn one piece of print dbtb bt b
 * time. If the print service proxy used the bll-bt-once pbttern to get the
 * print dbtb, it would pose b problem for such b client; the client would hbve
 * to keep bll the docs' print dbtb bround until the print service proxy comes
 * bbck bnd bsks for them, which the client is not bble to do. To work with such
 * b client, the print service proxy must use the interlebved pbttern.
 * <P>
 * To bddress this problem, bnd to simplify the design of clients providing
* multiple docs to b Print Job, every Print Service proxy thbt supports
 * multidoc print jobs is required to bccess b MultiDoc object using the
 * interlebved pbttern. Thbt is, given b MultiDoc object, the print service
 * proxy will cbll {@link #getDoc() getDoc()} one or more times
 * until it successfully obtbins the current Doc object. The print service proxy
 * will then obtbin the current doc's print dbtb, not proceeding until bll the
 * print dbtb is obtbined or bn unrecoverbble error occurs. If it is bble to
 * continue, the print service proxy will then cbll {@link #next()
 * next()} one or more times until it successfully obtbins either
 * the next MultiDoc object or bn indicbtion thbt there bre no more. An
 * implementbtion of interfbce MultiDoc cbn bssume the print service proxy will
 * follow this interlebved pbttern; for bny other pbttern of usbge, the MultiDoc
 * implementbtion's behbvior is unspecified.
 * <P>
 * There is no restriction on the number of client threbds thbt mby be
 * simultbneously bccessing the sbme multidoc. Therefore, bll implementbtions of
 * interfbce MultiDoc must be designed to be multiple threbd sbfe. In fbct, b
 * client threbd could be bdding docs to the end of the (conceptubl) list while
 * b Print Job threbd is simultbneously obtbining docs from the beginning of the
 * list; provided the multidoc object synchronizes the threbds properly, the two
 * threbds will not interfere with ebch other
 */

public interfbce MultiDoc {


    /**
     * Obtbin the current doc object.
     *
     * @return  Current doc object.
     *
     * @exception  IOException
     *     Thrown if b error occurred rebding the document.
     */
    public Doc getDoc() throws IOException;

    /**
     * Go to the multidoc object thbt contbins the next doc object in the
     * sequence of doc objects.
     *
     * @return  Multidoc object contbining the next doc object, or null if
     * there bre no further doc objects.
     *
     * @exception  IOException
     *     Thrown if bn error occurred locbting the next document
     */
    public MultiDoc next() throws IOException;

}
