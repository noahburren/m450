describe('Student Form Test', () => {
  beforeEach(() => {
    // Backend-API Mock: Passe die URL an deine API an
    cy.intercept('POST', '/students', {
      statusCode: 200,
      body: { id: 123, name: 'John Doe', email: 'john@example.com' },
    }).as('saveStudent');

    cy.visit('http://localhost:4200/addstudents');  // deine Route zum Formular
  });

  it('should render form fields', () => {
    cy.get('input[name="name"]').should('exist');
    cy.get('input[name="email"]').should('exist');
    cy.get('button[type="submit"]').should('exist');
  });

  it('should show validation errors when fields are touched but empty', () => {
    cy.get('.alert-danger').should('not.exist');

    cy.get('input[name="name"]').focus().blur();
    cy.get('.alert-danger').contains('Name is required').should('exist');

    cy.get('input[name="email"]').focus().blur();
    cy.get('.alert-danger').contains('Email is required').should('exist');
  });

  it('should disable submit button if form is invalid', () => {
    cy.get('button[type="submit"]').should('be.disabled');

    cy.get('input[name="name"]').type('John Doe');
    cy.get('button[type="submit"]').should('be.disabled');

    cy.get('input[name="email"]').type('john@example.com');
    cy.get('button[type="submit"]').should('not.be.disabled');
  });

  it('should submit form successfully and navigate to /students', () => {
    cy.get('input[name="name"]').type('John Doe');
    cy.get('input[name="email"]').type('john@example.com');

    cy.get('button[type="submit"]').click();

    // Warte auf gemockten API Call
    cy.wait('@saveStudent');

    // Prüfe ob die URL auf /students geändert wurde
    cy.url().should('include', '/students');
  });
});
