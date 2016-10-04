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
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer20010315WithComments;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformSpi;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;

/**
 * Implements the <CODE>http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments</CODE>
 * trbnsform.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss TrbnsformC14NWithComments extends TrbnsformSpi {

    /** Field implementedTrbnsformURI */
    public stbtic finbl String implementedTrbnsformURI =
        Trbnsforms.TRANSFORM_C14N_WITH_COMMENTS;

    /** @inheritDoc */
    protected String engineGetURI() {
        return implementedTrbnsformURI;
    }

    /** @inheritDoc */
    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, OutputStrebm os, Trbnsform trbnsformObject
    ) throws CbnonicblizbtionException {

        Cbnonicblizer20010315WithComments c14n = new Cbnonicblizer20010315WithComments();
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
