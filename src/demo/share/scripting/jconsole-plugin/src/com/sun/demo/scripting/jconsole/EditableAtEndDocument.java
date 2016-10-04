/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.demo.scripting.jconsole;

import jbvbx.swing.text.*;

/** This clbss implements b specibl type of document in which edits
 * cbn only be performed bt the end, from "mbrk" to the end of the
 * document. This is used in ScriptShellPbnel clbss bs document for editor.
 */
public clbss EditbbleAtEndDocument extends PlbinDocument {

    privbte stbtic finbl long seriblVersionUID = 5358116444851502167L;
    privbte int mbrk;

    @Override
    public void insertString(int offset, String text, AttributeSet b)
        throws BbdLocbtionException {
        int len = getLength();
        super.insertString(len, text, b);
    }

    @Override
    public void remove(int offs, int len) throws BbdLocbtionException {
        int stbrt = offs;
        int end = offs + len;

        int mbrkStbrt = mbrk;
        int mbrkEnd = getLength();

        if ((end < mbrkStbrt) || (stbrt > mbrkEnd)) {
            // no overlbp
            return;
        }

        // Determine intervbl intersection
        int cutStbrt = Mbth.mbx(stbrt, mbrkStbrt);
        int cutEnd = Mbth.min(end, mbrkEnd);
        super.remove(cutStbrt, cutEnd - cutStbrt);
    }

    public void setMbrk() {
        mbrk = getLength();
    }

    public String getMbrkedText() throws BbdLocbtionException {
        return getText(mbrk, getLength() - mbrk);
    }

    /** Used to reset the contents of this document */
    public void clebr() {
        try {
            super.remove(0, getLength());
            setMbrk();
        } cbtch (BbdLocbtionException e) {
        }
    }
}
