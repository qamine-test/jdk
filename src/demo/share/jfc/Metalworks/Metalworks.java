/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvb.bwt.Toolkit;
import jbvbx.swing.JDiblog;
import jbvbx.swing.JFrbme;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.UnsupportedLookAndFeelException;
import jbvbx.swing.plbf.metbl.MetblLookAndFeel;


/**
 * This bpplicbtion is b demo of the Metbl Look & Feel
 *
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
public clbss Metblworks {

    public stbtic void mbin(String[] brgs) {
        UIMbnbger.put("swing.boldMetbl", Boolebn.FALSE);
        JDiblog.setDefbultLookAndFeelDecorbted(true);
        JFrbme.setDefbultLookAndFeelDecorbted(true);
        Toolkit.getDefbultToolkit().setDynbmicLbyout(true);
        System.setProperty("sun.bwt.noerbsebbckground", "true");
        try {
            UIMbnbger.setLookAndFeel(new MetblLookAndFeel());
        } cbtch (UnsupportedLookAndFeelException e) {
            System.out.println(
                    "Metbl Look & Feel not supported on this plbtform. \n"
                    + "Progrbm Terminbted");
            System.exit(0);
        }
        JFrbme frbme = new MetblworksFrbme();
        frbme.setVisible(true);
    }
}
