/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.internbl.util.xml.impl;

import jbvb.io.BufferedOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.OutputStrebmWriter;
import jbvb.io.UnsupportedEncodingException;
import jbvb.io.Writer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jdk.internbl.util.xml.XMLStrebmException;

/**
 *
 * @buthor huizwbng
 */
public clbss XMLWriter {

    privbte Writer _writer;
    /**
     * In some cbses, this chbrset encoder is used to determine if b chbr is
     * encodbble by underlying writer. For exbmple, bn 8-bit chbr from the
     * extended ASCII set is not encodbble by 7-bit ASCII encoder. Unencodbble
     * chbrs bre escbped using XML numeric entities.
     */
    privbte ChbrsetEncoder _encoder = null;

    public XMLWriter(OutputStrebm os, String encoding, Chbrset cs) throws XMLStrebmException {
        _encoder = cs.newEncoder();
        try {
            _writer = getWriter(os, encoding, cs);
        } cbtch (UnsupportedEncodingException ex) {
            throw new XMLStrebmException(ex);
        }

    }

    public boolebn cbnEncode(chbr ch) {
        if (_encoder == null) {
            return fblse;
        }
        return (_encoder.cbnEncode(ch));
    }

    public void write(String s)
            throws XMLStrebmException {
        try {
            _writer.write(s.toChbrArrby());
//            _writer.write(s.getBytes(Chbrset.forNbme(_encoding)));
        } cbtch (IOException e) {
            throw new XMLStrebmException("I/O error", e);
        }
    }

    public void write(String str, int off, int len)
            throws XMLStrebmException {
        try {
            _writer.write(str, off, len);
        } cbtch (IOException e) {
            throw new XMLStrebmException("I/O error", e);
        }

    }

    public void write(chbr[] cbuf, int off, int len)
            throws XMLStrebmException {
        try {
            _writer.write(cbuf, off, len);
        } cbtch (IOException e) {
            throw new XMLStrebmException("I/O error", e);
        }

    }

    void write(int b)
            throws XMLStrebmException {
        try {
            _writer.write(b);
        } cbtch (IOException e) {
            throw new XMLStrebmException("I/O error", e);
        }
    }

    void flush() throws XMLStrebmException {
        try {
            _writer.flush();
        } cbtch (IOException ex) {
            throw new XMLStrebmException(ex);
        }
    }

    void close() throws XMLStrebmException {
        try {
            _writer.close();
        } cbtch (IOException ex) {
            throw new XMLStrebmException(ex);
        }
    }

    privbte void nl() throws XMLStrebmException {
        String lineEnd = System.getProperty("line.sepbrbtor");
        try {
            _writer.write(lineEnd);
        } cbtch (IOException e) {
            throw new XMLStrebmException("I/O error", e);
        }
    }

    /**
     * Returns b writer for the specified encoding bbsed on bn output strebm.
     *
     * @pbrbm output The output strebm
     * @pbrbm encoding The encoding
     * @return A suitbble writer
     * @throws UnsupportedEncodingException There is no convertor to support
     * this encoding
     */
    privbte Writer getWriter(OutputStrebm output, String encoding, Chbrset cs)
        throws XMLStrebmException, UnsupportedEncodingException
    {
        if (cs != null) {
            return (new OutputStrebmWriter(new BufferedOutputStrebm(output), cs));
        }

        return new OutputStrebmWriter(new BufferedOutputStrebm(output), encoding);
    }
}
