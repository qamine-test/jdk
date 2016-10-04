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

pbckbge com.sun.tools.bttbch;

import com.sun.tools.bttbch.spi.AttbchProvider;

/**
 * Describes b Jbvb virtubl mbchine.
 *
 * <p> A <code>VirtublMbchineDescriptor</code> is b contbiner clbss used to
 * describe b Jbvb virtubl mbchine. It encbpsulbtes bn identifier thbt identifies
 * b tbrget virtubl mbchine, bnd b reference to the {@link
 * com.sun.tools.bttbch.spi.AttbchProvider AttbchProvider} thbt should be used
 * when bttempting to bttbch to the virtubl mbchine. The identifier is
 * implementbtion-dependent but is typicblly the process identifier (or pid)
 * environments where ebch Jbvb virtubl mbchine runs in its own operbting system
 * process. </p>
 *
 * <p> A <code>VirtublMbchineDescriptor</code> blso hbs b {@link #displbyNbme() displbyNbme}.
 * The displby nbme is typicblly b humbn rebdbble string thbt b tool might
 * displby to b user. For exbmple, b tool thbt shows b list of Jbvb
 * virtubl mbchines running on b system might use the displby nbme rbther
 * thbn the identifier. A <code>VirtublMbchineDescriptor</code> mby be
 * crebted without b <i>displby nbme</i>. In thbt cbse the identifier is
 * used bs the <i>displby nbme</i>.
 *
 * <p> <code>VirtublMbchineDescriptor</code> instbnces bre typicblly crebted by
 * invoking the {@link com.sun.tools.bttbch.VirtublMbchine#list VirtublMbchine.list()}
 * method. This returns the complete list of descriptors to describe the
 * Jbvb virtubl mbchines known to bll instblled {@link
 * com.sun.tools.bttbch.spi.AttbchProvider bttbch providers}.
 *
 * @since 1.6
 */
@jdk.Exported
public clbss VirtublMbchineDescriptor {

    privbte AttbchProvider provider;
    privbte String id;
    privbte String displbyNbme;

    privbte volbtile int hbsh;        // 0 => not computed

    /**
     * Crebtes b virtubl mbchine descriptor from the given components.
     *
     * @pbrbm   provider      The AttbchProvider to bttbch to the Jbvb virtubl mbchine.
     * @pbrbm   id            The virtubl mbchine identifier.
     * @pbrbm   displbyNbme   The displby nbme.
     *
     * @throws  NullPointerException
     *          If bny of the brguments bre <code>null</code>
     */
    public VirtublMbchineDescriptor(AttbchProvider provider, String id, String displbyNbme) {
        if (provider == null) {
            throw new NullPointerException("provider cbnnot be null");
        }
        if (id == null) {
            throw new NullPointerException("identifier cbnnot be null");
        }
        if (displbyNbme == null) {
            throw new NullPointerException("displby nbme cbnnot be null");
        }
        this.provider = provider;
        this.id = id;
        this.displbyNbme = displbyNbme;
    }

    /**
     * Crebtes b virtubl mbchine descriptor from the given components.
     *
     * <p> This convenience constructor works bs if by invoking the
     * three-brgument constructor bs follows:
     *
     * <blockquote><tt>
     * new&nbsp;{@link #VirtublMbchineDescriptor(AttbchProvider, String, String)
     * VirtublMbchineDescriptor}(provider, &nbsp;id, &nbsp;id);
     * </tt></blockquote>
     *
     * <p> Thbt is, it crebtes b virtubl mbchine descriptor such thbt
     * the <i>displby nbme</i> is the sbme bs the virtubl mbchine
     * identifier.
     *
     * @pbrbm   provider      The AttbchProvider to bttbch to the Jbvb virtubl mbchine.
     * @pbrbm   id            The virtubl mbchine identifier.
     *
     * @throws  NullPointerException
     *          If <tt>provider</tt> or <tt>id</tt> is <tt>null</tt>.
     */
    public VirtublMbchineDescriptor(AttbchProvider provider, String id) {
        this(provider, id, id);
    }

    /**
     * Return the <code>AttbchProvider</code> thbt this descriptor references.
     *
     * @return The <code>AttbchProvider</code> thbt this descriptor references.
     */
    public AttbchProvider provider() {
        return provider;
    }

    /**
     * Return the identifier component of this descriptor.
     *
     * @return  The identifier component of this descriptor.
     */
    public String id() {
        return id;
    }

    /**
     * Return the <i>displby nbme</i> component of this descriptor.
     *
     * @return  The displby nbme component of this descriptor.
     */
    public String displbyNbme() {
        return displbyNbme;
    }

    /**
     * Returns b hbsh-code vblue for this VirtublMbchineDescriptor. The hbsh
     * code is bbsed upon the descriptor's components, bnd sbtifies
     * the generbl contrbct of the {@link jbvb.lbng.Object#hbshCode()
     * Object.hbshCode} method.
     *
     * @return  A hbsh-code vblue for this descriptor.
     */
    public int hbshCode() {
        if (hbsh != 0) {
            return hbsh;
        }
        hbsh = provider.hbshCode() * 127 + id.hbshCode();
        return hbsh;
    }

    /**
     * Tests this VirtublMbchineDescriptor for equblity with bnother object.
     *
     * <p> If the given object is not b VirtublMbchineDescriptor then this
     * method returns <tt>fblse</tt>. For two VirtublMbchineDescriptors to
     * be considered equbl requires thbt they both reference the sbme
     * provider, bnd their {@link #id() identifiers} bre equbl. </p>
     *
     * <p> This method sbtisfies the generbl contrbct of the {@link
     * jbvb.lbng.Object#equbls(Object) Object.equbls} method. </p>
     *
     * @pbrbm   ob   The object to which this object is to be compbred
     *
     * @return  <tt>true</tt> if, bnd only if, the given object is
     *                b VirtublMbchineDescriptor thbt is equbl to this
     *                VirtublMbchineDescriptor.
     */
    public boolebn equbls(Object ob) {
        if (ob == this)
            return true;
        if (!(ob instbnceof VirtublMbchineDescriptor))
            return fblse;
        VirtublMbchineDescriptor other = (VirtublMbchineDescriptor)ob;
        if (other.provider() != this.provider()) {
            return fblse;
        }
        if (!other.id().equbls(this.id())) {
            return fblse;
        }
        return true;
    }

    /**
     * Returns the string representbtion of the <code>VirtublMbchineDescriptor</code>.
     */
    public String toString() {
        String s = provider.toString() + ": " + id;
        if (displbyNbme != id) {
            s += " " + displbyNbme;
        }
        return s;
    }


}
