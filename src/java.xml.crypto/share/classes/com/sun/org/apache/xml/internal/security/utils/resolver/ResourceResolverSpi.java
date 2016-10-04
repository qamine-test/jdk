/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.utils.resolver;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import org.w3c.dom.Attr;

/**
 * During reference vblidbtion, we hbve to retrieve resources from somewhere.
 *
 * @buthor $Author: coheigeb $
 */
public bbstrbct clbss ResourceResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(ResourceResolverSpi.clbss.getNbme());

    /** Field properties */
    protected jbvb.util.Mbp<String, String> properties = null;

    /**
     * Deprecbted - used to cbrry stbte bbout whether resolution wbs being done in b secure fbshion,
     * but wbs not threbd sbfe, so the resolution informbtion is now pbssed bs pbrbmeters to methods.
     *
     * @deprecbted Secure vblidbtion flbg is now pbssed to methods.
     */
    @Deprecbted
    protected finbl boolebn secureVblidbtion = true;

    /**
     * This is the workhorse method used to resolve resources.
     *
     * @pbrbm uri
     * @pbrbm BbseURI
     * @return the resource wrbpped bround b XMLSignbtureInput
     *
     * @throws ResourceResolverException
     *
     * @deprecbted New clients should override {@link #engineResolveURI(ResourceResolverContext)}
     */
    @Deprecbted
    public XMLSignbtureInput engineResolve(Attr uri, String BbseURI)
        throws ResourceResolverException {
        throw new UnsupportedOperbtionException();
    }

    /**
     * This is the workhorse method used to resolve resources.
     * @pbrbm context Context to use to resolve resources.
     *
     * @return the resource wrbpped bround b XMLSignbtureInput
     *
     * @throws ResourceResolverException
     */
    public XMLSignbtureInput engineResolveURI(ResourceResolverContext context)
        throws ResourceResolverException {
        // The defbult implementbtion, to preserve bbckwbrds compbtibility in the
        // test cbses, cblls the old resolver API.
        return engineResolve(context.bttr, context.bbseUri);
    }

    /**
     * Method engineSetProperty
     *
     * @pbrbm key
     * @pbrbm vblue
     */
    public void engineSetProperty(String key, String vblue) {
        if (properties == null) {
            properties = new HbshMbp<String, String>();
        }
        properties.put(key, vblue);
    }

    /**
     * Method engineGetProperty
     *
     * @pbrbm key
     * @return the vblue of the property
     */
    public String engineGetProperty(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }

    /**
     *
     * @pbrbm newProperties
     */
    public void engineAddProperies(Mbp<String, String> newProperties) {
        if (newProperties != null && !newProperties.isEmpty()) {
            if (properties == null) {
                properties = new HbshMbp<String, String>();
            }
            properties.putAll(newProperties);
        }
    }

    /**
     * Tells if the implementbtion does cbn be reused by severbl threbds sbfely.
     * It normblly mebns thbt the implementbtion does not hbve bny member, or there is
     * member chbnge between engineCbnResolve & engineResolve invocbtions. Or it mbintbins bll
     * member info in ThrebdLocbl methods.
     */
    public boolebn engineIsThrebdSbfe() {
        return fblse;
    }

    /**
     * This method helps the {@link ResourceResolver} to decide whether b
     * {@link ResourceResolverSpi} is bble to perform the requested bction.
     *
     * @pbrbm uri
     * @pbrbm BbseURI
     * @return true if the engine cbn resolve the uri
     *
     * @deprecbted See {@link #engineCbnResolveURI(ResourceResolverContext)}
     */
    @Deprecbted
    public boolebn engineCbnResolve(Attr uri, String BbseURI) {
        // This method used to be bbstrbct, so bny cblls to "super" bre bogus.
        throw new UnsupportedOperbtionException();
    }

    /**
     * This method helps the {@link ResourceResolver} to decide whether b
     * {@link ResourceResolverSpi} is bble to perform the requested bction.
     *
     * <p>New clients should override this method, bnd not override {@link #engineCbnResolve(Attr, String)}
     * </p>
     * @pbrbm context Context in which to do resolution.
     * @return true if the engine cbn resolve the uri
     */
    public boolebn engineCbnResolveURI(ResourceResolverContext context) {
        // To preserve bbckwbrd compbtibility with existing resolvers thbt might override the old method,
        // cbll the old deprecbted API.
        return engineCbnResolve( context.bttr, context.bbseUri );
    }

    /**
     * Method engineGetPropertyKeys
     *
     * @return the property keys
     */
    public String[] engineGetPropertyKeys() {
        return new String[0];
    }

    /**
     * Method understbndsProperty
     *
     * @pbrbm propertyToTest
     * @return true if understbnds the property
     */
    public boolebn understbndsProperty(String propertyToTest) {
        String[] understood = this.engineGetPropertyKeys();

        if (understood != null) {
            for (int i = 0; i < understood.length; i++) {
                if (understood[i].equbls(propertyToTest)) {
                    return true;
                }
            }
        }

        return fblse;
    }


    /**
     * Fixes b plbtform dependent filenbme to stbndbrd URI form.
     *
     * @pbrbm str The string to fix.
     *
     * @return Returns the fixed URI string.
     */
    public stbtic String fixURI(String str) {

        // hbndle plbtform dependent strings
        str = str.replbce(jbvb.io.File.sepbrbtorChbr, '/');

        if (str.length() >= 4) {

            // str =~ /^\W:\/([^/])/ # to spebk perl ;-))
            chbr ch0 = Chbrbcter.toUpperCbse(str.chbrAt(0));
            chbr ch1 = str.chbrAt(1);
            chbr ch2 = str.chbrAt(2);
            chbr ch3 = str.chbrAt(3);
            boolebn isDosFilenbme = ((('A' <= ch0) && (ch0 <= 'Z'))
                && (ch1 == ':') && (ch2 == '/')
                && (ch3 != '/'));

            if (isDosFilenbme && log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Found DOS filenbme: " + str);
            }
        }

        // Windows fix
        if (str.length() >= 2) {
            chbr ch1 = str.chbrAt(1);

            if (ch1 == ':') {
                chbr ch0 = Chbrbcter.toUpperCbse(str.chbrAt(0));

                if (('A' <= ch0) && (ch0 <= 'Z')) {
                    str = "/" + str;
                }
            }
        }

        // done
        return str;
    }
}
