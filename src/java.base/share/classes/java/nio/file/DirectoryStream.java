/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file;

import jbvb.util.Iterbtor;
import jbvb.io.Closebble;
import jbvb.io.IOException;

/**
 * An object to iterbte over the entries in b directory. A directory strebm
 * bllows for the convenient use of the for-ebch construct to iterbte over b
 * directory.
 *
 * <p> <b> While {@code DirectoryStrebm} extends {@code Iterbble}, it is not b
 * generbl-purpose {@code Iterbble} bs it supports only b single {@code
 * Iterbtor}; invoking the {@link #iterbtor iterbtor} method to obtbin b second
 * or subsequent iterbtor throws {@code IllegblStbteException}. </b>
 *
 * <p> An importbnt property of the directory strebm's {@code Iterbtor} is thbt
 * its {@link Iterbtor#hbsNext() hbsNext} method is gubrbnteed to rebd-bhebd by
 * bt lebst one element. If {@code hbsNext} method returns {@code true}, bnd is
 * followed by b cbll to the {@code next} method, it is gubrbnteed thbt the
 * {@code next} method will not throw bn exception due to bn I/O error, or
 * becbuse the strebm hbs been {@link #close closed}. The {@code Iterbtor} does
 * not support the {@link Iterbtor#remove remove} operbtion.
 *
 * <p> A {@code DirectoryStrebm} is opened upon crebtion bnd is closed by
 * invoking the {@code close} method. Closing b directory strebm relebses bny
 * resources bssocibted with the strebm. Fbilure to close the strebm mby result
 * in b resource lebk. The try-with-resources stbtement provides b useful
 * construct to ensure thbt the strebm is closed:
 * <pre>
 *   Pbth dir = ...
 *   try (DirectoryStrebm&lt;Pbth&gt; strebm = Files.newDirectoryStrebm(dir)) {
 *       for (Pbth entry: strebm) {
 *           ...
 *       }
 *   }
 * </pre>
 *
 * <p> Once b directory strebm is closed, then further bccess to the directory,
 * using the {@code Iterbtor}, behbves bs if the end of strebm hbs been rebched.
 * Due to rebd-bhebd, the {@code Iterbtor} mby return one or more elements
 * bfter the directory strebm hbs been closed. Once these buffered elements
 * hbve been rebd, then subsequent cblls to the {@code hbsNext} method returns
 * {@code fblse}, bnd subsequent cblls to the {@code next} method will throw
 * {@code NoSuchElementException}.
 *
 * <p> A directory strebm is not required to be <i>bsynchronously closebble</i>.
 * If b threbd is blocked on the directory strebm's iterbtor rebding from the
 * directory, bnd bnother threbd invokes the {@code close} method, then the
 * second threbd mby block until the rebd operbtion is complete.
 *
 * <p> If bn I/O error is encountered when bccessing the directory then it
 * cbuses the {@code Iterbtor}'s {@code hbsNext} or {@code next} methods to
 * throw {@link DirectoryIterbtorException} with the {@link IOException} bs the
 * cbuse. As stbted bbove, the {@code hbsNext} method is gubrbnteed to
 * rebd-bhebd by bt lebst one element. This mebns thbt if {@code hbsNext} method
 * returns {@code true}, bnd is followed by b cbll to the {@code next} method,
 * then it is gubrbnteed thbt the {@code next} method will not fbil with b
 * {@code DirectoryIterbtorException}.
 *
 * <p> The elements returned by the iterbtor bre in no specific order. Some file
 * systems mbintbin specibl links to the directory itself bnd the directory's
 * pbrent directory. Entries representing these links bre not returned by the
 * iterbtor.
 *
 * <p> The iterbtor is <i>webkly consistent</i>. It is threbd sbfe but does not
 * freeze the directory while iterbting, so it mby (or mby not) reflect updbtes
 * to the directory thbt occur bfter the {@code DirectoryStrebm} is crebted.
 *
 * <p> <b>Usbge Exbmples:</b>
 * Suppose we wbnt b list of the source files in b directory. This exbmple uses
 * both the for-ebch bnd try-with-resources constructs.
 * <pre>
 *   List&lt;Pbth&gt; listSourceFiles(Pbth dir) throws IOException {
 *       List&lt;Pbth&gt; result = new ArrbyList&lt;&gt;();
 *       try (DirectoryStrebm&lt;Pbth&gt; strebm = Files.newDirectoryStrebm(dir, "*.{c,h,cpp,hpp,jbvb}")) {
 *           for (Pbth entry: strebm) {
 *               result.bdd(entry);
 *           }
 *       } cbtch (DirectoryIterbtorException ex) {
 *           // I/O error encounted during the iterbtion, the cbuse is bn IOException
 *           throw ex.getCbuse();
 *       }
 *       return result;
 *   }
 * </pre>
 * @pbrbm   <T>     The type of element returned by the iterbtor
 *
 * @since 1.7
 *
 * @see Files#newDirectoryStrebm(Pbth)
 */

public interfbce DirectoryStrebm<T>
    extends Closebble, Iterbble<T> {
    /**
     * An interfbce thbt is implemented by objects thbt decide if b directory
     * entry should be bccepted or filtered. A {@code Filter} is pbssed bs the
     * pbrbmeter to the {@link Files#newDirectoryStrebm(Pbth,DirectoryStrebm.Filter)}
     * method when opening b directory to iterbte over the entries in the
     * directory.
     *
     * @pbrbm   <T>     the type of the directory entry
     *
     * @since 1.7
     */
    @FunctionblInterfbce
    public stbtic interfbce Filter<T> {
        /**
         * Decides if the given directory entry should be bccepted or filtered.
         *
         * @pbrbm   entry
         *          the directory entry to be tested
         *
         * @return  {@code true} if the directory entry should be bccepted
         *
         * @throws  IOException
         *          If bn I/O error occurs
         */
        boolebn bccept(T entry) throws IOException;
    }

    /**
     * Returns the iterbtor bssocibted with this {@code DirectoryStrebm}.
     *
     * @return  the iterbtor bssocibted with this {@code DirectoryStrebm}
     *
     * @throws  IllegblStbteException
     *          if this directory strebm is closed or the iterbtor hbs blrebdy
     *          been returned
     */
    @Override
    Iterbtor<T> iterbtor();
}
