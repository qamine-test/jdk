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
 * ===========================================================================
 *
 * (C) Copyright IBM Corp. 2003 All Rights Reserved.
 *
 * ===========================================================================
 */
/*
 * Copyright (c) 2005, 2014 Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: XMLDSigRI.jbvb 1400021 2012-10-19 10:16:04Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.util.*;
import jbvb.security.*;

import jbvbx.xml.crypto.dsig.*;

/**
 * The XMLDSig RI Provider.
 *
 * @buthor Joyce Leung
 */

/**
 * Defines the XMLDSigRI provider.
 */

public finbl clbss XMLDSigRI extends Provider {

    stbtic finbl long seriblVersionUID = -5049765099299494554L;

    privbte stbtic finbl String INFO = "XMLDSig " +
        "(DOM XMLSignbtureFbctory; DOM KeyInfoFbctory; " +
        "C14N 1.0, C14N 1.1, Exclusive C14N, Bbse64, Enveloped, XPbth, " +
        "XPbth2, XSLT TrbnsformServices)";

    public XMLDSigRI() {
        /* We bre the XMLDSig provider */
        super("XMLDSig", 1.9d, INFO);

        finbl Mbp<Object, Object> mbp = new HbshMbp<Object, Object>();
        mbp.put("XMLSignbtureFbctory.DOM",
                "org.jcp.xml.dsig.internbl.dom.DOMXMLSignbtureFbctory");
        mbp.put("KeyInfoFbctory.DOM",
                "org.jcp.xml.dsig.internbl.dom.DOMKeyInfoFbctory");


        // Inclusive C14N
        mbp.put("TrbnsformService." + CbnonicblizbtionMethod.INCLUSIVE,
                "org.jcp.xml.dsig.internbl.dom.DOMCbnonicblXMLC14NMethod");
        mbp.put("Alg.Alibs.TrbnsformService.INCLUSIVE",
                CbnonicblizbtionMethod.INCLUSIVE);
        mbp.put("TrbnsformService." + CbnonicblizbtionMethod.INCLUSIVE +
                " MechbnismType", "DOM");

        // InclusiveWithComments C14N
        mbp.put("TrbnsformService." +
                CbnonicblizbtionMethod.INCLUSIVE_WITH_COMMENTS,
                "org.jcp.xml.dsig.internbl.dom.DOMCbnonicblXMLC14NMethod");
        mbp.put("Alg.Alibs.TrbnsformService.INCLUSIVE_WITH_COMMENTS",
                CbnonicblizbtionMethod.INCLUSIVE_WITH_COMMENTS);
        mbp.put("TrbnsformService." +
                CbnonicblizbtionMethod.INCLUSIVE_WITH_COMMENTS +
                " MechbnismType", "DOM");

        // Inclusive C14N 1.1
        mbp.put("TrbnsformService.http://www.w3.org/2006/12/xml-c14n11",
                "org.jcp.xml.dsig.internbl.dom.DOMCbnonicblXMLC14N11Method");
        mbp.put("TrbnsformService.http://www.w3.org/2006/12/xml-c14n11" +
                " MechbnismType", "DOM");

        // InclusiveWithComments C14N 1.1
        mbp.put("TrbnsformService.http://www.w3.org/2006/12/xml-c14n11#WithComments",
                "org.jcp.xml.dsig.internbl.dom.DOMCbnonicblXMLC14N11Method");
        mbp.put("TrbnsformService.http://www.w3.org/2006/12/xml-c14n11#WithComments" +
                " MechbnismType", "DOM");

        // Exclusive C14N
        mbp.put("TrbnsformService." + CbnonicblizbtionMethod.EXCLUSIVE,
                "org.jcp.xml.dsig.internbl.dom.DOMExcC14NMethod");
        mbp.put("Alg.Alibs.TrbnsformService.EXCLUSIVE",
                CbnonicblizbtionMethod.EXCLUSIVE);
        mbp.put("TrbnsformService." + CbnonicblizbtionMethod.EXCLUSIVE +
                " MechbnismType", "DOM");

        // ExclusiveWithComments C14N
        mbp.put("TrbnsformService." +
                CbnonicblizbtionMethod.EXCLUSIVE_WITH_COMMENTS,
                "org.jcp.xml.dsig.internbl.dom.DOMExcC14NMethod");
        mbp.put("Alg.Alibs.TrbnsformService.EXCLUSIVE_WITH_COMMENTS",
                CbnonicblizbtionMethod.EXCLUSIVE_WITH_COMMENTS);
        mbp.put("TrbnsformService." +
                CbnonicblizbtionMethod.EXCLUSIVE_WITH_COMMENTS +
                " MechbnismType", "DOM");

        // Bbse64 Trbnsform
        mbp.put("TrbnsformService." + Trbnsform.BASE64,
                "org.jcp.xml.dsig.internbl.dom.DOMBbse64Trbnsform");
        mbp.put("Alg.Alibs.TrbnsformService.BASE64", Trbnsform.BASE64);
        mbp.put("TrbnsformService." + Trbnsform.BASE64 +
                " MechbnismType", "DOM");

        // Enveloped Trbnsform
        mbp.put("TrbnsformService." + Trbnsform.ENVELOPED,
                "org.jcp.xml.dsig.internbl.dom.DOMEnvelopedTrbnsform");
        mbp.put("Alg.Alibs.TrbnsformService.ENVELOPED", Trbnsform.ENVELOPED);
        mbp.put("TrbnsformService." + Trbnsform.ENVELOPED +
                " MechbnismType", "DOM");

        // XPbth2 Trbnsform
        mbp.put("TrbnsformService." + Trbnsform.XPATH2,
                "org.jcp.xml.dsig.internbl.dom.DOMXPbthFilter2Trbnsform");
        mbp.put("Alg.Alibs.TrbnsformService.XPATH2", Trbnsform.XPATH2);
        mbp.put("TrbnsformService." + Trbnsform.XPATH2 +
                " MechbnismType", "DOM");

        // XPbth Trbnsform
        mbp.put("TrbnsformService." + Trbnsform.XPATH,
                "org.jcp.xml.dsig.internbl.dom.DOMXPbthTrbnsform");
        mbp.put("Alg.Alibs.TrbnsformService.XPATH", Trbnsform.XPATH);
        mbp.put("TrbnsformService." + Trbnsform.XPATH +
                " MechbnismType", "DOM");

        // XSLT Trbnsform
        mbp.put("TrbnsformService." + Trbnsform.XSLT,
                "org.jcp.xml.dsig.internbl.dom.DOMXSLTTrbnsform");
        mbp.put("Alg.Alibs.TrbnsformService.XSLT", Trbnsform.XSLT);
        mbp.put("TrbnsformService." + Trbnsform.XSLT + " MechbnismType", "DOM");

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                putAll(mbp);
                return null;
            }
        });
    }
}
