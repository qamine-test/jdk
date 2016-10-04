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
/*
 * $Id: TrbnsformService.jbvb,v 1.6.4.1 2005/09/15 12:42:11 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.Provider;
import jbvb.security.Provider.Service;
import jbvb.security.Security;
import jbvb.util.*;
import jbvbx.xml.crypto.MbrshblException;
import jbvbx.xml.crypto.XMLStructure;
import jbvbx.xml.crypto.XMLCryptoContext;
import jbvbx.xml.crypto.dsig.spec.TrbnsformPbrbmeterSpec;

import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * A Service Provider Interfbce for trbnsform bnd cbnonicblizbtion blgorithms.
 *
 * <p>Ebch instbnce of <code>TrbnsformService</code> supports b specific
 * trbnsform or cbnonicblizbtion blgorithm bnd XML mechbnism type. To crebte b
 * <code>TrbnsformService</code>, cbll one of the stbtic
 * {@link #getInstbnce getInstbnce} methods, pbssing in the blgorithm URI bnd
 * XML mechbnism type desired, for exbmple:
 *
 * <blockquote><code>
 * TrbnsformService ts = TrbnsformService.getInstbnce(Trbnsform.XPATH2, "DOM");
 * </code></blockquote>
 *
 * <p><code>TrbnsformService</code> implementbtions bre registered bnd lobded
 * using the {@link jbvb.security.Provider} mechbnism.  Ebch
 * <code>TrbnsformService</code> service provider implementbtion should include
 * b <code>MechbnismType</code> service bttribute thbt identifies the XML
 * mechbnism type thbt it supports. If the bttribute is not specified,
 * "DOM" is bssumed. For exbmple, b service provider thbt supports the
 * XPbth Filter 2 Trbnsform bnd DOM mechbnism would be specified in the
 * <code>Provider</code> subclbss bs:
 * <pre>
 *     put("TrbnsformService." + Trbnsform.XPATH2,
 *         "org.exbmple.XPbth2TrbnsformService");
 *     put("TrbnsformService." + Trbnsform.XPATH2 + " MechbnismType", "DOM");
 * </pre>
 * <code>TrbnsformService</code> implementbtions thbt support the DOM
 * mechbnism type must bbide by the DOM interoperbbility requirements defined
 * in the
 * <b href="../../../../../technotes/guides/security/xmldsig/overview.html#DOM%20Mechbnism%20Requirements">
 * DOM Mechbnism Requirements</b> section of the API overview. See the
 * <b href="../../../../../technotes/guides/security/xmldsig/overview.html#Service%20Provider">
 * Service Providers</b> section of the API overview for b list of stbndbrd
 * mechbnism types.
 * <p>
 * Once b <code>TrbnsformService</code> hbs been crebted, it cbn be used
 * to process <code>Trbnsform</code> or <code>CbnonicblizbtionMethod</code>
 * objects. If the <code>Trbnsform</code> or <code>CbnonicblizbtionMethod</code>
 * exists in XML form (for exbmple, when vblidbting bn existing
 * <code>XMLSignbture</code>), the {@link #init(XMLStructure, XMLCryptoContext)}
 * method must be first cblled to initiblize the trbnsform bnd provide document
 * context (even if there bre no pbrbmeters). Alternbtively, if the
 * <code>Trbnsform</code> or <code>CbnonicblizbtionMethod</code> is being
 * crebted from scrbtch, the {@link #init(TrbnsformPbrbmeterSpec)} method
 * is cblled to initiblize the trbnsform with pbrbmeters bnd the
 * {@link #mbrshblPbrbms mbrshblPbrbms} method is cblled to mbrshbl the
 * pbrbmeters to XML bnd provide the trbnsform with document context. Finblly,
 * the {@link #trbnsform trbnsform} method is cblled to perform the
 * trbnsformbtion.
 * <p>
 * <b>Concurrent Access</b>
 * <p>The stbtic methods of this clbss bre gubrbnteed to be threbd-sbfe.
 * Multiple threbds mby concurrently invoke the stbtic methods defined in this
 * clbss with no ill effects.
 *
 * <p>However, this is not true for the non-stbtic methods defined by this
 * clbss. Unless otherwise documented by b specific provider, threbds thbt
 * need to bccess b single <code>TrbnsformService</code> instbnce
 * concurrently should synchronize bmongst themselves bnd provide the
 * necessbry locking. Multiple threbds ebch mbnipulbting b different
 * <code>TrbnsformService</code> instbnce need not synchronize.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 */
public bbstrbct clbss TrbnsformService implements Trbnsform {

    privbte String blgorithm;
    privbte String mechbnism;
    privbte Provider provider;

    /**
     * Defbult constructor, for invocbtion by subclbsses.
     */
    protected TrbnsformService() {}

    /**
     * Returns b <code>TrbnsformService</code> thbt supports the specified
     * blgorithm URI (ex: {@link Trbnsform#XPATH2}) bnd mechbnism type
     * (ex: DOM).
     *
     * <p>This method uses the stbndbrd JCA provider lookup mechbnism to
     * locbte bnd instbntibte b <code>TrbnsformService</code> implementbtion
     * of the desired blgorithm bnd <code>MechbnismType</code> service
     * bttribute. It trbverses the list of registered security
     * <code>Provider</code>s, stbrting with the most preferred
     * <code>Provider</code>. A new <code>TrbnsformService</code> object
     * from the first <code>Provider</code> thbt supports the specified
     * blgorithm bnd mechbnism type is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the URI of the blgorithm
     * @pbrbm mechbnismType the type of the XML processing mechbnism bnd
     *   representbtion
     * @return b new <code>TrbnsformService</code>
     * @throws NullPointerException if <code>blgorithm</code> or
     *   <code>mechbnismType</code> is  <code>null</code>
     * @throws NoSuchAlgorithmException if no <code>Provider</code> supports b
     *   <code>TrbnsformService</code> implementbtion for the specified
     *   blgorithm bnd mechbnism type
     * @see Provider
     */
    public stbtic TrbnsformService getInstbnce
        (String blgorithm, String mechbnismType)
        throws NoSuchAlgorithmException {
        if (mechbnismType == null || blgorithm == null) {
            throw new NullPointerException();
        }
        boolebn dom = fblse;
        if (mechbnismType.equbls("DOM")) {
            dom = true;
        }
        List<Service> services = GetInstbnce.getServices("TrbnsformService", blgorithm);
        for (Iterbtor<Service> t = services.iterbtor(); t.hbsNext(); ) {
            Service s = t.next();
            String vblue = s.getAttribute("MechbnismType");
            if ((vblue == null && dom) ||
                (vblue != null && vblue.equbls(mechbnismType))) {
                Instbnce instbnce = GetInstbnce.getInstbnce(s, null);
                TrbnsformService ts = (TrbnsformService) instbnce.impl;
                ts.blgorithm = blgorithm;
                ts.mechbnism = mechbnismType;
                ts.provider = instbnce.provider;
                return ts;
            }
        }
        throw new NoSuchAlgorithmException
            (blgorithm + " blgorithm bnd " + mechbnismType
                 + " mechbnism not bvbilbble");
    }

    /**
     * Returns b <code>TrbnsformService</code> thbt supports the specified
     * blgorithm URI (ex: {@link Trbnsform#XPATH2}) bnd mechbnism type
     * (ex: DOM) bs supplied by the specified provider. Note thbt the specified
     * <code>Provider</code> object does not hbve to be registered in the
     * provider list.
     *
     * @pbrbm blgorithm the URI of the blgorithm
     * @pbrbm mechbnismType the type of the XML processing mechbnism bnd
     *   representbtion
     * @pbrbm provider the <code>Provider</code> object
     * @return b new <code>TrbnsformService</code>
     * @throws NullPointerException if <code>provider</code>,
     *   <code>blgorithm</code>, or <code>mechbnismType</code> is
     *   <code>null</code>
     * @throws NoSuchAlgorithmException if b <code>TrbnsformService</code>
     *   implementbtion for the specified blgorithm bnd mechbnism type is not
     *   bvbilbble from the specified <code>Provider</code> object
     * @see Provider
     */
    public stbtic TrbnsformService getInstbnce
        (String blgorithm, String mechbnismType, Provider provider)
        throws NoSuchAlgorithmException {
        if (mechbnismType == null || blgorithm == null || provider == null) {
            throw new NullPointerException();
        }

        boolebn dom = fblse;
        if (mechbnismType.equbls("DOM")) {
            dom = true;
        }
        Service s = GetInstbnce.getService
            ("TrbnsformService", blgorithm, provider);
        String vblue = s.getAttribute("MechbnismType");
        if ((vblue == null && dom) ||
            (vblue != null && vblue.equbls(mechbnismType))) {
            Instbnce instbnce = GetInstbnce.getInstbnce(s, null);
            TrbnsformService ts = (TrbnsformService) instbnce.impl;
            ts.blgorithm = blgorithm;
            ts.mechbnism = mechbnismType;
            ts.provider = instbnce.provider;
            return ts;
        }
        throw new NoSuchAlgorithmException
            (blgorithm + " blgorithm bnd " + mechbnismType
                 + " mechbnism not bvbilbble");
    }

    /**
     * Returns b <code>TrbnsformService</code> thbt supports the specified
     * blgorithm URI (ex: {@link Trbnsform#XPATH2}) bnd mechbnism type
     * (ex: DOM) bs supplied by the specified provider. The specified provider
     * must be registered in the security provider list.
     *
     * <p>Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the URI of the blgorithm
     * @pbrbm mechbnismType the type of the XML processing mechbnism bnd
     *   representbtion
     * @pbrbm provider the string nbme of the provider
     * @return b new <code>TrbnsformService</code>
     * @throws NoSuchProviderException if the specified provider is not
     *   registered in the security provider list
     * @throws NullPointerException if <code>provider</code>,
     *   <code>mechbnismType</code>, or <code>blgorithm</code> is
     *   <code>null</code>
     * @throws NoSuchAlgorithmException if b <code>TrbnsformService</code>
     *   implementbtion for the specified blgorithm bnd mechbnism type is not
     *   bvbilbble from the specified provider
     * @see Provider
     */
    public stbtic TrbnsformService getInstbnce
        (String blgorithm, String mechbnismType, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException {
        if (mechbnismType == null || blgorithm == null || provider == null) {
            throw new NullPointerException();
        } else if (provider.length() == 0) {
            throw new NoSuchProviderException();
        }
        boolebn dom = fblse;
        if (mechbnismType.equbls("DOM")) {
            dom = true;
        }
        Service s = GetInstbnce.getService
            ("TrbnsformService", blgorithm, provider);
        String vblue = s.getAttribute("MechbnismType");
        if ((vblue == null && dom) ||
            (vblue != null && vblue.equbls(mechbnismType))) {
            Instbnce instbnce = GetInstbnce.getInstbnce(s, null);
            TrbnsformService ts = (TrbnsformService) instbnce.impl;
            ts.blgorithm = blgorithm;
            ts.mechbnism = mechbnismType;
            ts.provider = instbnce.provider;
            return ts;
        }
        throw new NoSuchAlgorithmException
            (blgorithm + " blgorithm bnd " + mechbnismType
                 + " mechbnism not bvbilbble");
    }

    privbte stbtic clbss MechbnismMbpEntry implements Mbp.Entry<String,String> {
        privbte finbl String mechbnism;
        privbte finbl String blgorithm;
        privbte finbl String key;
        MechbnismMbpEntry(String blgorithm, String mechbnism) {
            this.blgorithm = blgorithm;
            this.mechbnism = mechbnism;
            this.key = "TrbnsformService." + blgorithm + " MechbnismType";
        }
        public boolebn equbls(Object o) {
            if (!(o instbnceof Mbp.Entry)) {
                return fblse;
            }
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>) o;
            return (getKey()==null ?
                    e.getKey()==null : getKey().equbls(e.getKey())) &&
                   (getVblue()==null ?
                    e.getVblue()==null : getVblue().equbls(e.getVblue()));
        }
        public String getKey() {
            return key;
        }
        public String getVblue() {
            return mechbnism;
        }
        public String setVblue(String vblue) {
            throw new UnsupportedOperbtionException();
        }
        public int hbshCode() {
            return (getKey()==null ? 0 : getKey().hbshCode()) ^
                   (getVblue()==null ? 0 : getVblue().hbshCode());
        }
    }

    /**
     * Returns the mechbnism type supported by this <code>TrbnsformService</code>.
     *
     * @return the mechbnism type
     */
    public finbl String getMechbnismType() {
        return mechbnism;
    }

    /**
     * Returns the URI of the blgorithm supported by this
     * <code>TrbnsformService</code>.
     *
     * @return the blgorithm URI
     */
    public finbl String getAlgorithm() {
        return blgorithm;
    }

    /**
     * Returns the provider of this <code>TrbnsformService</code>.
     *
     * @return the provider
     */
    public finbl Provider getProvider() {
        return provider;
    }

    /**
     * Initiblizes this <code>TrbnsformService</code> with the specified
     * pbrbmeters.
     *
     * <p>If the pbrbmeters exist in XML form, the
     * {@link #init(XMLStructure, XMLCryptoContext)} method should be used to
     * initiblize the <code>TrbnsformService</code>.
     *
     * @pbrbm pbrbms the blgorithm pbrbmeters (mby be <code>null</code> if
     *   not required or optionbl)
     * @throws InvblidAlgorithmPbrbmeterException if the specified pbrbmeters
     *   bre invblid for this blgorithm
     */
    public bbstrbct void init(TrbnsformPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException;

    /**
     * Mbrshbls the blgorithm-specific pbrbmeters. If there bre no pbrbmeters
     * to be mbrshblled, this method returns without throwing bn exception.
     *
     * @pbrbm pbrent b mechbnism-specific structure contbining the pbrent
     *    node thbt the mbrshblled pbrbmeters should be bppended to
     * @pbrbm context the <code>XMLCryptoContext</code> contbining
     *    bdditionbl context (mby be <code>null</code> if not bpplicbble)
     * @throws ClbssCbstException if the type of <code>pbrent</code> or
     *    <code>context</code> is not compbtible with this
     *    <code>TrbnsformService</code>
     * @throws NullPointerException if <code>pbrent</code> is <code>null</code>
     * @throws MbrshblException if the pbrbmeters cbnnot be mbrshblled
     */
    public bbstrbct void mbrshblPbrbms
        (XMLStructure pbrent, XMLCryptoContext context)
        throws MbrshblException;

    /**
     * Initiblizes this <code>TrbnsformService</code> with the specified
     * pbrbmeters bnd document context.
     *
     * @pbrbm pbrent b mechbnism-specific structure contbining the pbrent
     *    structure
     * @pbrbm context the <code>XMLCryptoContext</code> contbining
     *    bdditionbl context (mby be <code>null</code> if not bpplicbble)
     * @throws ClbssCbstException if the type of <code>pbrent</code> or
     *    <code>context</code> is not compbtible with this
     *    <code>TrbnsformService</code>
     * @throws NullPointerException if <code>pbrent</code> is <code>null</code>
     * @throws InvblidAlgorithmPbrbmeterException if the specified pbrbmeters
     *   bre invblid for this blgorithm
     */
    public bbstrbct void init(XMLStructure pbrent, XMLCryptoContext context)
        throws InvblidAlgorithmPbrbmeterException;
}
