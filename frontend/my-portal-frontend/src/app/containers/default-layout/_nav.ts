import { INavData } from '@coreui/angular';


export const navItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    iconComponent: { name: 'cil-speedometer' },
    badge: {
      color: 'info',
      text: 'NEW'
    }
  },
  // {
  //   title: true,
  //   name: 'Theme'
  // },
  {
    name: 'Profile',
    url: '/theme/colors',
    iconComponent: { name: 'cil-user' }
  },
  {
    name: 'General',
    url: '/general',
    iconComponent: { name: 'cil-notes' },
    children: [
      {
        name: 'Options',
        url: '/base/loans'
      },
      {
        name: 'Job Master',
        url: '/general/job-master'
      },
      {
        name: 'Database',
        url: '/general/database-information'
      },
      {
        name: 'Logs',
        url: '/base/loans'
      },
      {
        name: 'To Do Master',
        url: '/general/task-master'
      },
      {
        name: 'System Information',
        url: '/general/system-information'
      },
    
    ],
  },
  {
    name: 'E-mail',
    url: '/theme/typography',
    linkProps: { fragment: 'someAnchor' },
    iconComponent: { name: 'cil-user' },
    children:[
      {
        name: 'Options',
        url: '/base/loans'
      },
      {
        name: 'Outgoing Mail Accounts',
        url: '/base/loans'
      },
      {
        name: 'Incoming Mail Accounts',
        url: '/base/loans'
      },
    ]
  },
  {
    name: 'File',
    url: '/theme/colors',
    iconComponent: { name: 'cil-user' },
    children:[
      {
        name: 'Upload File',
        url: '/base/file-upload'
      },
      {
        name: 'File Logs',
        url: '/base/loans'
      },
      {
        name: 'File Upload Details',
        url: '/base/loans'
      },
      {
        name: 'File Type Rule Attribute',
        url: '/base/loans'
      },
    ]
  },
  {
    name: 'Alert',
    url: '/alert',
    iconComponent: { name: 'cil-bell' },
    children:[
      {
        name: 'Events',
        url: '/alert/events'
      },
      {
        name: 'Notifications',
        url: '/alert/notifications'
      },
      {
        name: 'Schedulers',
        url: '/alert/schedulers'
      },
    ]
  },
  // {
  //   name: 'Financial Portfolio',
  //   title: true
  // },
  {
    name: 'Loans & Insurance',
    url: '/base',
    iconComponent: { name: 'cil-puzzle' },
    children: [
      {
        name: 'Loans',
        url: '/base/loans'
      },
      {
        name: 'Life Insurance',
        url: '/base/life-insurance'
      },
      {
        name: 'Health Insurance',
        url: '/base/health-insurance'
      },
      {
        name: 'Vehicle Insurance',
        url: '/base/vehicle-insurance'
      },
      {
        name: 'Mutual Funds/ULIP',
        url: '/base/mf-ulips'
      },
      {
        name: 'Education Expense',
        url: '/base/education-expense'
      },
      {
        name: 'Samar Education',
        url: '/base/navs'
      },
      {
        name: 'Error 404',
        url: '/base/404'
      },
      {
        name: 'Error 500',
        url: '/base/500'
      }
      // ,
      // {
      //   name: 'Pagination',
      //   url: '/base/pagination'
      // },
      // {
      //   name: 'Placeholder',
      //   url: '/base/placeholder'
      // },
      // {
      //   name: 'Popovers',
      //   url: '/base/popovers'
      // },
      // {
      //   name: 'Progress',
      //   url: '/base/progress'
      // },
      // {
      //   name: 'Spinners',
      //   url: '/base/spinners'
      // },
      // {
      //   name: 'Tables',
      //   url: '/base/tables'
      // },
      // {
      //   name: 'Tabs',
      //   url: '/base/tabs'
      // },
      // {
      //   name: 'Tooltips',
      //   url: '/base/tooltips'
      // }
    ]
  },
  {
    name: 'Alert',
    url: '/alert',
    iconComponent: { name: 'cil-bell' },
    children: [
      {
        name: 'Events',
        url: '/alert/events'
      },
      {
        name: 'Notifications',
        url: '/alert/notifications'
      },      
      {
        name: 'Schedulers',
        url: '/alert/schedulers'
      },
      {
        name: 'Buttons',
        url: '/alert/buttons'
      },
      {
        name: 'Button groups',
        url: '/alert/button-groups'
      },
      {
        name: 'Dropdowns',
        url: '/alert/dropdowns'
      },
    ]
  },
  {
    name: 'Forms',
    url: '/forms',
    iconComponent: { name: 'cil-notes' },
    children: [
      {
        name: 'Form Control',
        url: '/forms/form-control'
      },
      {
        name: 'Select',
        url: '/forms/select'
      },
      {
        name: 'Checks & Radios',
        url: '/forms/checks-radios'
      },
      {
        name: 'Range',
        url: '/forms/range'
      },
      {
        name: 'Input Group',
        url: '/forms/input-group'
      },
      {
        name: 'Floating Labels',
        url: '/forms/floating-labels'
      },
      {
        name: 'Layout',
        url: '/forms/layout'
      },
      {
        name: 'Validation',
        url: '/forms/validation'
      }
    ]
  },
  {
    name: 'Charts',
    url: '/charts',
    iconComponent: { name: 'cil-chart-pie' }
  },
  {
    name: 'Icons',
    iconComponent: { name: 'cil-star' },
    url: '/icons',
    children: [
      {
        name: 'CoreUI Free',
        url: '/icons/coreui-icons',
        badge: {
          color: 'success',
          text: 'FREE'
        }
      },
      {
        name: 'CoreUI Flags',
        url: '/icons/flags'
      },
      {
        name: 'CoreUI Brands',
        url: '/icons/brands'
      }
    ]
  },
  {
    name: 'Notifications',
    url: '/notifications',
    iconComponent: { name: 'cil-bell' },
    children: [
      {
        name: 'Alerts',
        url: '/notifications/alerts'
      },
      {
        name: 'Badges',
        url: '/notifications/badges'
      },
      {
        name: 'Modal',
        url: '/notifications/modal'
      },
      {
        name: 'Toast',
        url: '/notifications/toasts'
      }
    ]
  },
  {
    name: 'Widgets',
    url: '/widgets',
    iconComponent: { name: 'cil-calculator' },
    badge: {
      color: 'info',
      text: 'NEW'
    }
  },
  {
    title: true,
    name: 'Extras'
  },
  {
    name: 'Pages',
    url: '/login',
    iconComponent: { name: 'cil-star' },
    children: [
      {
        name: 'Login',
        url: '/login'
      },
      {
        name: 'Register',
        url: '/register'
      },
      {
        name: 'Error 404',
        url: '/404'
      },
      {
        name: 'Error 500',
        url: '/500'
      }
    ]
  },
];

