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
/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: ApbcheOctetStrebmDbtb.jbvb 1197150 2011-11-03 14:34:57Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.io.IOException;
import jbvbx.xml.crypto.OctetStrebmDbtb;
import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;

public clbss ApbcheOctetStrebmDbtb extends OctetStrebmDbtb
    implements ApbcheDbtb {

    privbte XMLSignbtureInput xi;

    public ApbcheOctetStrebmDbtb(XMLSignbtureInput xi)
        throws CbnonicblizbtionException, IOException
    {
        super(xi.getOctetStrebm(), xi.getSourceURI(), xi.getMIMEType());
        this.xi = xi;
    }

    public XMLSignbtureInput getXMLSignbtureInput() {
        return xi;
    }
}
