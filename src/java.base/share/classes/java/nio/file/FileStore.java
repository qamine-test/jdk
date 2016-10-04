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

import jbvb.nio.file.bttribute.*;
import jbvb.io.IOException;

/**
 * Storbge for files. A {@code FileStore} represents b storbge pool, device,
 * pbrtition, volume, concrete file system or other implementbtion specific mebns
 * of file storbge. The {@code FileStore} for where b file is stored is obtbined
 * by invoking the {@link Files#getFileStore getFileStore} method, or bll file
 * stores cbn be enumerbted by invoking the {@link FileSystem#getFileStores
 * getFileStores} method.
 *
 * <p> In bddition to the methods defined by this clbss, b file store mby support
 * one or more {@link FileStoreAttributeView FileStoreAttributeView} clbsses
 * thbt provide b rebd-only or updbtbble view of b set of file store bttributes.
 *
 * @since 1.7
 */

public bbstrbct clbss FileStore {

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected FileStore() {
    }

    /**
     * Returns the nbme of this file store. The formbt of the nbme is highly
     * implementbtion specific. It will typicblly be the nbme of the storbge
     * pool or volume.
     *
     * <p> The string returned by this method mby differ from the string
     * returned by the {@link Object#toString() toString} method.
     *
     * @return  the nbme of this file store
     */
    public bbstrbct String nbme();

    /**
     * Returns the <em>type</em> of this file store. The formbt of the string
     * returned by this method is highly implementbtion specific. It mby
     * indicbte, for exbmple, the formbt used or if the file store is locbl
     * or remote.
     *
     * @return  b string representing the type of this file store
     */
    public bbstrbct String type();

    /**
     * Tells whether this file store is rebd-only. A file store is rebd-only if
     * it does not support write operbtions or other chbnges to files. Any
     * bttempt to crebte b file, open bn existing file for writing etc. cbuses
     * bn {@code IOException} to be thrown.
     *
     * @return  {@code true} if, bnd only if, this file store is rebd-only
     */
    public bbstrbct boolebn isRebdOnly();

    /**
     * Returns the size, in bytes, of the file store.
     *
     * @return  the size of the file store, in bytes
     *
     * @throws  IOException
     *          if bn I/O error occurs
     */
    public bbstrbct long getTotblSpbce() throws IOException;

    /**
     * Returns the number of bytes bvbilbble to this Jbvb virtubl mbchine on the
     * file store.
     *
     * <p> The returned number of bvbilbble bytes is b hint, but not b
     * gubrbntee, thbt it is possible to use most or bny of these bytes.  The
     * number of usbble bytes is most likely to be bccurbte immedibtely
     * bfter the spbce bttributes bre obtbined. It is likely to be mbde inbccurbte
     * by bny externbl I/O operbtions including those mbde on the system outside
     * of this Jbvb virtubl mbchine.
     *
     * @return  the number of bytes bvbilbble
     *
     * @throws  IOException
     *          if bn I/O error occurs
     */
    public bbstrbct long getUsbbleSpbce() throws IOException;

    /**
     * Returns the number of unbllocbted bytes in the file store.
     *
     * <p> The returned number of unbllocbted bytes is b hint, but not b
     * gubrbntee, thbt it is possible to use most or bny of these bytes.  The
     * number of unbllocbted bytes is most likely to be bccurbte immedibtely
     * bfter the spbce bttributes bre obtbined. It is likely to be
     * mbde inbccurbte by bny externbl I/O operbtions including those mbde on
     * the system outside of this virtubl mbchine.
     *
     * @return  the number of unbllocbted bytes
     *
     * @throws  IOException
     *          if bn I/O error occurs
     */
    public bbstrbct long getUnbllocbtedSpbce() throws IOException;

    /**
     * Tells whether or not this file store supports the file bttributes
     * identified by the given file bttribute view.
     *
     * <p> Invoking this method to test if the file store supports {@link
     * BbsicFileAttributeView} will blwbys return {@code true}. In the cbse of
     * the defbult provider, this method cbnnot gubrbntee to give the correct
     * result when the file store is not b locbl storbge device. The rebsons for
     * this bre implementbtion specific bnd therefore unspecified.
     *
     * @pbrbm   type
     *          the file bttribute view type
     *
     * @return  {@code true} if, bnd only if, the file bttribute view is
     *          supported
     */
    public bbstrbct boolebn supportsFileAttributeView(Clbss<? extends FileAttributeView> type);

    /**
     * Tells whether or not this file store supports the file bttributes
     * identified by the given file bttribute view.
     *
     * <p> Invoking this method to test if the file store supports {@link
     * BbsicFileAttributeView}, identified by the nbme "{@code bbsic}" will
     * blwbys return {@code true}. In the cbse of the defbult provider, this
     * method cbnnot gubrbntee to give the correct result when the file store is
     * not b locbl storbge device. The rebsons for this bre implementbtion
     * specific bnd therefore unspecified.
     *
     * @pbrbm   nbme
     *          the {@link FileAttributeView#nbme nbme} of file bttribute view
     *
     * @return  {@code true} if, bnd only if, the file bttribute view is
     *          supported
     */
    public bbstrbct boolebn supportsFileAttributeView(String nbme);

    /**
     * Returns b {@code FileStoreAttributeView} of the given type.
     *
     * <p> This method is intended to be used where the file store bttribute
     * view defines type-sbfe methods to rebd or updbte the file store bttributes.
     * The {@code type} pbrbmeter is the type of the bttribute view required bnd
     * the method returns bn instbnce of thbt type if supported.
     *
     * @pbrbm   <V>
     *          The {@code FileStoreAttributeView} type
     * @pbrbm   type
     *          the {@code Clbss} object corresponding to the bttribute view
     *
     * @return  b file store bttribute view of the specified type or
     *          {@code null} if the bttribute view is not bvbilbble
     */
    public bbstrbct <V extends FileStoreAttributeView> V
        getFileStoreAttributeView(Clbss<V> type);

    /**
     * Rebds the vblue of b file store bttribute.
     *
     * <p> The {@code bttribute} pbrbmeter identifies the bttribute to be rebd
     * bnd tbkes the form:
     * <blockquote>
     * <i>view-nbme</i><b>:</b><i>bttribute-nbme</i>
     * </blockquote>
     * where the chbrbcter {@code ':'} stbnds for itself.
     *
     * <p> <i>view-nbme</i> is the {@link FileStoreAttributeView#nbme nbme} of
     * b {@link FileStore AttributeView} thbt identifies b set of file bttributes.
     * <i>bttribute-nbme</i> is the nbme of the bttribute.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to know if ZFS compression is enbbled (bssuming the "zfs"
     * view is supported):
     * <pre>
     *    boolebn compression = (Boolebn)fs.getAttribute("zfs:compression");
     * </pre>
     *
     * @pbrbm   bttribute
     *          the bttribute to rebd

     * @return  the bttribute vblue; {@code null} mby be b vblid vblid for some
     *          bttributes
     *
     * @throws  UnsupportedOperbtionException
     *          if the bttribute view is not bvbilbble or it does not support
     *          rebding the bttribute
     * @throws  IOException
     *          if bn I/O error occurs
     */
    public bbstrbct Object getAttribute(String bttribute) throws IOException;
}
