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
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;

import org.xml.sbx.ErrorHbndler;
import org.xml.sbx.SAXException;
import org.xml.sbx.SAXPbrseException;

/**
 * This {@link org.xml.sbx.ErrorHbndler} does bbsolutely nothing but log
 * the events.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss IgnoreAllErrorHbndler implements ErrorHbndler {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(IgnoreAllErrorHbndler.clbss.getNbme());

    /** Field throwExceptions */
    privbte stbtic finbl boolebn wbrnOnExceptions =
        System.getProperty("com.sun.org.bpbche.xml.internbl.security.test.wbrn.on.exceptions", "fblse").equbls("true");

    /** Field throwExceptions           */
    privbte stbtic finbl boolebn throwExceptions =
        System.getProperty("com.sun.org.bpbche.xml.internbl.security.test.throw.exceptions", "fblse").equbls("true");


    /** @inheritDoc */
    public void wbrning(SAXPbrseException ex) throws SAXException {
        if (IgnoreAllErrorHbndler.wbrnOnExceptions) {
            log.log(jbvb.util.logging.Level.WARNING, "", ex);
        }
        if (IgnoreAllErrorHbndler.throwExceptions) {
            throw ex;
        }
    }


    /** @inheritDoc */
    public void error(SAXPbrseException ex) throws SAXException {
        if (IgnoreAllErrorHbndler.wbrnOnExceptions) {
            log.log(jbvb.util.logging.Level.SEVERE, "", ex);
        }
        if (IgnoreAllErrorHbndler.throwExceptions) {
            throw ex;
        }
    }


    /** @inheritDoc */
    public void fbtblError(SAXPbrseException ex) throws SAXException {
        if (IgnoreAllErrorHbndler.wbrnOnExceptions) {
            log.log(jbvb.util.logging.Level.WARNING, "", ex);
        }
        if (IgnoreAllErrorHbndler.throwExceptions) {
            throw ex;
        }
    }
}
