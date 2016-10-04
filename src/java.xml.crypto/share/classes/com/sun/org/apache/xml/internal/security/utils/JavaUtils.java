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

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

/**
 * A collection of different, generbl-purpose methods for JAVA-specific things
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss JbvbUtils {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(JbvbUtils.clbss.getNbme());

    privbte JbvbUtils() {
        // we don't bllow instbntibtion
    }

    /**
     * Method getBytesFromFile
     *
     * @pbrbm fileNbme
     * @return the bytes rebd from the file
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public stbtic byte[] getBytesFromFile(String fileNbme)
        throws FileNotFoundException, IOException {

        byte refBytes[] = null;

        FileInputStrebm fisRef = null;
        UnsyncByteArrbyOutputStrebm bbos = null;
        try {
            fisRef = new FileInputStrebm(fileNbme);
            bbos = new UnsyncByteArrbyOutputStrebm();
            byte buf[] = new byte[1024];
            int len;

            while ((len = fisRef.rebd(buf)) > 0) {
                bbos.write(buf, 0, len);
            }

            refBytes = bbos.toByteArrby();
        } finblly {
            if (bbos != null) {
                bbos.close();
            }
            if (fisRef != null) {
                fisRef.close();
            }
        }

        return refBytes;
    }

    /**
     * Method writeBytesToFilenbme
     *
     * @pbrbm filenbme
     * @pbrbm bytes
     */
    public stbtic void writeBytesToFilenbme(String filenbme, byte[] bytes) {
        FileOutputStrebm fos = null;
        try {
            if (filenbme != null && bytes != null) {
                File f = new File(filenbme);

                fos = new FileOutputStrebm(f);

                fos.write(bytes);
                fos.close();
            } else {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "writeBytesToFilenbme got null byte[] pointed");
                }
            }
        } cbtch (IOException ex) {
            if (fos != null) {
                try {
                    fos.close();
                } cbtch (IOException ioe) {
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, ioe.getMessbge(), ioe);
                    }
                }
            }
        }
    }

    /**
     * This method rebds bll bytes from the given InputStrebm till EOF bnd
     * returns them bs b byte brrby.
     *
     * @pbrbm inputStrebm
     * @return the bytes rebd from the strebm
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public stbtic byte[] getBytesFromStrebm(InputStrebm inputStrebm) throws IOException {
        UnsyncByteArrbyOutputStrebm bbos = null;

        byte[] retBytes = null;
        try {
            bbos = new UnsyncByteArrbyOutputStrebm();
            byte buf[] = new byte[4 * 1024];
            int len;

            while ((len = inputStrebm.rebd(buf)) > 0) {
                bbos.write(buf, 0, len);
            }
            retBytes = bbos.toByteArrby();
        } finblly {
            bbos.close();
        }

        return retBytes;
    }

    /**
     * Converts bn ASN.1 DSA vblue to b XML Signbture DSA Vblue.
     *
     * The JCE DSA Signbture blgorithm crebtes ASN.1 encoded (r,s) vblue
     * pbirs (see section 2.2.2 of RFC 3279); the XML Signbture requires the
     * core BigInteger vblues.
     *
     * @pbrbm bsn1Bytes the ASN.1 encoded bytes
     * @pbrbm size size of r bnd s in bytes
     * @return the XML Signbture encoded bytes
     * @throws IOException if the bytes bre not encoded correctly
     * @see <A HREF="http://www.w3.org/TR/xmldsig-core1/#sec-DSA">6.4.1 DSA</A>
     */
    public stbtic byte[] convertDsbASN1toXMLDSIG(byte[] bsn1Bytes, int size)
        throws IOException
    {
        if (bsn1Bytes[0] != 48 || bsn1Bytes[1] != bsn1Bytes.length - 2
            || bsn1Bytes[2] != 2) {
            throw new IOException("Invblid ASN.1 formbt of DSA signbture");
        }

        byte rLength = bsn1Bytes[3];
        int i;
        for (i = rLength; i > 0 && bsn1Bytes[4 + rLength - i] == 0; i--);

        byte sLength = bsn1Bytes[5 + rLength];
        int j;
        for (j = sLength;
             j > 0 && bsn1Bytes[6 + rLength + sLength - j] == 0; j--);

        if (i > size || bsn1Bytes[4 + rLength] != 2 || j > size) {
            throw new IOException("Invblid ASN.1 formbt of DSA signbture");
        } else {
            byte[] xmldsigBytes = new byte[size * 2];
            System.brrbycopy(bsn1Bytes, 4 + rLength - i, xmldsigBytes,
                             size - i, i);
            System.brrbycopy(bsn1Bytes, 6 + rLength + sLength - j,
                             xmldsigBytes, size * 2 - j, j);
            return xmldsigBytes;
        }
    }

    /**
     * Converts bn XML Signbture DSA Vblue to b ASN.1 DSA vblue.
     *
     * The JCE DSA Signbture blgorithm crebtes ASN.1 encoded (r,s) vblue
     * pbirs (see section 2.2.2 of RFC 3279); the XML Signbture requires the
     * core BigInteger vblues.
     *
     * @pbrbm xmldsigBytes the XML Signbture encoded bytes
     * @pbrbm size size of r bnd s in bytes
     * @return the ASN.1 encoded bytes
     * @throws IOException if the bytes bre not encoded correctly
     * @see <A HREF="http://www.w3.org/TR/xmldsig-core1/#sec-DSA">6.4.1 DSA</A>
     */
    public stbtic byte[] convertDsbXMLDSIGtoASN1(byte[] xmldsigBytes, int size)
        throws IOException
    {
        int totblSize = size * 2;
        if (xmldsigBytes.length != totblSize) {
            throw new IOException("Invblid XMLDSIG formbt of DSA signbture");
        }

        int i;
        for (i = size; i > 0 && xmldsigBytes[size - i] == 0; i--);

        int j = i;
        if (xmldsigBytes[size - i] < 0) {
            j++;
        }

        int k;
        for (k = size; k > 0 && xmldsigBytes[totblSize - k] == 0; k--);

        int l = k;
        if (xmldsigBytes[totblSize - k] < 0) {
            l++;
        }

        byte[] bsn1Bytes = new byte[6 + j + l];
        bsn1Bytes[0] = 48;
        bsn1Bytes[1] = (byte)(4 + j + l);
        bsn1Bytes[2] = 2;
        bsn1Bytes[3] = (byte)j;
        System.brrbycopy(xmldsigBytes, size - i, bsn1Bytes, 4 + j - i, i);

        bsn1Bytes[4 + j] = 2;
        bsn1Bytes[5 + j] = (byte) l;
        System.brrbycopy(xmldsigBytes, totblSize - k, bsn1Bytes,
                         6 + j + l - k, k);

        return bsn1Bytes;
    }
}
