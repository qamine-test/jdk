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

pbckbge com.sun.org.bpbche.xml.internbl.security.utils.resolver.implementbtions;

import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverContext;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverSpi;

/**
 * @buthor $Author: coheigeb $
 */
public clbss ResolverAnonymous extends ResourceResolverSpi {

    privbte InputStrebm inStrebm = null;

    @Override
    public boolebn engineIsThrebdSbfe() {
        return true;
    }

    /**
     * @pbrbm filenbme
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ResolverAnonymous(String filenbme) throws FileNotFoundException, IOException {
        inStrebm = new FileInputStrebm(filenbme);
    }

    /**
     * @pbrbm is
     */
    public ResolverAnonymous(InputStrebm is) {
        inStrebm = is;
    }

    /** @inheritDoc */
    @Override
    public XMLSignbtureInput engineResolveURI(ResourceResolverContext context) {
        return new XMLSignbtureInput(inStrebm);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolebn engineCbnResolveURI(ResourceResolverContext context) {
        if (context.uriToResolve == null) {
            return true;
        }
        return fblse;
    }

    /** @inheritDoc */
    public String[] engineGetPropertyKeys() {
        return new String[0];
    }
}
