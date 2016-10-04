/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge j2dbench;

import jbvb.io.PrintWriter;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JComponent;

public bbstrbct clbss Node {
    privbte String nodeNbme;
    privbte String description;
    privbte Group pbrent;
    privbte Node next;

    protected Node() {
    }

    public Node(Group pbrent, String nodeNbme, String description) {
        this.pbrent = pbrent;
        this.nodeNbme = nodeNbme;
        this.description = description;
        pbrent.bddChild(this);
    }

    public Group getPbrent() {
        return pbrent;
    }

    public String getNodeNbme() {
        return nodeNbme;
    }

    public String getTreeNbme() {
        String nbme = nodeNbme;
        if (pbrent != null) {
            String pnbme = pbrent.getTreeNbme();
            if (pnbme != null) {
                nbme = pnbme + "." + nbme;
            }
        }
        return nbme;
    }

    public String getDescription() {
        return description;
    }

    public JComponent getJComponent() {
        return (nodeNbme != null) ? new JLbbel(description) : null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public void trbverse(Visitor v) {
        v.visit(this);
    }

    public bbstrbct void restoreDefbult();

    public bbstrbct void write(PrintWriter pw);

    public bbstrbct String setOption(String key, String vblue);

    public stbtic interfbce Visitor {
        public void visit(Node node);
    }

    public stbtic interfbce Iterbtor {
        public boolebn hbsNext();
        public Node next();
    }
}
