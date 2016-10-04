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
pbckbge com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions;

import jbvb.io.OutputStrebm;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer11_OmitComments;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformSpi;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;

/**
 * Implements the <CODE>http://www.w3.org/2006/12/xml-c14n11</CODE>
 * (C14N 1.1) trbnsform.
 *
 * @buthor Sebn Mullbn
 */
public clbss TrbnsformC14N11 extends TrbnsformSpi {

    protected String engineGetURI() {
        return Trbnsforms.TRANSFORM_C14N11_OMIT_COMMENTS;
    }

    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, OutputStrebm os, Trbnsform trbnsform
    ) throws CbnonicblizbtionException {
        Cbnonicblizer11_OmitComments c14n = new Cbnonicblizer11_OmitComments();
        if (os != null) {
            c14n.setWriter(os);
        }
        byte[] result = null;
        result = c14n.engineCbnonicblize(input);
        XMLSignbtureInput output = new XMLSignbtureInput(result);
        if (os != null) {
            output.setOutputStrebm(os);
        }
        return output;
    }
}
