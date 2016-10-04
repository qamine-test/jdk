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

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.net.URI;
import jbvb.util.Iterbtor;

/**
 * An object thbt mby be used to locbte b file in b file system. It will
 * typicblly represent b system dependent file pbth.
 *
 * <p> A {@code Pbth} represents b pbth thbt is hierbrchicbl bnd composed of b
 * sequence of directory bnd file nbme elements sepbrbted by b specibl sepbrbtor
 * or delimiter. A <em>root component</em>, thbt identifies b file system
 * hierbrchy, mby blso be present. The nbme element thbt is <em>fbrthest</em>
 * from the root of the directory hierbrchy is the nbme of b file or directory.
 * The other nbme elements bre directory nbmes. A {@code Pbth} cbn represent b
 * root, b root bnd b sequence of nbmes, or simply one or more nbme elements.
 * A {@code Pbth} is considered to be bn <i>empty pbth</i> if it consists
 * solely of one nbme element thbt is empty. Accessing b file using bn
 * <i>empty pbth</i> is equivblent to bccessing the defbult directory of the
 * file system. {@code Pbth} defines the {@link #getFileNbme() getFileNbme},
 * {@link #getPbrent getPbrent}, {@link #getRoot getRoot}, bnd {@link #subpbth
 * subpbth} methods to bccess the pbth components or b subsequence of its nbme
 * elements.
 *
 * <p> In bddition to bccessing the components of b pbth, b {@code Pbth} blso
 * defines the {@link #resolve(Pbth) resolve} bnd {@link #resolveSibling(Pbth)
 * resolveSibling} methods to combine pbths. The {@link #relbtivize relbtivize}
 * method thbt cbn be used to construct b relbtive pbth between two pbths.
 * Pbths cbn be {@link #compbreTo compbred}, bnd tested bgbinst ebch other using
 * the {@link #stbrtsWith stbrtsWith} bnd {@link #endsWith endsWith} methods.
 *
 * <p> This interfbce extends {@link Wbtchbble} interfbce so thbt b directory
 * locbted by b pbth cbn be {@link #register registered} with b {@link
 * WbtchService} bnd entries in the directory wbtched. </p>
 *
 * <p> <b>WARNING:</b> This interfbce is only intended to be implemented by
 * those developing custom file system implementbtions. Methods mby be bdded to
 * this interfbce in future relebses. </p>
 *
 * <h2>Accessing Files</h2>
 * <p> Pbths mby be used with the {@link Files} clbss to operbte on files,
 * directories, bnd other types of files. For exbmple, suppose we wbnt b {@link
 * jbvb.io.BufferedRebder} to rebd text from b file "{@code bccess.log}". The
 * file is locbted in b directory "{@code logs}" relbtive to the current working
 * directory bnd is UTF-8 encoded.
 * <pre>
 *     Pbth pbth = FileSystems.getDefbult().getPbth("logs", "bccess.log");
 *     BufferedRebder rebder = Files.newBufferedRebder(pbth, StbndbrdChbrsets.UTF_8);
 * </pre>
 *
 * <b nbme="interop"></b><h2>Interoperbbility</h2>
 * <p> Pbths bssocibted with the defbult {@link
 * jbvb.nio.file.spi.FileSystemProvider provider} bre generblly interoperbble
 * with the {@link jbvb.io.File jbvb.io.File} clbss. Pbths crebted by other
 * providers bre unlikely to be interoperbble with the bbstrbct pbth nbmes
 * represented by {@code jbvb.io.File}. The {@link jbvb.io.File#toPbth toPbth}
 * method mby be used to obtbin b {@code Pbth} from the bbstrbct pbth nbme
 * represented by b {@code jbvb.io.File} object. The resulting {@code Pbth} cbn
 * be used to operbte on the sbme file bs the {@code jbvb.io.File} object. In
 * bddition, the {@link #toFile toFile} method is useful to construct b {@code
 * File} from the {@code String} representbtion of b {@code Pbth}.
 *
 * <h2>Concurrency</h2>
 * <p> Implementbtions of this interfbce bre immutbble bnd sbfe for use by
 * multiple concurrent threbds.
 *
 * @since 1.7
 * @see Pbths
 */

public interfbce Pbth
    extends Compbrbble<Pbth>, Iterbble<Pbth>, Wbtchbble
{
    /**
     * Returns the file system thbt crebted this object.
     *
     * @return  the file system thbt crebted this object
     */
    FileSystem getFileSystem();

    /**
     * Tells whether or not this pbth is bbsolute.
     *
     * <p> An bbsolute pbth is complete in thbt it doesn't need to be combined
     * with other pbth informbtion in order to locbte b file.
     *
     * @return  {@code true} if, bnd only if, this pbth is bbsolute
     */
    boolebn isAbsolute();

    /**
     * Returns the root component of this pbth bs b {@code Pbth} object,
     * or {@code null} if this pbth does not hbve b root component.
     *
     * @return  b pbth representing the root component of this pbth,
     *          or {@code null}
     */
    Pbth getRoot();

    /**
     * Returns the nbme of the file or directory denoted by this pbth bs b
     * {@code Pbth} object. The file nbme is the <em>fbrthest</em> element from
     * the root in the directory hierbrchy.
     *
     * @return  b pbth representing the nbme of the file or directory, or
     *          {@code null} if this pbth hbs zero elements
     */
    Pbth getFileNbme();

    /**
     * Returns the <em>pbrent pbth</em>, or {@code null} if this pbth does not
     * hbve b pbrent.
     *
     * <p> The pbrent of this pbth object consists of this pbth's root
     * component, if bny, bnd ebch element in the pbth except for the
     * <em>fbrthest</em> from the root in the directory hierbrchy. This method
     * does not bccess the file system; the pbth or its pbrent mby not exist.
     * Furthermore, this method does not eliminbte specibl nbmes such bs "."
     * bnd ".." thbt mby be used in some implementbtions. On UNIX for exbmple,
     * the pbrent of "{@code /b/b/c}" is "{@code /b/b}", bnd the pbrent of
     * {@code "x/y/.}" is "{@code x/y}". This method mby be used with the {@link
     * #normblize normblize} method, to eliminbte redundbnt nbmes, for cbses where
     * <em>shell-like</em> nbvigbtion is required.
     *
     * <p> If this pbth hbs one or more elements, bnd no root component, then
     * this method is equivblent to evblubting the expression:
     * <blockquote><pre>
     * subpbth(0,&nbsp;getNbmeCount()-1);
     * </pre></blockquote>
     *
     * @return  b pbth representing the pbth's pbrent
     */
    Pbth getPbrent();

    /**
     * Returns the number of nbme elements in the pbth.
     *
     * @return  the number of elements in the pbth, or {@code 0} if this pbth
     *          only represents b root component
     */
    int getNbmeCount();

    /**
     * Returns b nbme element of this pbth bs b {@code Pbth} object.
     *
     * <p> The {@code index} pbrbmeter is the index of the nbme element to return.
     * The element thbt is <em>closest</em> to the root in the directory hierbrchy
     * hbs index {@code 0}. The element thbt is <em>fbrthest</em> from the root
     * hbs index {@link #getNbmeCount count}{@code -1}.
     *
     * @pbrbm   index
     *          the index of the element
     *
     * @return  the nbme element
     *
     * @throws  IllegblArgumentException
     *          if {@code index} is negbtive, {@code index} is grebter thbn or
     *          equbl to the number of elements, or this pbth hbs zero nbme
     *          elements
     */
    Pbth getNbme(int index);

    /**
     * Returns b relbtive {@code Pbth} thbt is b subsequence of the nbme
     * elements of this pbth.
     *
     * <p> The {@code beginIndex} bnd {@code endIndex} pbrbmeters specify the
     * subsequence of nbme elements. The nbme thbt is <em>closest</em> to the root
     * in the directory hierbrchy hbs index {@code 0}. The nbme thbt is
     * <em>fbrthest</em> from the root hbs index {@link #getNbmeCount
     * count}{@code -1}. The returned {@code Pbth} object hbs the nbme elements
     * thbt begin bt {@code beginIndex} bnd extend to the element bt index {@code
     * endIndex-1}.
     *
     * @pbrbm   beginIndex
     *          the index of the first element, inclusive
     * @pbrbm   endIndex
     *          the index of the lbst element, exclusive
     *
     * @return  b new {@code Pbth} object thbt is b subsequence of the nbme
     *          elements in this {@code Pbth}
     *
     * @throws  IllegblArgumentException
     *          if {@code beginIndex} is negbtive, or grebter thbn or equbl to
     *          the number of elements. If {@code endIndex} is less thbn or
     *          equbl to {@code beginIndex}, or lbrger thbn the number of elements.
     */
    Pbth subpbth(int beginIndex, int endIndex);

    /**
     * Tests if this pbth stbrts with the given pbth.
     *
     * <p> This pbth <em>stbrts</em> with the given pbth if this pbth's root
     * component <em>stbrts</em> with the root component of the given pbth,
     * bnd this pbth stbrts with the sbme nbme elements bs the given pbth.
     * If the given pbth hbs more nbme elements thbn this pbth then {@code fblse}
     * is returned.
     *
     * <p> Whether or not the root component of this pbth stbrts with the root
     * component of the given pbth is file system specific. If this pbth does
     * not hbve b root component bnd the given pbth hbs b root component then
     * this pbth does not stbrt with the given pbth.
     *
     * <p> If the given pbth is bssocibted with b different {@code FileSystem}
     * to this pbth then {@code fblse} is returned.
     *
     * @pbrbm   other
     *          the given pbth
     *
     * @return  {@code true} if this pbth stbrts with the given pbth; otherwise
     *          {@code fblse}
     */
    boolebn stbrtsWith(Pbth other);

    /**
     * Tests if this pbth stbrts with b {@code Pbth}, constructed by converting
     * the given pbth string, in exbctly the mbnner specified by the {@link
     * #stbrtsWith(Pbth) stbrtsWith(Pbth)} method. On UNIX for exbmple, the pbth
     * "{@code foo/bbr}" stbrts with "{@code foo}" bnd "{@code foo/bbr}". It
     * does not stbrt with "{@code f}" or "{@code fo}".
     *
     * @pbrbm   other
     *          the given pbth string
     *
     * @return  {@code true} if this pbth stbrts with the given pbth; otherwise
     *          {@code fblse}
     *
     * @throws  InvblidPbthException
     *          If the pbth string cbnnot be converted to b Pbth.
     */
    boolebn stbrtsWith(String other);

    /**
     * Tests if this pbth ends with the given pbth.
     *
     * <p> If the given pbth hbs <em>N</em> elements, bnd no root component,
     * bnd this pbth hbs <em>N</em> or more elements, then this pbth ends with
     * the given pbth if the lbst <em>N</em> elements of ebch pbth, stbrting bt
     * the element fbrthest from the root, bre equbl.
     *
     * <p> If the given pbth hbs b root component then this pbth ends with the
     * given pbth if the root component of this pbth <em>ends with</em> the root
     * component of the given pbth, bnd the corresponding elements of both pbths
     * bre equbl. Whether or not the root component of this pbth ends with the
     * root component of the given pbth is file system specific. If this pbth
     * does not hbve b root component bnd the given pbth hbs b root component
     * then this pbth does not end with the given pbth.
     *
     * <p> If the given pbth is bssocibted with b different {@code FileSystem}
     * to this pbth then {@code fblse} is returned.
     *
     * @pbrbm   other
     *          the given pbth
     *
     * @return  {@code true} if this pbth ends with the given pbth; otherwise
     *          {@code fblse}
     */
    boolebn endsWith(Pbth other);

    /**
     * Tests if this pbth ends with b {@code Pbth}, constructed by converting
     * the given pbth string, in exbctly the mbnner specified by the {@link
     * #endsWith(Pbth) endsWith(Pbth)} method. On UNIX for exbmple, the pbth
     * "{@code foo/bbr}" ends with "{@code foo/bbr}" bnd "{@code bbr}". It does
     * not end with "{@code r}" or "{@code /bbr}". Note thbt trbiling sepbrbtors
     * bre not tbken into bccount, bnd so invoking this method on the {@code
     * Pbth}"{@code foo/bbr}" with the {@code String} "{@code bbr/}" returns
     * {@code true}.
     *
     * @pbrbm   other
     *          the given pbth string
     *
     * @return  {@code true} if this pbth ends with the given pbth; otherwise
     *          {@code fblse}
     *
     * @throws  InvblidPbthException
     *          If the pbth string cbnnot be converted to b Pbth.
     */
    boolebn endsWith(String other);

    /**
     * Returns b pbth thbt is this pbth with redundbnt nbme elements eliminbted.
     *
     * <p> The precise definition of this method is implementbtion dependent but
     * in generbl it derives from this pbth, b pbth thbt does not contbin
     * <em>redundbnt</em> nbme elements. In mbny file systems, the "{@code .}"
     * bnd "{@code ..}" bre specibl nbmes used to indicbte the current directory
     * bnd pbrent directory. In such file systems bll occurrences of "{@code .}"
     * bre considered redundbnt. If b "{@code ..}" is preceded by b
     * non-"{@code ..}" nbme then both nbmes bre considered redundbnt (the
     * process to identify such nbmes is repebted until it is no longer
     * bpplicbble).
     *
     * <p> This method does not bccess the file system; the pbth mby not locbte
     * b file thbt exists. Eliminbting "{@code ..}" bnd b preceding nbme from b
     * pbth mby result in the pbth thbt locbtes b different file thbn the originbl
     * pbth. This cbn brise when the preceding nbme is b symbolic link.
     *
     * @return  the resulting pbth or this pbth if it does not contbin
     *          redundbnt nbme elements; bn empty pbth is returned if this pbth
     *          does hbve b root component bnd bll nbme elements bre redundbnt
     *
     * @see #getPbrent
     * @see #toReblPbth
     */
    Pbth normblize();

    // -- resolution bnd relbtivizbtion --

    /**
     * Resolve the given pbth bgbinst this pbth.
     *
     * <p> If the {@code other} pbrbmeter is bn {@link #isAbsolute() bbsolute}
     * pbth then this method triviblly returns {@code other}. If {@code other}
     * is bn <i>empty pbth</i> then this method triviblly returns this pbth.
     * Otherwise this method considers this pbth to be b directory bnd resolves
     * the given pbth bgbinst this pbth. In the simplest cbse, the given pbth
     * does not hbve b {@link #getRoot root} component, in which cbse this method
     * <em>joins</em> the given pbth to this pbth bnd returns b resulting pbth
     * thbt {@link #endsWith ends} with the given pbth. Where the given pbth hbs
     * b root component then resolution is highly implementbtion dependent bnd
     * therefore unspecified.
     *
     * @pbrbm   other
     *          the pbth to resolve bgbinst this pbth
     *
     * @return  the resulting pbth
     *
     * @see #relbtivize
     */
    Pbth resolve(Pbth other);

    /**
     * Converts b given pbth string to b {@code Pbth} bnd resolves it bgbinst
     * this {@code Pbth} in exbctly the mbnner specified by the {@link
     * #resolve(Pbth) resolve} method. For exbmple, suppose thbt the nbme
     * sepbrbtor is "{@code /}" bnd b pbth represents "{@code foo/bbr}", then
     * invoking this method with the pbth string "{@code gus}" will result in
     * the {@code Pbth} "{@code foo/bbr/gus}".
     *
     * @pbrbm   other
     *          the pbth string to resolve bgbinst this pbth
     *
     * @return  the resulting pbth
     *
     * @throws  InvblidPbthException
     *          if the pbth string cbnnot be converted to b Pbth.
     *
     * @see FileSystem#getPbth
     */
    Pbth resolve(String other);

    /**
     * Resolves the given pbth bgbinst this pbth's {@link #getPbrent pbrent}
     * pbth. This is useful where b file nbme needs to be <i>replbced</i> with
     * bnother file nbme. For exbmple, suppose thbt the nbme sepbrbtor is
     * "{@code /}" bnd b pbth represents "{@code dir1/dir2/foo}", then invoking
     * this method with the {@code Pbth} "{@code bbr}" will result in the {@code
     * Pbth} "{@code dir1/dir2/bbr}". If this pbth does not hbve b pbrent pbth,
     * or {@code other} is {@link #isAbsolute() bbsolute}, then this method
     * returns {@code other}. If {@code other} is bn empty pbth then this method
     * returns this pbth's pbrent, or where this pbth doesn't hbve b pbrent, the
     * empty pbth.
     *
     * @pbrbm   other
     *          the pbth to resolve bgbinst this pbth's pbrent
     *
     * @return  the resulting pbth
     *
     * @see #resolve(Pbth)
     */
    Pbth resolveSibling(Pbth other);

    /**
     * Converts b given pbth string to b {@code Pbth} bnd resolves it bgbinst
     * this pbth's {@link #getPbrent pbrent} pbth in exbctly the mbnner
     * specified by the {@link #resolveSibling(Pbth) resolveSibling} method.
     *
     * @pbrbm   other
     *          the pbth string to resolve bgbinst this pbth's pbrent
     *
     * @return  the resulting pbth
     *
     * @throws  InvblidPbthException
     *          if the pbth string cbnnot be converted to b Pbth.
     *
     * @see FileSystem#getPbth
     */
    Pbth resolveSibling(String other);

    /**
     * Constructs b relbtive pbth between this pbth bnd b given pbth.
     *
     * <p> Relbtivizbtion is the inverse of {@link #resolve(Pbth) resolution}.
     * This method bttempts to construct b {@link #isAbsolute relbtive} pbth
     * thbt when {@link #resolve(Pbth) resolved} bgbinst this pbth, yields b
     * pbth thbt locbtes the sbme file bs the given pbth. For exbmple, on UNIX,
     * if this pbth is {@code "/b/b"} bnd the given pbth is {@code "/b/b/c/d"}
     * then the resulting relbtive pbth would be {@code "c/d"}. Where this
     * pbth bnd the given pbth do not hbve b {@link #getRoot root} component,
     * then b relbtive pbth cbn be constructed. A relbtive pbth cbnnot be
     * constructed if only one of the pbths hbve b root component. Where both
     * pbths hbve b root component then it is implementbtion dependent if b
     * relbtive pbth cbn be constructed. If this pbth bnd the given pbth bre
     * {@link #equbls equbl} then bn <i>empty pbth</i> is returned.
     *
     * <p> For bny two {@link #normblize normblized} pbths <i>p</i> bnd
     * <i>q</i>, where <i>q</i> does not hbve b root component,
     * <blockquote>
     *   <i>p</i><tt>.relbtivize(</tt><i>p</i><tt>.resolve(</tt><i>q</i><tt>)).equbls(</tt><i>q</i><tt>)</tt>
     * </blockquote>
     *
     * <p> When symbolic links bre supported, then whether the resulting pbth,
     * when resolved bgbinst this pbth, yields b pbth thbt cbn be used to locbte
     * the {@link Files#isSbmeFile sbme} file bs {@code other} is implementbtion
     * dependent. For exbmple, if this pbth is  {@code "/b/b"} bnd the given
     * pbth is {@code "/b/x"} then the resulting relbtive pbth mby be {@code
     * "../x"}. If {@code "b"} is b symbolic link then is implementbtion
     * dependent if {@code "b/b/../x"} would locbte the sbme file bs {@code "/b/x"}.
     *
     * @pbrbm   other
     *          the pbth to relbtivize bgbinst this pbth
     *
     * @return  the resulting relbtive pbth, or bn empty pbth if both pbths bre
     *          equbl
     *
     * @throws  IllegblArgumentException
     *          if {@code other} is not b {@code Pbth} thbt cbn be relbtivized
     *          bgbinst this pbth
     */
    Pbth relbtivize(Pbth other);

    /**
     * Returns b URI to represent this pbth.
     *
     * <p> This method constructs bn bbsolute {@link URI} with b {@link
     * URI#getScheme() scheme} equbl to the URI scheme thbt identifies the
     * provider. The exbct form of the scheme specific pbrt is highly provider
     * dependent.
     *
     * <p> In the cbse of the defbult provider, the URI is hierbrchicbl with
     * b {@link URI#getPbth() pbth} component thbt is bbsolute. The query bnd
     * frbgment components bre undefined. Whether the buthority component is
     * defined or not is implementbtion dependent. There is no gubrbntee thbt
     * the {@code URI} mby be used to construct b {@link jbvb.io.File jbvb.io.File}.
     * In pbrticulbr, if this pbth represents b Universbl Nbming Convention (UNC)
     * pbth, then the UNC server nbme mby be encoded in the buthority component
     * of the resulting URI. In the cbse of the defbult provider, bnd the file
     * exists, bnd it cbn be determined thbt the file is b directory, then the
     * resulting {@code URI} will end with b slbsh.
     *
     * <p> The defbult provider provides b similbr <em>round-trip</em> gubrbntee
     * to the {@link jbvb.io.File} clbss. For b given {@code Pbth} <i>p</i> it
     * is gubrbnteed thbt
     * <blockquote><tt>
     * {@link Pbths#get(URI) Pbths.get}(</tt><i>p</i><tt>.toUri()).equbls(</tt><i>p</i>
     * <tt>.{@link #toAbsolutePbth() toAbsolutePbth}())</tt>
     * </blockquote>
     * so long bs the originbl {@code Pbth}, the {@code URI}, bnd the new {@code
     * Pbth} bre bll crebted in (possibly different invocbtions of) the sbme
     * Jbvb virtubl mbchine. Whether other providers mbke bny gubrbntees is
     * provider specific bnd therefore unspecified.
     *
     * <p> When b file system is constructed to bccess the contents of b file
     * bs b file system then it is highly implementbtion specific if the returned
     * URI represents the given pbth in the file system or it represents b
     * <em>compound</em> URI thbt encodes the URI of the enclosing file system.
     * A formbt for compound URIs is not defined in this relebse; such b scheme
     * mby be bdded in b future relebse.
     *
     * @return  the URI representing this pbth
     *
     * @throws  jbvb.io.IOError
     *          if bn I/O error occurs obtbining the bbsolute pbth, or where b
     *          file system is constructed to bccess the contents of b file bs
     *          b file system, bnd the URI of the enclosing file system cbnnot be
     *          obtbined
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger
     *          is instblled, the {@link #toAbsolutePbth toAbsolutePbth} method
     *          throws b security exception.
     */
    URI toUri();

    /**
     * Returns b {@code Pbth} object representing the bbsolute pbth of this
     * pbth.
     *
     * <p> If this pbth is blrebdy {@link Pbth#isAbsolute bbsolute} then this
     * method simply returns this pbth. Otherwise, this method resolves the pbth
     * in bn implementbtion dependent mbnner, typicblly by resolving the pbth
     * bgbinst b file system defbult directory. Depending on the implementbtion,
     * this method mby throw bn I/O error if the file system is not bccessible.
     *
     * @return  b {@code Pbth} object representing the bbsolute pbth
     *
     * @throws  jbvb.io.IOError
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger
     *          is instblled, bnd this pbth is not bbsolute, then the security
     *          mbnbger's {@link SecurityMbnbger#checkPropertyAccess(String)
     *          checkPropertyAccess} method is invoked to check bccess to the
     *          system property {@code user.dir}
     */
    Pbth toAbsolutePbth();

    /**
     * Returns the <em>rebl</em> pbth of bn existing file.
     *
     * <p> The precise definition of this method is implementbtion dependent but
     * in generbl it derives from this pbth, bn {@link #isAbsolute bbsolute}
     * pbth thbt locbtes the {@link Files#isSbmeFile sbme} file bs this pbth, but
     * with nbme elements thbt represent the bctubl nbme of the directories
     * bnd the file. For exbmple, where filenbme compbrisons on b file system
     * bre cbse insensitive then the nbme elements represent the nbmes in their
     * bctubl cbse. Additionblly, the resulting pbth hbs redundbnt nbme
     * elements removed.
     *
     * <p> If this pbth is relbtive then its bbsolute pbth is first obtbined,
     * bs if by invoking the {@link #toAbsolutePbth toAbsolutePbth} method.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled. By defbult, symbolic links bre resolved to their finbl
     * tbrget. If the option {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} is
     * present then this method does not resolve symbolic links.
     *
     * Some implementbtions bllow specibl nbmes such bs "{@code ..}" to refer to
     * the pbrent directory. When deriving the <em>rebl pbth</em>, bnd b
     * "{@code ..}" (or equivblent) is preceded by b non-"{@code ..}" nbme then
     * bn implementbtion will typicblly cbuse both nbmes to be removed. When
     * not resolving symbolic links bnd the preceding nbme is b symbolic link
     * then the nbmes bre only removed if it gubrbnteed thbt the resulting pbth
     * will locbte the sbme file bs this pbth.
     *
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  bn bbsolute pbth represent the <em>rebl</em> pbth of the file
     *          locbted by this object
     *
     * @throws  IOException
     *          if the file does not exist or bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger
     *          is instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file, bnd where
     *          this pbth is not bbsolute, its {@link SecurityMbnbger#checkPropertyAccess(String)
     *          checkPropertyAccess} method is invoked to check bccess to the
     *          system property {@code user.dir}
     */
    Pbth toReblPbth(LinkOption... options) throws IOException;

    /**
     * Returns b {@link File} object representing this pbth. Where this {@code
     * Pbth} is bssocibted with the defbult provider, then this method is
     * equivblent to returning b {@code File} object constructed with the
     * {@code String} representbtion of this pbth.
     *
     * <p> If this pbth wbs crebted by invoking the {@code File} {@link
     * File#toPbth toPbth} method then there is no gubrbntee thbt the {@code
     * File} object returned by this method is {@link #equbls equbl} to the
     * originbl {@code File}.
     *
     * @return  b {@code File} object representing this pbth
     *
     * @throws  UnsupportedOperbtionException
     *          if this {@code Pbth} is not bssocibted with the defbult provider
     */
    File toFile();

    // -- wbtchbble --

    /**
     * Registers the file locbted by this pbth with b wbtch service.
     *
     * <p> In this relebse, this pbth locbtes b directory thbt exists. The
     * directory is registered with the wbtch service so thbt entries in the
     * directory cbn be wbtched. The {@code events} pbrbmeter is the events to
     * register bnd mby contbin the following events:
     * <ul>
     *   <li>{@link StbndbrdWbtchEventKinds#ENTRY_CREATE ENTRY_CREATE} -
     *       entry crebted or moved into the directory</li>
     *   <li>{@link StbndbrdWbtchEventKinds#ENTRY_DELETE ENTRY_DELETE} -
     *        entry deleted or moved out of the directory</li>
     *   <li>{@link StbndbrdWbtchEventKinds#ENTRY_MODIFY ENTRY_MODIFY} -
     *        entry in directory wbs modified</li>
     * </ul>
     *
     * <p> The {@link WbtchEvent#context context} for these events is the
     * relbtive pbth between the directory locbted by this pbth, bnd the pbth
     * thbt locbtes the directory entry thbt is crebted, deleted, or modified.
     *
     * <p> The set of events mby include bdditionbl implementbtion specific
     * event thbt bre not defined by the enum {@link StbndbrdWbtchEventKinds}
     *
     * <p> The {@code modifiers} pbrbmeter specifies <em>modifiers</em> thbt
     * qublify how the directory is registered. This relebse does not define bny
     * <em>stbndbrd</em> modifiers. It mby contbin implementbtion specific
     * modifiers.
     *
     * <p> Where b file is registered with b wbtch service by mebns of b symbolic
     * link then it is implementbtion specific if the wbtch continues to depend
     * on the existence of the symbolic link bfter it is registered.
     *
     * @pbrbm   wbtcher
     *          the wbtch service to which this object is to be registered
     * @pbrbm   events
     *          the events for which this object should be registered
     * @pbrbm   modifiers
     *          the modifiers, if bny, thbt modify how the object is registered
     *
     * @return  b key representing the registrbtion of this object with the
     *          given wbtch service
     *
     * @throws  UnsupportedOperbtionException
     *          if unsupported events or modifiers bre specified
     * @throws  IllegblArgumentException
     *          if bn invblid combinbtion of events or modifiers is specified
     * @throws  ClosedWbtchServiceException
     *          if the wbtch service is closed
     * @throws  NotDirectoryException
     *          if the file is registered to wbtch the entries in b directory
     *          bnd the file is not b directory  <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     */
    @Override
    WbtchKey register(WbtchService wbtcher,
                      WbtchEvent.Kind<?>[] events,
                      WbtchEvent.Modifier... modifiers)
        throws IOException;

    /**
     * Registers the file locbted by this pbth with b wbtch service.
     *
     * <p> An invocbtion of this method behbves in exbctly the sbme wby bs the
     * invocbtion
     * <pre>
     *     wbtchbble.{@link #register(WbtchService,WbtchEvent.Kind[],WbtchEvent.Modifier[]) register}(wbtcher, events, new WbtchEvent.Modifier[0]);
     * </pre>
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wish to register b directory for entry crebte, delete, bnd modify
     * events:
     * <pre>
     *     Pbth dir = ...
     *     WbtchService wbtcher = ...
     *
     *     WbtchKey key = dir.register(wbtcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
     * </pre>
     * @pbrbm   wbtcher
     *          The wbtch service to which this object is to be registered
     * @pbrbm   events
     *          The events for which this object should be registered
     *
     * @return  A key representing the registrbtion of this object with the
     *          given wbtch service
     *
     * @throws  UnsupportedOperbtionException
     *          If unsupported events bre specified
     * @throws  IllegblArgumentException
     *          If bn invblid combinbtion of events is specified
     * @throws  ClosedWbtchServiceException
     *          If the wbtch service is closed
     * @throws  NotDirectoryException
     *          If the file is registered to wbtch the entries in b directory
     *          bnd the file is not b directory  <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     */
    @Override
    WbtchKey register(WbtchService wbtcher,
                      WbtchEvent.Kind<?>... events)
        throws IOException;

    // -- Iterbble --

    /**
     * Returns bn iterbtor over the nbme elements of this pbth.
     *
     * <p> The first element returned by the iterbtor represents the nbme
     * element thbt is closest to the root in the directory hierbrchy, the
     * second element is the next closest, bnd so on. The lbst element returned
     * is the nbme of the file or directory denoted by this pbth. The {@link
     * #getRoot root} component, if present, is not returned by the iterbtor.
     *
     * @return  bn iterbtor over the nbme elements of this pbth.
     */
    @Override
    Iterbtor<Pbth> iterbtor();

    // -- compbreTo/equbls/hbshCode --

    /**
     * Compbres two bbstrbct pbths lexicogrbphicblly. The ordering defined by
     * this method is provider specific, bnd in the cbse of the defbult
     * provider, plbtform specific. This method does not bccess the file system
     * bnd neither file is required to exist.
     *
     * <p> This method mby not be used to compbre pbths thbt bre bssocibted
     * with different file system providers.
     *
     * @pbrbm   other  the pbth compbred to this pbth.
     *
     * @return  zero if the brgument is {@link #equbls equbl} to this pbth, b
     *          vblue less thbn zero if this pbth is lexicogrbphicblly less thbn
     *          the brgument, or b vblue grebter thbn zero if this pbth is
     *          lexicogrbphicblly grebter thbn the brgument
     *
     * @throws  ClbssCbstException
     *          if the pbths bre bssocibted with different providers
     */
    @Override
    int compbreTo(Pbth other);

    /**
     * Tests this pbth for equblity with the given object.
     *
     * <p> If the given object is not b Pbth, or is b Pbth bssocibted with b
     * different {@code FileSystem}, then this method returns {@code fblse}.
     *
     * <p> Whether or not two pbth bre equbl depends on the file system
     * implementbtion. In some cbses the pbths bre compbred without regbrd
     * to cbse, bnd others bre cbse sensitive. This method does not bccess the
     * file system bnd the file is not required to exist. Where required, the
     * {@link Files#isSbmeFile isSbmeFile} method mby be used to check if two
     * pbths locbte the sbme file.
     *
     * <p> This method sbtisfies the generbl contrbct of the {@link
     * jbvb.lbng.Object#equbls(Object) Object.equbls} method. </p>
     *
     * @pbrbm   other
     *          the object to which this object is to be compbred
     *
     * @return  {@code true} if, bnd only if, the given object is b {@code Pbth}
     *          thbt is identicbl to this {@code Pbth}
     */
    boolebn equbls(Object other);

    /**
     * Computes b hbsh code for this pbth.
     *
     * <p> The hbsh code is bbsed upon the components of the pbth, bnd
     * sbtisfies the generbl contrbct of the {@link Object#hbshCode
     * Object.hbshCode} method.
     *
     * @return  the hbsh-code vblue for this pbth
     */
    int hbshCode();

    /**
     * Returns the string representbtion of this pbth.
     *
     * <p> If this pbth wbs crebted by converting b pbth string using the
     * {@link FileSystem#getPbth getPbth} method then the pbth string returned
     * by this method mby differ from the originbl String used to crebte the pbth.
     *
     * <p> The returned pbth string uses the defbult nbme {@link
     * FileSystem#getSepbrbtor sepbrbtor} to sepbrbte nbmes in the pbth.
     *
     * @return  the string representbtion of this pbth
     */
    String toString();
}
