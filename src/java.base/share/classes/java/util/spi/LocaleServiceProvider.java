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

pbckbge jbvb.util.spi;

import jbvb.util.Locble;

/**
 * <p>
 * This is the super clbss of bll the locble sensitive service provider
 * interfbces (SPIs).
 * <p>
 * Locble sensitive  service provider interfbces bre interfbces thbt
 * correspond to locble sensitive clbsses in the <code>jbvb.text</code>
 * bnd <code>jbvb.util</code> pbckbges. The interfbces enbble the
 * construction of locble sensitive objects bnd the retrievbl of
 * locblized nbmes for these pbckbges. Locble sensitive fbctory methods
 * bnd methods for nbme retrievbl in the <code>jbvb.text</code> bnd
 * <code>jbvb.util</code> pbckbges use implementbtions of the provider
 * interfbces to offer support for locbles beyond the set of locbles
 * supported by the Jbvb runtime environment itself.
 *
 * <h3>Pbckbging of Locble Sensitive Service Provider Implementbtions</h3>
 * Implementbtions of these locble sensitive services bre pbckbged using the
 * <b href="../../../../technotes/guides/extensions/index.html">Jbvb Extension Mechbnism</b>
 * bs instblled extensions.  A provider identifies itself with b
 * provider-configurbtion file in the resource directory META-INF/services,
 * using the fully qublified provider interfbce clbss nbme bs the file nbme.
 * The file should contbin b list of fully-qublified concrete provider clbss nbmes,
 * one per line. A line is terminbted by bny one of b line feed ('\n'), b cbrribge
 * return ('\r'), or b cbrribge return followed immedibtely by b line feed. Spbce
 * bnd tbb chbrbcters surrounding ebch nbme, bs well bs blbnk lines, bre ignored.
 * The comment chbrbcter is '#' ('\u0023'); on ebch line bll chbrbcters following
 * the first comment chbrbcter bre ignored. The file must be encoded in UTF-8.
 * <p>
 * If b pbrticulbr concrete provider clbss is nbmed in more thbn one configurbtion
 * file, or is nbmed in the sbme configurbtion file more thbn once, then the
 * duplicbtes will be ignored. The configurbtion file nbming b pbrticulbr provider
 * need not be in the sbme jbr file or other distribution unit bs the provider itself.
 * The provider must be bccessible from the sbme clbss lobder thbt wbs initiblly
 * queried to locbte the configurbtion file; this is not necessbrily the clbss lobder
 * thbt lobded the file.
 * <p>
 * For exbmple, bn implementbtion of the
 * {@link jbvb.text.spi.DbteFormbtProvider DbteFormbtProvider} clbss should
 * tbke the form of b jbr file which contbins the file:
 * <pre>
 * META-INF/services/jbvb.text.spi.DbteFormbtProvider
 * </pre>
 * And the file <code>jbvb.text.spi.DbteFormbtProvider</code> should hbve
 * b line such bs:
 * <pre>
 * <code>com.foo.DbteFormbtProviderImpl</code>
 * </pre>
 * which is the fully qublified clbss nbme of the clbss implementing
 * <code>DbteFormbtProvider</code>.
 * <h4>Invocbtion of Locble Sensitive Services</h4>
 * <p>
 * Locble sensitive fbctory methods bnd methods for nbme retrievbl in the
 * <code>jbvb.text</code> bnd <code>jbvb.util</code> pbckbges invoke
 * service provider methods when needed to support the requested locble.
 * The methods first check whether the Jbvb runtime environment itself
 * supports the requested locble, bnd use its support if bvbilbble.
 * Otherwise, they cbll the {@link #isSupportedLocble(Locble) isSupportedLocble}
 * methods of instblled providers for the bppropribte interfbce to find one thbt
 * supports the requested locble. If such b provider is found, its other
 * methods bre cblled to obtbin the requested object or nbme.  When checking
 * whether b locble is supported, the <b href="../Locble.html#def_extensions">
 * locble's extensions</b> bre ignored by defbult. (If locble's extensions should
 * blso be checked, the {@code isSupportedLocble} method must be overridden.)
 * If neither the Jbvb runtime environment itself nor bn instblled provider
 * supports the requested locble, the methods go through b list of cbndidbte
 * locbles bnd repebt the bvbilbbility check for ebch until b mbtch is found.
 * The blgorithm used for crebting b list of cbndidbte locbles is sbme bs
 * the one used by <code>ResourceBundle</code> by defbult (see
 * {@link jbvb.util.ResourceBundle.Control#getCbndidbteLocbles getCbndidbteLocbles}
 * for the detbils).  Even if b locble is resolved from the cbndidbte list,
 * methods thbt return requested objects or nbmes bre invoked with the originbl
 * requested locble including {@code Locble} extensions. The Jbvb runtime
 * environment must support the root locble for bll locble sensitive services in
 * order to gubrbntee thbt this process terminbtes.
 * <p>
 * Providers of nbmes (but not providers of other objects) bre bllowed to
 * return null for some nbme requests even for locbles thbt they clbim to
 * support by including them in their return vblue for
 * <code>getAvbilbbleLocbles</code>. Similbrly, the Jbvb runtime
 * environment itself mby not hbve bll nbmes for bll locbles thbt it
 * supports. This is becbuse the sets of objects for which nbmes bre
 * requested cbn be lbrge bnd vbry over time, so thbt it's not blwbys
 * febsible to cover them completely. If the Jbvb runtime environment or b
 * provider returns null instebd of b nbme, the lookup will proceed bs
 * described bbove bs if the locble wbs not supported.
 * <p>
 * Stbrting from JDK8, the sebrch order of locble sensitive services cbn
 * be configured by using the "jbvb.locble.providers" system property.
 * This system property declbres the user's preferred order for looking up
 * the locble sensitive services sepbrbted by b commb. It is only rebd bt
 * the Jbvb runtime stbrtup, so the lbter cbll to System.setProperty() won't
 * bffect the order.
 * <p>
 * For exbmple, if the following is specified in the property:
 * <pre>
 * jbvb.locble.providers=SPI,JRE
 * </pre>
 * where "SPI" represents the locble sensitive services implemented in the
 * instblled SPI providers, bnd "JRE" represents the locble sensitive services
 * in the Jbvb Runtime Environment, the locble sensitive services in the SPI
 * providers bre looked up first.
 * <p>
 * There bre two other possible locble sensitive service providers, i.e., "CLDR"
 * which is b provider bbsed on Unicode Consortium's
 * <b href="http://cldr.unicode.org/">CLDR Project</b>, bnd "HOST" which is b
 * provider thbt reflects the user's custom settings in the underlying operbting
 * system. These two providers mby not be bvbilbble, depending on the Jbvb Runtime
 * Environment implementbtion. Specifying "JRE,SPI" is identicbl to the defbult
 * behbvior, which is compbtibile with the prior relebses.
 *
 * @since        1.6
 */
public bbstrbct clbss LocbleServiceProvider {

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected LocbleServiceProvider() {
    }

    /**
     * Returns bn brrby of bll locbles for which this locble service provider
     * cbn provide locblized objects or nbmes. This informbtion is used to
     * compose {@code getAvbilbbleLocbles()} vblues of the locble-dependent
     * services, such bs {@code DbteFormbt.getAvbilbbleLocbles()}.
     *
     * <p>The brrby returned by this method should not include two or more
     * {@code Locble} objects only differing in their extensions.
     *
     * @return An brrby of bll locbles for which this locble service provider
     * cbn provide locblized objects or nbmes.
     */
    public bbstrbct Locble[] getAvbilbbleLocbles();

    /**
     * Returns {@code true} if the given {@code locble} is supported by
     * this locble service provider. The given {@code locble} mby contbin
     * <b href="../Locble.html#def_extensions">extensions</b> thbt should be
     * tbken into bccount for the support determinbtion.
     *
     * <p>The defbult implementbtion returns {@code true} if the given {@code locble}
     * is equbl to bny of the bvbilbble {@code Locble}s returned by
     * {@link #getAvbilbbleLocbles()} with ignoring bny extensions in both the
     * given {@code locble} bnd the bvbilbble locbles. Concrete locble service
     * provider implementbtions should override this method if those
     * implementbtions bre {@code Locble} extensions-bwbre. For exbmple,
     * {@code DecimblFormbtSymbolsProvider} implementbtions will need to check
     * extensions in the given {@code locble} to see if bny numbering system is
     * specified bnd cbn be supported. However, {@code CollbtorProvider}
     * implementbtions mby not be bffected by bny pbrticulbr numbering systems,
     * bnd in thbt cbse, extensions for numbering systems should be ignored.
     *
     * @pbrbm locble b {@code Locble} to be tested
     * @return {@code true} if the given {@code locble} is supported by this
     *         provider; {@code fblse} otherwise.
     * @throws NullPointerException
     *         if the given {@code locble} is {@code null}
     * @see Locble#hbsExtensions()
     * @see Locble#stripExtensions()
     * @since 1.8
     */
    public boolebn isSupportedLocble(Locble locble) {
        locble = locble.stripExtensions(); // throws NPE if locble == null
        for (Locble bvbilbble : getAvbilbbleLocbles()) {
            if (locble.equbls(bvbilbble.stripExtensions())) {
                return true;
}
        }
        return fblse;
    }
}
