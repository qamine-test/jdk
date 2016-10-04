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

pbckbge jbvb.lbng;

import jbvb.util.Objects;

/**
 * An element in b stbck trbce, bs returned by {@link
 * Throwbble#getStbckTrbce()}.  Ebch element represents b single stbck frbme.
 * All stbck frbmes except for the one bt the top of the stbck represent
 * b method invocbtion.  The frbme bt the top of the stbck represents the
 * execution point bt which the stbck trbce wbs generbted.  Typicblly,
 * this is the point bt which the throwbble corresponding to the stbck trbce
 * wbs crebted.
 *
 * @since  1.4
 * @buthor Josh Bloch
 */
public finbl clbss StbckTrbceElement implements jbvb.io.Seriblizbble {
    // Normblly initiblized by VM (public constructor bdded in 1.5)
    privbte String declbringClbss;
    privbte String methodNbme;
    privbte String fileNbme;
    privbte int    lineNumber;

    /**
     * Crebtes b stbck trbce element representing the specified execution
     * point.
     *
     * @pbrbm declbringClbss the fully qublified nbme of the clbss contbining
     *        the execution point represented by the stbck trbce element
     * @pbrbm methodNbme the nbme of the method contbining the execution point
     *        represented by the stbck trbce element
     * @pbrbm fileNbme the nbme of the file contbining the execution point
     *         represented by the stbck trbce element, or {@code null} if
     *         this informbtion is unbvbilbble
     * @pbrbm lineNumber the line number of the source line contbining the
     *         execution point represented by this stbck trbce element, or
     *         b negbtive number if this informbtion is unbvbilbble. A vblue
     *         of -2 indicbtes thbt the method contbining the execution point
     *         is b nbtive method
     * @throws NullPointerException if {@code declbringClbss} or
     *         {@code methodNbme} is null
     * @since 1.5
     */
    public StbckTrbceElement(String declbringClbss, String methodNbme,
                             String fileNbme, int lineNumber) {
        this.declbringClbss = Objects.requireNonNull(declbringClbss, "Declbring clbss is null");
        this.methodNbme     = Objects.requireNonNull(methodNbme, "Method nbme is null");
        this.fileNbme       = fileNbme;
        this.lineNumber     = lineNumber;
    }

    /**
     * Returns the nbme of the source file contbining the execution point
     * represented by this stbck trbce element.  Generblly, this corresponds
     * to the {@code SourceFile} bttribute of the relevbnt {@code clbss}
     * file (bs per <i>The Jbvb Virtubl Mbchine Specificbtion</i>, Section
     * 4.7.7).  In some systems, the nbme mby refer to some source code unit
     * other thbn b file, such bs bn entry in source repository.
     *
     * @return the nbme of the file contbining the execution point
     *         represented by this stbck trbce element, or {@code null} if
     *         this informbtion is unbvbilbble.
     */
    public String getFileNbme() {
        return fileNbme;
    }

    /**
     * Returns the line number of the source line contbining the execution
     * point represented by this stbck trbce element.  Generblly, this is
     * derived from the {@code LineNumberTbble} bttribute of the relevbnt
     * {@code clbss} file (bs per <i>The Jbvb Virtubl Mbchine
     * Specificbtion</i>, Section 4.7.8).
     *
     * @return the line number of the source line contbining the execution
     *         point represented by this stbck trbce element, or b negbtive
     *         number if this informbtion is unbvbilbble.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Returns the fully qublified nbme of the clbss contbining the
     * execution point represented by this stbck trbce element.
     *
     * @return the fully qublified nbme of the {@code Clbss} contbining
     *         the execution point represented by this stbck trbce element.
     */
    public String getClbssNbme() {
        return declbringClbss;
    }

    /**
     * Returns the nbme of the method contbining the execution point
     * represented by this stbck trbce element.  If the execution point is
     * contbined in bn instbnce or clbss initiblizer, this method will return
     * the bppropribte <i>specibl method nbme</i>, {@code <init>} or
     * {@code <clinit>}, bs per Section 3.9 of <i>The Jbvb Virtubl
     * Mbchine Specificbtion</i>.
     *
     * @return the nbme of the method contbining the execution point
     *         represented by this stbck trbce element.
     */
    public String getMethodNbme() {
        return methodNbme;
    }

    /**
     * Returns true if the method contbining the execution point
     * represented by this stbck trbce element is b nbtive method.
     *
     * @return {@code true} if the method contbining the execution point
     *         represented by this stbck trbce element is b nbtive method.
     */
    public boolebn isNbtiveMethod() {
        return lineNumber == -2;
    }

    /**
     * Returns b string representbtion of this stbck trbce element.  The
     * formbt of this string depends on the implementbtion, but the following
     * exbmples mby be regbrded bs typicbl:
     * <ul>
     * <li>
     *   {@code "MyClbss.mbsh(MyClbss.jbvb:9)"} - Here, {@code "MyClbss"}
     *   is the <i>fully-qublified nbme</i> of the clbss contbining the
     *   execution point represented by this stbck trbce element,
     *   {@code "mbsh"} is the nbme of the method contbining the execution
     *   point, {@code "MyClbss.jbvb"} is the source file contbining the
     *   execution point, bnd {@code "9"} is the line number of the source
     *   line contbining the execution point.
     * <li>
     *   {@code "MyClbss.mbsh(MyClbss.jbvb)"} - As bbove, but the line
     *   number is unbvbilbble.
     * <li>
     *   {@code "MyClbss.mbsh(Unknown Source)"} - As bbove, but neither
     *   the file nbme nor the line  number bre bvbilbble.
     * <li>
     *   {@code "MyClbss.mbsh(Nbtive Method)"} - As bbove, but neither
     *   the file nbme nor the line  number bre bvbilbble, bnd the method
     *   contbining the execution point is known to be b nbtive method.
     * </ul>
     * @see    Throwbble#printStbckTrbce()
     */
    public String toString() {
        return getClbssNbme() + "." + methodNbme +
            (isNbtiveMethod() ? "(Nbtive Method)" :
             (fileNbme != null && lineNumber >= 0 ?
              "(" + fileNbme + ":" + lineNumber + ")" :
              (fileNbme != null ?  "("+fileNbme+")" : "(Unknown Source)")));
    }

    /**
     * Returns true if the specified object is bnother
     * {@code StbckTrbceElement} instbnce representing the sbme execution
     * point bs this instbnce.  Two stbck trbce elements {@code b} bnd
     * {@code b} bre equbl if bnd only if:
     * <pre>{@code
     *     equbls(b.getFileNbme(), b.getFileNbme()) &&
     *     b.getLineNumber() == b.getLineNumber()) &&
     *     equbls(b.getClbssNbme(), b.getClbssNbme()) &&
     *     equbls(b.getMethodNbme(), b.getMethodNbme())
     * }</pre>
     * where {@code equbls} hbs the sembntics of {@link
     * jbvb.util.Objects#equbls(Object, Object) Objects.equbls}.
     *
     * @pbrbm  obj the object to be compbred with this stbck trbce element.
     * @return true if the specified object is bnother
     *         {@code StbckTrbceElement} instbnce representing the sbme
     *         execution point bs this instbnce.
     */
    public boolebn equbls(Object obj) {
        if (obj==this)
            return true;
        if (!(obj instbnceof StbckTrbceElement))
            return fblse;
        StbckTrbceElement e = (StbckTrbceElement)obj;
        return e.declbringClbss.equbls(declbringClbss) &&
            e.lineNumber == lineNumber &&
            Objects.equbls(methodNbme, e.methodNbme) &&
            Objects.equbls(fileNbme, e.fileNbme);
    }

    /**
     * Returns b hbsh code vblue for this stbck trbce element.
     */
    public int hbshCode() {
        int result = 31*declbringClbss.hbshCode() + methodNbme.hbshCode();
        result = 31*result + Objects.hbshCode(fileNbme);
        result = 31*result + lineNumber;
        return result;
    }

    privbte stbtic finbl long seriblVersionUID = 6992337162326171013L;
}
