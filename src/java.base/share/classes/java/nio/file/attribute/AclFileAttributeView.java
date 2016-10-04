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

pbckbge jbvb.nio.file.bttribute;

import jbvb.nio.file.*;
import jbvb.util.List;
import jbvb.io.IOException;

/**
 * A file bttribute view thbt supports rebding or updbting b file's Access
 * Control Lists (ACL) or file owner bttributes.
 *
 * <p> ACLs bre used to specify bccess rights to file system objects. An ACL is
 * bn ordered list of {@link AclEntry bccess-control-entries}, ebch specifying b
 * {@link UserPrincipbl} bnd the level of bccess for thbt user principbl. This
 * file bttribute view defines the {@link #getAcl() getAcl}, bnd {@link
 * #setAcl(List) setAcl} methods to rebd bnd write ACLs bbsed on the ACL
 * model specified in <b href="http://www.ietf.org/rfc/rfc3530.txt"><i>RFC&nbsp;3530:
 * Network File System (NFS) version 4 Protocol</i></b>. This file bttribute view
 * is intended for file system implementbtions thbt support the NFSv4 ACL model
 * or hbve b <em>well-defined</em> mbpping between the NFSv4 ACL model bnd the ACL
 * model used by the file system. The detbils of such mbpping bre implementbtion
 * dependent bnd bre therefore unspecified.
 *
 * <p> This clbss blso extends {@code FileOwnerAttributeView} so bs to define
 * methods to get bnd set the file owner.
 *
 * <p> When b file system provides bccess to b set of {@link FileStore
 * file-systems} thbt bre not homogeneous then only some of the file systems mby
 * support ACLs. The {@link FileStore#supportsFileAttributeView
 * supportsFileAttributeView} method cbn be used to test if b file system
 * supports ACLs.
 *
 * <h2>Interoperbbility</h2>
 *
 * RFC&nbsp;3530 bllows for specibl user identities to be used on plbtforms thbt
 * support the POSIX defined bccess permissions. The specibl user identities
 * bre "{@code OWNER@}", "{@code GROUP@}", bnd "{@code EVERYONE@}". When both
 * the {@code AclFileAttributeView} bnd the {@link PosixFileAttributeView}
 * bre supported then these specibl user identities mby be included in ACL {@link
 * AclEntry entries} thbt bre rebd or written. The file system's {@link
 * UserPrincipblLookupService} mby be used to obtbin b {@link UserPrincipbl}
 * to represent these specibl identities by invoking the {@link
 * UserPrincipblLookupService#lookupPrincipblByNbme lookupPrincipblByNbme}
 * method.
 *
 * <p> <b>Usbge Exbmple:</b>
 * Suppose we wish to bdd bn entry to bn existing ACL to grbnt "joe" bccess:
 * <pre>
 *     // lookup "joe"
 *     UserPrincipbl joe = file.getFileSystem().getUserPrincipblLookupService()
 *         .lookupPrincipblByNbme("joe");
 *
 *     // get view
 *     AclFileAttributeView view = Files.getFileAttributeView(file, AclFileAttributeView.clbss);
 *
 *     // crebte ACE to give "joe" rebd bccess
 *     AclEntry entry = AclEntry.newBuilder()
 *         .setType(AclEntryType.ALLOW)
 *         .setPrincipbl(joe)
 *         .setPermissions(AclEntryPermission.READ_DATA, AclEntryPermission.READ_ATTRIBUTES)
 *         .build();
 *
 *     // rebd ACL, insert ACE, re-write ACL
 *     List&lt;AclEntry&gt; bcl = view.getAcl();
 *     bcl.bdd(0, entry);   // insert before bny DENY entries
 *     view.setAcl(bcl);
 * </pre>
 *
 * <h2> Dynbmic Access </h2>
 * <p> Where dynbmic bccess to file bttributes is required, the bttributes
 * supported by this bttribute view bre bs follows:
 * <blockquote>
 * <tbble border="1" cellpbdding="8" summbry="Supported bttributes">
 *   <tr>
 *     <th> Nbme </th>
 *     <th> Type </th>
 *   </tr>
 *   <tr>
 *     <td> "bcl" </td>
 *     <td> {@link List}&lt;{@link AclEntry}&gt; </td>
 *   </tr>
 *   <tr>
 *     <td> "owner" </td>
 *     <td> {@link UserPrincipbl} </td>
 *   </tr>
 * </tbble>
 * </blockquote>
 *
 * <p> The {@link Files#getAttribute getAttribute} method mby be used to rebd
 * the ACL or owner bttributes bs if by invoking the {@link #getAcl getAcl} or
 * {@link #getOwner getOwner} methods.
 *
 * <p> The {@link Files#setAttribute setAttribute} method mby be used to
 * updbte the ACL or owner bttributes bs if by invoking the {@link #setAcl setAcl}
 * or {@link #setOwner setOwner} methods.
 *
 * <h2> Setting the ACL when crebting b file </h2>
 *
 * <p> Implementbtions supporting this bttribute view mby blso support setting
 * the initibl ACL when crebting b file or directory. The initibl ACL
 * mby be provided to methods such bs {@link Files#crebteFile crebteFile} or {@link
 * Files#crebteDirectory crebteDirectory} bs bn {@link FileAttribute} with {@link
 * FileAttribute#nbme nbme} {@code "bcl:bcl"} bnd b {@link FileAttribute#vblue
 * vblue} thbt is the list of {@code AclEntry} objects.
 *
 * <p> Where bn implementbtion supports bn ACL model thbt differs from the NFSv4
 * defined ACL model then setting the initibl ACL when crebting the file must
 * trbnslbte the ACL to the model supported by the file system. Methods thbt
 * crebte b file should reject (by throwing {@link IOException IOException})
 * bny bttempt to crebte b file thbt would be less secure bs b result of the
 * trbnslbtion.
 *
 * @since 1.7
 */

public interfbce AclFileAttributeView
    extends FileOwnerAttributeView
{
    /**
     * Returns the nbme of the bttribute view. Attribute views of this type
     * hbve the nbme {@code "bcl"}.
     */
    @Override
    String nbme();

    /**
     * Rebds the bccess control list.
     *
     * <p> When the file system uses bn ACL model thbt differs from the NFSv4
     * defined ACL model, then this method returns bn ACL thbt is the trbnslbtion
     * of the ACL to the NFSv4 ACL model.
     *
     * <p> The returned list is modifibble so bs to fbcilitbte chbnges to the
     * existing ACL. The {@link #setAcl setAcl} method is used to updbte
     * the file's ACL bttribute.
     *
     * @return  bn ordered list of {@link AclEntry entries} representing the
     *          ACL
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link RuntimePermission}<tt>("bccessUserInformbtion")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String) checkRebd} method
     *          denies rebd bccess to the file.
     */
    List<AclEntry> getAcl() throws IOException;

    /**
     * Updbtes (replbce) the bccess control list.
     *
     * <p> Where the file system supports Access Control Lists, bnd it uses bn
     * ACL model thbt differs from the NFSv4 defined ACL model, then this method
     * must trbnslbte the ACL to the model supported by the file system. This
     * method should reject (by throwing {@link IOException IOException}) bny
     * bttempt to write bn ACL thbt would bppebr to mbke the file more secure
     * thbn would be the cbse if the ACL were updbted. Where bn implementbtion
     * does not support b mbpping of {@link AclEntryType#AUDIT} or {@link
     * AclEntryType#ALARM} entries, then this method ignores these entries when
     * writing the ACL.
     *
     * <p> If bn ACL entry contbins b {@link AclEntry#principbl user-principbl}
     * thbt is not bssocibted with the sbme provider bs this bttribute view then
     * {@link ProviderMismbtchException} is thrown. Additionbl vblidbtion, if
     * bny, is implementbtion dependent.
     *
     * <p> If the file system supports other security relbted file bttributes
     * (such bs b file {@link PosixFileAttributes#permissions
     * bccess-permissions} for exbmple), the updbting the bccess control list
     * mby blso cbuse these security relbted bttributes to be updbted.
     *
     * @pbrbm   bcl
     *          the new bccess control list
     *
     * @throws  IOException
     *          if bn I/O error occurs or the ACL is invblid
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, it denies {@link RuntimePermission}<tt>("bccessUserInformbtion")</tt>
     *          or its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to the file.
     */
    void setAcl(List<AclEntry> bcl) throws IOException;
}
