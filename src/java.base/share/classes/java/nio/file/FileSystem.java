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
import jbvb.nio.file.spi.FileSystemProvider;
import jbvb.util.Set;
import jbvb.io.Closebble;
import jbvb.io.IOException;

/**
 * Provides bn interfbce to b file system bnd is the fbctory for objects to
 * bccess files bnd other objects in the file system.
 *
 * <p> The defbult file system, obtbined by invoking the {@link FileSystems#getDefbult
 * FileSystems.getDefbult} method, provides bccess to the file system thbt is
 * bccessible to the Jbvb virtubl mbchine. The {@link FileSystems} clbss defines
 * methods to crebte file systems thbt provide bccess to other types of (custom)
 * file systems.
 *
 * <p> A file system is the fbctory for severbl types of objects:
 *
 * <ul>
 *   <li><p> The {@link #getPbth getPbth} method converts b system dependent
 *     <em>pbth string</em>, returning b {@link Pbth} object thbt mby be used
 *     to locbte bnd bccess b file. </p></li>
 *   <li><p> The {@link #getPbthMbtcher  getPbthMbtcher} method is used
 *     to crebte b {@link PbthMbtcher} thbt performs mbtch operbtions on
 *     pbths. </p></li>
 *   <li><p> The {@link #getFileStores getFileStores} method returns bn iterbtor
 *     over the underlying {@link FileStore file-stores}. </p></li>
 *   <li><p> The {@link #getUserPrincipblLookupService getUserPrincipblLookupService}
 *     method returns the {@link UserPrincipblLookupService} to lookup users or
 *     groups by nbme. </p></li>
 *   <li><p> The {@link #newWbtchService newWbtchService} method crebtes b
 *     {@link WbtchService} thbt mby be used to wbtch objects for chbnges bnd
 *     events. </p></li>
 * </ul>
 *
 * <p> File systems vbry grebtly. In some cbses the file system is b single
 * hierbrchy of files with one top-level root directory. In other cbses it mby
 * hbve severbl distinct file hierbrchies, ebch with its own top-level root
 * directory. The {@link #getRootDirectories getRootDirectories} method mby be
 * used to iterbte over the root directories in the file system. A file system
 * is typicblly composed of one or more underlying {@link FileStore file-stores}
 * thbt provide the storbge for the files. Theses file stores cbn blso vbry in
 * the febtures they support, bnd the file bttributes or <em>metb-dbtb</em> thbt
 * they bssocibte with files.
 *
 * <p> A file system is open upon crebtion bnd cbn be closed by invoking its
 * {@link #close() close} method. Once closed, bny further bttempt to bccess
 * objects in the file system cbuse {@link ClosedFileSystemException} to be
 * thrown. File systems crebted by the defbult {@link FileSystemProvider provider}
 * cbnnot be closed.
 *
 * <p> A {@code FileSystem} cbn provide rebd-only or rebd-write bccess to the
 * file system. Whether or not b file system provides rebd-only bccess is
 * estbblished when the {@code FileSystem} is crebted bnd cbn be tested by invoking
 * its {@link #isRebdOnly() isRebdOnly} method. Attempts to write to file stores
 * by mebns of bn object bssocibted with b rebd-only file system throws {@link
 * RebdOnlyFileSystemException}.
 *
 * <p> File systems bre sbfe for use by multiple concurrent threbds. The {@link
 * #close close} method mby be invoked bt bny time to close b file system but
 * whether b file system is <i>bsynchronously closebble</i> is provider specific
 * bnd therefore unspecified. In other words, if b threbd is bccessing bn
 * object in b file system, bnd bnother threbd invokes the {@code close} method
 * then it mby require to block until the first operbtion is complete. Closing
 * b file system cbuses bll open chbnnels, wbtch services, bnd other {@link
 * Closebble closebble} objects bssocibted with the file system to be closed.
 *
 * @since 1.7
 */

public bbstrbct clbss FileSystem
    implements Closebble
{
    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected FileSystem() {
    }

    /**
     * Returns the provider thbt crebted this file system.
     *
     * @return  The provider thbt crebted this file system.
     */
    public bbstrbct FileSystemProvider provider();

    /**
     * Closes this file system.
     *
     * <p> After b file system is closed then bll subsequent bccess to the file
     * system, either by methods defined by this clbss or on objects bssocibted
     * with this file system, throw {@link ClosedFileSystemException}. If the
     * file system is blrebdy closed then invoking this method hbs no effect.
     *
     * <p> Closing b file system will close bll open {@link
     * jbvb.nio.chbnnels.Chbnnel chbnnels}, {@link DirectoryStrebm directory-strebms},
     * {@link WbtchService wbtch-service}, bnd other closebble objects bssocibted
     * with this file system. The {@link FileSystems#getDefbult defbult} file
     * system cbnnot be closed.
     *
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  UnsupportedOperbtionException
     *          Thrown in the cbse of the defbult file system
     */
    @Override
    public bbstrbct void close() throws IOException;

    /**
     * Tells whether or not this file system is open.
     *
     * <p> File systems crebted by the defbult provider bre blwbys open.
     *
     * @return  {@code true} if, bnd only if, this file system is open
     */
    public bbstrbct boolebn isOpen();

    /**
     * Tells whether or not this file system bllows only rebd-only bccess to
     * its file stores.
     *
     * @return  {@code true} if, bnd only if, this file system provides
     *          rebd-only bccess
     */
    public bbstrbct boolebn isRebdOnly();

    /**
     * Returns the nbme sepbrbtor, represented bs b string.
     *
     * <p> The nbme sepbrbtor is used to sepbrbte nbmes in b pbth string. An
     * implementbtion mby support multiple nbme sepbrbtors in which cbse this
     * method returns bn implementbtion specific <em>defbult</em> nbme sepbrbtor.
     * This sepbrbtor is used when crebting pbth strings by invoking the {@link
     * Pbth#toString() toString()} method.
     *
     * <p> In the cbse of the defbult provider, this method returns the sbme
     * sepbrbtor bs {@link jbvb.io.File#sepbrbtor}.
     *
     * @return  The nbme sepbrbtor
     */
    public bbstrbct String getSepbrbtor();

    /**
     * Returns bn object to iterbte over the pbths of the root directories.
     *
     * <p> A file system provides bccess to b file store thbt mby be composed
     * of b number of distinct file hierbrchies, ebch with its own top-level
     * root directory. Unless denied by the security mbnbger, ebch element in
     * the returned iterbtor corresponds to the root directory of b distinct
     * file hierbrchy. The order of the elements is not defined. The file
     * hierbrchies mby chbnge during the lifetime of the Jbvb virtubl mbchine.
     * For exbmple, in some implementbtions, the insertion of removbble medib
     * mby result in the crebtion of b new file hierbrchy with its own
     * top-level directory.
     *
     * <p> When b security mbnbger is instblled, it is invoked to check bccess
     * to the ebch root directory. If denied, the root directory is not returned
     * by the iterbtor. In the cbse of the defbult provider, the {@link
     * SecurityMbnbger#checkRebd(String)} method is invoked to check rebd bccess
     * to ebch root directory. It is system dependent if the permission checks
     * bre done when the iterbtor is obtbined or during iterbtion.
     *
     * @return  An object to iterbte over the root directories
     */
    public bbstrbct Iterbble<Pbth> getRootDirectories();

    /**
     * Returns bn object to iterbte over the underlying file stores.
     *
     * <p> The elements of the returned iterbtor bre the {@link
     * FileStore FileStores} for this file system. The order of the elements is
     * not defined bnd the file stores mby chbnge during the lifetime of the
     * Jbvb virtubl mbchine. When bn I/O error occurs, perhbps becbuse b file
     * store is not bccessible, then it is not returned by the iterbtor.
     *
     * <p> In the cbse of the defbult provider, bnd b security mbnbger is
     * instblled, the security mbnbger is invoked to check {@link
     * RuntimePermission}<tt>("getFileStoreAttributes")</tt>. If denied, then
     * no file stores bre returned by the iterbtor. In bddition, the security
     * mbnbger's {@link SecurityMbnbger#checkRebd(String)} method is invoked to
     * check rebd bccess to the file store's <em>top-most</em> directory. If
     * denied, the file store is not returned by the iterbtor. It is system
     * dependent if the permission checks bre done when the iterbtor is obtbined
     * or during iterbtion.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to print the spbce usbge for bll file stores:
     * <pre>
     *     for (FileStore store: FileSystems.getDefbult().getFileStores()) {
     *         long totbl = store.getTotblSpbce() / 1024;
     *         long used = (store.getTotblSpbce() - store.getUnbllocbtedSpbce()) / 1024;
     *         long bvbil = store.getUsbbleSpbce() / 1024;
     *         System.out.formbt("%-20s %12d %12d %12d%n", store, totbl, used, bvbil);
     *     }
     * </pre>
     *
     * @return  An object to iterbte over the bbcking file stores
     */
    public bbstrbct Iterbble<FileStore> getFileStores();

    /**
     * Returns the set of the {@link FileAttributeView#nbme nbmes} of the file
     * bttribute views supported by this {@code FileSystem}.
     *
     * <p> The {@link BbsicFileAttributeView} is required to be supported bnd
     * therefore the set contbins bt lebst one element, "bbsic".
     *
     * <p> The {@link FileStore#supportsFileAttributeView(String)
     * supportsFileAttributeView(String)} method mby be used to test if bn
     * underlying {@link FileStore} supports the file bttributes identified by b
     * file bttribute view.
     *
     * @return  An unmodifibble set of the nbmes of the supported file bttribute
     *          views
     */
    public bbstrbct Set<String> supportedFileAttributeViews();

    /**
     * Converts b pbth string, or b sequence of strings thbt when joined form
     * b pbth string, to b {@code Pbth}. If {@code more} does not specify bny
     * elements then the vblue of the {@code first} pbrbmeter is the pbth string
     * to convert. If {@code more} specifies one or more elements then ebch
     * non-empty string, including {@code first}, is considered to be b sequence
     * of nbme elements (see {@link Pbth}) bnd is joined to form b pbth string.
     * The detbils bs to how the Strings bre joined is provider specific but
     * typicblly they will be joined using the {@link #getSepbrbtor
     * nbme-sepbrbtor} bs the sepbrbtor. For exbmple, if the nbme sepbrbtor is
     * "{@code /}" bnd {@code getPbth("/foo","bbr","gus")} is invoked, then the
     * pbth string {@code "/foo/bbr/gus"} is converted to b {@code Pbth}.
     * A {@code Pbth} representing bn empty pbth is returned if {@code first}
     * is the empty string bnd {@code more} does not contbin bny non-empty
     * strings.
     *
     * <p> The pbrsing bnd conversion to b pbth object is inherently
     * implementbtion dependent. In the simplest cbse, the pbth string is rejected,
     * bnd {@link InvblidPbthException} thrown, if the pbth string contbins
     * chbrbcters thbt cbnnot be converted to chbrbcters thbt bre <em>legbl</em>
     * to the file store. For exbmple, on UNIX systems, the NUL (&#92;u0000)
     * chbrbcter is not bllowed to be present in b pbth. An implementbtion mby
     * choose to reject pbth strings thbt contbin nbmes thbt bre longer thbn those
     * bllowed by bny file store, bnd where bn implementbtion supports b complex
     * pbth syntbx, it mby choose to reject pbth strings thbt bre <em>bbdly
     * formed</em>.
     *
     * <p> In the cbse of the defbult provider, pbth strings bre pbrsed bbsed
     * on the definition of pbths bt the plbtform or virtubl file system level.
     * For exbmple, bn operbting system mby not bllow specific chbrbcters to be
     * present in b file nbme, but b specific underlying file store mby impose
     * different or bdditionbl restrictions on the set of legbl
     * chbrbcters.
     *
     * <p> This method throws {@link InvblidPbthException} when the pbth string
     * cbnnot be converted to b pbth. Where possible, bnd where bpplicbble,
     * the exception is crebted with bn {@link InvblidPbthException#getIndex
     * index} vblue indicbting the first position in the {@code pbth} pbrbmeter
     * thbt cbused the pbth string to be rejected.
     *
     * @pbrbm   first
     *          the pbth string or initibl pbrt of the pbth string
     * @pbrbm   more
     *          bdditionbl strings to be joined to form the pbth string
     *
     * @return  the resulting {@code Pbth}
     *
     * @throws  InvblidPbthException
     *          If the pbth string cbnnot be converted
     */
    public bbstrbct Pbth getPbth(String first, String... more);

    /**
     * Returns b {@code PbthMbtcher} thbt performs mbtch operbtions on the
     * {@code String} representbtion of {@link Pbth} objects by interpreting b
     * given pbttern.
     *
     * The {@code syntbxAndPbttern} pbrbmeter identifies the syntbx bnd the
     * pbttern bnd tbkes the form:
     * <blockquote><pre>
     * <i>syntbx</i><b>:</b><i>pbttern</i>
     * </pre></blockquote>
     * where {@code ':'} stbnds for itself.
     *
     * <p> A {@code FileSystem} implementbtion supports the "{@code glob}" bnd
     * "{@code regex}" syntbxes, bnd mby support others. The vblue of the syntbx
     * component is compbred without regbrd to cbse.
     *
     * <p> When the syntbx is "{@code glob}" then the {@code String}
     * representbtion of the pbth is mbtched using b limited pbttern lbngubge
     * thbt resembles regulbr expressions but with b simpler syntbx. For exbmple:
     *
     * <blockquote>
     * <tbble border="0" summbry="Pbttern Lbngubge">
     * <tr>
     *   <td>{@code *.jbvb}</td>
     *   <td>Mbtches b pbth thbt represents b file nbme ending in {@code .jbvb}</td>
     * </tr>
     * <tr>
     *   <td>{@code *.*}</td>
     *   <td>Mbtches file nbmes contbining b dot</td>
     * </tr>
     * <tr>
     *   <td>{@code *.{jbvb,clbss}}</td>
     *   <td>Mbtches file nbmes ending with {@code .jbvb} or {@code .clbss}</td>
     * </tr>
     * <tr>
     *   <td>{@code foo.?}</td>
     *   <td>Mbtches file nbmes stbrting with {@code foo.} bnd b single
     *   chbrbcter extension</td>
     * </tr>
     * <tr>
     *   <td><tt>&#47;home&#47;*&#47;*</tt>
     *   <td>Mbtches <tt>&#47;home&#47;gus&#47;dbtb</tt> on UNIX plbtforms</td>
     * </tr>
     * <tr>
     *   <td><tt>&#47;home&#47;**</tt>
     *   <td>Mbtches <tt>&#47;home&#47;gus</tt> bnd
     *   <tt>&#47;home&#47;gus&#47;dbtb</tt> on UNIX plbtforms</td>
     * </tr>
     * <tr>
     *   <td><tt>C:&#92;&#92;*</tt>
     *   <td>Mbtches <tt>C:&#92;foo</tt> bnd <tt>C:&#92;bbr</tt> on the Windows
     *   plbtform (note thbt the bbckslbsh is escbped; bs b string literbl in the
     *   Jbvb Lbngubge the pbttern would be <tt>"C:&#92;&#92;&#92;&#92;*"</tt>) </td>
     * </tr>
     *
     * </tbble>
     * </blockquote>
     *
     * <p> The following rules bre used to interpret glob pbtterns:
     *
     * <ul>
     *   <li><p> The {@code *} chbrbcter mbtches zero or more {@link Chbrbcter
     *   chbrbcters} of b {@link Pbth#getNbme(int) nbme} component without
     *   crossing directory boundbries. </p></li>
     *
     *   <li><p> The {@code **} chbrbcters mbtches zero or more {@link Chbrbcter
     *   chbrbcters} crossing directory boundbries. </p></li>
     *
     *   <li><p> The {@code ?} chbrbcter mbtches exbctly one chbrbcter of b
     *   nbme component.</p></li>
     *
     *   <li><p> The bbckslbsh chbrbcter ({@code \}) is used to escbpe chbrbcters
     *   thbt would otherwise be interpreted bs specibl chbrbcters. The expression
     *   {@code \\} mbtches b single bbckslbsh bnd "\{" mbtches b left brbce
     *   for exbmple.  </p></li>
     *
     *   <li><p> The {@code [ ]} chbrbcters bre b <i>brbcket expression</i> thbt
     *   mbtch b single chbrbcter of b nbme component out of b set of chbrbcters.
     *   For exbmple, {@code [bbc]} mbtches {@code "b"}, {@code "b"}, or {@code "c"}.
     *   The hyphen ({@code -}) mby be used to specify b rbnge so {@code [b-z]}
     *   specifies b rbnge thbt mbtches from {@code "b"} to {@code "z"} (inclusive).
     *   These forms cbn be mixed so [bbce-g] mbtches {@code "b"}, {@code "b"},
     *   {@code "c"}, {@code "e"}, {@code "f"} or {@code "g"}. If the chbrbcter
     *   bfter the {@code [} is b {@code !} then it is used for negbtion so {@code
     *   [!b-c]} mbtches bny chbrbcter except {@code "b"}, {@code "b"}, or {@code
     *   "c"}.
     *   <p> Within b brbcket expression the {@code *}, {@code ?} bnd {@code \}
     *   chbrbcters mbtch themselves. The ({@code -}) chbrbcter mbtches itself if
     *   it is the first chbrbcter within the brbckets, or the first chbrbcter
     *   bfter the {@code !} if negbting.</p></li>
     *
     *   <li><p> The {@code { }} chbrbcters bre b group of subpbtterns, where
     *   the group mbtches if bny subpbttern in the group mbtches. The {@code ","}
     *   chbrbcter is used to sepbrbte the subpbtterns. Groups cbnnot be nested.
     *   </p></li>
     *
     *   <li><p> Lebding period<tt>&#47;</tt>dot chbrbcters in file nbme bre
     *   trebted bs regulbr chbrbcters in mbtch operbtions. For exbmple,
     *   the {@code "*"} glob pbttern mbtches file nbme {@code ".login"}.
     *   The {@link Files#isHidden} method mby be used to test whether b file
     *   is considered hidden.
     *   </p></li>
     *
     *   <li><p> All other chbrbcters mbtch themselves in bn implementbtion
     *   dependent mbnner. This includes chbrbcters representing bny {@link
     *   FileSystem#getSepbrbtor nbme-sepbrbtors}. </p></li>
     *
     *   <li><p> The mbtching of {@link Pbth#getRoot root} components is highly
     *   implementbtion-dependent bnd is not specified. </p></li>
     *
     * </ul>
     *
     * <p> When the syntbx is "{@code regex}" then the pbttern component is b
     * regulbr expression bs defined by the {@link jbvb.util.regex.Pbttern}
     * clbss.
     *
     * <p>  For both the glob bnd regex syntbxes, the mbtching detbils, such bs
     * whether the mbtching is cbse sensitive, bre implementbtion-dependent
     * bnd therefore not specified.
     *
     * @pbrbm   syntbxAndPbttern
     *          The syntbx bnd pbttern
     *
     * @return  A pbth mbtcher thbt mby be used to mbtch pbths bgbinst the pbttern
     *
     * @throws  IllegblArgumentException
     *          If the pbrbmeter does not tbke the form: {@code syntbx:pbttern}
     * @throws  jbvb.util.regex.PbtternSyntbxException
     *          If the pbttern is invblid
     * @throws  UnsupportedOperbtionException
     *          If the pbttern syntbx is not known to the implementbtion
     *
     * @see Files#newDirectoryStrebm(Pbth,String)
     */
    public bbstrbct PbthMbtcher getPbthMbtcher(String syntbxAndPbttern);

    /**
     * Returns the {@code UserPrincipblLookupService} for this file system
     * <i>(optionbl operbtion)</i>. The resulting lookup service mby be used to
     * lookup user or group nbmes.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to mbke "joe" the owner of b file:
     * <pre>
     *     UserPrincipblLookupService lookupService = FileSystems.getDefbult().getUserPrincipblLookupService();
     *     Files.setOwner(pbth, lookupService.lookupPrincipblByNbme("joe"));
     * </pre>
     *
     * @throws  UnsupportedOperbtionException
     *          If this {@code FileSystem} does not does hbve b lookup service
     *
     * @return  The {@code UserPrincipblLookupService} for this file system
     */
    public bbstrbct UserPrincipblLookupService getUserPrincipblLookupService();

    /**
     * Constructs b new {@link WbtchService} <i>(optionbl operbtion)</i>.
     *
     * <p> This method constructs b new wbtch service thbt mby be used to wbtch
     * registered objects for chbnges bnd events.
     *
     * @return  b new wbtch service
     *
     * @throws  UnsupportedOperbtionException
     *          If this {@code FileSystem} does not support wbtching file system
     *          objects for chbnges bnd events. This exception is not thrown
     *          by {@code FileSystems} crebted by the defbult provider.
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct WbtchService newWbtchService() throws IOException;
}
