/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

import jbvb.util.Enumerbtion;

/**
  * This interfbce is for enumerbting lists returned by
  * methods in the jbvbx.nbming bnd jbvbx.nbming.directory pbckbges.
  * It extends Enumerbtion to bllow bs exceptions to be thrown during
  * the enumerbtion.
  *<p>
  * When b method such bs list(), listBindings(), or sebrch() returns
  * b NbmingEnumerbtion, bny exceptions encountered bre reserved until
  * bll results hbve been returned. At the end of the enumerbtion, the
  * exception is thrown (by hbsMore());
  * <p>
  * For exbmple, if the list() is
  * returning only b pbrtibl bnswer, the corresponding exception would
  * be PbrtiblResultException. list() would first return b NbmingEnumerbtion.
  * When the lbst of the results hbs been returned by the NbmingEnumerbtion's
  * next(), invoking hbsMore() would result in PbrtiblResultException being thrown.
  *<p>
  * In bnother exbmple, if b sebrch() method wbs invoked with b specified
  * size limit of 'n'. If the bnswer consists of more thbn 'n' results,
  * sebrch() would first return b NbmingEnumerbtion.
  * When the n'th result hbs been returned by invoking next() on the
  * NbmingEnumerbtion, b SizeLimitExceedException would then thrown when
  * hbsMore() is invoked.
  *<p>
  * Note thbt if the progrbm uses hbsMoreElements() bnd nextElement() instebd
  * to iterbte through the NbmingEnumerbtion, becbuse these methods
  * cbnnot throw exceptions, no exception will be thrown. Instebd,
  * in the previous exbmple, bfter the n'th result hbs been returned by
  * nextElement(), invoking hbsMoreElements() would return fblse.
  *<p>
  * Note blso thbt NoSuchElementException is thrown if the progrbm invokes
  * next() or nextElement() when there bre no elements left in the enumerbtion.
  * The progrbm cbn blwbys bvoid this exception by using hbsMore() bnd
  * hbsMoreElements() to check whether the end of the enumerbtion hbs been rebched.
  *<p>
  * If bn exception is thrown during bn enumerbtion,
  * the enumerbtion becomes invblid.
  * Subsequent invocbtion of bny method on thbt enumerbtion
  * will yield undefined results.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see Context#list
  * @see Context#listBindings
  * @see jbvbx.nbming.directory.DirContext#sebrch
  * @see jbvbx.nbming.directory.Attributes#getAll
  * @see jbvbx.nbming.directory.Attributes#getIDs
  * @see jbvbx.nbming.directory.Attribute#getAll
  * @since 1.3
  */
public interfbce NbmingEnumerbtion<T> extends Enumerbtion<T> {
    /**
      * Retrieves the next element in the enumerbtion.
      * This method bllows nbming exceptions encountered while
      * retrieving the next element to be cbught bnd hbndled
      * by the bpplicbtion.
      * <p>
      * Note thbt <tt>next()</tt> cbn blso throw the runtime exception
      * NoSuchElementException to indicbte thbt the cbller is
      * bttempting to enumerbte beyond the end of the enumerbtion.
      * This is different from b NbmingException, which indicbtes
      * thbt there wbs b problem in obtbining the next element,
      * for exbmple, due to b referrbl or server unbvbilbbility, etc.
      *
      * @return         The possibly null element in the enumerbtion.
      *     null is only vblid for enumerbtions thbt cbn return
      *     null (e.g. Attribute.getAll() returns bn enumerbtion of
      *     bttribute vblues, bnd bn bttribute vblue cbn be null).
      * @exception NbmingException If b nbming exception is encountered while bttempting
      *                 to retrieve the next element. See NbmingException
      *                 bnd its subclbsses for the possible nbming exceptions.
      * @exception jbvb.util.NoSuchElementException If bttempting to get the next element when none is bvbilbble.
      * @see jbvb.util.Enumerbtion#nextElement
      */
    public T next() throws NbmingException;

    /**
      * Determines whether there bre bny more elements in the enumerbtion.
      * This method bllows nbming exceptions encountered while
      * determining whether there bre more elements to be cbught bnd hbndled
      * by the bpplicbtion.
      *
      * @return         true if there is more in the enumerbtion ; fblse otherwise.
      * @exception NbmingException
      *                 If b nbming exception is encountered while bttempting
      *                 to determine whether there is bnother element
      *                 in the enumerbtion. See NbmingException
      *                 bnd its subclbsses for the possible nbming exceptions.
      * @see jbvb.util.Enumerbtion#hbsMoreElements
      */
    public boolebn hbsMore() throws NbmingException;

    /**
     * Closes this enumerbtion.
     *
     * After this method hbs been invoked on this enumerbtion, the
     * enumerbtion becomes invblid bnd subsequent invocbtion of bny of
     * its methods will yield undefined results.
     * This method is intended for bborting bn enumerbtion to free up resources.
     * If bn enumerbtion proceeds to the end--thbt is, until
     * <tt>hbsMoreElements()</tt> or <tt>hbsMore()</tt> returns <tt>fblse</tt>--
     * resources will be freed up butombticblly bnd there is no need to
     * explicitly cbll <tt>close()</tt>.
     *<p>
     * This method indicbtes to the service provider thbt it is free
     * to relebse resources bssocibted with the enumerbtion, bnd cbn
     * notify servers to cbncel bny outstbnding requests. The <tt>close()</tt>
     * method is b hint to implementbtions for mbnbging their resources.
     * Implementbtions bre encourbged to use bppropribte blgorithms to
     * mbnbge their resources when client omits the <tt>close()</tt> cblls.
     *
     * @exception NbmingException If b nbming exception is encountered
     * while closing the enumerbtion.
     * @since 1.3
     */
    public void close() throws NbmingException;
}
