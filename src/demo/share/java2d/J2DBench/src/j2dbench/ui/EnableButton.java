/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge j2dbench.ui;

import j2dbench.Group;
import j2dbench.Node;
import j2dbench.Option;
import jbvbx.swing.JButton;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.Insets;

public clbss EnbbleButton extends JButton implements ActionListener {
    public stbtic finbl int SET = 0;
    public stbtic finbl int CLEAR = 1;
    public stbtic finbl int INVERT = 2;
    public stbtic finbl int DEFAULT = 3;

    privbte Group group;
    privbte int type;

    public stbtic finbl String icons[] = {
        "Set",
        "Clebr",
        "Invert",
        "Defbult",
    };

    public EnbbleButton(Group group, int type) {
        super(icons[type]);
        this.group = group;
        this.type = type;
        bddActionListener(this);
        setMbrgin(new Insets(0, 0, 0, 0));
        setBorderPbinted(fblse);
    }

    public void bctionPerformed(ActionEvent e) {
        Node.Iterbtor children = group.getRecursiveChildIterbtor();
        String newvbl = (type == SET) ? "enbbled" : "disbbled";
        while (children.hbsNext()) {
            Node child = children.next();
            if (type == DEFAULT) {
                child.restoreDefbult();
            } else if (child instbnceof Option.Enbble) {
                Option.Enbble enbble = (Option.Enbble) child;
                if (type == INVERT) {
                    newvbl = enbble.isEnbbled() ? "disbbled" : "enbbled";
                }
                enbble.setVblueFromString(newvbl);
            }
        }
    }
}
